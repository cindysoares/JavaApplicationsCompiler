package br.com.assignment.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sun.jersey.core.header.FormDataContentDisposition;

import static org.easymock.EasyMock.*; 

@RunWith(EasyMockRunner.class) 
public class ProjectCompilerTest {
	
	@Mock
	FormDataContentDisposition contentDisposition;
	
	@Before
	public void setUp() {
		expect(contentDisposition.getFileName()).andReturn("nomeArquivo.zip");
		replay(contentDisposition);
	}
	
	@After
	public void tearDown() {
		verify(contentDisposition);
	}
	
	@Test
	public void testCorruptedZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-corrupted.zip").toURI()));
		String response = new ProjectCompiler().run(request, contentDisposition);
		Assert.assertEquals(
				"INFO - Opening zip file nomeArquivo.zip ...\r\n"+
				"INFO - Extracting HelloWorld.java\r\n", response);
	}
	
	@Test
	public void testMavenProjectZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-maven-project.zip").toURI()));
		String response = new ProjectCompiler().run(request, contentDisposition);
		Assert.assertEquals(
				"INFO - Opening zip file nomeArquivo.zip ...\r\n" +
				"INFO - Extracting pom.xml\r\n" +
				"INFO - Extracting src/\r\n" +
				"INFO - Extracting src/main/\r\n" +
				"INFO - Extracting src/main/java/\r\n" +
				"INFO - Extracting src/main/java/helloworld/\r\n" +
				"INFO - Extracting src/main/java/helloworld/HelloWorld.java\r\n" +
				"INFO - Extracting src/main/resources/\r\n" +
				"INFO - Extracting src/test/\r\n" +
				"INFO - Extracting src/test/java/\r\n" +
				"INFO - Extracting src/test/java/helloworld/\r\n" +
				"INFO - Extracting src/test/java/helloworld/HelloWorldTest.java\r\n" +
				"INFO - Extracting src/test/resources/\r\n", response);
	}

}
