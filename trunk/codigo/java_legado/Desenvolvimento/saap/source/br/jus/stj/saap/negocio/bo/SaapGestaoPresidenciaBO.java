/*
 * SaapGestaoPresidenciaBO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
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
 * @author Politec Inform�tica
 */
 @Component
public class SaapGestaoPresidenciaBO extends BO<SaapGestaoPresidencia> {

	private SaapGestaoPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapGestaoPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
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
	 * Consulta a �ltima gest�o.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia consultarUltimaGestao() {
		return getDao().consultarUltimaGestao();
	}

	/**
	 * Obt�m gest�o pelo per�odo.
	 * 
	 * @param dataPeriodo Date
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo)  {
		return getDao().obterGestaoPeloPeriodo(dataPeriodo);
	}

	/**
	 * Consulta �ltima gest�o para altera��o.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	public SaapGestaoPresidencia consultarUltimaGestaoParaAlteracao() {
		return getDao().consultarUltimaGestaoParaAlteracao();
	}
}
