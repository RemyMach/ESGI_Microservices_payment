package io.swagger.domain;

import java.time.ZonedDateTime;

public class PaymentProof {
    PaymentProofId paymentProofId;
    PaymentStatus paymentStatus;

    TransactionId transactionId;

    String date;

    public PaymentProof(PaymentProofId paymentProofId, PaymentStatus paymentStatus, TransactionId transactionId, String date) {
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

    public String getDate() {
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
