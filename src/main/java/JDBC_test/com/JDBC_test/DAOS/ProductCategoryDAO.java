package JDBC_test.com.JDBC_test.DAOS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_test.com.JDBC_test.models.Employee;

public class ProductCategoryDAO {

	public static ArrayList<String> getAll() throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT NAME FROM BD_2.PRODUCT_CATEGORY");
		
		ResultSet results = statement.executeQuery();

		ArrayList<String> productCategories = getFromResults(results);
		
		results.close();
		statement.close();
		
		return productCategories;
	}

	private static ArrayList<String> getFromResults(ResultSet results) throws SQLException {
		
		ArrayList<String> productCategories = new ArrayList<>();
		
		while(results.next()) {
			
			productCategories.add(results.getString(1));
		}
		
		return productCategories;
	}
}
