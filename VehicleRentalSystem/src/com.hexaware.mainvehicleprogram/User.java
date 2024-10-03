package com.hexaware.mainvehicleprogram;
import com.hexaware.exceptions.*;
import com.hexaware.abstractclasses.Vehicle;

// Class representing a user who can rent and return vehicles
public class User {
    // Array to store the rented vehicles by the user
    private Vehicle[] rentedVehicles;
    private int rentedCount; // Counter for the number of rented vehicles

    // Constructor to initialize the array and rented count
    public User() {
        this.rentedVehicles = new Vehicle[10]; // Assuming a maximum of 10 rented vehicles
        this.rentedCount = 0; // Initialize the count of rented vehicles to zero
    }

    // Method to rent a vehicle
    public void rentVehicle(Vehicle vehicle) throws VehicleNotAvailableException, MaxRentedVehiclesException {
        // Check if the vehicle is not already rented
    	if (vehicle.isRented()) {
            throw new VehicleNotAvailableException(vehicle.getName() + " is already rented.");
        }
        if (rentedCount >= rentedVehicles.length) {
            throw new MaxRentedVehiclesException("You cannot rent more vehicles. Max limit reached.");
        }
        vehicle.rentVehicle();
        rentedVehicles[rentedCount++] = vehicle;
    }

    // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle) throws VehicleNotRentedException {
        boolean found = false; // Flag to check if the vehicle was found
        // Iterate through the rented vehicles to find the vehicle
        for (int i = 0; i < rentedCount; i++) {
        	 if (rentedVehicles[i].equals(vehicle)) {
                 vehicle.returnVehicle();
                 rentedVehicles[i] = rentedVehicles[--rentedCount];  // Shift the last vehicle into the returned vehicle's slot
                 found = true;
                 break;
             }
        }
        // If the vehicle was not found in the rented list
        if (!found) {
        	 throw new VehicleNotRentedException("You haven't rented this vehicle.");
        }
    }

    // Method to view the list of rented vehicles
    public void viewRentedVehicles() {
        if (rentedCount == 0) { // Check if no vehicles are rented
            System.out.println("No vehicles currently rented.");
        } else {
            System.out.println("Rented vehicles:");
            for (int i = 0; i < rentedCount; i++) {
                System.out.println(rentedVehicles[i].getName()); // Display each rented vehicle's name
            }
        }
    }
}
