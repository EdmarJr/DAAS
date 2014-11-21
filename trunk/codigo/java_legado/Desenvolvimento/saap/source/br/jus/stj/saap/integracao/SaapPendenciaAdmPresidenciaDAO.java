/*
 * SaapPendenciaAdmPresidenciaDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarPendenciasTO;

/**
 * Interface para persist�ncia do objeto SaapPendenciaAdmPresidencia
 *
 * @author Politec Inform�tica S/A
 */
public interface SaapPendenciaAdmPresidenciaDAO extends DAO<SaapPendenciaAdmPresidencia> {

	/**
	 * Consulta pend�ncia(s).
	 * 
	 * @param consultarendenciasTO ConsultarPendenciasTO
	 * @return Collection<SaapPendenciaAdmPresidencia>
	 */
	Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO);

	/**
	 * Desativa pend�ncia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 */
	void desativar(SaapPendenciaAdmPresidencia entidade);
}
