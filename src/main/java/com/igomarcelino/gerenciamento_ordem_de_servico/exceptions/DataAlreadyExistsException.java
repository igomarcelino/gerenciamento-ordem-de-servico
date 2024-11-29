package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message) {
        super(message);
    }
    public DataAlreadyExistsException(String message, Object ...params){
        super(String.format(message,params));
    }
}
