/*
 * ListarPendenciasTO.java
 * 
 * Data de cria��o: 04/08/2009
 *
 * Desenvolvido por Politec Ltda.
 * F�brica de Software - Bras�lia
 */
package br.jus.stj.saap.to;

/**
 * Respons�vel pela representa��o das informa��es da lista de pend�ncia.
 * @author jesler.santos
 */
public class ListarAndamentosTO extends SaapTOAbstrato {

	private String descAdmtAdm;
	private java.util.Date tsEntradaAdmt;
	private String responsavel;
	
	/**
	 * Retorna descAdmtAdm.
	 * 
	 * @return String
	 */
	public String getDescAdmtAdm() {
		return descAdmtAdm;
	}
	
	/**
	 * Atribui descAdmtAdm.
	 * 
	 * @param descAdmtAdm descAdmtAdm
	 */
	public void setDescAdmtAdm(String descAdmtAdm) {
		this.descAdmtAdm = descAdmtAdm;
	}
	
	/**
	 * Retorna tsEntradaAdmt.
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getTsEntradaAdmt() {
		return tsEntradaAdmt;
	}
	
	/**
	 * Atribui tsEntradaAdmt.
	 * 
	 * @param tsEntradaAdmt tsEntradaAdmt
	 */
	public void setTsEntradaAdmt(java.util.Date tsEntradaAdmt) {
		this.tsEntradaAdmt = tsEntradaAdmt;
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
