package br.com.assignment.login;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.multipart.FormDataParam;

@Path("/")
public class Login {

	private static final Logger log = Logger.getLogger(Login.class);
	
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
		
		//String message = "Successful login as " + username;
		try {
			return Response.temporaryRedirect(new URI("../uploadFile.html")).build();
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
	
}
