package br.com.assignment.compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;


public class Compiler {
	
	private static final Logger log = Logger.getLogger(Compiler.class);

	public String compile(File javaFile) throws Exception {
		long startedTime = System.currentTimeMillis();
		log.setLevel(Level.INFO);
		SimpleLayout layout = new SimpleLayout();
		OutputStream logOutputStream = new ByteArrayOutputStream();
		WriterAppender appender = new WriterAppender(layout, logOutputStream);
		log.addAppender(appender);
		
		boolean compileResult = false;
		try {             
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  

			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);  
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, null);  

			int indexOfExtension = javaFile.getName().lastIndexOf(".");
			String className = javaFile.getName().substring(0, indexOfExtension);
			log.info("Compiling " + className + " ...");
			List<JavaFile> lista = Arrays.asList(new JavaFile(className, extractFileText(javaFile)));

			Writer outputStreamWriter = new OutputStreamWriter(logOutputStream);
			CompilationTask compilationTask = compiler.getTask(outputStreamWriter, fileManager, null, null, null, lista);
			compileResult = compilationTask.call();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		long endedTime = System.currentTimeMillis();
		double elapsedTimeInSeconds = (endedTime-startedTime)/1000.0;
		if(compileResult) {
			log.info("Successfull compiled in " + elapsedTimeInSeconds + " seconds");
		} else {
			log.info("Compile with errors in " + elapsedTimeInSeconds + " seconds");
		}

		return logOutputStream.toString();
	}

	class JavaFile extends SimpleJavaFileObject {  
		private String source;  

		public JavaFile(String classe, String source) throws URISyntaxException {  
			super(new URI(classe + ".java"), Kind.SOURCE);  
			this.source = source;  
		}  

		@Override  
		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {  
			return source;  
		}  
	}  

	public String extractFileText(File javaFile) throws IOException {

		@SuppressWarnings("resource")
		FileReader fileReader = new FileReader(javaFile);
		StringBuilder fileContents = new StringBuilder();
		int i ;
		while((i =  fileReader.read())!=-1){
			char ch = (char)i;
			fileContents.append(ch); 
		}

		return fileContents.toString();
	}
}
