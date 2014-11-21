/*
 * ListarPendenciasTO.java
 * 
 * Data de criação: 01/06/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import java.util.Date;

/**
 * Responsável pela representação das informações da lista de pendência.
 * @author jesler.santos
 */
public class ListarPendenciasTO extends SaapTOAbstrato {

	private String assunto;
	private String enderecado;
	private String acompanhamento;
	private Date tsEntradaPendencia;
	private String registradoPor;
	private String descEnderecoPendencia;
	private String situacao;

	/**
	 * Retorna assunto.
	 * 
	 * @return String
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * Atribui assunto.
	 * 
	 * @param assunto assunto
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * Retorna enderecado.
	 * 
	 * @return String
	 */
	public String getEnderecado() {
		return enderecado;
	}

	/**
	 * Atribui enderecado.
	 * 
	 * @param enderecado enderecado
	 */
	public void setEnderecado(String enderecado) {
		this.enderecado = enderecado;
	}

	/**
	 * Retorna acompanhamento.
	 * 
	 * @return String
	 */
	public String getAcompanhamento() {
		return acompanhamento;
	}

	/**
	 * Atribui acompanhamento.
	 * 
	 * @param acompanhamento acompanhamento
	 */
	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	
	/**
	 * Retorna tsEntradaPendencia.
	 * 
	 * @return Date
	 */
	public Date getTsEntradaPendencia() {
		return tsEntradaPendencia;
	}

	
	/**
	 * Atribui tsEntradaPendencia.
	 * 
	 * @param tsEntradaPendencia tsEntradaPendencia
	 */
	public void setTsEntradaPendencia(Date tsEntradaPendencia) {
		this.tsEntradaPendencia = tsEntradaPendencia;
	}

	
	/**
	 * Retorna registradoPor.
	 * 
	 * @return String
	 */
	public String getRegistradoPor() {
		return registradoPor;
	}

	
	/**
	 * Atribui registradoPor.
	 * 
	 * @param registradoPor registradoPor
	 */
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

	
	/**
	 * Retorna descEnderecoPendencia.
	 * 
	 * @return String
	 */
	public String getDescEnderecoPendencia() {
		return descEnderecoPendencia;
	}

	
	/**
	 * Atribui descEnderecoPendencia.
	 * 
	 * @param descEnderecoPendencia descEnderecoPendencia
	 */
	public void setDescEnderecoPendencia(String descEnderecoPendencia) {
		this.descEnderecoPendencia = descEnderecoPendencia;
	}

	
	/**
	 * Retorna situacao.
	 * 
	 * @return String
	 */
	public String getSituacao() {
		return situacao;
	}

	
	/**
	 * Atribui situacao.
	 * 
	 * @param situacao situacao
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	
	
}
