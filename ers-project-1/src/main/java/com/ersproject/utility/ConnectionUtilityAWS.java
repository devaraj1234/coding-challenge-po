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
		String RDS_HOSTNAME = System.getenv("AWSDB_HOSTNAME");
		String RDS_USERNAME = "postgres";
		String RDS_PASSWORD = System.getenv("AWSDB_PASSWORD");
		String jdbcUrl = "jdbc:postgresql://" + RDS_HOSTNAME + ":" + 5432 + "/" +dbName 
				+"?user=" + RDS_USERNAME + "&password=" + RDS_PASSWORD;

		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(jdbcUrl);
		}
		return conn;
	}
}
