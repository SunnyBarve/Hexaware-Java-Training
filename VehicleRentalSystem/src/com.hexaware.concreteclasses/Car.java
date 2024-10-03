package com.hexaware.concreteclasses;

import com.hexaware.abstractclasses.Vehicle;

//Concrete class representing a Car, inherits from Vehicle
public class Car extends Vehicle {
    public Car(String name, double pricePerDay) {
        super(name, pricePerDay); // Call the constructor of the abstract Vehicle class
    }

 // Implementation of the abstract methods from Vehicle class
    @Override
    public void rentVehicle() {
        if (!isRented()) {
            System.out.println("Car " + getName() + " has been rented for $" + getPricePerDay() + " per day.");
            setRented(true);
        } else {
            System.out.println("Car " + getName() + " is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented()) {
            System.out.println("Car " + getName() + " has been returned.");
            setRented(false);
        } else {
            System.out.println("Car " + getName() + " was not rented.");
        }
    }
}
