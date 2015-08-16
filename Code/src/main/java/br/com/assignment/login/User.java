package br.com.assignment.login;

public class User {
	
	private String username;
	private String password;
	
	protected User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}

}
