/*
 * DominioBO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.negocio.bo;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.saap.integracao.DominioDAO;
import br.jus.stj.alp01.arquitetura.negocio.bo.BO;

/**
 * Classe para encapsulamento da regra de negocio do objeto Dominio.
 *
 * @author Politec Inform�tica
 */
 @Component
public class DominioBO extends BO<Dominio> {

	private DominioDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	DominioBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "dominioDaoImpl")
	protected void setDao(DominioDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected DominioDAO getDao() {
		return dao;
	}

    /**
     * Consulta os tipos de documentos.
     * 
     * @return Collection<Dominio>
     */
	public Collection<Dominio> consultarTiposDocumentos(){
		 return getDao().consultarTiposDocumentos();
	 }
}
