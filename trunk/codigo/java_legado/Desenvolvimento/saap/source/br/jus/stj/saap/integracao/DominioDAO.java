/*
 * DominioDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persist�ncia do objeto Dominio
 *
 * @author Politec Inform�tica S/A
 */
public interface DominioDAO extends DAO<Dominio> {

    /**
     * Consulta os tipos de documentos.
     * 
     * @return Collection<Dominio>
     */
	Collection<Dominio> consultarTiposDocumentos();
}