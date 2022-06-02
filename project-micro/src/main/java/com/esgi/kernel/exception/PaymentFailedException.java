package com.esgi.kernel.exception;

import java.time.OffsetDateTime;

public class PaymentFailedException extends RuntimeException{

    private OffsetDateTime occuredAt;

    public PaymentFailedException(String message, OffsetDateTime occuredAt) {
        super(message);
        this.occuredAt = occuredAt;
    }

    public OffsetDateTime getOccuredAt() {
        return occuredAt;
    }
}