package com.hexaware.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> cartItems;

    // Constructor to initialize the cartItems list
    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    // Method to add an item to the shopping cart
    public void addItem(Item item) {
        cartItems.add(item);
        System.out.println(item.getItemName() + " added to the cart.");
    }

    // Method to remove an item from the shopping cart
    public boolean removeItem(String itemName) {
        for (Item item : cartItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                cartItems.remove(item);
                System.out.println(item.getItemName() + " removed from the cart.");
                return true; // Item found and removed
            }
        }
        System.out.println(itemName + " is not in the cart.");
        return false; // Item not found
    }

    // Method to display all items in the cart
    public void listCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Items in your shopping cart:");
            for (Item item : cartItems) {
                System.out.println(item);
            }
        }
    }

    // Method to clear all items from the shopping cart
    public void clearCart() {
        cartItems.clear();
        System.out.println("Shopping cart cleared.");
    }

    // Getter for cartItems
    public List<Item> getCartItems() {
        return cartItems;
    }
}
