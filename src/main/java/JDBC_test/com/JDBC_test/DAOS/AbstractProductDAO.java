package JDBC_test.com.JDBC_test.DAOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.services.ImageService;

public class AbstractProductDAO {

	public static void create(AbstractProduct product) throws SQLException, IllegalStateException {
		
		String query = "{call BD_2.INSERT_ABSTRACT_PRODUCT(?,?,?,?,?,?,?,?,?,?)}"; 
		CallableStatement statement;

		statement = ConnectionDAO.connection.prepareCall(query);
		
		fillStatement(statement, product);
		statement.executeQuery(); 
		
		statement.close();
	}
	
	private static void fillStatement(PreparedStatement statement, AbstractProduct product) throws SQLException {
		
		statement.setLong(1, product.getProduct_category_id()); 
		statement.setLong(2, product.getProducer_id()); 
		statement.setBytes(3, product.getImage());
		statement.setDouble(4, product.getPrice());
		statement.setString(5, product.getName());
		statement.setString(6, product.getDescription());
		statement.setDouble(7, product.getWeight());
		statement.setDouble(8, product.getHeight());
		statement.setDouble(9, product.getWidth());
		statement.setFloat(10, product.getTax_value());
	}

	private static AbstractProduct getOneFromResults(ResultSet results) throws SQLException {
		
		if(!results.next()) {
			
			return null;
		}
		
		AbstractProduct product = new AbstractProduct(
				results.getLong(1), results.getLong(2), results.getLong(3), results.getBytes(4), results.getDouble(5), results.getString(6),
				results.getString(7), results.getDouble(8), results.getDouble(9), results.getDouble(10), results.getFloat(11));
		
		return product;
	}
	
	public static ArrayList<AbstractProduct> getAll() throws SQLException{
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT * FROM BD_2.ABSTRACT_PRODUCT");
		
		ResultSet results = statement.executeQuery();

		ArrayList<AbstractProduct> products = getFromResults(results);
		
		results.close();
		statement.close();
		
		return products;
	}
	
	private static ArrayList<AbstractProduct> getFromResults(ResultSet results) throws SQLException {
		
		ArrayList<AbstractProduct> products = new ArrayList<>();
		
		while(results.next()) {
			
			AbstractProduct product = new AbstractProduct(
					results.getLong(1), results.getLong(2), results.getLong(3), results.getBytes(4), results.getDouble(5), results.getString(6),
					results.getString(7), results.getDouble(8), results.getDouble(9), results.getDouble(10), results.getFloat(11));
			
			products.add(product);
		}
		
		return products;
	}

	public static AbstractProduct getById(Long id) throws SQLException {
	
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT * FROM BD_2.ABSTRACT_PRODUCT WHERE ID = ?");
		statement.setLong(1, id);
		
		ResultSet results = statement.executeQuery();
	
		AbstractProduct product = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return product;
	}
	
	public static ArrayList<AbstractProduct> searchByCriteria(Long productCategoryId, String productName) throws SQLException {
		
		String query = "SELECT * FROM BD_2.ABSTRACT_PRODUCT WHERE PRODUCT_CATEGORY_ID = ?";
		
		if(!productName.isEmpty()) {
			
			query += " AND UPPER(name) LIKE ?";
		}
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement(query);
		
		statement.setLong(1, productCategoryId);
		
		if(!productName.isEmpty()) {
			
			statement.setString(2, productName.toUpperCase() + "%");
		}
		
		ResultSet results = statement.executeQuery();
	
		ArrayList<AbstractProduct> products = getFromResults(results);
		
		results.close();
		statement.close();
		
		return products;
	}
	
	public static AbstractProduct getLast() throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("select * from BD_2.ABSTRACT_PRODUCT ORDER BY ID DESC");
		
		ResultSet results = statement.executeQuery();
	
		AbstractProduct product = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return product;
	}
	
	public static boolean updateById(Long id, AbstractProduct product) throws SQLException {
		
		if(id <= 0 || product == null) {
			
			return false;
		}
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement(
				  "UPDATE BD_2.ABSTRACT_PRODUCT "
				+ "SET PRODUCT_CATEGORY_ID=?, PRODUCER_ID=?, IMAGE=?, PRICE=?, NAME=?, "
				+ "DESCRIPTION=?, WEIGHT=?, HEIGHT=?, WIDTH=?, TAX_VALUE=? "
				+ "WHERE ID = ?");
		
		fillStatement(statement, product);
		statement.setLong(11, id);  
		
		statement.executeUpdate();
		statement.close();
		
		return true;
	}
	
	public static boolean updateImageById(Long id, byte[] image) throws SQLException {
		
		if(id <= 0 || image == null) {
			
			return false;
		}
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement(
				  "UPDATE BD_2.ABSTRACT_PRODUCT SET image=? WHERE ID = ?");
	
		statement.setBytes(1, image);
		statement.setLong(2, id);
		
		statement.executeUpdate();
		statement.close();
		
		return true;
	}
	
	public static boolean deleteById(Long id) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("DELETE FROM BD_2.ABSTRACT_PRODUCT WHERE ID=?");
		statement.setLong(1, id);
		
		statement.executeUpdate();
		
		statement.close();
		
		return true;
	}
}
