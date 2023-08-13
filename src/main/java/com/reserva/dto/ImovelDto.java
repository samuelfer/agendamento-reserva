package com.reserva.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImovelDto {

    private Long id;

    @NotNull(message = "O campo número não pode ser nulo")
    @NotEmpty(message = "O campo número não pode ser vazio")
    @Column(unique = true)
    private String numero;
}
