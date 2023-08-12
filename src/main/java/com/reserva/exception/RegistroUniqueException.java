package com.reserva.exception;

public class RegistroUniqueException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public RegistroUniqueException(String value) {
        super("Registro: "+value +" já existe na base de dados");
    }
}
