package com.esgi.microservice.microredis.infrastructure;

import com.esgi.microservice.microredis.domain.PaymentProof;
import com.esgi.microservice.microredis.domain.PaymentProofs;

public class RedisPaymentProofs implements PaymentProofs {

    @Override
    public void add(PaymentProof proof) {

    }

    @Override
    public PaymentProof findById(String id) {
        return null;
    }
}
