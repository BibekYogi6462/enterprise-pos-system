package com.bibek.enterprisepossystem.model;


import com.bibek.enterprisepossystem.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType paymentType;
    private Double totalAmount;
    private int transactionCount;
    private Double percentage;
}
