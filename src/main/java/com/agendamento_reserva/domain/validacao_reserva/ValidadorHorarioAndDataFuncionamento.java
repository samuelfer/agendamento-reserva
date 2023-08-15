package com.agendamento_reserva.domain.validacao_reserva;

import com.agendamento_reserva.dto.AgendamentoReservaDto;
import com.agendamento_reserva.exception.RegrasAgendamentoValidadorException;
import com.agendamento_reserva.model.AgendaReserva;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;

@Component
public class ValidadorHorarioAndDataFuncionamento implements IValidadorAgendamentoReserva  {

    @Override
    public void validar(AgendamentoReservaDto dados, List<AgendaReserva> reservasImovel) {
        validarHorariosFuncionamento(dados);
        validarDatasFuncionamento(dados);
    }

    private void validarHorariosFuncionamento(AgendamentoReservaDto dados) {
        boolean horarioReservaIsAntesDoPermitido = dados.getDataHoraReserva().getHour() < 7;
        boolean horarioReservaIsDepoisDoPermitido = dados.getDataHoraReserva().getHour() > 23;

        if (horarioReservaIsAntesDoPermitido) {
            throw new RegrasAgendamentoValidadorException("Horário de início da reserva não permitido. A área só pode ser reservada a partir das 07:00");
        }

        if (horarioReservaIsDepoisDoPermitido) {
            throw new RegrasAgendamentoValidadorException("Horário de fim da reserva não permitido. A área só pode ser reservada até às 18:00");
        }
    }

    private void validarDatasFuncionamento(AgendamentoReservaDto dados) {
        boolean dataReservaIsPermitida = dados.getDataHoraReserva().getDayOfWeek().equals(DayOfWeek.FRIDAY);

        if (dataReservaIsPermitida) {
            throw new RegrasAgendamentoValidadorException("Não é permitido agendamento de reserva para essa área na segunda-feira");
        }
    }
}
