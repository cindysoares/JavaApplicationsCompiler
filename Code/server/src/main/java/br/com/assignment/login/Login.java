package br.com.assignment.login;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/login")
public class Login {

	@GET()
	@Path("/{username}/{password}")
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		return "Login Sucessfull as " + username;
	}
	
}
