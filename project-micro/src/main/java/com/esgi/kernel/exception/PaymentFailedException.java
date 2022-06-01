package com.esgi.kernel.exception;

import java.time.OffsetDateTime;

public class PaymentFailedException extends RuntimeException{

    private OffsetDateTime occuredAt;

    public PaymentFailedException(String message, OffsetDateTime occuredAt) {
        super(message);
    }

    public OffsetDateTime getOccuredAt() {
        return occuredAt;
    }
}