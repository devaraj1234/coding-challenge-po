package com.shopapi.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {

		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");

		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
}
