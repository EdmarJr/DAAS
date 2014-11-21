/*
 * SaapPendenciaAdmPresidenciaDaoImpl.java
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

import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapPendenciaAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe para persistência do objeto <code>SaapPendenciaAdmPresidencia</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapPendenciaAdmPresidenciaDaoImpl extends HibernateDAOAbstrato<SaapPendenciaAdmPresidencia> 
		implements SaapPendenciaAdmPresidenciaDAO {

	/**
	 * @see br.jus.stj.saap.integracao.SaapPendenciaAdmPresidenciaDAO#
	 * 		consultarPendencias(br.jus.stj.saap.to.ConsultarPendenciasTO)
	 */
	public Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO){
    	Criteria criteria = novoCriteria();
    	Criteria critSaapAcompPendenciaAdm = null;
    	String desc = consultarendenciasTO.getEntidadePendencia().getDescEnderecoPendencia();
    	if(!isVazio(desc)) {
    		criteria.add(novoCriterioLike("descEnderecoPendencia", desc));
    	}
    	String assunto = consultarendenciasTO.getEntidadePendencia().getTxtAssuntoPendencia();
    	if(!isVazio(assunto)) {
    		criteria.add(novoCriterioLike("txtAssuntoPendencia", assunto));
    	}
    	String descAcomp = consultarendenciasTO.getDescAcompanhamentoPendencia();
    	if(!isVazio(descAcomp)) {
    		critSaapAcompPendenciaAdm = criteria.createCriteria("saapAcompPendenciaAdms");
    		critSaapAcompPendenciaAdm.add(novoCriterioLike("txtObsAcompPendencia", descAcomp));
    	}
    	Character ind = consultarendenciasTO.getEntidadePendencia().getIndSituacaoPendencia();
    	if(isReferencia(ind) && ind.charValue() != Constante.TODOS) {
    		criteria.add(Restrictions.eq("indSituacaoPendencia", ind.charValue()));
    	}
    	addCriteriaSaapPendenciaAdmPresidencia(consultarendenciasTO, criteria);
    	criteria.addOrder(novaOrdenacaoASC("tsEntradaPendencia"));
		return consultar(criteria);
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapPendenciaAdmPresidenciaDAO#
	 * 		desativar(br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
	 */
	public void desativar(SaapPendenciaAdmPresidencia entidade) {
		Query query = getQuery("desativar-pendencia");
		query.setInteger(0,entidade.getId());
		executar(query);
	}

	/**
	 * @see br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato#consultar()
	 */
	@Override
	public Collection<SaapPendenciaAdmPresidencia> consultar() {
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("indSituacaoPendencia", Constante.PENDENCIA_ATIVA));
		crit.addOrder(Order.asc("tsEntradaPendencia"));
    	return consultar(crit);
	}

	/**
	 * Adiciona critérios para SaapPendenciaAdmPresidencia.
	 * 
	 * @param to ConsultarPendenciasTO
	 * @param criteria Criteria
	 */
	protected void addCriteriaSaapPendenciaAdmPresidencia(ConsultarPendenciasTO to,
			Criteria criteria) {
    	if(to.getDataInicial() != null && to.getDataFinal() != null){
    		criteria.add(Restrictions.between("tsEntradaPendencia", to.getDataInicial(), 
    				UtilData.ajustarData(to.getDataFinal(),24,0,0)));
    	}else if(to.getDataInicial() != null && to.getDataFinal() == null) {
    		criteria.add(Restrictions.gt("tsEntradaPendencia", to.getDataInicial()));
    	}else if(to.getDataInicial() == null && to.getDataFinal() != null) {
    		criteria.add(Restrictions.lt("tsEntradaPendencia", UtilData.ajustarData(to.
    				getDataFinal(),24,0,0)));
    	}
	}
}
