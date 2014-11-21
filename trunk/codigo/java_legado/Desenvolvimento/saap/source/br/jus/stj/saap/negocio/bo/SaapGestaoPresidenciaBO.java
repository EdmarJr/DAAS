/*
 * SaapGestaoPresidenciaBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.bo;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO;
import br.jus.stj.alp01.arquitetura.negocio.bo.BO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapGestaoPresidencia.
 *
 * @author Politec Informática
 */
 @Component
public class SaapGestaoPresidenciaBO extends BO<SaapGestaoPresidencia> {

	private SaapGestaoPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	SaapGestaoPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do método para injeção da implementação da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapGestaoPresidenciaDaoImpl")
	protected void setDao(SaapGestaoPresidenciaDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapGestaoPresidenciaDAO getDao() {
		return dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#
	 * 		consultar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	public Collection<SaapGestaoPresidencia> consultar(SaapGestaoPresidencia entidade){
		return getDao().consultarGestoes(entidade);
	}

	/**
	 * Consulta a última gestão.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia consultarUltimaGestao() {
		return getDao().consultarUltimaGestao();
	}

	/**
	 * Obtém gestão pelo período.
	 * 
	 * @param dataPeriodo Date
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo)  {
		return getDao().obterGestaoPeloPeriodo(dataPeriodo);
	}

	/**
	 * Consulta última gestão para alteração.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia consultarUltimaGestaoParaAlteracao() {
		return getDao().consultarUltimaGestaoParaAlteracao();
	}
}
