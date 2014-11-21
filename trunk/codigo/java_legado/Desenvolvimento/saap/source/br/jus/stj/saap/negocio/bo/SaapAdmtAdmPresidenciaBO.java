/*
 * SaapAdmtAdmPresidenciaBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.bo;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.alp01.arquitetura.negocio.bo.BO;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.integracao.SaapAdmtAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapAdmtAdmPresidencia.
 *
 * @author Politec Informática
 */
 @Component
public class SaapAdmtAdmPresidenciaBO extends BO<SaapAdmtAdmPresidencia> {

	private SaapAdmtAdmPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	SaapAdmtAdmPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do método para injeção da implementação da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapAdmtAdmPresidenciaDaoImpl")
	protected void setDao(SaapAdmtAdmPresidenciaDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapAdmtAdmPresidenciaDAO getDao() {
		return dao;
	}

	/**
	 * Obtém a próxima chave.
	 * 
	 * @return Integer
	 */
	public Integer obterProximaChave(){
		return getDao().obterProximaChave();
	}

    /**
     * Consulta andamento(s) pelo idDocumento.
     * 
     * @param idDocumento Integer
     * @return Collection<SaapAdmtAdmPresidencia>
     */
	public Collection<SaapAdmtAdmPresidencia> consultarAndamentos(Integer idDocumento){
		return getDao().consultarAndamentos(idDocumento);
	}

    /**
     * Consulta andamento(s) pelo tipo da pendência.
     * 
     * @param idPendencia Integer
     * @return Collection<SaapAdmtAdmPresidencia>
     */
	public Collection<SaapAdmtAdmPresidencia> consultarAndamentosTipoPendencia(Integer idPendencia){
		return getDao().consultarAndamentosTipoPendencia(idPendencia);
	}

    /**
     * Consulta o(s) andamento(s) pelo filtro.
     * 
     * @param to ConsultarAndamentosTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(
    		ConsultarAndamentosTO to) {
    	return getDao().consultarAndamentosPeloFiltro(to);
    }

    /**
     * Consulta o(s) andamento(s) pelo documento.
     * 
     * @param entidade SaapDocAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloDocumento(
    		SaapDocAdmPresidencia entidade) {
    	return getDao().consultarAndamentosPeloDocumento(entidade);
    }
    
    /**
     * Consulta o(s) andamento(s) pela pendência
     * 
     * @param entidade SaapPendenciaAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade) {
    	return getDao().consultarAndamentosPelaPendencia(entidade);
    }    
    
    
    /**
     * Consulta o(s) andamento(s) da(s) pendência(s)
     * 
     * @param to ConsultarAndamentosPendenciaTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to) {
    	return getDao().consultarAndamentosPendenciaPeloFiltro(to);
    }    
    
}
