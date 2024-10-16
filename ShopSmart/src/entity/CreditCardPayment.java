package com.hexaware.entity;


public class CreditCardPayment extends Payment {
    private String cardNumber;

    // Constructor to initialize payerName, amount, and cardNumber
    public CreditCardPayment(String payerName, double amount, String cardNumber) {
        super(payerName, amount); // Calling the superclass constructor
        this.cardNumber = cardNumber;
    }

    // Method to process the credit card payment
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment for " + payerName + " of amount $" + amount);
        System.out.println("Card Number: " + cardNumber);
        
    }

    // Getters and setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
