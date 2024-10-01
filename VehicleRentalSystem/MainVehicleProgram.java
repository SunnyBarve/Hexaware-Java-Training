package com.hexaware.mainvehicleprogram;
import com.hexaware.exceptions.*;
import com.hexaware.abstractclasses.Vehicle;

import com.hexaware.abstractclasses.Vehicle;
import com.hexaware.concreteclasses.Car;
import com.hexaware.concreteclasses.Bike;
import com.hexaware.concreteclasses.Truck;
import java.util.Scanner;

public class MainVehicleProgram {
	 public static void main(String[] args) {
	        // Create a user
	        User user = new User();
	        Scanner scanner = new Scanner(System.in);

	        // Create vehicles (Car, Bike, Truck)
	        Vehicle car = new Car("Honda", 50);
	        Vehicle bike = new Bike("Yamaha", 20);
	        Vehicle truck = new Truck("TATA", 100);

	        // Main program loop for user interaction
	        while (true) {
	            // Display menu options to the user
	            System.out.println("\n--- Vehicle Rental System ---");
	            System.out.println("1. Rent a Vehicle");
	            System.out.println("2. Return a Vehicle");
	            System.out.println("3. View Rented Vehicles");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt(); // Get user's choice
               try {
	            switch (choice) {
	                case 1:
	                    // Display available vehicles for renting
	                    System.out.println("Available vehicles: ");
	                    System.out.println("1. Car - Honda ($50 per day)");
	                    System.out.println("2. Bike - Yamaha ($20 per day)");
	                    System.out.println("3. Truck - TATA ($100 per day)");
	                    System.out.print("Choose a vehicle to rent: ");
	                    int vehicleChoice = scanner.nextInt(); // Get vehicle choice
	                    // Rent the chosen vehicle
	                    switch (vehicleChoice) {
                        case 1:
                            user.rentVehicle(car);
                            break;
                        case 2:
                            user.rentVehicle(bike);
                            break;
                        case 3:
                            user.rentVehicle(truck);
                            break;
                        default:
                            throw new InvalidChoiceException("Invalid vehicle choice.");
                    }
	                    break;
	                case 2:
	                    // Allow the user to return a rented vehicle
	                    System.out.println("Return a vehicle:");
	                    System.out.println("1. Car");
	                    System.out.println("2. Bike");
	                    System.out.println("3. Truck");
	                    System.out.print("Choose a vehicle to return: ");
	                    int returnChoice = scanner.nextInt(); // Get return choice
	                    // Return the chosen vehicle
	                    switch (returnChoice) {
                        case 1:
                            user.returnVehicle(car);
                            break;
                        case 2:
                            user.returnVehicle(bike);
                            break;
                        case 3:
                            user.returnVehicle(truck);
                            break;
                        default:
                            throw new InvalidChoiceException("Invalid vehicle choice.");
	                    }
	                    break;
	                case 3:
	                    // Display the user's currently rented vehicles
	                    user.viewRentedVehicles();
	                    break;
	                case 4:
	                    // Exit the program
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    return;
	                default:
	                    // Handle invalid menu choices
	                	  throw new InvalidChoiceException("Invalid menu choice.");
	            }
               } catch (InvalidChoiceException | VehicleNotAvailableException | MaxRentedVehiclesException | VehicleNotRentedException e) {
                   System.out.println(e.getMessage());
               }
           }
       }
   }
