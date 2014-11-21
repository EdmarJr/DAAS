/*
 * ConsultarDocumentosTO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.to;

import java.util.Date;

import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;

public class ConsultarDocumentosTO extends SaapTOAbstrato{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date	dataInicial;
	private Date	dataFinal;
	private Date	dataInicialConvite;
	private Date	dataFinalConvite;
	private String	horaInicialConvite;
	private String	horaFinalConvite;
	private Integer	idGestao;
	private SaapDocAdmPresidencia docEntidade;
	
	
	public SaapDocAdmPresidencia getDocEntidade() {
		if (docEntidade == null) {
			docEntidade = new SaapDocAdmPresidencia();
		}
		return docEntidade;
	}

	/**
	 * Atribui classificacao.
	 * 
	 * @param classificacao classificacao
	 */
	public void setDocEntidade(SaapDocAdmPresidencia docEntidade) {
		this.docEntidade = docEntidade;
	}

	
	
	public Integer getIdGestao() {
		return idGestao;
	}

	public void setIdGestao(Integer idGestao) {
		this.idGestao = idGestao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDataInicialConvite() {
		return dataInicialConvite;
	}
	public void setDataInicialConvite(Date dataInicialConvite) {
		this.dataInicialConvite = dataInicialConvite;
	}
	public Date getDataFinalConvite() {
		return dataFinalConvite;
	}
	public void setDataFinalConvite(Date dataFinalConvite) {
		this.dataFinalConvite = dataFinalConvite;
	}

	public String getHoraInicialConvite() {
		return horaInicialConvite;
	}

	public void setHoraInicialConvite(String horaInicialConvite) {
		this.horaInicialConvite = horaInicialConvite;
	}

	public String getHoraFinalConvite() {
		return horaFinalConvite;
	}

	public void setHoraFinalConvite(String horaFinalConvite) {
		this.horaFinalConvite = horaFinalConvite;
	}

	
	
	

}
