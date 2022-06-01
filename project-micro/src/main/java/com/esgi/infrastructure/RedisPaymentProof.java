package com.esgi.infrastructure;

public class RedisPaymentProof {
    public String id;
    public String transactionId;
    public String date;
    public String PaymentStatus;

    public RedisPaymentProof(String id, String transactionId, String date, String paymentStatus) {
        this.id = id;
        this.transactionId = transactionId;
        this.date = date;
        PaymentStatus = paymentStatus;
    }

    public RedisPaymentProof() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RedisPaymentProof{" +
                "id='" + id + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", date='" + date + '\'' +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                '}';
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}
