package com.hexaware.dao;

import com.hexaware.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
	  List<Order> getOrderHistory() throws SQLException;
    void placeOrder(Order order) throws SQLException;
}
