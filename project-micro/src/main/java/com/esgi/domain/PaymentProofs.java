package com.esgi.domain;

public interface PaymentProofs {
    void add(PaymentProof paymentProof);
    PaymentProof findById(String id);
}
