/*
 * SaapHistPendenciaAdmBO.java
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
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapHistPendenciaAdmDAO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapHistPendenciaAdm.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapHistPendenciaAdmBO extends BO<SaapHistPendenciaAdm> {

	private SaapHistPendenciaAdmDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapHistPendenciaAdmBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapHistPendenciaAdmDaoImpl")
	protected void setDao(SaapHistPendenciaAdmDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapHistPendenciaAdmDAO getDao() {
		return dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#consultar(
	 * 		br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	public Collection<SaapHistPendenciaAdm> consultar(SaapHistPendenciaAdm entidade){
		return getDao().consultar(entidade);
	}

	/**
	 * Obtem o �ltimo hist�rico.
	 * 
	 * @param identificador Integer
	 * @return SaapHistPendenciaAdm
	 */
	public SaapHistPendenciaAdm obterUltimoHistorico(Integer identificador){
		return getDao().obterUltimoHistorico(identificador);
	}

	/**
	 * Retorna hist�rico da pend�ncia pela pk e tsHistPendencia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @return SaapHistPendenciaAdm
	 */
	public SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade) {
		return getDao().obtemPelaPkETsHistPendencia(entidade);
	}
}
