package br.com.assignment.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class ProjectCompilerTest {
	
	@Test
	public void testCorruptedZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-corrupted.zip").toURI()));
		String response = new ProjectCompiler().run(request);
		Assert.assertEquals(
				"INFO - Opening zip file ... \r\n"+
				"INFO - Extracting HelloWorld.java\r\n", response);
	}
	
	@Test
	public void testMavenProjectZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-maven-project.zip").toURI()));
		String response = new ProjectCompiler().run(request);
		Assert.assertEquals(
				"INFO - Opening zip file ... \r\n" +
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
