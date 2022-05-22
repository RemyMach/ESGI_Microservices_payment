package com.esgi.microservice.microredis.domain;

public interface PaymentProofs {
    void add(PaymentProof paymentProof);
    PaymentProof findById(String id);
}
