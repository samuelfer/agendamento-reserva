package com.reserva.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reserva")
@Table(name = "reserva")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Reserva {

    @Id
    @SequenceGenerator(name = "reserva_sequence", sequenceName = "reserva_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_sequence")
    private Long id;

    @ManyToOne
    private Imovel imovel;

    @Column
    private LocalDateTime dataHoraReserva;

    @Column
    private Long areaComum;
}
