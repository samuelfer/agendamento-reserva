package com.reserva.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity(name = "area_comum")
@Table(name = "area_comum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AreaComum {

    @Id
    @SequenceGenerator(name = "area_comum_sequence", sequenceName = "area_comum_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_comum_sequence")
    private Long id;

    @NotNull
    private String codAreaComum;

    @NotNull
    private String descricaoAreaComum;

    @Column
    @NotNull
    private Long horarioEmMinutosMinimoAntecedencia;

    @Column
    @NotNull
    private int quantidadeReservaPermitidaPorSemana;

    @Column
    @NotNull
    private int quantidadeReservaPermitidaPorMes;

}
