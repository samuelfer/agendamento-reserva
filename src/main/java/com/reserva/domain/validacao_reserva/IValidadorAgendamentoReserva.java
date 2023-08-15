package com.reserva.domain.validacao_reserva;

import com.reserva.dto.AgendamentoReservaDto;
import com.reserva.model.AgendaReserva;

import java.util.List;

public interface IValidadorAgendamentoReserva {

    void validar(AgendamentoReservaDto dados, List<AgendaReserva> reservasImovel);
}
