package com.hexaware.entity;

import java.util.List;

public class Order {
    private String orderID;
    private List<Item> itemList;
    private double totalPrice;

    // Constructor to initialize orderID, itemList, and totalPrice
    public Order(String orderID, List<Item> itemList,double totalPrice) {
        this.orderID = orderID;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        this.totalPrice = calculateTotalPrice(); // Recalculate the total price when items change
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Method to calculate total price of all items in the list
    public double calculateTotalPrice() {
        double sum = 0;
        for (Item item : itemList) {
            sum += item.getPrice();
        }
        return sum;
    }

    // toString() method to return a string representation of the order
    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", itemList=" + itemList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
