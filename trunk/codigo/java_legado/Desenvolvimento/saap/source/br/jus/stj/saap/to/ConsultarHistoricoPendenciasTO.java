/*
 * ConsultarHistoricoPendenciasTO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.to;

import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;

public class ConsultarHistoricoPendenciasTO extends SaapTOAbstrato{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaapHistPendenciaAdm saapHistPendenciaAdm;
	
    private String nomeUsuario;

	
	/**
	 * Retorna saapHistPendenciaAdm.
	 * 
	 * @return SaapHistPendenciaAdm
	 */
	public SaapHistPendenciaAdm getSaapHistPendenciaAdm() {
		if (saapHistPendenciaAdm == null) {
			saapHistPendenciaAdm = new SaapHistPendenciaAdm();
		}
		
		return saapHistPendenciaAdm;
	}

	
	/**
	 * Atribui saapHistPendenciaAdm.
	 * 
	 * @param saapHistPendenciaAdm saapHistPendenciaAdm
	 */
	public void setSaapHistPendenciaAdm(SaapHistPendenciaAdm saapHistPendenciaAdm) {
		this.saapHistPendenciaAdm = saapHistPendenciaAdm;
	}

	
	/**
	 * Retorna nomeUsuario.
	 * 
	 * @return String
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	
	/**
	 * Atribui nomeUsuario.
	 * 
	 * @param nomeUsuario nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	

    
}
