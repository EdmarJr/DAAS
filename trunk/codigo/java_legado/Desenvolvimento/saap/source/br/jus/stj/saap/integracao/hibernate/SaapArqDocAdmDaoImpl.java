/*
 * SaapArqDocAdmDaoImpl.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.integracao.SaapArqDocAdmDAO;

/**
 * Classe para persist�ncia do objeto <code>SaapArqDocAdm</code>.
 *
 * @author Politec Inform�tica S/A
 */
@Repository
class SaapArqDocAdmDaoImpl extends HibernateDAOAbstrato<SaapArqDocAdm> 
		implements SaapArqDocAdmDAO {
	// Classe gerada
    
    
    
    /**
     * @param idDocumento
     * @return
     */
    public Collection<SaapArqDocAdm> consultarAnexos(Integer idDocumento){
        Query query = getQuery("consultar-anexos");
        query.setInteger(0,idDocumento);
        return consultar(query);
    }
    
}
