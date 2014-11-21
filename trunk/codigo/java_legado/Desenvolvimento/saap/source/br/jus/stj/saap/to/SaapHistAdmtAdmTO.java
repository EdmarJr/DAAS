/*
 * SaapHistAdmtAdmTO.java
 * 
 * Data de criação: 30/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação das informações dos dados do histórico do andamento da pendência.
 * @author jesler.santos
 */
public class SaapHistAdmtAdmTO extends SaapTOAbstrato {

	private SaapHistAdmtAdm saapHistAdmtAdm;
	private String nomeUsuario;

	/**
	 * Retorna saapHistAdmtAdm.
	 * 
	 * @return SaapHistAdmtAdm
	 */
	public SaapHistAdmtAdm getSaapHistAdmtAdm() {
		if (saapHistAdmtAdm == null) {
			saapHistAdmtAdm = EntidadeFactory.getInstancia().novoSaapHistAdmtAdm();
		}
		return saapHistAdmtAdm;
	}

	/**
	 * Atribui saapHistAdmtAdm.
	 * 
	 * @param saapHistAdmtAdm saapHistAdmtAdm
	 */
	public void setSaapHistAdmtAdm(SaapHistAdmtAdm saapHistAdmtAdm) {
		this.saapHistAdmtAdm = saapHistAdmtAdm;
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
