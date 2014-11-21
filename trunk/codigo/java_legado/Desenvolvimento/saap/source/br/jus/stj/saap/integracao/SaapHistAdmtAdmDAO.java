/*
 * SaapHistAdmtAdmDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;

/**
 * Interface para persist�ncia do objeto SaapHistAdmtAdm
 *
 * @author Politec Inform�tica S/A
 */
public interface SaapHistAdmtAdmDAO extends DAO<SaapHistAdmtAdm> {

	/**
	 * Consultar o hist�rico de andamentos filtrando pelo documento.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @return Collection<SaapHistAdmtAdm>
	 */
	Collection<SaapHistAdmtAdm> consultar(SaapAdmtAdmPresidencia entidade);

	/**
	 * Obt�m o hist�rico pela pk e pelo tsEntradaAdmt.
	 * 
	 * @param entidade
	 * @return SaapHistAdmtAdm
	 */
	SaapHistAdmtAdm obtemPelaPkETsEntradaAdmt(SaapAdmtAdmPresidencia entidade);
}
