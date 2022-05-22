package io.swagger.application;

import io.swagger.domain.*;
import io.swagger.kernel.CommandHandler;
import io.swagger.model.Payment;

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
                throw new RuntimeException();
            }

            if(paymentProofFromRepo.getPaymentStatus().equals(PaymentStatus.PAYMENT_SUCCESS)) {
                return paymentProofFromRepo;
            }
        }

        // on enregistre le paiement en DB avec un status processing
        PaymentProof paymentProof = new PaymentProof(new PaymentProofId(createPayment.id), PaymentStatus.PAYMENT_PROCESSING, new TransactionId(UUID.randomUUID().toString()), createPayment.Date);
        this.paymentProofs.add(paymentProof);
        sleep(1000);

        if(paymentProof.getPaymentProofId().equals(new PaymentProofId("2"))) {
            paymentProof.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
            this.paymentProofs.add(paymentProof);
            return paymentProof;
        }


        paymentProof.setPaymentStatus(PaymentStatus.PAYMENT_SUCCESS);
        this.paymentProofs.add(paymentProof);
        return paymentProof;
    }
}
