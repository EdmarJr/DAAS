/*
 * SaapAcompPendenciaAdmDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.integracao.SaapAcompPendenciaAdmDAO;

/**
 * Classe para persistência do objeto <code>SaapAcompPendenciaAdm</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapAcompPendenciaAdmDaoImpl extends HibernateDAOAbstrato<SaapAcompPendenciaAdm> 
		implements SaapAcompPendenciaAdmDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapAcompPendenciaAdmDAO#consultarAcompanhamentos(
	 * 		br.jus.stj.saap.entidade.SaapAcompPendenciaAdm)
	 */
	public Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(
			SaapAcompPendenciaAdm entidade) {
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("saapPendenciaAdmPresidencia.id", entidade.
				getSaapPendenciaAdmPresidencia().getId()));
		if(isReferencia(entidade.getSeqUsuario()) && entidade.getSeqUsuario().intValue() > 0) {
			crit.add(Restrictions.eq("seqUsuario", entidade.getSeqUsuario()));
		}
    	return consultar(crit);
	}
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapAcompPendenciaAdmDAO#consultarUsuariosPendencia(
	 * br.jus.stj.saap.entidade.SaapAcompPendenciaAdm)
	 */
	public Collection<SaapAcompPendenciaAdm> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade) {
		Query query = getQuery("consultar-usuarios-pendencia");
		query.setInteger(0, entidade.getSaapPendenciaAdmPresidencia().getId());
		query.setInteger(1, entidade.getSeqUsuario());	
		return consultar(query);
	}
	
	
}
