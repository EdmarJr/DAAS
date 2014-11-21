package br.jus.stj.saad.identificador;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.jus.stj.saad.exception.IdentificadorInvalidoException;
import br.jus.stj.saad.string.StringUtils;

public class IdentificadorUtils {

	private static final String REGEX_IDENTIFADOR_DOC = "[a-zA-Z]{3}-\\d{6}/\\d{4}";

	public static String obterAno(String identificador) {
		String ano = identificador.substring(identificador.indexOf("/") + 1,
				identificador.length());
		return ano;
	}

	public static String obterIdentificacaoDocumentoSTJ(String identificador) {
		String identificacaoDocumentoSTJ = identificador.substring(
				identificador.indexOf("-") + 1, identificador.indexOf("/"));
		return identificacaoDocumentoSTJ;
	}

	public static String obterSigla(String identificador) {
		String sigla = identificador.substring(0, identificador.indexOf("-"))
				.toUpperCase();
		return sigla;
	}
	
	public static String obterIdentificadorDocumentoFormatado(String sigla, String idDocStj,
			String anoInclusao) {
		
		return sigla.toUpperCase()+ "-" +idDocStj + "/"+ anoInclusao;
		
	}
	
	public static String obterIdentificadorDocumentoFormatado(String sigla, String idDocStj,
			Date dataInclusao) {
		if(dataInclusao == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String anoInclusao = simpleDateFormat.format(dataInclusao);
		
		return obterIdentificadorDocumentoFormatado(sigla, idDocStj, anoInclusao);
		
	}
	
	public static String obterIdentificadorComMascaraPorDocumento(String siglaTipoDocumento, String numeroIdentificador, String ano) {
		String ultimoNumero = numeroIdentificador;
		return siglaTipoDocumento
				+ "-"
				+ StringUtils
						.obterNumeroComMascaraDeSeisCaracteres(ultimoNumero)
				+ "/" + ano;
	}
	
	public static void validarIdentificadorDocumento(String identificador)
			throws IdentificadorInvalidoException {

		if (identificador == null || identificador.equals(""))
			throw new IdentificadorInvalidoException();

		if (!StringUtils.validaStringComRegex(identificador,
				REGEX_IDENTIFADOR_DOC))
			throw new IdentificadorInvalidoException();

	}
	
}
