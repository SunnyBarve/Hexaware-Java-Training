package com.hexaware.project;

public class CurrentAccount extends BankAccount implements InterestCalculator {

	public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
	@Override
	public void calculateInterest() {
		// TODO Auto-generated method stub

	}
	 @Override
	    public void applyInterest() {
	        calculateInterest();
	    }

}
