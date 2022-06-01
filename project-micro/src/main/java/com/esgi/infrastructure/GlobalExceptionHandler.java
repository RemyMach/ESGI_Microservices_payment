package com.esgi.infrastructure;

import com.esgi.kernel.exception.PaymentFailedException;
import com.esgi.kernel.exception.ServerInterruptButNotPaymentException;
import com.esgi.model.AddPayment400Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServerInterruptButNotPaymentException.class)
    public ResponseEntity<AddPayment400Response> serverInterruptHandler(ServerInterruptButNotPaymentException ex) {
        return ResponseEntity.badRequest()
                .body(new AddPayment400Response()
                                .codeError("PAYMENT_PROCESSING")
                                .occurredDate(ex.getOccuredAt())
                                .message(ex.getMessage())
                );

    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<AddPayment400Response> paymentFailedHandler(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(new AddPayment400Response()
                        .codeError("PAYMENT_FAILED")
                        .occurredDate(OffsetDateTime.from(ZonedDateTime.now()))
                        .message(ex.getMessage())
                );
    }
}
