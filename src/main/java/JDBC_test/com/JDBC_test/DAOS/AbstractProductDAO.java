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
		statement.setDouble(9, product.getHeight());
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
		
		for(AbstractProduct p : products) {
			
			PrintWriter writer = null;
			try {
				writer = new PrintWriter("the-file-name.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.println(p.getImage());
			writer.close();
		}
		
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
	
	public static AbstractProduct getLast() throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("select * from BD_2.ABSTRACT_PRODUCT ORDER BY ID DESC");
		
		ResultSet results = statement.executeQuery();
	
		AbstractProduct product = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return product;
	}
	
	public static boolean deleteById(Long id) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("DELETE FROM BD_2.ABSTRACT_PRODUCT WHERE ID=?");
		statement.setLong(1, id);
		
		statement.executeUpdate();
		
		statement.close();
		
		return true;
	}
}
