/*
 * SaapHistDocAdmDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persist�ncia do objeto SaapHistDocAdm
 *
 * @author Politec Inform�tica S/A
 */
public interface SaapHistDocAdmDAO extends DAO<SaapHistDocAdm> {

	/**
	 * Obtem o �ltimo hist�rico.
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
	 * Obt�m hist�rico pela pk e pelo tsEntradaAdm.
	 * 
	 * @param entidade
	 * @return SaapHistDocAdm
	 */
	SaapHistDocAdm obtemPelaPkETsEntradaDoc(SaapDocAdmPresidencia entidade);
}
