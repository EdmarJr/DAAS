/*
 * SaapArqDocAdmDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapArqDocAdm;

/**
 * Interface para persistência do objeto SaapArqDocAdm
 *
 * @author Politec Informática S/A
 */
public interface SaapArqDocAdmDAO extends DAO<SaapArqDocAdm> {
	// Interface gerada
    
    public Collection<SaapArqDocAdm> consultarAnexos(Integer idDocumento);
    
}