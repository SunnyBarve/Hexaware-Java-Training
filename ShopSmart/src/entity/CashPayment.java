package com.hexaware.entity;

import com.hexaware.exception.InsufficientPaymentException;
public class CashPayment extends Payment {
    private double cashReceived;

    // Constructor to initialize payerName, amount, and cashReceived
    public CashPayment(String payerName, double amount, double cashReceived) {
        super(payerName, amount); // Calling the superclass constructor
        this.cashReceived = cashReceived;
    }

    // Method to process the cash payment
    @Override
    public void processPayment() throws InsufficientPaymentException  {
    	if (cashReceived < amount) {
            throw new InsufficientPaymentException("Insufficient cash received. Payment cannot be processed.");
        }
    	 System.out.println("Cash payment successful for " + payerName + " of amount $" + amount);
         double change = cashReceived - amount;
         System.out.println("Change to return: $" + change);
     }
    // Getters and setters
    public double getCashReceived() {
        return cashReceived;
    }

    public void setCashReceived(double cashReceived) {
        this.cashReceived = cashReceived;
    }
}
