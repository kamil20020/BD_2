package JDBC_test.com.JDBC_test.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Person{

	private Long id;
	private String firstname;
	private String surname;
	private String e_mail;
	private String username;
	private char[] password;
	private String phoneNumber;
	
	public Person(Long id, String firstname, String surname, String e_mail, String username, char[] password, String phoneNumber) {
		
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.e_mail = e_mail;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public Person(Long id, String firstname, String surname, String e_mail, String username, char[] password) {
		
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.e_mail = e_mail;
		this.username = username;
		this.password = password;
	}
	
	public Person(String firstname, String surname, String e_mail, String username, char[] password, String phoneNumber) {
		
		this.firstname = firstname;
		this.surname = surname;
		this.e_mail = e_mail;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getE_mail() {
		return e_mail;
	}
	
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		
		this.phoneNumber = phoneNumber;
	}
	
	public static char[] convertPassword(String password) {
		
		char[] pass = new char[255];
		
		for(int i=0; i < password.length(); i++) {
			
			pass[i] = password.charAt(i);
		}
		
		for(int i = password.length(); i < 255; i++) {
			
			pass[i] = ' ';
		}
		
		return pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e_mail == null) ? 0 : e_mail.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (e_mail == null) {
			if (other.e_mail != null)
				return false;
		} else if (!e_mail.equals(other.e_mail))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(password, other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
