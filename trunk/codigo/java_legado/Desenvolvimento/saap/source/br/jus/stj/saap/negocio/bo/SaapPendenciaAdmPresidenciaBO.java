/*
 * SaapPendenciaAdmPresidenciaBO.java
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
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapPendenciaAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapPendenciaAdmPresidencia.
 *
 * @author Politec Inform�tica
 */
 @Component
public class SaapPendenciaAdmPresidenciaBO extends BO<SaapPendenciaAdmPresidencia> {

	private SaapPendenciaAdmPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapPendenciaAdmPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapPendenciaAdmPresidenciaDaoImpl")
	protected void setDao(SaapPendenciaAdmPresidenciaDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapPendenciaAdmPresidenciaDAO getDao() {
		return dao;
	}

	/**
	 * Desativa pend�ncia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 */
	public void desativar(SaapPendenciaAdmPresidencia entidade){
		getDao().desativar(entidade);
	}

	/**
	 * Consulta pend�ncia(s).
	 * 
	 * @param consultarendenciasTO ConsultarPendenciasTO
	 * @return Collection<SaapPendenciaAdmPresidencia>
	 */
	public Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO){
		return getDao().consultarPendencias(consultarendenciasTO);
	}
}
