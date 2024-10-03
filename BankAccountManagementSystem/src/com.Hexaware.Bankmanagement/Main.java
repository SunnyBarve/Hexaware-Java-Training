package com.Hexaware.Bankmanagement;
import com.Hexaware.exceptions.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;  // The account starts as null

        while (true) {
           
                // Displaying the menu options
                System.out.println("\n--- Bank Account Management System ---");
                System.out.println("1. Create New Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                try {
                switch (choice) {
                    case 1:
                        // Create a new account
                        System.out.print("Enter account holder name: ");
                        scanner.nextLine();  
                        String accountHolder = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double initialBalance = scanner.nextDouble();
                        account = new BankAccount(accountHolder, initialBalance);
                        System.out.println("Account created for " + accountHolder);
                        break;

                    case 2:
                        // Deposit money
                        if (account == null) {
                            throw new NullPointerException("No account found. Please create an account first.");
                        }
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 3:
                        // Withdraw money
                        if (account == null) {
                            throw new NullPointerException("No account found. Please create an account first.");
                        }
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        // Check balance
                        if (account == null) {
                            throw new NullPointerException("No account found. Please create an account first.");
                        }
                        System.out.println("Current balance: " + account.getBalance());
                        break;

                    case 5:
                        // Exit the program
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        throw new InvalidChoiceException("Invalid choice! Please select a valid option.");
                }

            } catch (InvalidAmountException | InsufficientFundsException | InvalidChoiceException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
