package com.igomarcelino.gerenciamento_ordem_de_servico.handler;

import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ElementNotFoundException;
import com.igomarcelino.gerenciamento_ordem_de_servico.exceptions.ResponseError;
import jakarta.annotation.Resource;
import org.aspectj.bridge.Message;
import org.springframework.context.MessageSource;
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

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;
    private HttpHeaders headers (){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // Classe modelo para mostrar as exceptions no front
    private ResponseError responseError(String message, HttpStatus status){
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setError(message);
        responseError.setStatusCode(status.value());
        responseError.setTimestamp(Date.from(Instant.now()));
        return responseError;
    }

    //handler Geral
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handlerGeneral(Exception e , WebRequest request){
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)){
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return handlerElementNotFoundException((ElementNotFoundException) exception.getUndeclaredThrowable(),request);
        }else {
            String message = messageSource.getMessage("error.server", new Object[]{e.getMessage()},null);
            ResponseError error = responseError(message,HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(e,error,headers(),HttpStatus.INTERNAL_SERVER_ERROR,request);
        }
    }

    // elemento nao localizado
    @ExceptionHandler({ElementNotFoundException.class})
    private ResponseEntity<Object> handlerElementNotFoundException(ElementNotFoundException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(),HttpStatus.NOT_FOUND);
        return handleExceptionInternal(e,error,headers(),HttpStatus.NOT_FOUND,request);
    }
}
