/*
 * ConsultarHistoricoAndamentosTO.java
 * 
 * Data de cria��o: 29/04/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.to;

import br.jus.stj.saap.entidade.SaapHistAdmtAdm;

/**
 * 
 * Respons�vel pela COnsulta de Hist�rico de Andamentos
 * @author Politec Tecnologia da Informa��o
 */
public class ConsultarHistoricoAndamentosTO extends SaapTOAbstrato{

    /**
	 * 
	 */
	private SaapHistAdmtAdm saapHistAdmtAdm;
	
    private String nomeUsuario;

	/**
	 * @return SaapHistAdmtAdm
	 */
	public SaapHistAdmtAdm getSaapHistAdmtAdm() {
		if (saapHistAdmtAdm == null) {
			saapHistAdmtAdm = new SaapHistAdmtAdm();
		}
		return saapHistAdmtAdm;
	}

	/**
	 * @param saapHistAdmtAdm SaapHistAdmtAdm
	 */
	public void setSaapHistAdmtAdm(SaapHistAdmtAdm saapHistAdmtAdm) {
		this.saapHistAdmtAdm = saapHistAdmtAdm;
	}

	/**
	 * @return String
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario String
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
    
}
