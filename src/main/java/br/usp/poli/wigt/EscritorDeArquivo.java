package br.usp.poli.wigt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorDeArquivo {
	
    public static void escreveArquivoTexto(String path, List<String> linhas){
    	try {
	      FileWriter writer = new FileWriter(path);
	      for (String linha : linhas) {
	    	  writer.write(linha);
	      }
	      writer.close();
	      System.out.println("Successfully wrote entries to the file: " + path);
	    } catch (IOException e) {
	      System.out.println("An error occurred while writing to file: " + path);
	      e.printStackTrace();
	    }
    }
    
}
