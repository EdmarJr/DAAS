/*
 * SaapAcompPendenciaAdmDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persistência do objeto SaapAcompPendenciaAdm
 *
 * @author Politec Informática S/A
 */
public interface SaapAcompPendenciaAdmDAO extends DAO<SaapAcompPendenciaAdm> {

	/**
	 * Consulta o(s) acompanhamento(s) pelo id da pendência e seqUsuario.
	 * 
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
	Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(SaapAcompPendenciaAdm entidade);
	
	/**
	 * Retorna todos os usuarios dos acompanhamentos 
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<SaapAcompPendenciaAdm> 
	 */	
	Collection<SaapAcompPendenciaAdm> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade);	
	
}