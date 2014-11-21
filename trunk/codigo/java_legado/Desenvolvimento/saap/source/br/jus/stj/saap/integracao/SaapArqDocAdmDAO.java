/*
 * SaapArqDocAdmDAO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapArqDocAdm;

/**
 * Interface para persist�ncia do objeto SaapArqDocAdm
 *
 * @author Politec Inform�tica S/A
 */
public interface SaapArqDocAdmDAO extends DAO<SaapArqDocAdm> {
	// Interface gerada
    
    public Collection<SaapArqDocAdm> consultarAnexos(Integer idDocumento);
    
}