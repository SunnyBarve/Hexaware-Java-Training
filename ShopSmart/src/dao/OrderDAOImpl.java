package com.hexaware.dao;

import com.hexaware.entity.Order;
import com.hexaware.exception.InvalidPriceException;
import com.hexaware.entity.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.util.DatabaseConnection;

public class OrderDAOImpl implements OrderDAO {
    
    private Connection connection;

    public OrderDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Order> getOrderHistory() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders"; // Query to get all orders
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String orderId = rs.getString("orderID");
                double totalPrice = rs.getDouble("totalPrice");
                List<Item> items = getItemsForOrder(orderId);
                orders.add(new Order(orderId, items, totalPrice));
            }
        }
        return orders;
    }
   
    @Override
    public void placeOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (orderID,totalPrice) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, order.getOrderID());
            stmt.setDouble(2, order.calculateTotalPrice()); // Calculate total price of items in the order
            stmt.executeUpdate();
            System.out.println("Order placed: " + order.getOrderID());
        }

        
    }
    private List<Item> getItemsForOrder(String orderId) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE orderID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String itemName = rs.getString("itemName");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                try {
					items.add(new Item(itemName, price, category));
				} catch (InvalidPriceException e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid price for item: " + itemName + ". Error: " + e.getMessage());
			        
				}
            }
        }
        return items;
    }
}
