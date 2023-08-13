package com.reserva.service;

import com.reserva.domain.validacao_reserva.IValidadorAgendamentoReserva;
import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.model.AreaComum;
import com.reserva.model.Imovel;
import com.reserva.model.Reserva;
import com.reserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ImovelService imovelService;
    @Autowired
    private AreaComumService areaComumService;
    @Autowired
    private List<IValidadorAgendamentoReserva> validadores;

    public List<Reserva> listAll() {
        return reservaRepository.findAll();
    }

    public void agendarReserva(DadosAgendamentoReservaDto dados) {
        Imovel imovel = imovelService.getById(dados.getImovelId());
        AreaComum areaComum = areaComumService.getById(dados.getAreaComum());

        List<Reserva> reservasImovel = reservaRepository.findByImovelId(dados.getImovelId());

        validadores.forEach(v -> v.validar(dados, reservasImovel));
        Reserva reserva = new Reserva();
        reserva.setImovel(imovel);
        reserva.setAreaComum(areaComum.getId());
        reserva.setDataHoraReserva(dados.getDataHoraReserva());
        reservaRepository.save(reserva);
    }

    public List<Reserva> listAllReservasPorImovel(Long imovelId) {
       return reservaRepository.findByImovelId(imovelId);
    }

    public List<Reserva> listAllReservasPorArea(Long areaId) {
        areaComumService.getById(areaId);
        return reservaRepository.findByAreaComum(areaId);
    }
}
