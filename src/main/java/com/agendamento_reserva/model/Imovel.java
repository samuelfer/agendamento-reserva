package com.agendamento_reserva.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "imovel")
@Table(name = "imovel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Imovel {

    @Id
    @SequenceGenerator(name = "imovel_sequence", sequenceName = "imovel_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imovel_sequence")
    private Long id;

    @Column
    @NotNull
    private String numero;

    @Column
    private Boolean inadimplente = false;
}
