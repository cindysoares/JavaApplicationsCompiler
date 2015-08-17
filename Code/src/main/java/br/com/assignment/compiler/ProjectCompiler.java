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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;

@Path("/compiler")
public class ProjectCompiler {
	
	private static final Logger log = Logger.getLogger(ProjectCompiler.class);

	@POST
    @Path("/run")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String run(InputStream javaProject) {
		log.setLevel(Level.INFO);
		SimpleLayout layout = new SimpleLayout();
		OutputStream logOutputStream = new ByteArrayOutputStream();
		WriterAppender appender = new WriterAppender(layout, logOutputStream);
		log.addAppender(appender);
		
		log.info("Opening zip file ... " );
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(javaProject);
			ZipEntry zipEntry;
			while((zipEntry = zipInputStream.getNextEntry())!= null) {
				log.info("Extracting " + zipEntry.getName());
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
		return logOutputStream.toString();
	}
	
}
