package edu.northeastern.cs5200;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2019-wang.cnnyzemfweys.us-east-2.rds.amazonaws.com:3306/cs5200_jdbc"; 
	private static final String USER = "yiwei";
	private static final String PASSWORD = "wyw361580";
	private static java.sql.Connection dbConnection = null;
	
	// add static java.sql.Connection to fix error mismatch for type
	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException { 
		Class.forName(DRIVER);
	
	if (dbConnection == null) {
		dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
		return dbConnection;
	} 
	else { 
		return dbConnection; 
		 } 
	}
	
	public static void closeConnection() { 
		try {
			if (dbConnection != null) 
				{ dbConnection.close(); dbConnection = null; }
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} 
	}
}
