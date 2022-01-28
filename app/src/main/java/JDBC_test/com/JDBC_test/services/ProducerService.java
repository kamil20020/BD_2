package JDBC_test.com.JDBC_test.services;

import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_test.com.JDBC_test.DAOS.ProducerDAO;
import JDBC_test.com.JDBC_test.DAOS.ProductCategoryDAO;

public class ProducerService {

public static ArrayList<String> getAll() throws SQLException{
		
		return ProducerDAO.getAll();
	}
}
