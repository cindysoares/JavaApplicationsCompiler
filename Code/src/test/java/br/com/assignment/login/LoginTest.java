package br.com.assignment.login;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;


public class LoginTest {
	
	Login login = new Login();
	
	@Test
	public void testLoginAsAnExistingUser() throws Exception {
		Response result = login.login("admin", "admin");
		Assert.assertNotNull(result);
		Assert.assertEquals(Response.Status.TEMPORARY_REDIRECT.getStatusCode(), result.getStatus());
	}

	@Test
	public void testLoginAsAnUnexistingUser() throws Exception {
		Response result = login.login("adm", "admin");
		Assert.assertNotNull(result);
		Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), result.getStatus());		
		Assert.assertEquals("Failed login", result.getEntity());
	}
	
	@Test
	public void testLoginAsAnExistingUserAndWrongPassword() throws Exception {
		Response result = login.login("admin", "adm");
		Assert.assertNotNull(result);
		Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), result.getStatus());		
		Assert.assertEquals("Failed login", result.getEntity());
	}

}
