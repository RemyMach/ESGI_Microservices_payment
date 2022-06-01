package com.esgi.domain;

import java.time.OffsetDateTime;

public class PaymentProof {

    PaymentProofId paymentProofId;
    PaymentStatus paymentStatus;
    TransactionId transactionId;
    OffsetDateTime date;

    public PaymentProof(PaymentProofId paymentProofId, PaymentStatus paymentStatus, TransactionId transactionId, OffsetDateTime date) {
        this.paymentProofId = paymentProofId;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.date = date;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public PaymentProofId getPaymentProofId() {
        return paymentProofId;
    }

    @Override
    public String toString() {
        return "PaymentProof{" +
                "paymentProofId=" + paymentProofId +
                ", paymentStatus=" + paymentStatus +
                ", transactionId=" + transactionId +
                ", date='" + date + '\'' +
                '}';
    }
}

