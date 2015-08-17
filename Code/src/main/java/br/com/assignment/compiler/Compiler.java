package br.com.assignment.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;


public class Compiler {
	
	public String compile(File javaFile) throws Exception {

        try {             
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
  
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);  
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File("../Code/build/tmp/compileJava/")));  
  
  
            List<JavaFile> lista = Arrays.asList(new JavaFile("HelloWorld", montadoClasse(javaFile)));  
  
            compiler.getTask(null, fileManager, null, null, null, lista).call();  

        } catch (Exception e) {  
            e.printStackTrace();  
        }

		return "rodou";
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
    
    public String montadoClasse(File javaFile) throws IOException {

	FileReader fileReader = new FileReader(javaFile);

     String fileContents = "";
    
     int i ;
    
     while((i =  fileReader.read())!=-1){
      char ch = (char)i;
    
      fileContents = fileContents + ch; 
     }
    
     return fileContents;
    }
}
