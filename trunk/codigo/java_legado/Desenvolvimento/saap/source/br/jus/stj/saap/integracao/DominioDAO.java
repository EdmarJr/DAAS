/*
 * DominioDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.alp01.arquitetura.integracao.DAO;

/**
 * Interface para persistência do objeto Dominio
 *
 * @author Politec Informática S/A
 */
public interface DominioDAO extends DAO<Dominio> {

    /**
     * Consulta os tipos de documentos.
     * 
     * @return Collection<Dominio>
     */
	Collection<Dominio> consultarTiposDocumentos();
}