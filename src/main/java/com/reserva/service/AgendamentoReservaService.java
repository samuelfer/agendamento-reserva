package com.reserva.service;

import com.reserva.domain.validacao_reserva.IValidadorAgendamentoReserva;
import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.exception.RegrasAgendamentoValidadorException;
import com.reserva.model.AreaComum;
import com.reserva.model.Imovel;
import com.reserva.model.AgendaReserva;
import com.reserva.repository.AgendamentoReservaRepository;
import com.reserva.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    public void agendarReserva(DadosAgendamentoReservaDto dados) {
        Imovel imovel = imovelService.validaStatusInadimplencia(dados.getImovelId());

        existsAgendamentoParaAreaNaDataHoraSolicitada(dados);

        AreaComum areaComum = areaComumService.getById(dados.getAreaComum());

        List<AgendaReserva> reservasImovel = reservaRepository.findByImovelId(dados.getImovelId());

        validadores.forEach(v -> v.validar(dados, reservasImovel));
        AgendaReserva reserva = new AgendaReserva();
        reserva.setImovel(imovel);
        reserva.setAreaComum(areaComum.getId());
        reserva.setDataHoraReserva(dados.getDataHoraReserva());
        reservaRepository.save(reserva);
    }

    public List<AgendaReserva> listAllReservasPorImovel(Long imovelId) {
       return reservaRepository.findByImovelId(imovelId);
    }

    public List<AgendaReserva> listAllReservasPorArea(Long areaId) {
        areaComumService.getById(areaId);
        return reservaRepository.findByAreaComum(areaId);
    }

    private void existsAgendamentoParaAreaNaDataHoraSolicitada(DadosAgendamentoReservaDto dados) {
        Date dateAgendamento = DateUtil.localDateTimeToDate(dados.getDataHoraReserva());
        Long jaExistsAgendamento = reservaRepository
               .existsByAreaComumAndAndDataHoraReserva(dados.getAreaComum(), dateAgendamento);
       if (jaExistsAgendamento > 0) {
           throw new RegrasAgendamentoValidadorException("Já existe um agendamento de reserva nesse horário e data para a área solicitada");
       }

    }
}
