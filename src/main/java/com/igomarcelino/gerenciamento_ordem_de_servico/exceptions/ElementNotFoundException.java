package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message) {
        super(message);
    }
    public ElementNotFoundException(String message, Object ...params){
        super(String.format(message,params));
    }
}
