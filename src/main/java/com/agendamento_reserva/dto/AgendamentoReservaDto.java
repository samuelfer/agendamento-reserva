package com.agendamento_reserva.dto;

import com.agendamento_reserva.model.AgendaReserva;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class AgendamentoReservaDto {

    private Long id;

    @NotNull(message = "O campo imóvel não pode ser nulo")
    private Long imovelId;

    @NotNull(message = "O campo data e hora não pode ser nulo")
    @Future
    private LocalDateTime dataHoraReserva;

    @NotNull(message = "O campo área comum não pode ser nulo")
    private Long areaComum;

    public AgendamentoReservaDto agendamentoReservaResponseDto(AgendaReserva agendaReserva) {
        this.id = agendaReserva.getId();
        this.imovelId = agendaReserva.getImovel().getId();
        this.dataHoraReserva = agendaReserva.getDataHoraReserva();
        this.areaComum = agendaReserva.getAreaComum();
        return this;
    }
}
