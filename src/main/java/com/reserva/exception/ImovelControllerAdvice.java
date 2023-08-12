package com.reserva.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ImovelControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ImovelNotFoundException.class)
    public ResponseEntity<MensagemExceptionHandler> imovelNotFoundException(ImovelNotFoundException e) {
        MensagemExceptionHandler erro = new MensagemExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Imóvel não encontrado"
        );
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemExceptionHandler> argumentNotValid(MethodArgumentNotValidException notValidException) {

        BindingResult result = notValidException.getBindingResult();
        List<FieldError> camposDosErros = result.getFieldErrors();

        StringBuilder sb = new StringBuilder("Os campos seguintes não podem ser nulos: ");

        for (FieldError campoErro : camposDosErros) {
            sb.append(campoErro.getField());
            sb.append(", ");
        }

        MensagemExceptionHandler erro = new MensagemExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString()
        );
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
