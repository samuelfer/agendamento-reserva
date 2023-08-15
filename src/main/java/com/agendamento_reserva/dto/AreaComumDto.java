package com.agendamento_reserva.dto;

import com.agendamento_reserva.model.AreaComum;
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

    public AreaComum salvar(AreaComumDto areaComumDto) {
        AreaComum areaComum = new AreaComum();
        areaComum.setId(null);
        areaComum.setCodAreaComum(areaComumDto.getCodAreaComum());
        areaComum.setDescricaoAreaComum(areaComumDto.getDescricaoAreaComum());
        areaComum.setHorarioEmMinutosMinimoAntecedencia(areaComumDto.getHorarioEmMinutosMinimoAntecedencia());
        areaComum.setQuantidadeReservaPermitidaPorSemana(areaComum.getQuantidadeReservaPermitidaPorSemana());
        areaComum.setQuantidadeReservaPermitidaPorMes(areaComumDto.getQuantidadeReservaPermitidaPorMes());
        return areaComum;
    }
}
