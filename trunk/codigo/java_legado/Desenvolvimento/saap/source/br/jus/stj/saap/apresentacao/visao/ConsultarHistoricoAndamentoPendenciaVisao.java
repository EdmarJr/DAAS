/*
 * ConsultarHistoricoAndamentoPendenciaVisao.java
 * 
 * Data de criação: 23/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import br.jus.stj.alp01.comum.conversor.UtilConversorDeString;
import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Responsável pela representação da visão do caso de uso Consultar Histórico do Andamento da 
 * Pendência.
 * @author jesler.santos
 */
public class ConsultarHistoricoAndamentoPendenciaVisao extends ManutencaoVisao<SaapHistAdmtAdm>{

	private ConsultarHistoricoAndamentoPendenciaTO resultadoTO;
	private ConsultarHistoricoAndamentoPendenciaTO consultaTO;
	private String seqAdmtAdm;
	private String indTipoAdmtAdm;
	private String seqPendenciaAdm;

	/**
	 * Retorna seqAdmtAdm.
	 * 
	 * @return String
	 */
	public String getSeqAdmtAdm() {
		return seqAdmtAdm;
	}

	/**
	 * Atribui seqAdmtAdm.
	 * 
	 * @param seqAdmtAdm seqAdmtAdm
	 */
	public void setSeqAdmtAdm(String seqAdmtAdm) {
		this.seqAdmtAdm = seqAdmtAdm;
	}

	/**
	 * Retorna indTipoAdmtAdm.
	 * 
	 * @return String
	 */
	public String getIndTipoAdmtAdm() {
		return indTipoAdmtAdm;
	}

	/**
	 * Atribui indTipoAdmtAdm.
	 * 
	 * @param indTipoAdmtAdm indTipoAdmtAdm
	 */
	public void setIndTipoAdmtAdm(String indTipoAdmtAdm) {
		this.indTipoAdmtAdm = indTipoAdmtAdm;
	}

	/**
	 * Retorna seqPendenciaAdm.
	 * 
	 * @return String
	 */
	public String getSeqPendenciaAdm() {
		return seqPendenciaAdm;
	}

	/**
	 * Atribui seqPendenciaAdm.
	 * 
	 * @param seqPendenciaAdm seqPendenciaAdm
	 */
	public void setSeqPendenciaAdm(String seqPendenciaAdm) {
		this.seqPendenciaAdm = seqPendenciaAdm;
	}

	/**
	 * Retorna consultaTO.
	 * 
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	public ConsultarHistoricoAndamentoPendenciaTO getConsultaTO() {
		if (consultaTO == null) {
			consultaTO = TOFactory.getInstancia().novoConsultarHistoricoAndamentoPendenciaTO();
			consultaTO.getHistoricoAndamento().setSaapAdmtAdmPresidencia(
					getSaapAdmtAdmPresidenciaPreenchido());
		}
		return consultaTO;
	}

	/**
	 * Retorna saapAdmtAdmPresidencia.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getSaapAdmtAdmPresidenciaPreenchido() {
		
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidencia();
		
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = 
			 EntidadeFactory.getInstancia().novoSaapPendenciaAdmPresidencia();
		saapPendenciaAdmPresidencia.setId(UtilConversorDeString.
				converterParaInteger(getSeqPendenciaAdm()));
		saapAdmtAdmPresidencia.setSaapPendenciaAdmPresidencia(saapPendenciaAdmPresidencia);
		saapAdmtAdmPresidencia.setSaapDocAdmPresidencia(null);
		
		return saapAdmtAdmPresidencia;
	}

	/**
	 * Atribui consultaTO.
	 * 
	 * @param consultaTO consultaTO
	 */
	public void setHistoricoAndamentoPendenciaTO(
			ConsultarHistoricoAndamentoPendenciaTO consultaTO) {
		this.consultaTO = consultaTO;
	}

	/**
	 * Retorna resultadoTO.
	 * 
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	public ConsultarHistoricoAndamentoPendenciaTO getResultadoTO() {
		if (resultadoTO == null) {
			resultadoTO = TOFactory.getInstancia().novoConsultarHistoricoAndamentoPendenciaTO();
		}
		return resultadoTO;
	}

	/**
	 * Atribui resultadoTO.
	 * 
	 * @param resultadoTO resultadoTO
	 */
	public void setResultadoTO(ConsultarHistoricoAndamentoPendenciaTO resultadoTO) {
		this.resultadoTO = resultadoTO;
	}
}
