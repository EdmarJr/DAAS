package br.jus.stj.saad.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static String obterNumeroComMascaraDeSeisCaracteres(String numero) {
		int length = numero.length();
		int nZeros = 6 - length;
		for (int i = 0; i < nZeros; i++) {
			numero = "0" + numero;
		}
		return numero;
	}
	
	
	public static boolean isEmpty(String a){
		if(null != a){
			a = a.trim();
		}
		
		if(null == a || "".equalsIgnoreCase(a)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean charIsEmpty(Character ch){
		if(ch != null){
			if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '0') {
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	public static boolean validaStringComRegex(String a, String regex){
		if(a == null || "".equalsIgnoreCase(a)){
			return false;
		}else{
			 Pattern p = Pattern.compile(regex);
			 Matcher m = p.matcher(a);
			 
			if(m.find()){
				return true;
			}
			return false;
		}
	}
	
	public static String normalizarString(String s){
		s = s.replaceAll("[^a-zA-Z0-9]+","");
		return s;
	}
}
