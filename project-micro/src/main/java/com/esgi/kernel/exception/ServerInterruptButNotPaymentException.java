package com.esgi.kernel.exception;

import java.time.OffsetDateTime;

public class ServerInterruptButNotPaymentException extends RuntimeException {
    private OffsetDateTime occuredAt;
    public ServerInterruptButNotPaymentException(String message, OffsetDateTime occuredAt) {
        super(message);
        this.occuredAt = occuredAt;
    }

    public OffsetDateTime getOccuredAt() {
        return occuredAt;
    }
}
