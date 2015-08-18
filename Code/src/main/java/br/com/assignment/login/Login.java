package br.com.assignment.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.multipart.FormDataParam;

@Path("/")
public class Login {

	@POST()
	@Path("/login")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String login(
			@FormDataParam("username") String username, 
			@FormDataParam("password") String password) {
		
		UserBO userBO = new UserBO();
		User user = userBO.find(username);
		if(user == null || !user.getPassword().equals(password)) {
			return "Failed login";
		}
		return "Successful login as " + username;
	}
	
}
