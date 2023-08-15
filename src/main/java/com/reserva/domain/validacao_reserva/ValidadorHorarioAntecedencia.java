package com.reserva.domain.validacao_reserva;

import com.reserva.dto.AgendamentoReservaDto;
import com.reserva.exception.RegrasAgendamentoValidadorException;
import com.reserva.model.AgendaReserva;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorHorarioAntecedencia implements IValidadorAgendamentoReserva {

    @Override
    public void validar(AgendamentoReservaDto dados, List<AgendaReserva> reservasImovel) {
        LocalDateTime dataReserva = dados.getDataHoraReserva();
        LocalDateTime agora = LocalDateTime.now();
        Long difencaEmMinutos = Duration.between(agora, dataReserva).toMinutes();

        if (difencaEmMinutos < 30) {
            throw new RegrasAgendamentoValidadorException("A antecedência permitida para reservar essa área é de no mínimo 30 minutos");
        }

    }
}
