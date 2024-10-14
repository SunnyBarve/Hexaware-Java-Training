package com.hexaware.px.main;

import com.hexaware.px.util.DBPropertyUtil;
import com.hexaware.px.util.DatabaseContext;
import com.hexaware.px.dao.*;
import com.hexaware.px.entity.*;
import com.hexaware.px.exception.*;
import java.util.*;
import java.sql.Connection;

public class MainModule {

    private static Scanner scanner = new Scanner(System.in);
    private static EmployeeService employeeService;
    private static PayrollService payrollService;
    private static TaxService taxService;
    private static FinancialRecordService financialRecordService;

    public static void main(String[] args) {
    	  // Show the main menu
        showMainMenu();
    }

    private static void showMainMenu() {
        int choice = -1;

        while (choice != 5) {
            System.out.println("Welcome to PayXpert Payroll Management System");
            System.out.println("Please select an option:");
            System.out.println("1. Employee Management");
            System.out.println("2. Payroll Processing");
            System.out.println("3. Tax Calculation");
            System.out.println("4. Financial Reporting");
            System.out.println("5. Exit");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    processPayroll();
                    break;
                case 3:
                    calculateTax();
                    break;
                case 4:
                    manageFinancialRecords();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Employee management menu and options
    private static void manageEmployees() {
        int choice;
        do {
            System.out.println("Employee Management");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee by ID");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Back to Main Menu");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployeeById();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    removeEmployee();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addEmployee() {
        // Gather employee details and call employeeService.addEmployee()
        System.out.println("Enter Employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        
        // Add other employee details here...
        
        // Create the employee object
        Employee employee = new Employee(id, firstName, lastName, new Date(), "Male", "email@example.com", "1234567890", "123 Street", "Manager", new Date(), null);
        employeeService.addEmployee(employee);
        
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployeeById() {
        // Prompt for employee ID and call employeeService.getEmployeeById()
        System.out.println("Enter Employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        
        try {
            Employee employee = employeeService.getEmployeeById(id);
            System.out.println(employee);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {
        // Similar to addEmployee, but also call employeeService.updateEmployee()
        System.out.println("Enter Employee ID to Update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        
        System.out.println("Enter Updated First Name:");
        String firstName = scanner.nextLine();
        
        // Create the updated employee object and call updateEmployee()
        Employee employee = new Employee(id, firstName, "UpdatedLastName", new Date(), "Male", "newemail@example.com", "1234567890", "New Address", "Manager", new Date(), null);
        employeeService.updateEmployee(employee);
        
        System.out.println("Employee updated successfully!");
    }

    private static void removeEmployee() {
        // Prompt for employee ID and call employeeService.removeEmployee()
        System.out.println("Enter Employee ID to Remove:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        
        employeeService.removeEmployee(id);
        System.out.println("Employee removed successfully!");
    }

    // Placeholder for payroll processing methods
    private static void processPayroll() {
        System.out.println("Payroll processing functionality is not yet implemented.");
    }

    // Placeholder for tax calculation methods
    private static void calculateTax() {
        System.out.println("Tax calculation functionality is not yet implemented.");
    }

    // Placeholder for financial reporting methods
    private static void manageFinancialRecords() {
        System.out.println("Financial reporting functionality is not yet implemented.");
    }
}
