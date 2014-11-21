/*
 * SaapArqDocAdmBO.java
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
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.integracao.SaapArqDocAdmDAO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapArqDocAdm.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapArqDocAdmBO extends BO<SaapArqDocAdm> {

	private SaapArqDocAdmDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapArqDocAdmBO () {
		// Construtor default
	}

	/**
	 * @param idDocumento Integer
	 * @return Collection<SaapArqDocAdm>
	 */
	public Collection<SaapArqDocAdm> consultarAnexos(Integer idDocumento){
	    return getDao().consultarAnexos(idDocumento);
	}
	
	
	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapArqDocAdmDaoImpl")
	protected void setDao(SaapArqDocAdmDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapArqDocAdmDAO getDao() {
		return dao;
	}

}
