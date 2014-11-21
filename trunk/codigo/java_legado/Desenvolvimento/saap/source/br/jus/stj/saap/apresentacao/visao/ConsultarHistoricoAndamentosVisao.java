/*
 * ConsultarHistoricoAndamentosVisao.java
 * 
 * Data de criação: 23/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import java.util.ArrayList;
import java.util.Collection;

import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;


/**
 * Responsável pela representação da visão do caso de uso Consultar Histórico de Andamentos
 * @author lmartins
 */
public class ConsultarHistoricoAndamentosVisao extends ManutencaoVisao<SaapHistAdmtAdm> {

	private String seqDocAndamento;
	private Collection<ConsultarHistoricoAndamentosTO> listaHistorico;
	private DocumentoDetalhadoTO documentoDetalhadoTO;
	
	/**
	 *  Retorna Lista de Histórico
	 * @return Collection
	 */
	public Collection<ConsultarHistoricoAndamentosTO> getListaHistorico() {
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<ConsultarHistoricoAndamentosTO>();
		}
		return listaHistorico;
	}

	/**
	 * Atribui listaHistorico.
	 * 
	 * @param listaHistorico listaHistorico
	 */	
	public void setListaHistorico(
			Collection<ConsultarHistoricoAndamentosTO> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	/**
	 * Retorna documentoDetalhadoTO.
	 * 
	 * @return DocumentoDetalhadoTO
	 */
	public DocumentoDetalhadoTO getDocumentoDetalhadoTO() {
		if (documentoDetalhadoTO == null) {
			documentoDetalhadoTO = new DocumentoDetalhadoTO();
		}
		return documentoDetalhadoTO;
	}


	
	/**
	 * Atribui documentoDetalhadoTO.
	 * 
	 * @param documentoDetalhadoTO documentoDetalhadoTO
	 */
	public void setDocumentoDetalhadoTO(DocumentoDetalhadoTO documentoDetalhadoTO) {
		this.documentoDetalhadoTO = documentoDetalhadoTO;
	}

	
	/**
	 * Retorna seqDocAndamento.
	 * 
	 * @return String
	 */
	public String getSeqDocAndamento() {
		return seqDocAndamento;
	}

	
	/**
	 * Atribui seqDocAndamento.
	 * 
	 * @param seqDocAndamento seqDocAndamento
	 */
	public void setSeqDocAndamento(String seqDocAndamento) {
		this.seqDocAndamento = seqDocAndamento;
	}


	
	
	
}
