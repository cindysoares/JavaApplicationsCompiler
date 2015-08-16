package br.com.assignment.compiler;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/compiler")
public class ProjectCompiler {

	@POST
    @Path("/run")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String run(InputStream javaProject) {
		return Boolean.toString(javaProject!=null);
	}
	
}
