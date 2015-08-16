package br.com.assignment.compiler;

import java.io.File;

import junit.framework.Assert;

public class CompilerTest {
	
	public void testHelloWorld() {
		String result = new Compiler().compile(new File("HelloWorld.txt"));
		
		Assert.assertNotNull(result);
		// TODO continuar...
	}

}
