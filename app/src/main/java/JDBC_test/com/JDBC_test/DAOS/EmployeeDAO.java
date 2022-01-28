package JDBC_test.com.JDBC_test.DAOS;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC_test.com.JDBC_test.models.Employee;

public class EmployeeDAO {

	public static void create(Employee employee) throws SQLException, IllegalStateException {
		
		if(getByUsername(employee.getUsername()) != null) {
			
			throw new IllegalStateException("Istnieje już pracownik o takim loginie");
		}
		
		String query = "{call BD_2.INSERT_EMPLOYEE(?,?,?,?,?,?,?)}"; 
		CallableStatement statement;

		statement = ConnectionDAO.connection.prepareCall(query);
		
		fillStatement(statement, employee);
		statement.executeQuery(); 
		
		statement.close();
	}
	
	private static void fillStatement(PreparedStatement statement, Employee employee) throws SQLException {
		
		statement.setString(1, employee.getFirstname());  
		statement.setString(2, employee.getSurname());  
		statement.setString(3, employee.getE_mail());  
		statement.setString(4, employee.getUsername());  
		statement.setString(5, new String(employee.getPassword()));
		statement.setTimestamp(6, employee.getHiring_date());
		statement.setLong(7, employee.getEmployee_number());  
	}
	
	private static Employee getOneFromResults(ResultSet results) throws SQLException {
		
		if(!results.next()) {
			
			return null;
		}
		
		Employee employee = new Employee(
				results.getLong(1), results.getString("firstname"), results.getString("surname"),
				results.getString("e_mail"), results.getString("username"), results.getString("password").toCharArray(),
				results.getTimestamp("hiring_date"), results.getLong("employee_number"));
		
		return employee;
	}
	
	public static Employee getByUsername(String username) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT * FROM BD_2.PERSON WHERE username = ?");
		statement.setString(1, username);
		
		ResultSet results = statement.executeQuery();

		Employee employee = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return employee;
	}
	
	public static Employee getById(Long id) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT * FROM BD_2.PERSON WHERE id = ?");
		statement.setLong(1, id);
		
		ResultSet results = statement.executeQuery();
		
		Employee employee = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return employee;
	}
	
	public static boolean updateById(Long id, Employee employee) throws SQLException {
		
		if(id <= 0 || employee == null) {
			
			return false;
		}
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement(
				  "UPDATE BD_2.PERSON "
				+ "SET firstname=?, surname=?, e_mail=?, username=?, password=?, hiring_date=?, employee_number=? "
				+ "WHERE ID = ?");
		
		fillStatement(statement, employee);
		statement.setLong(8, id);  
		
		statement.executeUpdate();
		statement.close();
		
		return true;
	}
}
