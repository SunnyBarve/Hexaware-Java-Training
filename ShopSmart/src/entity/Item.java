package com.hexaware.entity;

import com.hexaware.exception.InvalidPriceException;
public class Item {
    private String itemName;
    private double price;
    private String category;

    // Constructor to initialize itemName, price, and category
    public Item(String itemName, double price, String category) throws InvalidPriceException {
    	if (price <= 0) {
            throw new InvalidPriceException("Price must be a positive value.");
        }
    	this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString() method to return a string representation of the item
    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
