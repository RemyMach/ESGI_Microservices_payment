package com.esgi.infrastructure;

import com.esgi.kernel.exception.PaymentFailedException;
import com.esgi.kernel.exception.ServerInterruptButNotPaymentException;
import com.esgi.model.AddPayment400Response;
import com.esgi.model.AddPayment500Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ResponseEntity<AddPayment400Response> paymentFailedHandler(PaymentFailedException ex) {
        return ResponseEntity.badRequest()
                .body(new AddPayment400Response()
                        .codeError("PAYMENT_FAILED")
                        .occurredDate(ex.getOccuredAt())
                        .message(ex.getMessage())
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AddPayment500Response> paymentFailedHandler(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new AddPayment500Response()
                        .message("internal server error")
                );
    }

}
