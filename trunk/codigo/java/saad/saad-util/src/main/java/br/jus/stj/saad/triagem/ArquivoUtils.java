package br.jus.stj.saad.triagem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Iterator;
import java.util.Properties;

import br.jus.stj.saad.exception.PropertiesException;

public class ArquivoUtils {
	private static final String PROP_TRIAGEM="endereco_pasta_triagem";
	private static final String PROP_SAAD="endereco_pasta_saad";
	
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(ArquivoUtils.class.getResourceAsStream("/META-INF/saad.properties"));
		} catch (IOException e) {
			throw new PropertiesException("saad.properties não encontrado");
		}
	}
	
	public static String obterEnderecoPastaTriagem() {
		return prop.getProperty(PROP_TRIAGEM);
	}
	
	public static String obterEnderecoPastaSaad() {
		return prop.getProperty(PROP_SAAD);
	}
	
	public static String retornoAmigavel(String string){
		string = string.trim();
		string = string.replaceAll(" ", "_").toUpperCase();
		
		return string == null ? null
		        : Normalizer.normalize(string, Form.NFD)
		            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public static void criarCaminho(Path path) {
		Iterator<Path> iterator = path.iterator();
		Path next = Paths.get(path.getRoot().toString() + iterator.next().toString());
		while(iterator.hasNext()) {
			Path next2 = iterator.next();
			next = Paths.get(next.toString() + "/" + next2.toString());
			if(!next.toFile().exists() && next2.toString().indexOf(".") == -1) {
				try {
					Files.createDirectories(next);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
