/*
 * SaapHistAdmtAdmDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;

/**
 * Interface para persistência do objeto SaapHistAdmtAdm
 *
 * @author Politec Informática S/A
 */
public interface SaapHistAdmtAdmDAO extends DAO<SaapHistAdmtAdm> {

	/**
	 * Consultar o histórico de andamentos filtrando pelo documento.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @return Collection<SaapHistAdmtAdm>
	 */
	Collection<SaapHistAdmtAdm> consultar(SaapAdmtAdmPresidencia entidade);

	/**
	 * Obtém o histórico pela pk e pelo tsEntradaAdmt.
	 * 
	 * @param entidade
	 * @return SaapHistAdmtAdm
	 */
	SaapHistAdmtAdm obtemPelaPkETsEntradaAdmt(SaapAdmtAdmPresidencia entidade);
}
