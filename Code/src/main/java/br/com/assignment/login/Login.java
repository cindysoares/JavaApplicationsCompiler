package br.com.assignment.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

@Path("/")
public class Login {

	@POST()
	@Path("/login")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response login(
			@FormDataParam("username") String username, 
			@FormDataParam("password") String password) {
		
		UserBO userBO = new UserBO();
		User user = userBO.find(username);
		if(user == null || !user.getPassword().equals(password)) {
			String message = "Failed login";
			return Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		
		String message = "Successful login as " + username;
		return Response.ok(message).build();
	}
	
}
