package com.hexaware.dao;


import com.hexaware.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    void addItem(Item item) throws SQLException;
    void updateItem(Item item) throws SQLException;
    void deleteItem(String itemName) throws SQLException;
    List<Item> getAllItems() throws SQLException;
}
