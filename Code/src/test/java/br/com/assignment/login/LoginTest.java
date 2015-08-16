package br.com.assignment.login;

import org.junit.Test;

import junit.framework.Assert;

public class LoginTest {
	
	Login login = new Login();
	
	@Test
	public void testLoginAsAnExistingUser() {
		String result = login.login("admin", "admin");
		Assert.assertEquals("Successful login as admin", result);
	}

	@Test
	public void testLoginAsAnUnexistingUser() {
		String result = login.login("adm", "admin");
		Assert.assertEquals("Failed login", result);
	}
	
	@Test
	public void testLoginAsAnExistingUserAndWrongPassword() {
		String result = login.login("admin", "adm");
		Assert.assertEquals("Failed login", result);
	}

}
