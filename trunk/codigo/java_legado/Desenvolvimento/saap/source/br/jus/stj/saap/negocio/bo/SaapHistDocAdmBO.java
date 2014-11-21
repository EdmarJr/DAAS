/*
 * SaapHistDocAdmBO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.negocio.bo;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.integracao.SaapHistDocAdmDAO;
import br.jus.stj.alp01.arquitetura.negocio.bo.BO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapHistDocAdm.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapHistDocAdmBO extends BO<SaapHistDocAdm> {

	private SaapHistDocAdmDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapHistDocAdmBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapHistDocAdmDaoImpl")
	protected void setDao(SaapHistDocAdmDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapHistDocAdmDAO getDao() {
		return dao;
	}

	/**
	 * Obtem o �ltimo hist�rico.
	 * 
	 * @param identificador Integer
	 * @return SaapHistDocAdm
	 */
	public SaapHistDocAdm obterUltimoHistorico(Integer identificador){
		return getDao().obterUltimoHistorico(identificador);
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#consultar(
	 * 		br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	public Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade){
		return getDao().consultar(entidade);
	}

	/**
	 * Obt�m hist�rico do documento pela pk e tsEntradaAdm.
	 * 
	 * @param entidade SaapDocAdmPresidencia
	 * @return SaapHistDocAdm
	 */
	public SaapHistDocAdm obtemPelaPkETsEntradaDoc(SaapDocAdmPresidencia entidade) {
		return getDao().obtemPelaPkETsEntradaDoc(entidade);
	}
}
