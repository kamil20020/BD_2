package JDBC_test.com.JDBC_test.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import JDBC_test.com.JDBC_test.DAOS.EmployeeDAO;
import JDBC_test.com.JDBC_test.DAOS.PhoneDAO;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Phone;

public class EmployeeService {

	public static Employee register(Employee employee) throws IllegalStateException, SQLException {
			
		EmployeeDAO.create(employee);
		
		Employee employee1 = EmployeeDAO.getByUsername(employee.getUsername());
		
		PhoneDAO.create(new Phone(employee1.getId(), employee.getPhoneNumber()));
		
		String phoneNumber = PhoneDAO.getByPersonId(employee1.getId()).getPhone_number();
		
		employee1.setPhoneNumber(phoneNumber);
		
		return employee1;
	}
	
	public static Employee login(String username, char[] password) throws SQLException {
		
		Optional<Employee> employee = Optional.ofNullable(EmployeeDAO.getByUsername(username));
		
		if(employee.isEmpty()) {
			
			return null;
		}
		
		if(Arrays.equals(employee.get().getPassword(), password)) {
			
			employee.get().setPhoneNumber(PhoneDAO.getByPersonId(employee.get().getId()).getPhone_number());
			
			return employee.get();
		}
		
		return null;
	}
	
	public static Employee update(Long id, Employee employee) throws SQLException {
		
		Optional<Employee> foundEmployee =  Optional.ofNullable(EmployeeDAO.getById(id));
		
		if(foundEmployee.isPresent()) {
			
			EmployeeDAO.updateById(id, employee);
			PhoneDAO.updateByPersonId(id, employee.getPhoneNumber());
			
			Employee employee1 = EmployeeDAO.getById(id);
			employee1.setPhoneNumber(PhoneDAO.getByPersonId(id).getPhone_number());
			
			return employee1;
		}
		
		return null;
	}
}
