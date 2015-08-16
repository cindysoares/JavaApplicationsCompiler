package br.com.assignment.login;

import java.util.HashMap;
import java.util.Map;

public class UserBO {
	
	private static Map<String, User> users = new HashMap<String, User>();
	static {
		User adminUser = new User("admin", "admin");
		users.put(adminUser.getUsername(), adminUser);
	}
	
	public User find(String username) {
		return users.get(username);
	}

}
