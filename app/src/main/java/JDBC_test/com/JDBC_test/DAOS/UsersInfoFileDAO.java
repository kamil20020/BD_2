package JDBC_test.com.JDBC_test.DAOS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import JDBC_test.com.JDBC_test.models.DBUser;
import JDBC_test.com.JDBC_test.models.DBUserType;

public class UsersInfoFileDAO {
	
	public static Map<DBUserType, DBUser> loadUsers() {
		
		Map <DBUserType, DBUser> users = new HashMap<>();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream("Users.txt");
				
		InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		
		try {
			
			for (String line; (line = reader.readLine()) != null;) {
				
			    String[] words = line.split(";");
			    
			    DBUser newUser = new DBUser(DBUserType.valueOf(words[0]), words[1], words[2].toCharArray());
			    
			    users.put(DBUserType.valueOf(words[0]), newUser);
			}
		} 
		catch (IOException e) {
			
			System.out.println("Users file is invalid");
			
			return null;
		}
		
		return users;
	}
}
