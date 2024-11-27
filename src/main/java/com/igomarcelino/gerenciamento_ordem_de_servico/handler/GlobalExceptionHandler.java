package com.igomarcelino.gerenciamento_ordem_de_servico.handler;

import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ObjectNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ResponseError;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolationException;
import org.aspectj.bridge.Message;
import org.hibernate.PersistentObjectException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // Classe modelo para mostrar as exceptions no front
    private ResponseError responseError(String message, HttpStatus status) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setError(message);
        responseError.setStatusCode(status.value());
        responseError.setTimestamp(Date.from(Instant.now()));
        return responseError;
    }

    //handler Geral
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handlerGeneral(Exception e, WebRequest request) {
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return handlerObjectNotFoundException((ObjectNotFoundException) exception.getUndeclaredThrowable(), request);
        } else {
            String message = messageSource.getMessage("error.server", new Object[]{e.getMessage()}, null);
            ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    // elemento nao localizado
    @ExceptionHandler({ObjectNotFoundException.class})
    private ResponseEntity<Object> handlerObjectNotFoundException(ObjectNotFoundException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        return handleExceptionInternal(e, error, headers(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    private ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        ResponseError error = responseError(
                e.getConstraintViolations().
                        stream().
                        map(constraintViolation -> constraintViolation.getMessageTemplate())
                        .collect(Collectors.joining()), HttpStatus.CONFLICT);
        return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
        ResponseError error = responseError("CPF ja cadastrado ", HttpStatus.UNPROCESSABLE_ENTITY);
        return handleExceptionInternal(e, error, headers(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(PersistentObjectException.class)
    private ResponseEntity<Object> handlerPersistentObjectException(PersistentObjectException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(), HttpStatus.PRECONDITION_REQUIRED);
        return handleExceptionInternal(e, error, headers(), HttpStatus.PRECONDITION_REQUIRED, request);
    }

    @ExceptionHandler(NoSuchMessageException.class)
    private ResponseEntity<Object> handleNoSuchMessageException(NoSuchMessageException e, WebRequest request) {
        String message = "Mensagem não encontrada: " + e.getMessage();
        ResponseError error = responseError(message, HttpStatus.NOT_FOUND);
        return handleExceptionInternal(e, error, headers(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    private ResponseEntity<Object> handlerInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e, WebRequest request){
        ResponseError error = responseError(e.getLocalizedMessage() + ": Verificar na documentacao o padrao de objeto",HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(e,error,headers(),HttpStatus.BAD_REQUEST,request);
    }
}