/*
 * SaapArqDocAdmBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
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
 * @author Politec Informática
 */
 @Component
public class SaapArqDocAdmBO extends BO<SaapArqDocAdm> {

	private SaapArqDocAdmDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
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
	 * Sobrescrita do método para injeção da implementação da Dao.
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
