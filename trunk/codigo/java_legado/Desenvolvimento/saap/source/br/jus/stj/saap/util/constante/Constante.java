/*
 * Constante.java
 * 
 * Data de criação: 10/01/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.util.constante;

/**
 * Responsável pela Felippe
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
	 * Tipo de Documento Ofício
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
	 * Prefixo do diretório
	 */
	public static final String PREFIXO_DIRETORIO = "SAAP-DOC-";

	/**
	 * Tipo de Andamento Documento
	 */
	public static final char TIPO_ANDAMENTO_DOCUMENTO = 'D';

	/**
	 * Tipo de Andamento Pendência
	 */
	public static final char TIPO_ANDAMENTO_PENDENCIA = 'P';

	/**
	 * Tipo de Andamento Inclusão
	 */
	public static final String OPERACAO_INCLUSAO = "Inclusão";

	/**
	 * Operação de Alteração
	 */
	public static final String OPERACAO_ALTERACAO = "Alteração";
	
	/**
	 * Operação de Desativação
	 */
	public static final String OPERACAO_DESATIVACAO = "Desativação";

	/**
	 * Limite de Registros da Popup
	 */
	public static final Integer LIMITE_REGISTROS_POPUP = 5;

	/**
	 * Limite de Registros 
	 */
	public static final Integer LIMITE_REGISTROS = 25;

	/**
	 * Pendência Ativa 
	 */
	public static final char PENDENCIA_ATIVA = 'A';

	/**
	 * Pendência desativada 
	 */
	public static final char PENDENCIA_DESATIVADA = 'D';

	/**
	 * TODOS
	 */
	public static final char TODOS = 'T';
}
