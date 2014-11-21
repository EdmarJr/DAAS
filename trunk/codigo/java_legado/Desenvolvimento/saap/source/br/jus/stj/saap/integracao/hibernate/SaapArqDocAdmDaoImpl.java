/*
 * SaapArqDocAdmDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.integracao.SaapArqDocAdmDAO;

/**
 * Classe para persistência do objeto <code>SaapArqDocAdm</code>.
 *
 * @author Politec Informática S/A
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
