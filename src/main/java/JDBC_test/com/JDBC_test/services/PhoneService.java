package JDBC_test.com.JDBC_test.services;

import java.sql.SQLException;
import java.util.Optional;

import JDBC_test.com.JDBC_test.DAOS.EmployeeDAO;
import JDBC_test.com.JDBC_test.DAOS.PhoneDAO;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Phone;

public class PhoneService {

	public static Phone create(Phone phone) throws IllegalStateException, SQLException {
		
		PhoneDAO.create(phone);
		
		return PhoneDAO.getByPersonId(phone.getPerson_id());
	}
	
	public static Phone update(Phone phone) throws SQLException {
		
		Optional<Phone> foundPhone =  Optional.ofNullable(PhoneDAO.getByPersonId(phone.getPerson_id()));
		
		if(foundPhone.isPresent()) {
			
			PhoneDAO.updateByPersonId(phone.getPerson_id(), phone.getPhone_number());
			
			return PhoneDAO.getByPersonId(phone.getPerson_id());
		}
		
		return null;
	}
}
