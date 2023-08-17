package com.agendamento_reserva.service;

import com.agendamento_reserva.domain.validacao_reserva.IValidadorAgendamentoReserva;
import com.agendamento_reserva.dto.AgendamentoReservaDto;
import com.agendamento_reserva.exception.RegrasAgendamentoValidadorException;
import com.agendamento_reserva.model.Imovel;
import com.agendamento_reserva.model.AgendaReserva;
import com.agendamento_reserva.repository.AgendamentoReservaRepository;
import com.agendamento_reserva.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AgendamentoReservaService {

    @Autowired
    private AgendamentoReservaRepository reservaRepository;
    @Autowired
    private ImovelService imovelService;
    @Autowired
    private AreaComumService areaComumService;
    @Autowired
    private List<IValidadorAgendamentoReserva> validadores;

    public List<AgendaReserva> listAll() {
        return reservaRepository.findAll();
    }

    @Transactional
    public AgendamentoReservaDto agendarReserva(AgendamentoReservaDto dadosAgendamento) {
        Imovel imovel = imovelService.validaStatusInadimplencia(dadosAgendamento.getImovelId());

        existsAgendamentoParaAreaNaDataHoraSolicitada(dadosAgendamento);
        List<AgendaReserva> reservasImovel = reservaRepository.findByImovelId(dadosAgendamento.getImovelId());

        //Antes de chamar as regras serah necessario verificar se o condominio as utiliza
        validadores.forEach(v -> v.validar(dadosAgendamento, reservasImovel));
        AgendaReserva agendamentoSalvo = reservaRepository.save(setDadosAgendamentoReserva(dadosAgendamento, imovel));
        return dadosAgendamento.agendamentoReservaResponseDto(agendamentoSalvo);
    }

    public List<AgendaReserva> listAllReservasPorImovel(Long imovelId) {
       return reservaRepository.findByImovelId(imovelId);
    }

    public List<AgendaReserva> listAllReservasPorArea(Long areaId) {
        areaComumService.getById(areaId);
        return reservaRepository.findByAreaComum(areaId);
    }

    private void existsAgendamentoParaAreaNaDataHoraSolicitada(AgendamentoReservaDto dados) {
        Long jaExistsAgendamento = reservaRepository
               .existsAgendamentoParaAreaComumAndDataHoraReserva(dados.getAreaComum(), dados.getDataHoraReserva());
       if (jaExistsAgendamento > 0) {
           throw new RegrasAgendamentoValidadorException("Já existe um agendamento de reserva nesse horário e data para a área solicitada");
       }
    }

    private AgendaReserva setDadosAgendamentoReserva(AgendamentoReservaDto dados, Imovel imovel) {
        AgendaReserva agendamentoReserva = new AgendaReserva();
        agendamentoReserva.setImovel(imovel);
        agendamentoReserva.setAreaComum(areaComumService.getById(dados.getAreaComum()).getId());
        agendamentoReserva.setDataHoraReserva(dados.getDataHoraReserva());
        return agendamentoReserva;
    }
}
