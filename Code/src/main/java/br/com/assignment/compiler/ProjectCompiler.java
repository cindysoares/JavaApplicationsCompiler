package br.com.assignment.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/compiler")
public class ProjectCompiler {
	
	private static final Logger log = Logger.getLogger(ProjectCompiler.class);

	@POST
    @Path("/run")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response run( @FormDataParam("zipFile") InputStream javaProject,
			@FormDataParam("zipFile") FormDataContentDisposition contentDisposition) {

		log.setLevel(Level.INFO);
		SimpleLayout layout = new SimpleLayout();
		OutputStream logOutputStream = new ByteArrayOutputStream();
		WriterAppender appender = new WriterAppender(layout, logOutputStream);
		log.addAppender(appender);
		
		log.info("Opening zip file " + contentDisposition.getFileName() + " ...<br>");
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(javaProject);
			ZipEntry zipEntry;
			while((zipEntry = zipInputStream.getNextEntry())!= null) {
				log.info("Extracting " + zipEntry.getName() + "<br>");
				byte [] buf = new byte[(int) zipEntry.getSize()];
	            zipInputStream.read(buf);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				zipInputStream.close();
			} catch (IOException ignored) {
			}
		}
		return Response.ok(logOutputStream.toString()).build();
	}
	
}
