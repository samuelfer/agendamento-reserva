package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.exception.RegrasAgendamentoValidadorException;
import com.reserva.model.AgendaReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorQuantidadeReserva implements IValidadorAgendamentoReserva {

    @Override
    public void validar(DadosAgendamentoReservaDto dados, List<AgendaReserva> reservasImovel) {
        validarQuantidadePermitidaPorMes(reservasImovel);
        validarQuantidadePermitidaPorSemana(reservasImovel);
    }

    private void validarQuantidadePermitidaPorMes(List<AgendaReserva> reservasImovel) {
        int quantidadeJaReservadaNoMes = reservasImovel.size();
        int quantidadePermitida = 2;//Isso deve ser pego da configuracao da areaComum

        if (quantidadeJaReservadaNoMes >= quantidadePermitida) {
            throw new RegrasAgendamentoValidadorException("Não é permitido reservar essa área mais de "+quantidadePermitida+" vezes no mês");
        }
    }

    private void validarQuantidadePermitidaPorSemana(List<AgendaReserva> reservasImovel) {
        int quantidadeJaReservadaNaSemana = 0;
        int quantidadePermitidaPorSemana = 1;//Isso deve ser pego da configuracao da areaComum;

        if (quantidadeJaReservadaNaSemana > quantidadePermitidaPorSemana) {
            throw new RegrasAgendamentoValidadorException("Não é permitido reservar essa área mais de "+quantidadePermitidaPorSemana+" vezes na semana");
        }
    }
}
