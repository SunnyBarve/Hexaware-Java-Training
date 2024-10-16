package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static DatabaseConnection instance;
	private static Connection connection;
	
	
	
	private static final String url = "jdbc:mysql://localhost:3306/mydatabase";
	private static final String username = "root";
	private static final String password = "root04";
	
	private DatabaseConnection()
	{
		
		try
		{
			
			connection =  DriverManager.getConnection(url,username,password);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static synchronized DatabaseConnection  getInstance()
	{
		
		if(instance == null)
		{
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public Connection getConnection()
	{
		return connection;
	}
}
