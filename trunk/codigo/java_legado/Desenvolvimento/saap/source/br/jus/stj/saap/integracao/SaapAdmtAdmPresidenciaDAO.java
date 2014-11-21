/*
 * SaapAdmtAdmPresidenciaDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;

/**
 * Interface para persistência do objeto SaapAdmtAdmPresidencia
 *
 * @author Politec Informática S/A
 */
public interface SaapAdmtAdmPresidenciaDAO extends DAO<SaapAdmtAdmPresidencia> {

	/**
	 * Obtém a próxima chave.
	 * 
	 * @return Integer
	 */
	Integer obterProximaChave();

    /**
     * Consulta andamento(s) pelo idDocumento.
     * 
     * @param idDocumento Integer
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentos(Integer idDocumento);

    /**
     * Consulta andamento(s) pelo tipo da pendência.
     * 
     * @param idPendencia Integer
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosTipoPendencia(Integer idPendencia);

    /**
     * Consulta o(s) andamento(s) pelo filtro.
     * 
     * @param to ConsultarAndamentosTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(ConsultarAndamentosTO to);
    
    /**
     * Consulta o(s) andamento(s) da pendência pelo filtro.
     * 
     * @param to ConsultarAndamentosPendenciaTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to);    

    /**
     * Consulta o(s) andamento(s) pelo documento.
     * 
     * @param entidade SaapDocAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloDocumento(
    		SaapDocAdmPresidencia entidade);
    

    /**
     * Consulta o(s) andamento(s) pelo documento.
     * 
     * @param entidade SaapPendenciaAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade);    
    
}
