/*
 * SaapGestaoPresidenciaDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;
import java.util.Date;

import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persistência do objeto SaapGestaoPresidencia
 *
 * @author Politec Informática S/A
 */
public interface SaapGestaoPresidenciaDAO extends DAO<SaapGestaoPresidencia> {

	/**
	 * Consulta última gestão.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia consultarUltimaGestao();

	/**
	 * Consulta última gestão para alteração.
	 * 
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia consultarUltimaGestaoParaAlteracao();

	/**
	 * Consulta gestão(ões).
	 * 
	 * @param entidade
	 * @return Collection<SaapGestaoPresidencia>
	 */
	Collection<SaapGestaoPresidencia> consultarGestoes(SaapGestaoPresidencia entidade);
	
	/**
	 * Obter gestão pelo período.
	 * 
	 * @param dataPeriodo
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo);
}
