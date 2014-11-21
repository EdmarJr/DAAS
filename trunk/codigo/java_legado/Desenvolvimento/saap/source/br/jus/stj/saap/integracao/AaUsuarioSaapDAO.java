/*
 * AaUsuarioDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;

/**
 * Interface para persist�ncia do objeto AaUsuario
 *
 * @author Politec Inform�tica S/A
 */
public interface AaUsuarioSaapDAO extends DAO<AaUsuario> {
	// Interface gerada
	
	/**
	 * @return Collection<AaUsuario>
	 * @param entidade SaapAcompPendenciaAdm
	 */
	public Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade); 
		

	
}