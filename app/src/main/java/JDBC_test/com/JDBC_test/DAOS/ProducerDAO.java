package JDBC_test.com.JDBC_test.DAOS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProducerDAO {

	public static ArrayList<String> getAll() throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT NAME FROM BD_2.PRODUCER");
		
		ResultSet results = statement.executeQuery();

		ArrayList<String> producers = getFromResults(results);
		
		results.close();
		statement.close();
		
		return producers;
	}

	private static ArrayList<String> getFromResults(ResultSet results) throws SQLException {
		
		ArrayList<String> producers = new ArrayList<>();
		
		while(results.next()) {
			
			producers.add(results.getString(1));
		}
		
		return producers;
	}
	
	private static String getOneFromResults(ResultSet results) throws SQLException {
		
		if(!results.next()) {
			
			return null;
		}
		
		return results.getString(1);
	}
	
	public static String getById(Long id) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT NAME FROM BD_2.PRODUCER WHERE ID=?");
		
		statement.setLong(1, id);
		
		ResultSet results = statement.executeQuery();

		String productCategory = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return productCategory;
	}
}
