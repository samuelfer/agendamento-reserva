package com.agendamento_reserva.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public ObjectNotFoundException(Long id) {
        super("Registro não encontrado com id: "+id);
    }

    public ObjectNotFoundException(String value) {
        super("Registro "+ value + " não encontrado ");
    }
}
