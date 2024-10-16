package com.hexaware.entity;

import com.hexaware.exception.InsufficientPaymentException;
public abstract class Payment {
    protected String payerName;
    protected double amount;

    // Constructor to initialize payerName and amount
    public Payment(String payerName, double amount) {
        this.payerName = payerName;
        this.amount = amount;
    }

    // Abstract method to process the payment
    public abstract void processPayment() throws InsufficientPaymentException;


    // Getters and setters
    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
