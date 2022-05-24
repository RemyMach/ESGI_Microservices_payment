package io.swagger.application;

import io.swagger.domain.*;
import io.swagger.kernel.CommandHandler;
import io.swagger.kernel.exception.PaymentFailedException;
import io.swagger.kernel.exception.ServerInterruptButNotPaymentException;
import io.swagger.model.Payment;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static java.lang.Thread.sleep;

public final class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, PaymentProof> {

    private final PaymentProofs paymentProofs;

    public CreatePaymentCommandHandler(PaymentProofs paymentProofs) {
        this.paymentProofs = paymentProofs;
    }

    @Override
    public PaymentProof handle(CreatePayment createPayment) throws InterruptedException {

        PaymentProof paymentProofFromRepo = this.paymentProofs.findById(createPayment.id);
        if(paymentProofFromRepo != null) {
            if(paymentProofFromRepo.getPaymentStatus().equals(PaymentStatus.PAYMENT_PROCESSING)) {
                throw new ServerInterruptButNotPaymentException("Server interrupt communication for let payment in mode processing");
            }

            if(paymentProofFromRepo.getPaymentStatus().equals(PaymentStatus.PAYMENT_SUCCESS)) {
                return paymentProofFromRepo;
            }
        }

        // for example change make payment status to Processing
        PaymentProof paymentProof = new PaymentProof(
                new PaymentProofId(createPayment.id),
                PaymentStatus.PAYMENT_PROCESSING,
                new TransactionId(UUID.randomUUID().toString()),
                Instant.now().toString()
        );
        this.paymentProofs.add(paymentProof);
        sleep(1000);

        // for example change payment status to fail
        if(paymentProof.getPaymentProofId().equals(new PaymentProofId("2"))) {
            paymentProof.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
            this.paymentProofs.add(paymentProof);
            throw new PaymentFailedException("sandbox Payment failed in the service");
        }


        // for example change payment status to Success
        if(paymentProof.getPaymentProofId().equals(new PaymentProofId("4"))) {
            throw new ServerInterruptButNotPaymentException("Server interrupt communication for let payment in mode processing");
        }

        paymentProof.setPaymentStatus(PaymentStatus.PAYMENT_SUCCESS);
        this.paymentProofs.add(paymentProof);
        return paymentProof;
    }
}
