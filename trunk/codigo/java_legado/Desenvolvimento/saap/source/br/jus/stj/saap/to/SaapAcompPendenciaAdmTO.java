/*
 * SaapAcompPendenciaAdmTO.java
 * 
 * Data de criação: 26/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação das informações dos dados do acompanhamento.
 * @author Politec
 */
public class SaapAcompPendenciaAdmTO extends SaapTOAbstrato {
	private SaapAcompPendenciaAdm saapAcompPendenciaAdm;
	private String acompanhamento;

	
	/**
	 * Retorna saapAcompPendenciaAdm.
	 * 
	 * @return SaapAcompPendenciaAdm
	 */
	public SaapAcompPendenciaAdm getSaapAcompPendenciaAdm() {
		
		if (saapAcompPendenciaAdm == null) {
			saapAcompPendenciaAdm = EntidadeFactory.getInstancia().novoSaapAcompPendenciaAdm();
		}
		
		return saapAcompPendenciaAdm;
	}

	
	/**
	 * Atribui saapAcompPendenciaAdm.
	 * 
	 * @param saapAcompPendenciaAdm saapAcompPendenciaAdm
	 */
	public void setSaapAcompPendenciaAdm(
			SaapAcompPendenciaAdm saapAcompPendenciaAdm) {
		this.saapAcompPendenciaAdm = saapAcompPendenciaAdm;
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

}
