package br.com.assignment.compiler;

import java.io.File;
import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.Assert;

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
				"INFO - Compile with errors in"));
	}


}
