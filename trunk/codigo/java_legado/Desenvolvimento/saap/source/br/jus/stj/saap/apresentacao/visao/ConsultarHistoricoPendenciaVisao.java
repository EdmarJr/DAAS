/*
 * ConsultarHistoricoPendenciaVisao.java
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
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.to.ConsultarHistoricoPendenciasTO;


/**
 * Responsável pela representação da visão do caso de uso Consultar Histórico da Pêndencia.
 * @author lmartins
 */
public class ConsultarHistoricoPendenciaVisao extends ManutencaoVisao<SaapHistPendenciaAdm> {

	private String seqPendenciaAdm;
	private Collection<ConsultarHistoricoPendenciasTO> listaHistorico;
	

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
	 * Retorna listaHistorico.
	 * 
	 * @return Collection<ConsultarHistoricoPendenciasTO>
	 */
	public Collection<ConsultarHistoricoPendenciasTO> getListaHistorico() {
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<ConsultarHistoricoPendenciasTO>();
		}
		return listaHistorico;
	}

	
	/**
	 * Atribui listaHistorico.
	 * 
	 * @param listaHistorico listaHistorico
	 */
	public void setListaHistorico(
			Collection<ConsultarHistoricoPendenciasTO> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	
}
