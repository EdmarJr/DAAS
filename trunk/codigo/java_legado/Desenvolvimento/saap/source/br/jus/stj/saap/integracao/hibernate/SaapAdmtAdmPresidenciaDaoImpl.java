/*
 * SaapAdmtAdmPresidenciaDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao.hibernate;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.comum.calculadores.UtilSomadoresDeNumero;
import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.util.UtilDate;

/**
 * Classe para persistência do objeto <code>SaapAdmtAdmPresidencia</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapAdmtAdmPresidenciaDaoImpl extends HibernateDAOAbstrato<SaapAdmtAdmPresidencia> 
		implements SaapAdmtAdmPresidenciaDAO {

	private static final Locale BRASIL = new Locale("pt", "BR");

	/**
	 * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#obterProximaChave()
	 */
	public Integer obterProximaChave() {
		Integer chave = null;
		Criteria criteria = novoCriteria();
		criteria.setProjection(Projections.max("id.seqAdmtAdm"));
		chave = (Integer) criteria.uniqueResult();
		return UtilSomadoresDeNumero.somar(chave, 1);
	}

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentos(java.lang.Integer)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentos(Integer idDocumento){
        Query query = getQuery("consultar-andamentos");
        query.setInteger(0,idDocumento);
        return consultar(query);
    }

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentosTipoPendencia(java.lang.Integer)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosTipoPendencia(Integer idPendencia){
        Query query = getQuery("consultar-andamentos-pendencia");
        query.setInteger(0,idPendencia);
        return consultar(query);
    }

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentosPeloFiltro(br.jus.stj.saap.to.ConsultarAndamentosTO)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(
    		ConsultarAndamentosTO to){
    	Criteria criteria = novoCriteria();
    	criteria.setFetchMode("saapDocAdmPresidencia", FetchMode.SELECT);
    	addCriteriaSaapDocAdmPresidencia(to, criteria);
    	addCriteriaSaapAdmtAdmPresidencia(to, criteria);
       	criteria.addOrder(novaOrdenacaoASC("descAdmtAdm"));
    	criteria.addOrder(novaOrdenacaoASC("tsEntradaAdmt"));
         return consultar(criteria);
    }

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentosPendenciaPeloFiltro(
     * 		br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to){

    	Query query = null;

    	if (!isVazio(to.getAaUsuario().getNomeNicknameUsuario())) {
        	query = getQuery("consultar-andament-pend-filtro-acompanhamento");
    		query.setString(5, "%"+to.getAaUsuario().getNomeNicknameUsuario()+"%");
    	}else {
        	query = getQuery("consultar-andament-pend-filtro");
    	}

    	if(to.getDataInicio() != null) {
    		query.setDate(0,to.getDataInicio());
    	}else {
    		query.setDate(0,getDataInicial());
    	}

    	query.setDate(1,getDataFinal(to.getDataFim()));

		query.setString(2, "%"+to.getSaapAdmtAdmPresidencia().getDescAdmtAdm()+"%");			
		query.setString(3, "%"+to.getSaapPendenciaAdmPresidencia().getDescEnderecoPendencia()+"%");
		query.setString(4, "%"+to.getSaapPendenciaAdmPresidencia().getTxtAssuntoPendencia()+"%");

		return consultar(query);
    }  

    private Date getDataInicial() {
    	GregorianCalendar dataIni = new GregorianCalendar(BRASIL);
    	dataIni.set(Calendar.YEAR, 1800);
    	dataIni.set(Calendar.MONTH, 0);
    	dataIni.set(Calendar.DAY_OF_MONTH, 1);
    	return dataIni.getTime();    	
    }

    private Date getDataFinal(Date dtFim) {
    	
    	GregorianCalendar dataFim = new GregorianCalendar(BRASIL);
    	
    	if (dtFim != null) {
    		dtFim = UtilDate.adicionaDias(dtFim, 1);
    	}else {
    		dataFim.add(Calendar.DAY_OF_MONTH, 1);
    		dtFim = dataFim.getTime();  
    	}
    	return dtFim;
    }

	/**
	 * Adiciona critérios para SaapAdmtAdmPresidencia.
	 * 
	 * @param to ConsultarAndamentosTO
	 * @param criteria Criteria
	 */
	protected void addCriteriaSaapAdmtAdmPresidencia(ConsultarAndamentosTO to,
			Criteria criteria) {
		if(!isVazio(to.getDescAdmtAdm())){
    		criteria.add(novoCriterioLike("descAdmtAdm", to.getDescAdmtAdm()));
    	}
    	if(to.getDataInicio() != null && to.getDataFim() != null){
    		criteria.add(Restrictions.between("tsEntradaAdmt", to.getDataInicio(), 
    				UtilData.ajustarData(to.getDataFim(),24,0,0)));
    	}else if(to.getDataInicio() != null && to.getDataFim() == null) {
    		criteria.add(Restrictions.gt("tsEntradaAdmt", to.getDataInicio()));
    	}else if(to.getDataInicio() == null && to.getDataFim() != null) {
    		criteria.add(Restrictions.lt("tsEntradaAdmt", UtilData.ajustarData(to.
    				getDataFim(),24,0,0)));
    	}
	}

	/**
	 * Adiciona critérios para SaapDocAdmPresidencia.
	 * 
	 * @param to ConsultarAndamentosTO
	 * @param criteria Criteria
	 */
	protected void addCriteriaSaapDocAdmPresidencia(ConsultarAndamentosTO to,
			Criteria criteria) {
		Criteria criteriaSaapDocAdmPresidencia = null;
		if(isAlgumCampoPreenchido(to)) {
			criteriaSaapDocAdmPresidencia = criteria.createCriteria("saapDocAdmPresidencia");
	    	if(isReferencia(to.getSaapDocAdmPresidencia().getId()) && 
	    			to.getSaapDocAdmPresidencia().getId().intValue() > 0) {
	    		criteriaSaapDocAdmPresidencia.add(Restrictions.eq("id", to.
	    				getSaapDocAdmPresidencia().getId()));
	    	}
	    	if(!isVazio(to.getSaapDocAdmPresidencia().getNomeLocalFisicoDoc())) {
	    		criteriaSaapDocAdmPresidencia.add(novoCriterioLike("nomeLocalFisicoDoc", to.
	    			getSaapDocAdmPresidencia().getNomeLocalFisicoDoc()));
	    	}
	    	if(isReferencia(to.getSaapDocAdmPresidencia().getIndTipoDoc()) && 
	    			to.getSaapDocAdmPresidencia().getIndTipoDoc().shortValue() > 0) {
	    		criteriaSaapDocAdmPresidencia.add(Restrictions.eq("indTipoDoc", to.
	    			getSaapDocAdmPresidencia().getIndTipoDoc()));
	    	}
	    	if(!isVazio(to.getSaapDocAdmPresidencia().getDescDoc())){
	    		criteriaSaapDocAdmPresidencia.add(novoCriterioLike("descDoc", to.
	    			getSaapDocAdmPresidencia().getDescDoc()));
	    	}
		}
	}

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentosPeloDocumento(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloDocumento(
    		SaapDocAdmPresidencia entidade){
    	Criteria criteria = novoCriteria();
   		Criteria criteriaSaapDocAdmPresidencia = criteria.createCriteria("saapDocAdmPresidencia");
    	criteriaSaapDocAdmPresidencia.add(Restrictions.eq("id", entidade.getId()));
    	criteria.addOrder(novaOrdenacaoASC("tsEntradaAdmt"));
    	criteria.addOrder(novaOrdenacaoASC("descAdmtAdm"));
        return consultar(criteria);
    }

    /**
     * @see br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO#
     * 		consultarAndamentosPelaPendencia(br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade){
    	Criteria criteria = novoCriteria();
   		Criteria criteriaSaapPendenciaAdmPresidencia = criteria.
   						createCriteria("saapPendenciaAdmPresidencia");
   		criteriaSaapPendenciaAdmPresidencia.add(Restrictions.eq("id", entidade.getId()));
    	criteria.addOrder(novaOrdenacaoASC("tsEntradaAdmt"));
    	criteria.addOrder(novaOrdenacaoASC("descAdmtAdm"));
        return consultar(criteria);
    }

    /**
     * Verifica se algum campo foi preenchido.
     * 
     * @param to ConsultarAndamentosTO
     * @return boolean
     */
    protected boolean isAlgumCampoPreenchido(ConsultarAndamentosTO to) {
    	return (isReferencia(to.getSaapDocAdmPresidencia().getId()) && 
    			to.getSaapDocAdmPresidencia().getId().intValue() > 0) ||
    			!isVazio(to.getSaapDocAdmPresidencia().getNomeLocalFisicoDoc()) ||
    			(isReferencia(to.getSaapDocAdmPresidencia().getIndTipoDoc()) && 
    	    	to.getSaapDocAdmPresidencia().getIndTipoDoc().shortValue() > 0) ||
    	    	!isVazio(to.getSaapDocAdmPresidencia().getDescDoc());
    }
}
