package com.reserva.exception;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders httpHeaders, HttpStatus status, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro validação. Verifque os seguintes campos.");

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> camposDosErros = result.getFieldErrors();

        for (FieldError campoErro : camposDosErros) {
            errorResponse.addValidationError(campoErro.getField(), campoErro.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
