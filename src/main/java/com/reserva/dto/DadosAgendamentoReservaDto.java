package com.reserva.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Getter
public class DadosAgendamentoReservaDto {

    private Long id;

    @NotNull
    private Long imovelId;

    @NotNull
    @Future
    private LocalDateTime dataHoraReserva;

    @NotNull
    private Long areaComum;
}
