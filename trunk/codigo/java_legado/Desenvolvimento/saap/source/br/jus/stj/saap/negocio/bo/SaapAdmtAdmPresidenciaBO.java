/*
 * SaapAdmtAdmPresidenciaBO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
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
 * @author Politec Inform�tica
 */
 @Component
public class SaapAdmtAdmPresidenciaBO extends BO<SaapAdmtAdmPresidencia> {

	private SaapAdmtAdmPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	SaapAdmtAdmPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o da Dao.
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
	 * Obt�m a pr�xima chave.
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
     * Consulta andamento(s) pelo tipo da pend�ncia.
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
     * Consulta o(s) andamento(s) pela pend�ncia
     * 
     * @param entidade SaapPendenciaAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade) {
    	return getDao().consultarAndamentosPelaPendencia(entidade);
    }    
    
    
    /**
     * Consulta o(s) andamento(s) da(s) pend�ncia(s)
     * 
     * @param to ConsultarAndamentosPendenciaTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to) {
    	return getDao().consultarAndamentosPendenciaPeloFiltro(to);
    }    
    
}
