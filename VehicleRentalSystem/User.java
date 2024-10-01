package com.hexaware.mainvehicleprogram;

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
    public void rentVehicle(Vehicle vehicle) {
        // Check if the vehicle is not already rented
        if (!vehicle.isRented()) {
            vehicle.rentVehicle(); // Mark the vehicle as rented
            rentedVehicles[rentedCount] = vehicle; // Add vehicle to the rented array
            rentedCount++; // Increment the rented count
            System.out.println(vehicle.getName() + " has been rented.");
        } else {
            System.out.println(vehicle.getName() + " is already rented.");
        }
    }

    // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle) {
        boolean found = false; // Flag to check if the vehicle was found
        // Iterate through the rented vehicles to find the vehicle
        for (int i = 0; i < rentedCount; i++) {
            if (rentedVehicles[i] == vehicle) { // Check if the vehicle matches
                vehicle.returnVehicle(); // Mark the vehicle as returned
                rentedVehicles[i] = rentedVehicles[rentedCount - 1]; // Replace it with the last rented vehicle
                rentedVehicles[rentedCount - 1] = null; // Clear the last position
                rentedCount--; // Decrement the rented count
                found = true; // Vehicle found and returned
                System.out.println(vehicle.getName() + " has been returned.");
                break;
            }
        }
        // If the vehicle was not found in the rented list
        if (!found) {
            System.out.println("You haven't rented this vehicle.");
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
