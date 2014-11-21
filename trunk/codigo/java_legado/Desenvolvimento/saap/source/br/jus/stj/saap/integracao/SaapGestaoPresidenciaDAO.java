/*
 * SaapGestaoPresidenciaDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;
import java.util.Date;

import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persist�ncia do objeto SaapGestaoPresidencia
 *
 * @author Politec Inform�tica S/A
 */
public interface SaapGestaoPresidenciaDAO extends DAO<SaapGestaoPresidencia> {

	/**
	 * Consulta �ltima gest�o.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia consultarUltimaGestao();

	/**
	 * Consulta �ltima gest�o para altera��o.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia consultarUltimaGestaoParaAlteracao();

	/**
	 * Consulta gest�o(�es).
	 * 
	 * @param entidade
	 * @return Collection<SaapGestaoPresidencia>
	 */
	Collection<SaapGestaoPresidencia> consultarGestoes(SaapGestaoPresidencia entidade);
	
	/**
	 * Obter gest�o pelo per�odo.
	 * 
	 * @param dataPeriodo
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo);
}
