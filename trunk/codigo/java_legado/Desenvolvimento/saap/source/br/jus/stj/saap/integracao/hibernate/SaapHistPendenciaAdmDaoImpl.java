/*
 * SaapHistPendenciaAdmDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapHistPendenciaAdmDAO;

/**
 * Classe para persistência do objeto <code>SaapHistPendenciaAdm</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapHistPendenciaAdmDaoImpl extends HibernateDAOAbstrato<SaapHistPendenciaAdm> 
		implements SaapHistPendenciaAdmDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistPendenciaAdmDAO#
	 * 		obterUltimoHistorico(java.lang.Integer)
	 */
	public SaapHistPendenciaAdm obterUltimoHistorico(Integer identificador) {
		Query query = getQuery("consultar-ultimo-historico-pendencia");
		query.setInteger(0, identificador);
		query.setInteger(1, identificador);
		return obter(query);
	}

    /**
     * @see br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato#
     * 		consultar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
     */
    @Override
	public Collection<SaapHistPendenciaAdm> consultar(SaapHistPendenciaAdm entidade) {
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("saapPendenciaAdmPresidencia.id", entidade.
					getSaapPendenciaAdmPresidencia().getId()));
		crit.addOrder(Order.asc("tsHistPendencia"));

    	return consultar(crit);
    }

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistPendenciaAdmDAO#
	 * 		obtemPelaPkETsHistPendencia(br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
	 */
	public SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade) {
		SaapHistPendenciaAdm saapHistPendenciaAdm = null;
		Criteria crit = novoCriteria();
		Criteria critSaapPendenciaAdmPresidencia = crit.
				createCriteria("saapPendenciaAdmPresidencia");
		critSaapPendenciaAdmPresidencia.add(Restrictions.eq("id", entidade.getId()));
		crit.addOrder(Order.desc("tsHistPendencia"));
		Collection<SaapHistPendenciaAdm> col = consultar(crit);
		if(!isVazio(col)) {
			saapHistPendenciaAdm = UtilColecao.getElementoDoIndice(col, 0);
		}
    	return saapHistPendenciaAdm;
    }
}
