/*
 * SaapHistPendenciaAdmDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;

/**
 * Interface para persistência do objeto SaapHistPendenciaAdm
 *
 * @author Politec Informática S/A
 */
public interface SaapHistPendenciaAdmDAO extends DAO<SaapHistPendenciaAdm> {

	/**
	 * Retorna o último histórico.
	 * 
	 * @param identificador
	 * @return SaapHistPendenciaAdm
	 */
	SaapHistPendenciaAdm obterUltimoHistorico(Integer identificador);

	/**
	 * @see br.jus.stj.alp01.arquitetura.integracao.DAO#consultar(
	 * 		br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	Collection<SaapHistPendenciaAdm> consultar(SaapHistPendenciaAdm entidade);

	/**
	 * Retorna o histórico pela pk e tshistPendencia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @return SaapHistPendenciaAdm
	 */
	SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade);
}
