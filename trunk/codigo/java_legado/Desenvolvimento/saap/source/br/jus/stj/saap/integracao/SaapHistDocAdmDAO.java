/*
 * SaapHistDocAdmDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persistência do objeto SaapHistDocAdm
 *
 * @author Politec Informática S/A
 */
public interface SaapHistDocAdmDAO extends DAO<SaapHistDocAdm> {

	/**
	 * Obtem o último histórico.
	 * 
	 * @param identificador
	 * @return SaapHistDocAdm
	 */
	SaapHistDocAdm obterUltimoHistorico(Integer identificador);

	/**
	 * @see br.jus.stj.alp01.arquitetura.integracao.DAO#consultar(
	 * 		br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade);

	/**
	 * Obtém histórico pela pk e pelo tsEntradaAdm.
	 * 
	 * @param entidade
	 * @return SaapHistDocAdm
	 */
	SaapHistDocAdm obtemPelaPkETsEntradaDoc(SaapDocAdmPresidencia entidade);
}
