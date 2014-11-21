/*
 * Constante.java
 * 
 * Data de cria��o: 10/01/2009
 *
 * Desenvolvido por Politec Ltda.
 * F�brica de Software - Bras�lia
 */
package br.jus.stj.saap.util.constante;

/**
 * Respons�vel pela Felippe
 * @author Politec Tecnologia
 */
public final class Constante {

	private Constante() {
		// Construtor
	}

	/**
	 * Tipo de Documento Aviso
	 */
	public static final int TIPO_DOCUMENTO_AVISO = 1;

	/**
	 * Tipo de Documento Carta
	 */
	public static final int TIPO_DOCUMENTO_CARTA = 2;

	/**
	 * Tipo de Documento Convite
	 */
	public static final int TIPO_DOCUMENTO_CONVITE = 3;

	/**
	 * Tipo de Documento Email
	 */
	public static final int TIPO_DOCUMENTO_EMAIL = 4;

	/**
	 * Tipo de Documento Memorando
	 */
	public static final int TIPO_DOCUMENTO_MEMORANDO = 5;
	
	/**
	 * Tipo de Documento Of�cio
	 */
	public static final int TIPO_DOCUMENTO_OFICIO = 6;

	/**
	 * Tipo de Documento Processo
	 */
	public static final int TIPO_DOCUMENTO_PROCESSO = 7;

	/**
	 * Documento Ativo
	 */
	public static final char DOCUMENTO_ATIVO = 'A';

	/**
	 * Documento Inativo
	 */
	public static final char DOCUMENTO_INATIVO = 'D';

	/**
	 * Prefixo do diret�rio
	 */
	public static final String PREFIXO_DIRETORIO = "SAAP-DOC-";

	/**
	 * Tipo de Andamento Documento
	 */
	public static final char TIPO_ANDAMENTO_DOCUMENTO = 'D';

	/**
	 * Tipo de Andamento Pend�ncia
	 */
	public static final char TIPO_ANDAMENTO_PENDENCIA = 'P';

	/**
	 * Tipo de Andamento Inclus�o
	 */
	public static final String OPERACAO_INCLUSAO = "Inclus�o";

	/**
	 * Opera��o de Altera��o
	 */
	public static final String OPERACAO_ALTERACAO = "Altera��o";
	
	/**
	 * Opera��o de Desativa��o
	 */
	public static final String OPERACAO_DESATIVACAO = "Desativa��o";

	/**
	 * Limite de Registros da Popup
	 */
	public static final Integer LIMITE_REGISTROS_POPUP = 5;

	/**
	 * Limite de Registros 
	 */
	public static final Integer LIMITE_REGISTROS = 25;

	/**
	 * Pend�ncia Ativa 
	 */
	public static final char PENDENCIA_ATIVA = 'A';

	/**
	 * Pend�ncia desativada 
	 */
	public static final char PENDENCIA_DESATIVADA = 'D';

	/**
	 * TODOS
	 */
	public static final char TODOS = 'T';
}
