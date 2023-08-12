package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.exception.ValidacaoException;
import com.reserva.model.Reserva;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorHorarioAntecedencia implements IValidadorAgendamentoReserva {

    public void validar(DadosAgendamentoReservaDto dados, List<Reserva> reservasImovel) {
        LocalDateTime dataReserva = dados.getDataHoraReserva();
        LocalDateTime agora = LocalDateTime.now();
        Long difencaEmMinutos = Duration.between(agora, dataReserva).toMinutes();

        if (difencaEmMinutos < 30) {
            throw new ValidacaoException("A antecedência permitida para reservar essa área é de no mínimo 30 minutos");
        }

    }
}
