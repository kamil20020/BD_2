package JDBC_test.com.JDBC_test.services;

import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_test.com.JDBC_test.DAOS.ProductCategoryDAO;

public class ProductCategoryService {

	public static ArrayList<String> getAll() throws SQLException{
		
		return ProductCategoryDAO.getAll();
	}
}
