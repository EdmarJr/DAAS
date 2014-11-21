/*
 * DominioDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.saap.integracao.DominioDAO;

/**
 * Classe para persistência do objeto <code>Dominio</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class DominioDaoImpl extends HibernateDAOAbstrato<Dominio> 
		implements DominioDAO {

    /**
     * @see br.jus.stj.saap.integracao.DominioDAO#consultarTiposDocumentos()
     */
    public Collection<Dominio> consultarTiposDocumentos() {
    	Criteria criteria = novoCriteria();
    	criteria.add(Restrictions.eq("id.nomeColuna", "IND_TIPO_DOC"));
    	criteria.add(Restrictions.eq("indStDominio", "A"));
    	criteria.addOrder(Order.asc("descEstado"));
        return consultar(criteria);
    }
}
