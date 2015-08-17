package br.com.assignment.compiler;

import java.io.File;

import org.junit.Test;

import junit.framework.Assert;

public class CompilerTest {
	
	@Test
	public void testHelloWorld() {

		String result = "";

		try {
			result = new Compiler().compile(new File("../Code/src/test/resources/br/com/assignment/compiler/HelloWorld.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Assert.assertNotNull(result);
		// TODO continuar...
	}

}
