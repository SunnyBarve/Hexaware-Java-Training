package com.hexaware.mainvehicleprogram;

import com.hexaware.abstractclasses.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class User {
	// List to store the rented vehicles by the user
    private List<Vehicle> rentedVehicles;

 // Constructor to initialize the list of rented vehicles
    public User() {
        this.rentedVehicles = new ArrayList<>();
    }

    // Method to rent a vehicle
    public void rentVehicle(Vehicle vehicle) {
        if (!vehicle.isRented()) {
            vehicle.rentVehicle();
            rentedVehicles.add(vehicle);
        } else {
            System.out.println(vehicle.getName() + " is already rented.");
        }
    }

 // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle) {
        if (rentedVehicles.contains(vehicle)) {
            vehicle.returnVehicle();
            rentedVehicles.remove(vehicle);
        } else {
            System.out.println("You haven't rented this vehicle.");
        }
    }
    
 // Method to view the list of rented vehicles
    public void viewRentedVehicles() {
        if (rentedVehicles.isEmpty()) {
            System.out.println("No vehicles currently rented.");
        } else {
            System.out.println("Rented vehicles:");
            for (Vehicle vehicle : rentedVehicles) {
                System.out.println(vehicle.getName());
            }
        }
    }
}
