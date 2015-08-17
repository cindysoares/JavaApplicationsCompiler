package br.com.assignment.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ITProjectCompiler {
	
	private static final String URL_COMPILER = "http://localhost:8080/Code/rest/compiler/run";

	@Test
	public void testSendingAZipFile() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld.zip").toURI()));

		Client client = Client.create();
		WebResource r=client.resource(URL_COMPILER);
		String response = r.type(MediaType.MULTIPART_FORM_DATA).post(String.class, request);
		Assert.assertTrue("\n" + response, response.startsWith("INFO - Opening zip file"));
	}

}
