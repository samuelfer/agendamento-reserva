package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.model.Reserva;

import java.util.List;

public interface IValidadorAgendamentoReserva {

    void validar(DadosAgendamentoReservaDto dados, List<Reserva> reservasImovel);
}
