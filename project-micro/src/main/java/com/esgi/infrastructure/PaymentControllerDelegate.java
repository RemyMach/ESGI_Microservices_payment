package com.esgi.infrastructure;

import com.esgi.api.PaymentsApiDelegate;
import com.esgi.application.CreatePayment;
import com.esgi.application.CreatePaymentCommandHandler;
import com.esgi.domain.PaymentProof;
import com.esgi.model.AddPayment201Response;
import com.esgi.model.Payment;
import com.esgi.kernel.exception.PaymentFailedException;
import com.esgi.kernel.exception.ServerInterruptButNotPaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@Service
public class PaymentControllerDelegate implements PaymentsApiDelegate {
    private final CreatePaymentCommandHandler createPaymentCommandHandler;

    @Autowired
    public PaymentControllerDelegate(CreatePaymentCommandHandler createPaymentCommandHandler) {
        this.createPaymentCommandHandler = createPaymentCommandHandler;
    }

    @Override
    public ResponseEntity<AddPayment201Response> addPayment(@Valid @RequestBody Payment body)  throws ServerInterruptButNotPaymentException, PaymentFailedException {
        try {
            PaymentProof paymentProof = this.createPaymentCommandHandler.handle(new CreatePayment(body.getId().toString(), body.getBuyerInfo(), body.getAmount(), body.getCurrency(), body.getDate(), body.getSellerAccount()));
            AddPayment201Response addPayment201Response = PaymentProofMapper.mapPaymentProofToPaymentProofResponse(paymentProof);
            System.out.println("paymentProofResponse dans le controller");
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(addPayment201Response);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
