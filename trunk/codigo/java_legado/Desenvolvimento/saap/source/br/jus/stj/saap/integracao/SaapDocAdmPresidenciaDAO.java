/*
 * SaapDocAdmPresidenciaDAO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao;

import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.DAO;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.to.ConsultarDocumentosTO;

/**
 * Interface para persistência do objeto SaapDocAdmPresidencia
 *
 * @author Politec Informática S/A
 */
public interface SaapDocAdmPresidenciaDAO extends DAO<SaapDocAdmPresidencia> {
	// Interface gerada
	
	/**
	 * Consultar os documentos
	 * @return  Collection<SaapDocAdmPresidencia>
	 * @param consultarDocumentosTO ConsultarDocumentosTO
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO);
	
	/**
	 * Consultar os documentos similares
	 * @param documento SaapDocAdmPresidencia
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(
			SaapDocAdmPresidencia documento);

	/**
	 * Obter primeiro documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento);

	/**
	 * obter proximo documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento);

	/**
	 * obter anterior documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento);

	/**
	 * obter documento detalhado
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterDocumentoDetalhado(Integer identificador);

	/**
	 * obter documento
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterDocumento(Integer identificador);

	/**
	 * obter proximo documento
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterProximoDocumento(Integer identificador);

	/**
	 * obter documento anterior
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador);

	/**
	 * Desativar todos os documentos
	 * @param entidades Collection<SaapDocAdmPresidencia>
	 */
	public void desativarTodas(Collection<SaapDocAdmPresidencia> entidades);

	/**
	 * Desativar o documento
	 * @param entidade SaapDocAdmPresidencia
	 */
	public void desativar(SaapDocAdmPresidencia entidade);

	/**
	 * Carregar o PDF
	 * @param path SaapArqDocAdm
	 * @return byte[]
	 */
    public byte[] carregarPDF(final SaapArqDocAdm path);
    
    

}