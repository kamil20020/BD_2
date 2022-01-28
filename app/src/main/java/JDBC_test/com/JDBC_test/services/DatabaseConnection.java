package JDBC_test.com.JDBC_test.services;

import JDBC_test.com.JDBC_test.DAOS.ConnectionDAO;
import JDBC_test.com.JDBC_test.DAOS.UsersInfoFileDAO;
import JDBC_test.com.JDBC_test.models.DBUser;
import JDBC_test.com.JDBC_test.models.DBUserType;

public class DatabaseConnection {
	
	public DatabaseConnection(DBUserType userType) {
		
		DBUser user = UsersInfoFileDAO.loadUsers().get(DBUserType.EMPLOYEE);
		
		ConnectionDAO.createConnection(user.getUsername(), user.getPassword());
	}
	
	@Override
	public void finalize() {
		
		ConnectionDAO.closeConnection();
	}
}
