package com.hexaware.abstractclasses;

//Abstract class to represent common vehicle properties and methods
public abstract class Vehicle {
	
	private String name;
    private double pricePerDay;
    private boolean isRented;
    
    // Constructor
    public Vehicle(String name, double pricePerDay) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.isRented = false; // Initially, the vehicle is not rented
    }
    
 // Getter for vehicle name
    public String getName() {
        return name;
    }
    
    // Getter for rental price
    public double getPricePerDay() {
        return pricePerDay;
    }
    
    // Check if the vehicle is rented
    public boolean isRented() {
        return isRented;
    }
    
 // Marks the vehicle as rented or not
    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }
    
    // Abstract method for renting and returning a vehicle 
    public abstract void rentVehicle();
    public abstract void returnVehicle();
}
