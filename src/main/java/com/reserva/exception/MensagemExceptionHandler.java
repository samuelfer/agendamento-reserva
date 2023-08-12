package com.reserva.exception;

import java.util.Date;

public class MensagemExceptionHandler {

    private Date data;
    private Integer status;
    private String mensagem;

    public MensagemExceptionHandler(Date data, Integer status, String mensagem) {
        this.data = data;
        this.status = status;
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
