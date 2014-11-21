package br.jus.stj.saad.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {
	public static List<String> obterListaNomeArquivosPorCaminho(String directory) {
		File folder = new File(directory);
        List<String> fileNames = new ArrayList<>();
        
        if(folder.exists()){        
	        for (File file : folder.listFiles()) {
	        	fileNames.add(file.getName());
			}
        }
        
		return fileNames;
	}
	
	public static List<String> obterListaNomeArquivosPorCaminhoTeste(String directory) throws Exception{
		File folder = new File(directory);
        List<String> fileNames = new ArrayList<>();
        
        if(!folder.exists()){
        	throw new Exception("Diretorio utilizado: "+ directory + "\n Pasta não existe \n" + folder.toString());
        }else{
        	if(folder.listFiles() == null){
        		throw new Exception("Diretorio utilizado: "+ directory + "\n Pasta sem arquivos \n" + folder.toString());
        	}else{
        		for (File file : folder.listFiles()) {
    	        	fileNames.add(file.getName());
    			}
        	}
        }
        
		return fileNames;
	}
	
	public static InputStream obterInputStreamPorCaminho(String caminhoArquivo) {
		InputStream is = null;
		try {
			is = new FileInputStream(caminhoArquivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;
	}
}
