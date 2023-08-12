package com.reserva.domain.validacao_reserva;

import com.reserva.dto.DadosAgendamentoReservaDto;
import com.reserva.exception.ValidacaoException;
import com.reserva.model.Reserva;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorQuantidadeReserva implements IValidadorAgendamentoReserva {

    public void validar(DadosAgendamentoReservaDto dados, List<Reserva> reservasImovel) {
        validarQuantidadePermitidaPorMes(reservasImovel);
        validarQuantidadePermitidaPorSemana(reservasImovel);
    }

    private void validarQuantidadePermitidaPorMes(List<Reserva> reservasImovel) {
        int quantidadeJaReservadaNoMes = reservasImovel.size();
        int quantidadePermitida = 2;//Isso deve ser pego da configuracao da areaComum

        if (quantidadeJaReservadaNoMes >= quantidadePermitida) {
            throw new ValidacaoException("Não é permitido reservar essa área mais de "+quantidadePermitida+" vezes no mês");
        }
    }

    private void validarQuantidadePermitidaPorSemana(List<Reserva> reservasImovel) {
        int quantidadeJaReservadaNaSemana = 0;
        int quantidadePermitidaPorSemana = 1;//Isso deve ser pego da configuracao da areaComum;

        if (quantidadeJaReservadaNaSemana > quantidadePermitidaPorSemana) {
            throw new ValidacaoException("Não é permitido reservar essa área mais de "+quantidadePermitidaPorSemana+" vezes na semana");
        }
    }
}
