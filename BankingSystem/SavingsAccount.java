package com.hexaware.project;

public class SavingsAccount extends BankAccount implements InterestCalculator  {


	
		// TODO Auto-generated method stub
		 public SavingsAccount(String accountNumber, double balance) {
		        super(accountNumber, balance);
		    }
		 @Override
		 public void calculateInterest() {
		        
		        double interest = getBalance() * 0.05;
		        setBalance(getBalance() + interest);
		        System.out.println("Interest added to Savings Account: $" + interest);
		    }
		
		 @Override
		    public void applyInterest() {
		        calculateInterest();
		    }

	}


