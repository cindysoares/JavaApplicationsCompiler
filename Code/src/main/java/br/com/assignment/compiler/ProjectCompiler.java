package br.com.assignment.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
		StringBuilder log = new StringBuilder();
		log.append("Opening zip file ... " );
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(javaProject);
			ZipEntry zipEntry;
			while((zipEntry = zipInputStream.getNextEntry())!= null) {
				log.append("\nExtracting " + zipEntry.getName());
				byte [] buf = new byte[(int) zipEntry.getSize()];
	            zipInputStream.read(buf);
			}
		} catch (IOException e) {
			log.append(e.getMessage());
		} finally {
			try {
				zipInputStream.close();
			} catch (IOException ignored) {
			}
		}
		return log.toString();
	}
	
}
