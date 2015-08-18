package br.com.assignment.login;


import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ITLogin {
	
	private static final String URL_LOGIN = "http://localhost:8080/Code/rest/login/admin/admin";
	
	@Test
	public void testSuccessfulLogin() {
		Client client = Client.create();
		WebResource r=client.resource(URL_LOGIN);
		String response = r.get(String.class);
		Assert.assertEquals("Successful login as admin", response);
	}

}
