package com.agendamento_reserva.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException e, WebRequest request) {
        final String mensagemErro = "Registro já cadastrado";
        log.error(mensagemErro, e);
        return buildErrorResponse(e, mensagemErro, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException e, WebRequest request) {
        log.error("Registro em duplicidade", e);
        return buildErrorResponse(e, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handeAllUncaughtException(Exception e, WebRequest request) {
        final String mensagemErro = "Erro desconhecido";
        log.error(mensagemErro, e);
        return buildErrorResponse(e, mensagemErro, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handeObjectNotFoundException(ObjectNotFoundException e, WebRequest request) {
        log.error("Registro não encontrado", e);
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception e, String mensagem,
                                                      HttpStatus httpStatus, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), mensagem);
        if (this.printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(e));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception e,
                                                      HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse(e, e.getMessage(), httpStatus, request);
    }
}
