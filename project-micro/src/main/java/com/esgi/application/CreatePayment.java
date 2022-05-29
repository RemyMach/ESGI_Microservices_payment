package com.esgi.application;

import com.esgi.kernel.Command;

public class CreatePayment implements Command {

    public final String id;
    public final String buyerInfo;
    public final String amount;
    public final String currency;
    public final String Date;
    public final String sellerAccount;

    public CreatePayment(String id, String buyerInfo, String amount, String currency, String date, String sellerAccount) {
        this.id = id;
        this.buyerInfo = buyerInfo;
        this.amount = amount;
        this.currency = currency;
        Date = date;
        this.sellerAccount = sellerAccount;
    }
}