package com.uduran.apiserverlet.webapp10.exceptions;

public class ServiceJdbcException extends RuntimeException{
    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
