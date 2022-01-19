package JDBC_test.com.JDBC_test.services;

import java.sql.SQLException;

import JDBC_test.com.JDBC_test.DAOS.AbstractProductDAO;
import JDBC_test.com.JDBC_test.DAOS.EmployeeDAO;
import JDBC_test.com.JDBC_test.DAOS.PhoneDAO;
import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Phone;

public class AbstractProductService {

	public static AbstractProduct create(AbstractProduct product) throws IllegalStateException, SQLException {
		
		AbstractProductDAO.create(product);
		
		AbstractProduct product1 = AbstractProductDAO.getLast();
		
		return product1;
	}
}
