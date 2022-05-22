package io.swagger.model;

public class PaymentProofResponse{
    public String transactionId;
    public String date;

    public PaymentProofResponse(String transactionId, String date) {
        this.transactionId = transactionId;
        this.date = date;
    }

}
