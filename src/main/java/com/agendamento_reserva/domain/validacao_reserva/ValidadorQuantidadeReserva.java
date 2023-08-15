package com.agendamento_reserva.domain.validacao_reserva;

import com.agendamento_reserva.dto.AgendamentoReservaDto;
import com.agendamento_reserva.exception.RegrasAgendamentoValidadorException;
import com.agendamento_reserva.model.AgendaReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorQuantidadeReserva implements IValidadorAgendamentoReserva {

    @Override
    public void validar(AgendamentoReservaDto dados, List<AgendaReserva> reservasImovel) {
        validarQuantidadePermitidaPorMes(reservasImovel);
        validarQuantidadePermitidaPorSemana(reservasImovel);
    }

    private void validarQuantidadePermitidaPorSemana(List<AgendaReserva> reservasImovel) {
        int quantidadeJaReservadaNaSemana = 0;
        int quantidadePermitidaPorSemana = 1;//Isso deve ser pego da configuracao da areaComum;

        if (quantidadeJaReservadaNaSemana > quantidadePermitidaPorSemana) {
            throw new RegrasAgendamentoValidadorException("Não é permitido reservar essa área mais de "+quantidadePermitidaPorSemana+" vezes na semana");
        }
    }

    private void validarQuantidadePermitidaPorMes(List<AgendaReserva> reservasImovel) {
        int quantidadeJaReservadaNoMes = reservasImovel.size();
        int quantidadePermitida = 5;//Isso deve ser pego da configuracao da areaComum

        if (quantidadeJaReservadaNoMes >= quantidadePermitida) {
            throw new RegrasAgendamentoValidadorException("Não é permitido reservar essa área mais de "+quantidadePermitida+" vezes no mês");
        }
    }
}
