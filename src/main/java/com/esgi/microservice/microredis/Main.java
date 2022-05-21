package com.esgi.microservice.microredis;

import com.esgi.microservice.microredis.domain.PaymentProof;
import com.esgi.microservice.microredis.domain.PaymentProofId;
import com.esgi.microservice.microredis.domain.PaymentProofs;
import com.esgi.microservice.microredis.infrastructure.RedisPaymentProofs;

public class Main {

    public static void main(String[] args) {
        PaymentProofs paymentProofs = new RedisPaymentProofs();
        paymentProofs.add(new PaymentProof(new PaymentProofId("1")));
        PaymentProof paymentProof = paymentProofs.findById(String.valueOf(1));
        System.out.println(paymentProof.getPaymentProofId());
    }
}
