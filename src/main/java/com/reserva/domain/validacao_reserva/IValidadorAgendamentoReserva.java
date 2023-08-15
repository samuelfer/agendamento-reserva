package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.model.AgendaReserva;

import java.util.List;

public interface IValidadorAgendamentoReserva {

    void validar(DadosAgendamentoReservaDto dados, List<AgendaReserva> reservasImovel);
}
