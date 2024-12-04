package com.igomarcelino.gerenciamento_ordem_de_servico.exceptions;

import java.time.LocalDate;
import java.util.Date;

public class ResponseError {
    private Date timestamp ;
    private String status ;
    private int statusCode ;
    private String error;

    public ResponseError(Date timestamp, String status, int statusCode, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
    }

    public ResponseError() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
