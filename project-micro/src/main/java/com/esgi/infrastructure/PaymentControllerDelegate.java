package com.esgi.infrastructure;

import com.esgi.application.CreatePayment;
import com.esgi.application.CreatePaymentCommandHandler;
import com.esgi.domain.PaymentProof;
import com.esgi.kernel.exception.PaymentFailedException;
import com.esgi.kernel.exception.ServerInterruptButNotPaymentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microredis.generate.api.V1ApiDelegate;
import com.microredis.generate.model.AddPayment201Response;
import com.microredis.generate.model.Payment;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public class PaymentControllerDelegate implements V1ApiDelegate {

    private static final Logger log = LoggerFactory.getLogger(V1ApiDelegate.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final CreatePaymentCommandHandler createPaymentCommandHandler;

    @Autowired
    public PaymentControllerDelegate(CreatePaymentCommandHandler createPaymentCommandHandler, ObjectMapper objectMapper, HttpServletRequest request) {
        this.createPaymentCommandHandler = createPaymentCommandHandler;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<AddPayment201Response> addPayment(@Valid @RequestBody Payment body)  throws ServerInterruptButNotPaymentException, PaymentFailedException {
        try {
            PaymentProof paymentProof = this.createPaymentCommandHandler.handle(new CreatePayment(body.getId().toString(), body.getBuyerInfo(), body.getAmount(), body.getCurrency(), body.getDate().toString(), body.getSellerAccount()));
            AddPayment201Response addPayment201Response = PaymentProofMapper.mapPaymentProofToPaymentProofResponse(paymentProof);
            System.out.println("paymentProofResponse dans le controller");
            System.out.println(addPayment201Response.toString());
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(addPayment201Response);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
