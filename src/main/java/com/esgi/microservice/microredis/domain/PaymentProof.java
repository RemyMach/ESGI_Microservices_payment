package com.esgi.microservice.microredis.domain;

public class PaymentProof {
    PaymentProofId paymentProofId;

    public PaymentProof(PaymentProofId paymentProofId) {
        this.paymentProofId = paymentProofId;
    }

    public PaymentProofId getPaymentProofId() {
        return paymentProofId;
    }
}
