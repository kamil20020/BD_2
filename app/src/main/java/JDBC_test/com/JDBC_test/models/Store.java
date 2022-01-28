package JDBC_test.com.JDBC_test.models;

public class Store {
	
	public static Employee employee;
	
	public static void setEmployee(Employee employee1) {
		
		employee = employee1;
	}
	
	public static Employee getEmployee() {
		
		return employee;
	}
	
	public static void clearEmployee() {
		
		employee = null;
	}
}
