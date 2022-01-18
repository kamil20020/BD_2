package JDBC_test.com.JDBC_test.DAOS;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Phone;

public class PhoneDAO {

	public static void create(Phone phone) throws SQLException, IllegalStateException {
		
		String query = "{call BD_2.INSERT_PHONE_NUMBER(?,?,?)}";
		CallableStatement statement;

		statement = ConnectionDAO.connection.prepareCall(query);
		
		fillStatement(statement, phone);
		statement.executeQuery(); 
		statement.close();
	}
	
	private static void fillStatement(PreparedStatement statement, Phone phone) throws SQLException {
		
		statement.setLong(1, phone.getPerson_id());  
		statement.setString(2, phone.getPhone_number());  
		statement.setLong(3, 1);  
	}
	
	private static Phone getOneFromResults(ResultSet results) throws SQLException {
		
		if(!results.next()) {
			
			return null;
		}
		
		Phone phone = new Phone(results.getLong(1), results.getLong(2), results.getString(3));
		
		return phone;
	}
	
	public static Phone getByPersonId(Long personId) throws SQLException {
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("SELECT * FROM BD_2.PHONE WHERE person_id = ?");
		statement.setLong(1, personId);
		
		ResultSet results = statement.executeQuery();
		
		Phone phone = getOneFromResults(results);
		
		results.close();
		statement.close();
		
		return phone;
	}
	
	public static boolean updateByPersonId(Long personId, String phoneNumber) throws SQLException {
		
		if(personId <= 0 || phoneNumber == null) {
			
			return false;
		}
		
		PreparedStatement statement = ConnectionDAO.connection.prepareStatement("UPDATE BD_2.PHONE SET phone_number=? WHERE PERSON_ID=?");
		
		statement.setString(1, phoneNumber);
		statement.setLong(2, personId);
		
		statement.executeUpdate();
		statement.close();
		
		return true;
	}
	
}
