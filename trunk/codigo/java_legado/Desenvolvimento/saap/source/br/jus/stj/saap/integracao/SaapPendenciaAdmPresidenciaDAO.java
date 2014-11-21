/*
 * SaapPendenciaAdmPresidenciaDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarPendenciasTO;

/**
 * Interface para persistência do objeto SaapPendenciaAdmPresidencia
 *
 * @author Politec Informática S/A
 */
public interface SaapPendenciaAdmPresidenciaDAO extends DAO<SaapPendenciaAdmPresidencia> {

	/**
	 * Consulta pendência(s).
	 * 
	 * @param consultarendenciasTO ConsultarPendenciasTO
	 * @return Collection<SaapPendenciaAdmPresidencia>
	 */
	Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO);

	/**
	 * Desativa pendência.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 */
	void desativar(SaapPendenciaAdmPresidencia entidade);
}
