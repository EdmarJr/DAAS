/*
 * SaapGestaoPresidenciaDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao.hibernate;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO;

/**
 * Classe para persistência do objeto <code>SaapGestaoPresidencia</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapGestaoPresidenciaDaoImpl extends HibernateDAOAbstrato<SaapGestaoPresidencia> 
		implements SaapGestaoPresidenciaDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO#consultarUltimaGestao()
	 */
	public SaapGestaoPresidencia consultarUltimaGestao() {
		Query query = getQuery("consultar-gestao-anterior");
		return obter(query);
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO#consultarUltimaGestaoParaAlteracao()
	 */
	public SaapGestaoPresidencia consultarUltimaGestaoParaAlteracao() {
		Query query = getQuery("consultar-gestao-anterior-alteracao");
		return obter(query);
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO#
	 * 		obterGestaoPeloPeriodo(java.util.Date)
	 */
	public SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo) {
		Query query = getQuery("obter-gestao-do-documento");
		query.setDate(0,dataPeriodo);
		query.setDate(1,dataPeriodo);
		return obter(query);
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapGestaoPresidenciaDAO#
	 * 		consultarGestoes(br.jus.stj.saap.entidade.SaapGestaoPresidencia)
	 */
	public Collection<SaapGestaoPresidencia> consultarGestoes(SaapGestaoPresidencia entidade) {
		Criteria criteria = novoCriteria();
		if(!isVazio(entidade.getNomeMinistroPresidente())){
    		criteria.add(novoCriterioLike("nomeMinistroPresidente", entidade.
    				getNomeMinistroPresidente()));
    	}
    	if(entidade.getDtInicialVigGestao() != null && 
    			entidade.getDtFinalVigGestao() != null){
    		criteria.add(Restrictions.gt("dtInicialVigGestao", 
    				UtilData.ajustarData(entidade.getDtInicialVigGestao(),-24,0,0)));
    		criteria.add(Restrictions.lt("dtFinalVigGestao", 
    				UtilData.ajustarData(entidade.getDtFinalVigGestao(),24,0,0)));
    	}else if(entidade.getDtInicialVigGestao() != null && 
    				entidade.getDtFinalVigGestao() == null) {
    		criteria.add(Restrictions.gt("dtInicialVigGestao", 
    				UtilData.ajustarData(entidade.getDtInicialVigGestao(),-24,0,0)));
    	}else if(entidade.getDtInicialVigGestao() == null && 
    				entidade.getDtFinalVigGestao() != null) {
    		criteria.add(Restrictions.lt("dtFinalVigGestao", 
    				UtilData.ajustarData(entidade.getDtFinalVigGestao(),24,0,0)));
    	}
    	criteria.addOrder(novaOrdenacaoASC("nomeMinistroPresidente"));
		return consultar(criteria);
	}
}
