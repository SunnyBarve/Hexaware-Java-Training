package com.hexaware.main;

import java.util.Scanner;
import com.hexaware.dao.ItemDAOImpl;
import com.hexaware.dao.OrderDAOImpl;
import com.hexaware.entity.Item;
import com.hexaware.entity.ShoppingCart;
import com.hexaware.entity.Order;
import com.hexaware.exception.InvalidPriceException;
import com.hexaware.exception.InsufficientPaymentException;

import java.sql.SQLException;
import java.util.List;

public class MainModule {
    public static void main(String[] args) throws InvalidPriceException {
        Scanner scanner = new Scanner(System.in);
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        ShoppingCart shoppingCart = new ShoppingCart();
        
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View all items");
            System.out.println("2. Add item to cart");
            System.out.println("3. Remove item from cart");
            System.out.println("4. View cart items");
            System.out.println("5. Place order");
            System.out.println("6. View order history");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1: // View all items
                    try {
                        List<Item> items = itemDAO.getAllItems();
                        System.out.println("Available items:");
                        for (Item item : items) {
                            System.out.println(item);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error retrieving items: " + e.getMessage());
                    }
                    break;

                case 2: // Add item to cart
                    System.out.print("Enter item name to add: ");
                    String itemNameToAdd = scanner.nextLine();
                    boolean itemAdded = false; //  check if item was added
                    try {
                        List<Item> itemsToCheck = itemDAO.getAllItems(); // Fetch all items from the database
                        for (Item item : itemsToCheck) {
                            if (item.getItemName().equalsIgnoreCase(itemNameToAdd)) {
                                shoppingCart.addItem(item); // Add to shopping cart
                                itemAdded = true; // Set flag to true
                                //System.out.println(item.getItemName() + " added to the cart.");
                                break; // Exit loop since item is found
                            }
                        }
                        if (!itemAdded) { // If item wasn't found
                            System.out.println("Item not found: " + itemNameToAdd);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error adding item to cart: " + e.getMessage());
                    }
                    break;

                case 3: // Remove item from cart
                    System.out.print("Enter item name to remove: ");
                String itemNameToRemove = scanner.nextLine();
                boolean removed = shoppingCart.removeItem(itemNameToRemove); // Attempt to remove the item
                if (removed) {
                    System.out.println(itemNameToRemove + " removed from the cart.");
                } else {
                    System.out.println(itemNameToRemove + " is not in the cart.");
                }
                System.out.println("Current cart items before removal: " + shoppingCart.getCartItems());
                break;

                case 4: // View cart items
                    System.out.println("Items in your cart:");
                    shoppingCart.listCartItems();
                    break;

                case 5: // Place order
                    if (shoppingCart.getCartItems().isEmpty()) {
                        System.out.println("Your cart is empty. Please add items before placing an order.");
                    } else {
                        Order order = new Order("ORD" + System.currentTimeMillis(), shoppingCart.getCartItems()); 
                        shoppingCart.clearCart(); // Clear the cart after placing the order
                        System.out.println("Order placed successfully!");
                    }
                    break;

                case 6: // View order history
                    try {
                        List<Order> orderHistory = orderDAO.getOrderHistory(); // Fetch all order history
                        if (orderHistory.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Order history:");
                            for (Order o : orderHistory) {
                                System.out.println(o); // Display each order
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Error retrieving order history: " + e.getMessage());
                    }
                    break;

                case 7: // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using ShopSmart!");
    }
}
