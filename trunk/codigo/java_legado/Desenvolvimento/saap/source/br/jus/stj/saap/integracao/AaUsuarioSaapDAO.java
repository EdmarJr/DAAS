/*
 * AaUsuarioDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;

/**
 * Interface para persistência do objeto AaUsuario
 *
 * @author Politec Informática S/A
 */
public interface AaUsuarioSaapDAO extends DAO<AaUsuario> {
	// Interface gerada
	
	/**
	 * @return Collection<AaUsuario>
	 * @param entidade SaapAcompPendenciaAdm
	 */
	public Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade); 
		

	
}