package JDBC_test.com.JDBC_test.DAOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

	private static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private static String driverName = "oracle.jdbc.driver.OracleDriver";   
	public static Connection connection;
	
	public static Connection createConnection(String username, char[] password) {
		
		try {
			
			Class.forName(driverName);
			
			connection = DriverManager.getConnection(url, username, String.valueOf(password));
		} 
		catch (ClassNotFoundException e) {

			System.out.println("Driver not found");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("Failed to create the database connection");
		}
		
		return connection;
	}
	
	public static boolean closeConnection() {
		
		try {
			connection.close();
			
			return true;
		} 
		catch (SQLException e) {
			
			System.out.println("Failed to close the database connection");
			
			return false;
		}
	}
	
}
