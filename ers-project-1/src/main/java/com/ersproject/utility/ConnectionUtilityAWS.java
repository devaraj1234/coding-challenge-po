package com.ersproject.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtilityAWS {
	
	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {
		
		      try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		      
		String dbName = "postgres";
		String RDS_HOSTNAME = "ersdatabase.crfu47uosrzt.us-east-2.rds.amazonaws.com";
		String RDS_USERNAME = "postgres";
		String password = "password";
		String jdbcUrl = "jdbc:postgresql://" + RDS_HOSTNAME + ":" + 5432 + "/" +dbName 
				+"?user=" + RDS_USERNAME + "&password=" + password;

		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(jdbcUrl);
		}
		return conn;
	}

}
