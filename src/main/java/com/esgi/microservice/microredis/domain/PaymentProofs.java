package com.esgi.microservice.microredis.domain;

public interface PaymentProofs {
    void add(Proof proof);
    Proof findById(String id);
}
