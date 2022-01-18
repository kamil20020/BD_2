package JDBC_test.com.JDBC_test.models;

public class DBUser {

	public DBUserType type;
	private String username;
	private char[] password;
	
	public DBUser(DBUserType type, String username, char[] password) {

		this.type = type;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}
	
}
