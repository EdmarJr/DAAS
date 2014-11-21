/*
 * SaapHistAdmtAdmBO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.negocio.bo;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.alp01.arquitetura.negocio.bo.BO;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.integracao.SaapHistAdmtAdmDAO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapHistAdmtAdm.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapHistAdmtAdmBO extends BO<SaapHistAdmtAdm> {

	private SaapHistAdmtAdmDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapHistAdmtAdmBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapHistAdmtAdmDaoImpl")
	protected void setDao(SaapHistAdmtAdmDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapHistAdmtAdmDAO getDao() {
		return dao;
	}

	/**
	 * Retorna uma lista de hist�rico de andamentos filtrando pelo n�mero do documento.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @return Collection<SaapHistAdmtAdm>
	 */
	public Collection<SaapHistAdmtAdm> consultar(SaapAdmtAdmPresidencia entidade){
		return getDao().consultar(entidade);
	}

	/**
	 * Obt�m o hist�rico pela pk e pelo tsEntradaAdmt.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @return SaapHistAdmtAdm
	 */
	public SaapHistAdmtAdm obtemPelaPkETsEntradaAdmt(SaapAdmtAdmPresidencia entidade) {
		return getDao().obtemPelaPkETsEntradaAdmt(entidade);
	}
}
