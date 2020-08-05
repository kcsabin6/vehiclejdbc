package com.servlet.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
	
	public static Connection getConnection() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_db","root","pelehuku");
		  return conn;
		
	}
	
	

}
