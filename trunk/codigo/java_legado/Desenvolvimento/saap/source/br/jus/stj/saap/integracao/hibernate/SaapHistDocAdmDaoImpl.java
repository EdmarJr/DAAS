/*
 * SaapHistDocAdmDaoImpl.java
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
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.integracao.SaapHistDocAdmDAO;

/**
 * Classe para persistência do objeto <code>SaapHistDocAdm</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapHistDocAdmDaoImpl extends HibernateDAOAbstrato<SaapHistDocAdm> 
		implements SaapHistDocAdmDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistDocAdmDAO#obterUltimoHistorico(java.lang.Integer)
	 */
	public SaapHistDocAdm obterUltimoHistorico(Integer identificador) {
		Query query = getQuery("consultar-ultimo-historico-documento");
		query.setInteger(0, identificador);
		query.setInteger(1, identificador);
		return obter(query);
	}

    /**
     * @see br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato#
     * 		consultar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
     */
    @Override
	public Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade) {
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("saapDocAdmPresidencia.id", entidade.
				getSaapDocAdmPresidencia().getId()));
		crit.addOrder(Order.asc("tsHistDoc"));
    	return consultar(crit);
    }

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistDocAdmDAO#
	 * 		obtemPelaPkETsEntradaDoc(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapHistDocAdm obtemPelaPkETsEntradaDoc(SaapDocAdmPresidencia entidade) {
		SaapHistDocAdm saapHistDocAdm = null;
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("saapDocAdmPresidencia.id", entidade.getId()));
		crit.addOrder(Order.asc("tsHistDoc"));
		Collection<SaapHistDocAdm> col = consultar(crit);
		if(!isVazio(col)){
			saapHistDocAdm = UtilColecao.getElementoDoIndice(col, 0);
		}
    	return saapHistDocAdm;
    }
}
