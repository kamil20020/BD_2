package JDBC_test.com.JDBC_test.models;

import java.sql.Timestamp;
import java.util.Arrays;

public class Employee extends Person implements Cloneable{
	
	private Timestamp hiring_date;
	private Long employee_number;

	public Employee(Long id, String firstname, String surname, String e_mail,
					String username, char[] password, Timestamp hiring_date, Long employee_number) {
		
		super(id, firstname, surname, e_mail, username, password);
		
		this.hiring_date = hiring_date;
		this.employee_number = employee_number;
	}
	
	public Employee(Long id, String firstname, String surname, String e_mail,
			String username, char[] password, Timestamp hiring_date, Long employee_number, String phoneNumber) {

		super(id, firstname, surname, e_mail, username, password, phoneNumber);
		
		this.hiring_date = hiring_date;
		this.employee_number = employee_number;
	}
	
	public Employee(String firstname, String surname, String e_mail,
			String username, char[] password, Timestamp hiring_date, Long employee_number, String phoneNumber) {

		super(firstname, surname, e_mail, username, password, phoneNumber);
		
		this.hiring_date = hiring_date;
		this.employee_number = employee_number;
	}

	public Timestamp getHiring_date() {
		return hiring_date;
	}

	public void setHiring_date(Timestamp hiring_date) {
		this.hiring_date = hiring_date;
	}

	public Long getEmployee_number() {
		return employee_number;
	}

	public void setEmployee_number(Long employee_number) {
		this.employee_number = employee_number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employee_number == null) ? 0 : employee_number.hashCode());
		result = prime * result + ((hiring_date == null) ? 0 : hiring_date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employee_number == null) {
			if (other.employee_number != null)
				return false;
		} else if (!employee_number.equals(other.employee_number))
			return false;
		if (hiring_date == null) {
			if (other.hiring_date != null)
				return false;
		} else if (!hiring_date.equals(other.hiring_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [hiring_date=" + hiring_date + ", employee_number=" + employee_number + ", getId()=" + getId()
				+ ", getFirstname()=" + getFirstname() + ", getSurname()=" + getSurname() + ", getE_mail()="
				+ getE_mail() + ", getUsername()=" + getUsername() + ", getPassword()=" + Arrays.toString(getPassword())
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	
}
