package com.esgi.application;

import com.esgi.kernel.Command;

import java.time.OffsetDateTime;

public class CreatePayment implements Command {

    public final String id;
    public final String buyerInfo;
    public final String amount;
    public final String currency;
    public final OffsetDateTime Date;
    public final String sellerAccount;

    public CreatePayment(String id, String buyerInfo, String amount, String currency, OffsetDateTime date, String sellerAccount) {
        this.id = id;
        this.buyerInfo = buyerInfo;
        this.amount = amount;
        this.currency = currency;
        Date = date;
        this.sellerAccount = sellerAccount;
    }
}