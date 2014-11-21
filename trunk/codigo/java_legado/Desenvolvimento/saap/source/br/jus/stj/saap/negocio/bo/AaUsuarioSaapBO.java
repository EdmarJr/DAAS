/*
 * AaUsuarioBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.bo;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.alp01.arquitetura.negocio.bo.BO;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.integracao.AaUsuarioSaapDAO;

/**
 * Classe para encapsulamento da regra de negocio do objeto AaUsuario.
 *
 * @author Politec Informática
 */
 @Component
public class AaUsuarioSaapBO extends BO<AaUsuario> {

	private AaUsuarioSaapDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	AaUsuarioSaapBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do método para injeção da implementação da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "aaUsuarioSaapDaoImpl")
	protected void setDao(AaUsuarioSaapDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected AaUsuarioSaapDAO getDao() {
		return dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#obter(java.io.Serializable)
	 */
	@Override
	public AaUsuario obter(Serializable identificador) {
		return getDao().obter(identificador);
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#consultar()
	 */
	@Override
	public Collection<AaUsuario> consultar() {
		return getDao().consultar();
	}	
	
}
