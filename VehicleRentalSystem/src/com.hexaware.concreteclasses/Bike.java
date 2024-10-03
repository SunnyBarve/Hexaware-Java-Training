package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

public class Bike extends Vehicle {
    public Bike(String name, double pricePerDay) {
        super(name, pricePerDay);
    }

 // Implementation of the abstract methods from Vehicle class
    @Override
    public void rentVehicle() {
        if (!isRented()) {
            System.out.println("Bike " + getName() + " has been rented for $" + getPricePerDay() + " per day.");
            setRented(true);
        } else {
            System.out.println("Bike " + getName() + " is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            System.out.println("Bike " + getName() + " has been returned.");
            setRented(false);
        } else {
            System.out.println("Bike " + getName() + " was not rented.");
        }
    }
}
