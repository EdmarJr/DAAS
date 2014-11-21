/*
 * SaapAcompPendenciaAdmBO.java
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
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.integracao.SaapAcompPendenciaAdmDAO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapAcompPendenciaAdm.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapAcompPendenciaAdmBO extends BO<SaapAcompPendenciaAdm> {

	private SaapAcompPendenciaAdmDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapAcompPendenciaAdmBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapAcompPendenciaAdmDaoImpl")
	protected void setDao(SaapAcompPendenciaAdmDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapAcompPendenciaAdmDAO getDao() {
		return dao;
	}

	/**
	 * Consulta o(s) acompanhamento(s) pelo id da pend�ncia e seqUsuario.
	 * 
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
	public Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(
			SaapAcompPendenciaAdm entidade) {
		return getDao().consultarAcompanhamentos(entidade);
	}
	
	/**
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<AaUsuario>
	 */
	public Collection<SaapAcompPendenciaAdm> consultarUsuariosPendencia(
				SaapAcompPendenciaAdm entidade) {
		return getDao().consultarUsuariosPendencia(entidade);
	}
	
	
}
