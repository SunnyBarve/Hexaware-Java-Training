package com.hexaware.mainvehicleprogram;

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
        Vehicle car = new Car("Honda ", 50);
        Vehicle bike = new Bike("Yamaha", 20);
        Vehicle truck = new Truck("TATA", 100);

        while (true) {
            System.out.println("\n--- Vehicle Rental System ---");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. View Rented Vehicles");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available vehicles: ");
                    System.out.println("1. Car - Honda  ($50 per day)");
                    System.out.println("2. Bike - Yamaha ($20 per day)");
                    System.out.println("3. Truck - TATA ($100 per day)");
                    System.out.print("Choose a vehicle to rent: ");
                    int vehicleChoice = scanner.nextInt();
                    if (vehicleChoice == 1) {
                        user.rentVehicle(car);
                    } else if (vehicleChoice == 2) {
                        user.rentVehicle(bike);
                    } else if (vehicleChoice == 3) {
                        user.rentVehicle(truck);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    System.out.println("Return a vehicle:");
                    System.out.println("1. Car");
                    System.out.println("2. Bike");
                    System.out.println("3. Truck");
                    System.out.print("Choose a vehicle to return: ");
                    int returnChoice = scanner.nextInt();
                    if (returnChoice == 1) {
                        user.returnVehicle(car);
                    } else if (returnChoice == 2) {
                        user.returnVehicle(bike);
                    } else if (returnChoice == 3) {
                        user.returnVehicle(truck);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    user.viewRentedVehicles();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
                    		
