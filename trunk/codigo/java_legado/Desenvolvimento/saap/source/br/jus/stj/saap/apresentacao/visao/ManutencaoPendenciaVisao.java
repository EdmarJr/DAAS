/*
 * ManutencaoPendenciaVisao.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.visao;


import org.richfaces.component.html.HtmlDataTable;

import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;

import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.PendenciaDetalhadaTO;


/**
 * Responsável pela representação da visão do caso de uso Manter Pendência.
 * @author Politec Informática
 */
public class ManutencaoPendenciaVisao extends ManutencaoVisao<SaapPendenciaAdmPresidencia>{

	private ConsultarPendenciasTO consultarPendenciasTO;
	private PendenciaDetalhadaTO pendenciaDetalhadaTO;
    private HtmlDataTable dataTableAndamentosDetalhe;
    private HtmlDataTable dataTableAndamentosAlterar;
    private String descAndamento;
	private Integer paginaVoltar;
	private String classBtnImprimir;
	private boolean disabledBtnImprimir;
	private Integer paginaDetalhe;
	private Integer paginaEdicao;
	private String caminhoRetornoInicial;

	/**
	 * Retorna classBtnImprimir.
	 * 
	 * @return String
	 */
	public String getClassBtnImprimir() {
		if(UtilString.isVazio(classBtnImprimir)) {
			classBtnImprimir = "form-button-p03";
		}
		return classBtnImprimir;
	}

	/**
	 * Atribui classBtnImprimir.
	 * 
	 * @param classBtnImprimir classBtnImprimir
	 */
	public void setClassBtnImprimir(String classBtnImprimir) {
		this.classBtnImprimir = classBtnImprimir;
	}

	/**
	 * Retorna disabledBtnImprimir.
	 * 
	 * @return boolean
	 */
	public boolean isDisabledBtnImprimir() {
		return disabledBtnImprimir;
	}

	/**
	 * Atribui disabledBtnImprimir.
	 * 
	 * @param disabledBtnImprimir disabledBtnImprimir
	 */
	public void setDisabledBtnImprimir(boolean disabledBtnImprimir) {
		this.disabledBtnImprimir = disabledBtnImprimir;
	}

	/**
	 * Retorna paginaVoltar.
	 * 
	 * @return Integer
	 */
	public Integer getPaginaVoltar() {
		if(!UtilObjeto.isReferencia(paginaVoltar) || paginaVoltar == 0) {
			paginaVoltar = new Integer(1);
		}
		return paginaVoltar;
	}

	/**
	 * Atribui paginaVoltar.
	 * 
	 * @param paginaVoltar paginaVoltar
	 */
	public void setPaginaVoltar(Integer paginaVoltar) {
		this.paginaVoltar = paginaVoltar;
	}

	/**
	 * Retorna descAndamento.
	 * 
	 * @return String
	 */
	public String getDescAndamento() {
		return descAndamento;
	}

	/**
	 * Atribui descAndamento.
	 * 
	 * @param descAndamento descAndamento
	 */
	public void setDescAndamento(String descAndamento) {
		this.descAndamento = descAndamento;
	}

	/**
	 * Retorna HtmlDataTable.
	 * 
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTableAndamentosDetalhe()	{
		if (dataTableAndamentosDetalhe == null) {
			dataTableAndamentosDetalhe = new HtmlDataTable();
		}
		return dataTableAndamentosDetalhe;
	}

	/**
	 * Atribui dataTableAndamentos.
	 * 
	 * @param dataTableAndamentosDetalhe HtmlDataTable
	 */
	public void setDataTableAndamentosDetalhe(HtmlDataTable dataTableAndamentosDetalhe)	{
		this.dataTableAndamentosDetalhe = dataTableAndamentosDetalhe;
	}

	/**
	 * Retorna dataTableAndamentosAlterar.
	 * 
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTableAndamentosAlterar() {
		if (dataTableAndamentosAlterar == null) {
			dataTableAndamentosAlterar = new HtmlDataTable();
		}
		return dataTableAndamentosAlterar;
	}

	/**
	 * Atribui dataTableAndamentosAlterar.
	 * 
	 * @param dataTableAndamentosAlterar dataTableAndamentosAlterar
	 */
	public void setDataTableAndamentosAlterar(
			HtmlDataTable dataTableAndamentosAlterar) {
		this.dataTableAndamentosAlterar = dataTableAndamentosAlterar;
	}

	/**
	 * Retorna PendenciaDetalhadaTO.
	 * 
	 * @return PendenciaDetalhadaTO
	 */
	public PendenciaDetalhadaTO getPendenciaDetalhadaTO() {
		if (pendenciaDetalhadaTO == null){
			pendenciaDetalhadaTO = new PendenciaDetalhadaTO();
		}
		return pendenciaDetalhadaTO;
	}

	/**
	 * Atribui pendenciaDetalhadaTO.
	 * 
	 * @param pendenciaDetalhadaTO PendenciaDetalhadaTO
	 */
	public void setPendenciaDetalhadaTO(PendenciaDetalhadaTO pendenciaDetalhadaTO) {
		this.pendenciaDetalhadaTO = pendenciaDetalhadaTO;
	}

	/**
	 * Retorna ConsultarPendenciasTO.
	 * 
	 * @return ConsultarPendenciasTO
	 */
	public ConsultarPendenciasTO getConsultarPendenciasTO() {
		if (consultarPendenciasTO == null) {
			consultarPendenciasTO = new ConsultarPendenciasTO();
		}
		return consultarPendenciasTO;
	}

	/**
	 * Atribui consultarPendenciasTO.
	 * 
	 * @param consultarPendenciasTO ConsultarPendenciasTO
	 */
	public void setConsultarPendenciasTO(ConsultarPendenciasTO consultarPendenciasTO) {
		this.consultarPendenciasTO = consultarPendenciasTO;
	}

	/**
	 * Retorna paginaDetalhe.
	 * 
	 * @return Integer
	 */
	public Integer getPaginaDetalhe() {
		return paginaDetalhe;
	}

	/**
	 * Atribui paginaDetalhe.
	 * 
	 * @param paginaDetalhe paginaDetalhe
	 */
	public void setPaginaDetalhe(Integer paginaDetalhe) {
		this.paginaDetalhe = paginaDetalhe;
	}

	/**
	 * Retorna paginaEdicao.
	 * 
	 * @return Integer
	 */
	public Integer getPaginaEdicao() {
		return paginaEdicao;
	}

	/**
	 * Atribui paginaEdicao.
	 * 
	 * @param paginaEdicao paginaEdicao
	 */
	public void setPaginaEdicao(Integer paginaEdicao) {
		this.paginaEdicao = paginaEdicao;
	}

	/**
	 * Retorna caminhoRetornoInicial.
	 * 
	 * @return String
	 */
	public String getCaminhoRetornoInicial() {
		return caminhoRetornoInicial;
	}

	/**
	 * Atribui caminhoRetornoInicial.
	 * 
	 * @param caminhoRetornoInicial caminhoRetornoInicial
	 */
	public void setCaminhoRetornoInicial(String caminhoRetornoInicial) {
		this.caminhoRetornoInicial = caminhoRetornoInicial;
	}
}
