package com.reserva.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImovelDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String numero;
}
