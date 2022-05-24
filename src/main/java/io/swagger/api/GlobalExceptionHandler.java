package io.swagger.api;

import io.swagger.api.exception.ExceptionResponse;
import io.swagger.kernel.exception.PaymentFailedException;
import io.swagger.kernel.exception.ServerInterruptButNotPaymentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerInterruptButNotPaymentException.class)
    public ResponseEntity<ExceptionResponse> serverInterruptHandler(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "PAYMENT_PROCESSING",
                        ZonedDateTime.now(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ExceptionResponse> paymentFailedHandler(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(
                        "PAYMENT_FAILED",
                        ZonedDateTime.now(),
                        ex.getMessage()
                ));
    }
}
