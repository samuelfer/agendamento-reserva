package com.reserva.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaComumDto {

    private Long id;

    @NotNull
    @Column(unique = true)
    private String codAreaComum;

    @NotNull
    private String descricaoAreaComum;

    @NotNull
    private Long horarioEmMinutosMinimoAntecedencia;

    @NotNull
    private int quantidadeReservaPermitidaPorSemana;

    @NotNull
    private int quantidadeReservaPermitidaPorMes;
}
