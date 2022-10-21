package com.example.loancalculatorapi.exception;


// this is an exception in a case that i'm going to execute if we are having bad gateway

public class BadGatewayException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public BadGatewayException(String message) {
        super(message);
    }
}
