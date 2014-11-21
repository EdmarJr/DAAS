/*
 * SaapAdmtAdmPresidenciaTO.java
 * 
 * Data de criação: 08/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação das informações dos dados do andamento.
 * @author jesler.santos
 */
public class SaapAdmtAdmPresidenciaTO extends SaapTOAbstrato {

	private SaapAdmtAdmPresidencia saapAdmtAdmPresidencia;
	private String responsavel;

	/**
	 * Retorna saapAdmtAdmPresidencia.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getSaapAdmtAdmPresidencia() {
		if (saapAdmtAdmPresidencia == null) {
			saapAdmtAdmPresidencia = EntidadeFactory.getInstancia().novoSaapAdmtAdmPresidencia();
		}
		return saapAdmtAdmPresidencia;
	}

	/**
	 * Atribui saapAdmtAdmPresidencia.
	 * 
	 * @param saapAdmtAdmPresidencia saapAdmtAdmPresidencia
	 */
	public void setSaapAdmtAdmPresidencia(
			SaapAdmtAdmPresidencia saapAdmtAdmPresidencia) {
		this.saapAdmtAdmPresidencia = saapAdmtAdmPresidencia;
	}

	/**
	 * Retorna responsavel.
	 * 
	 * @return String
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * Atribui responsavel.
	 * 
	 * @param responsavel responsavel
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
}
