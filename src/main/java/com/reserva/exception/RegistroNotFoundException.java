package com.reserva.exception;

public class RegistroNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public RegistroNotFoundException(Long id) {
        super("Registro não encontrado com id: "+id);
    }
}
