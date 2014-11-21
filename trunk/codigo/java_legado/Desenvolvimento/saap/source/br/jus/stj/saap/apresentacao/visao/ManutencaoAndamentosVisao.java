/*
 * ManutencaoAndamentosVisao.java
 * 
 * Data de criação: 08/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlDataTable;

import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.fabrica.TOFactory;


/**
 * Responsável pela representação da visão do caso de uso Manter Andamentos.
 * @author jesler.santos
 */
public class ManutencaoAndamentosVisao extends ManutencaoVisao<SaapAdmtAdmPresidencia> {

	private Collection<SelectItem> listaTiposDocumentos;
	private ConsultarAndamentosTO consultaTO;
	private ConsultarAndamentosTO resultadoTO;
	private SaapDocAdmPresidencia docSelecionado;
	private SaapAdmtAdmPresidencia andamentoSelecionado;
	private SaapAdmtAdmPresidencia andamentoSelecionadoConsulta;
	private Collection<SaapAdmtAdmPresidenciaTO> listaAndamentosDetalhe;
	private Integer paginaDetalhe;
	private String descricaoAndamentoSelecionado;
	private boolean apresentaBtnAlterar;
	private HtmlDataTable dataTableDetalhe;
	private Integer paginaVoltar;
	private String classBtnImprimir;
	private boolean disabledBtnImprimir;
	private String caminhoRetorno;

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
	 * Retorna dataTableDetalhe.
	 * 
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTableDetalhe() {
		return dataTableDetalhe;
	}

	/**
	 * Atribui dataTableDetalhe.
	 * 
	 * @param dataTableDetalhe dataTableDetalhe
	 */
	public void setDataTableDetalhe(HtmlDataTable dataTableDetalhe) {
		this.dataTableDetalhe = dataTableDetalhe;
	}

	/**
	 * Retorna apresentaBtnAlterar.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaBtnAlterar() {
		return apresentaBtnAlterar;
	}

	/**
	 * Atribui apresentaBtnAlterar.
	 * 
	 * @param apresentaBtnAlterar apresentaBtnAlterar
	 */
	public void setApresentaBtnAlterar(boolean apresentaBtnAlterar) {
		this.apresentaBtnAlterar = apresentaBtnAlterar;
	}

	/**
	 * Retorna descricaoAndamentoSelecionado.
	 * 
	 * @return String
	 */
	public String getDescricaoAndamentoSelecionado() {
		return descricaoAndamentoSelecionado;
	}

	/**
	 * Atribui descricaoAndamentoSelecionado.
	 * 
	 * @param descricaoAndamentoSelecionado descricaoAndamentoSelecionado
	 */
	public void setDescricaoAndamentoSelecionado(
			String descricaoAndamentoSelecionado) {
		this.descricaoAndamentoSelecionado = descricaoAndamentoSelecionado;
	}

	/**
	 * Retorna andamentoSelecionadoConsulta.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getAndamentoSelecionadoConsulta() {
		return andamentoSelecionadoConsulta;
	}

	/**
	 * Atribui andamentoSelecionadoConsulta.
	 * 
	 * @param andamentoSelecionadoConsulta andamentoSelecionadoConsulta
	 */
	public void setAndamentoSelecionadoConsulta(
			SaapAdmtAdmPresidencia andamentoSelecionadoConsulta) {
		this.andamentoSelecionadoConsulta = andamentoSelecionadoConsulta;
	}

	/**
	 * Retorna listaAndamentosDetalhe.
	 * 
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public Collection<SaapAdmtAdmPresidenciaTO> getListaAndamentosDetalhe() {
		if (listaAndamentosDetalhe == null) {
			listaAndamentosDetalhe = new ArrayList<SaapAdmtAdmPresidenciaTO>();
		}
		return listaAndamentosDetalhe;
	}

	/**
	 * Atribui listaAndamentosDetalhe.
	 * 
	 * @param listaAndamentosDetalhe listaAndamentosDetalhe
	 */
	public void setListaAndamentosDetalhe(
			Collection<SaapAdmtAdmPresidenciaTO> listaAndamentosDetalhe) {
		this.listaAndamentosDetalhe = listaAndamentosDetalhe;
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
	 * Retorna andamentoSelecionado.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getAndamentoSelecionado() {
		return andamentoSelecionado;
	}

	/**
	 * Atribui andamentoSelecionado.
	 * 
	 * @param andamentoSelecionado andamentoSelecionado
	 */
	public void setAndamentoSelecionado(SaapAdmtAdmPresidencia andamentoSelecionado) {
		this.andamentoSelecionado = andamentoSelecionado;
	}

	/**
	 * Retorna docSelecionado.
	 * 
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia getDocSelecionado() {
		return docSelecionado;
	}

	/**
	 * Atribui docSelecionado.
	 * 
	 * @param docSelecionado docSelecionado
	 */
	public void setDocSelecionado(SaapDocAdmPresidencia docSelecionado) {
		this.docSelecionado = docSelecionado;
	}

	/**
	 * Retorna listaTiposDocumentos.
	 * 
	 * @return Collection<SelectItem>
	 */
	public Collection<SelectItem> getListaTiposDocumentos() {
		if (listaTiposDocumentos == null) {
			listaTiposDocumentos = new ArrayList<SelectItem>();
		}
		return listaTiposDocumentos;
	}

	/**
	 * Atribui listaTiposDocumentos.
	 * 
	 * @param listaTiposDocumentos listaTiposDocumentos
	 */
	public void setListaTiposDocumentos(Collection<SelectItem> listaTiposDocumentos) {
		this.listaTiposDocumentos = listaTiposDocumentos;
	}

	/**
	 * Retorna consultaTO.
	 * 
	 * @return ConsultarAndamentosTO
	 */
	public ConsultarAndamentosTO getConsultaTO() {
		if (consultaTO == null) {
			consultaTO = TOFactory.getInstancia().novoConsultarAndamentosTO();
		}
		return consultaTO;
	}

	/**
	 * Atribui consultaTO.
	 * 
	 * @param consultaTO consultaTO
	 */
	public void setConsultaTO(ConsultarAndamentosTO consultaTO) {
		this.consultaTO = consultaTO;
	}

	/**
	 * Retorna resultadoTO.
	 * 
	 * @return ConsultarAndamentosTO
	 */
	public ConsultarAndamentosTO getResultadoTO() {
		if (resultadoTO == null) {
			resultadoTO = TOFactory.getInstancia().novoConsultarAndamentosTO();
		}
		return resultadoTO;
	}

	/**
	 * Atribui resultadoTO.
	 * 
	 * @param resultadoTO resultadoTO
	 */
	public void setResultadoTO(ConsultarAndamentosTO resultadoTO) {
		this.resultadoTO = resultadoTO;
	}

	
	/**
	 * Retorna caminhoRetorno.
	 * 
	 * @return String
	 */
	public String getCaminhoRetorno() {
		return caminhoRetorno;
	}

	
	/**
	 * Atribui caminhoRetorno.
	 * 
	 * @param caminhoRetorno caminhoRetorno
	 */
	public void setCaminhoRetorno(String caminhoRetorno) {
		this.caminhoRetorno = caminhoRetorno;
	}
	
	
}
