package com.esgi.kernel.exception;

public class ServerInterruptButNotPaymentException extends RuntimeException {
    public ServerInterruptButNotPaymentException(String message) {
        super(message);
    }
}
