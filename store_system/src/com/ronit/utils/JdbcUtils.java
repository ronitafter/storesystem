package com.ronit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
	
	public static String JdbcUrl = "jdbc:mysql://localhost:3306/store";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JdbcUrl, "root", "wNp2Y2");

	}

}
