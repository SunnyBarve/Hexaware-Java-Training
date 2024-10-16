package com.hexaware.dao;

import com.hexaware.entity.Item;
import com.hexaware.exception.InvalidPriceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.util.DatabaseConnection;

public class ItemDAOImpl implements ItemDAO {
    
    private Connection connection;

    public ItemDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (itemName, price, category) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getItemName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getCategory());
            stmt.executeUpdate();
            System.out.println("Item added: " + item);
        }
    }

    @Override
    public void updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET price = ?, category = ? WHERE itemName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, item.getPrice());
            stmt.setString(2, item.getCategory());
            stmt.setString(3, item.getItemName());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Item updated: " + item);
            } else {
                System.out.println("Item not found: " + item.getItemName());
            }
        }
    }

    @Override
    public void deleteItem(String itemName) throws SQLException {
        String sql = "DELETE FROM items WHERE itemName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Item deleted: " + itemName);
            } else {
                System.out.println("Item not found: " + itemName);
            }
        }
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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
