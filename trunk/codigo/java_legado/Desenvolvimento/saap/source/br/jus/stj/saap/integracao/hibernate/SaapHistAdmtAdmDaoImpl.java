/*
 * SaapHistAdmtAdmDaoImpl.java
 * 
 * Data de criação: 29/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.integracao.SaapHistAdmtAdmDAO;

/**
 * Classe para persistência do objeto <code>SaapHistAdmtAdm</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapHistAdmtAdmDaoImpl extends HibernateDAOAbstrato<SaapHistAdmtAdm> 
		implements SaapHistAdmtAdmDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistAdmtAdmDAO#
	 * 		consultar(br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia)
	 */
	public Collection<SaapHistAdmtAdm> consultar(SaapAdmtAdmPresidencia entidade) {
		Criteria crit = novoCriteria();
		Criteria critSaapAdmtAdmPresidencia = crit.createCriteria("saapAdmtAdmPresidencia");
		//Verifica se é histórico de Andamentos da Pendência ou Histórico de Andamentos
		if (UtilObjeto.isReferencia(entidade.getSaapPendenciaAdmPresidencia())) {
			critSaapAdmtAdmPresidencia.add(Restrictions.eq("saapPendenciaAdmPresidencia.id" , 
					entidade.getSaapPendenciaAdmPresidencia().getId()));
		}else {
			critSaapAdmtAdmPresidencia.add(Restrictions.eq("saapDocAdmPresidencia.id",	
					entidade.getSaapDocAdmPresidencia().getId()));
		}
		critSaapAdmtAdmPresidencia.addOrder(Order.asc("id.seqAdmtAdm"));
		crit.addOrder(Order.asc("tsHistAdmt"));
    	return consultar(crit);
    }

	/**
	 * @see br.jus.stj.saap.integracao.SaapHistAdmtAdmDAO#
	 * 		obtemPelaPkETsEntradaAdmt(br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia)
	 */
	public SaapHistAdmtAdm obtemPelaPkETsEntradaAdmt(SaapAdmtAdmPresidencia entidade) {
		SaapHistAdmtAdm saapHistAdmtAdm = null;
		Criteria crit = novoCriteria();
		Criteria critSaapAdmtAdmPresidencia = crit.createCriteria("saapAdmtAdmPresidencia");
		critSaapAdmtAdmPresidencia.add(Restrictions.eq("id", entidade.getId()));
		crit.addOrder(Order.desc("tsHistAdmt"));
		Collection<SaapHistAdmtAdm> col = consultar(crit);
		if(!isVazio(col)) {
			saapHistAdmtAdm = UtilColecao.getElementoDoIndice(col, 0);
		}
    	return saapHistAdmtAdm;
    }
}
