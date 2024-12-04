package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

public class FieldsException extends RuntimeException {
    public FieldsException(String message) {
        super(message);
    }
    public FieldsException(String message, Object ...params){
        super(String.format(message, params));
    }
}
