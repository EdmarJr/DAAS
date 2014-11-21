/*
 * SaapHistDocAdmBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
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
 * @author Politec Informática
 */
 @Component
public class SaapHistDocAdmBO extends BO<SaapHistDocAdm> {

	private SaapHistDocAdmDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	SaapHistDocAdmBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do método para injeção da implementação da Dao.
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
	 * Obtem o último histórico.
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
	 * Obtém histórico do documento pela pk e tsEntradaAdm.
	 * 
	 * @param entidade SaapDocAdmPresidencia
	 * @return SaapHistDocAdm
	 */
	public SaapHistDocAdm obtemPelaPkETsEntradaDoc(SaapDocAdmPresidencia entidade) {
		return getDao().obtemPelaPkETsEntradaDoc(entidade);
	}
}
