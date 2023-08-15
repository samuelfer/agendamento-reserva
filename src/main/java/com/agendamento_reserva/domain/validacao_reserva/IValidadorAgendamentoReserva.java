package com.agendamento_reserva.domain.validacao_reserva;

import com.agendamento_reserva.dto.AgendamentoReservaDto;
import com.agendamento_reserva.model.AgendaReserva;

import java.util.List;

public interface IValidadorAgendamentoReserva {

    void validar(AgendamentoReservaDto dados, List<AgendaReserva> reservasImovel);
}
