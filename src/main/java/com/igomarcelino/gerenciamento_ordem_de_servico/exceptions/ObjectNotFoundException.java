package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(String message, Object ...params){
        super(String.format(message,params));
    }
}
