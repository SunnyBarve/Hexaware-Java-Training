package com.hexaware.project;

public class Main {
	public static void main(String[] args) {
        
        SavingsAccount savings = new SavingsAccount("SA12345", 1000.00);
        savings.deposit(500);
        savings.withdraw(200);
        savings.applyInterest(); 
        System.out.println("Final Balance in Savings Account: $" + savings.getBalance());

       
        CurrentAccount current = new CurrentAccount("CA54321", 2000.00);
        current.deposit(1000);
        current.withdraw(1500);
        current.applyInterest(); 
        System.out.println("Final Balance in Current Account: $" + current.getBalance());
    }
}
