package br.com.assignment.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class ProjectCompilerTest {
	
	public void testCorruptedZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-corrupted.zip").toURI()));
		String response = new ProjectCompiler().run(request);
		Assert.assertEquals("", response);
	}
	
	@Test
	public void testMavenProjectZip() throws Exception {
		InputStream request = new FileInputStream(
				new File(this.getClass().getResource("./HelloWorld-maven-project.zip").toURI()));
		new ProjectCompiler().run(request);
	}

}
