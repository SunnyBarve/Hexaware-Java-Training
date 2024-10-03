package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

public class Truck extends Vehicle {
    public Truck(String name, double pricePerDay) {
        super(name, pricePerDay);
    }

 // Implementation of the abstract methods from Vehicle class
    @Override
    public void rentVehicle() {
        if (!isRented()) {
            System.out.println("Truck " + getName() + " has been rented for $" + getPricePerDay() + " per day.");
            setRented(true);
        } else {
            System.out.println("Truck " + getName() + " is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            System.out.println("Truck " + getName() + " has been returned.");
            setRented(false);
        } else {
            System.out.println("Truck " + getName() + " was not rented.");
        }
    }
}
