package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

public class BadReqException extends RuntimeException{
    public BadReqException(String message) {
        super(message);
    }
    public BadReqException(String message, Object ... params){
        super(String.format(message,params));
    }
}
