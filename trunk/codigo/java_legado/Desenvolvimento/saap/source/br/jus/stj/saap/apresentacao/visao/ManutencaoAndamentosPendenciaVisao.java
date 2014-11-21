/*
 * ManutencaoAndamentosPendenciaVisao.java
 * 
 * Data de criação: 20/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import java.util.ArrayList;
import java.util.Collection;

import org.richfaces.component.html.HtmlDataTable;

import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;


/**
 * Responsável pela representação da visão do caso de uso Manter Andamentos da Pendência
 *
 * @author Politec Informática
 */
public class ManutencaoAndamentosPendenciaVisao extends ManutencaoVisao<SaapAdmtAdmPresidencia> {
	
	private ConsultarAndamentosPendenciaTO consultaTO;
	private SaapPendenciaAdmPresidencia pendenciaSelecionada;
	private Collection<SaapAdmtAdmPresidenciaTO> listaAndamentosDetalhe;
	private ConsultarAndamentosPendenciaTO resultadoTO;
	private Integer paginaDetalhe;
	private HtmlDataTable dataTableDetalhe;
	private String descricaoAndamentoSelecionado;
	private boolean apresentaBtnAlterar;
	private SaapAdmtAdmPresidencia andamentoSelecionado;
	private Integer paginaVoltar;
	private SaapAdmtAdmPresidencia andamentoSelecionadoConsulta;
	private boolean disabledBtnImprimir;
	private String classBtnImprimir;
	private String idPendenciaSelecionada;
	private String caminhoRetorno;

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

	/**
	 * Retorna idPendenciaSelecionada.
	 * 
	 * @return String
	 */
	public String getIdPendenciaSelecionada() {
		return idPendenciaSelecionada;
	}

	/**
	 * Atribui idPendenciaSelecionada.
	 * 
	 * @param idPendenciaSelecionada idPendenciaSelecionada
	 */
	public void setIdPendenciaSelecionada(String idPendenciaSelecionada) {
		this.idPendenciaSelecionada = idPendenciaSelecionada;
	}

	/**
	 * Retorna consultaTO.
	 * 
	 * @return ConsultarAndamentosPendenciaTO
	 */
	public ConsultarAndamentosPendenciaTO getConsultaTO() {
		if (consultaTO == null) {
			consultaTO = TOFactory.getInstancia().novoConsultarAndamentosPendenciaTO();
		}
		return consultaTO;
	}

	/**
	 * Atribui consultaTO.
	 * 
	 * @param consultaTO consultaTO
	 */
	public void setConsultaTO(ConsultarAndamentosPendenciaTO consultaTO) {
		this.consultaTO = consultaTO;
	}



	/**
	 * Retorna pendenciaSelecionada.
	 * 
	 * @return SaapPendenciaAdmPresidencia
	 */
	public SaapPendenciaAdmPresidencia getPendenciaSelecionada() {
		if (pendenciaSelecionada == null) {
			pendenciaSelecionada = EntidadeFactory.
				getInstancia().novoSaapPendenciaAdmPresidencia();
		}
		return pendenciaSelecionada;
	}


	
	/**
	 * Atribui pendenciaSelecionada.
	 * 
	 * @param pendenciaSelecionada pendenciaSelecionada
	 */
	public void setPendenciaSelecionada(
			SaapPendenciaAdmPresidencia pendenciaSelecionada) {
		this.pendenciaSelecionada = pendenciaSelecionada;
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
	 * Retorna resultadoTO.
	 * 
	 * @return ConsultarAndamentosPendenciaTO
	 */
	public ConsultarAndamentosPendenciaTO getResultadoTO() {
		if (resultadoTO == null) {
			resultadoTO = TOFactory.getInstancia().novoConsultarAndamentosPendenciaTO();
		}
		
		return resultadoTO;
	}


	
	/**
	 * Atribui resultadoTO.
	 * 
	 * @param resultadoTO resultadoTO
	 */
	public void setResultadoTO(ConsultarAndamentosPendenciaTO resultadoTO) {
		this.resultadoTO = resultadoTO;
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
	 * Retorna andamentoSelecionado.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getAndamentoSelecionado() {
		
		if (andamentoSelecionado == null) {
			andamentoSelecionado = EntidadeFactory.getInstancia().novoSaapAdmtAdmPresidencia();
		}
		
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
	 * Retorna andamentoSelecionadoConsulta.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getAndamentoSelecionadoConsulta() {
		if (andamentoSelecionadoConsulta == null) {
			andamentoSelecionadoConsulta = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidencia();
		}
		
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
	
	
}
