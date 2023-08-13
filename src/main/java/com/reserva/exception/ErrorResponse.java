package com.reserva.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String mensagem;
    private String stackTrace;
    private List<ValidationError> erros;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String campo;
        private final String mensagem;
    }

    public void addValidationError(String campo, String mensagem) {
        if (Objects.isNull(erros)) {
            this.erros = new ArrayList<>();
        }
        this.erros.add(new ValidationError(campo, mensagem));
    }

}
