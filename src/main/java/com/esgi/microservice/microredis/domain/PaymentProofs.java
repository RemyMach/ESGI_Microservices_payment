package com.esgi.microservice.microredis.domain;

public interface PaymentProofs {
    void add(PaymentProof proof);
    PaymentProof findById(String id);
}
