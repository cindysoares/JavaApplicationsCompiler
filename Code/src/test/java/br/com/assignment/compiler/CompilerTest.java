package br.com.assignment.compiler;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class CompilerTest {
	
	@Test
	public void testHelloWorld() throws Exception {

		String result = new Compiler().compile(
				new File(this.getClass().getResource("./HelloWorld.txt").toURI()));
		Assert.assertTrue("\n" + result, result.startsWith(
			"INFO - Compiling HelloWorld ...\r\n" +
			"INFO - Successfull compiled in"));
	}
	
	@Test
	public void testHelloWorldCompilingWithErrors() throws Exception {
		String result = new Compiler().compile(
				new File(this.getClass().getResource("./HelloWorldWithErrors.txt").toURI()));
		Assert.assertTrue("\n" + result, result.startsWith(
				"INFO - Compiling HelloWorldWithErrors ...\r\n" +
				"HelloWorldWithErrors.java:1: error: class HelloWorld is public, should be declared in a file named HelloWorld.java\r\n" +
				"public class HelloWorld {\r\n" +
				"       ^\r\n" +
				"1 error\r\n" +
				"INFO - Compile with errors in"));
	}


}
