package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.exception.ValidacaoException;
import com.reserva.model.Reserva;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;

@Component
public class ValidadorHorarioAndDataFuncionamento implements IValidadorAgendamentoReserva  {

    public void validar(DadosAgendamentoReservaDto dados, List<Reserva> reservasImovel) {
        validarHorariosFuncionamento(dados);
        validarDatasFuncionamento(dados);
    }

    private void validarHorariosFuncionamento(DadosAgendamentoReservaDto dados) {
        boolean horarioReservaIsAntesDoPermitido = dados.getDataHoraReserva().getHour() < 7;
        boolean horarioReservaIsDepoisDoPermitido = dados.getDataHoraReserva().getHour() > 18;

        if (horarioReservaIsAntesDoPermitido) {
            throw new ValidacaoException("Horário de início da reserva não permitido");
        }

        if (horarioReservaIsDepoisDoPermitido) {
            throw new ValidacaoException("Horário de fim da reserva não permitido");
        }

    }

    private void validarDatasFuncionamento(DadosAgendamentoReservaDto dados) {
        boolean dataReservaIsPermitida = dados.getDataHoraReserva().getDayOfWeek().equals(DayOfWeek.MONDAY);

        if (dataReservaIsPermitida) {
            throw new ValidacaoException("Data da reserva não permitida");
        }
    }
}
