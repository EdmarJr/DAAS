/*
 * SaapDocAdmPresidenciaBO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import br.jus.stj.alp01.arquitetura.negocio.bo.BO;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarDocumentosTO;

/**
 * Classe para encapsulamento da regra de negocio do objeto SaapDocAdmPresidencia.
 *
 * @author Politec Informática
 */
 @Component
public class SaapDocAdmPresidenciaBO extends BO<SaapDocAdmPresidencia> {

	private SaapDocAdmPresidenciaDAO dao;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	SaapDocAdmPresidenciaBO () {
		// Construtor default
	}

	/**
	 * Obtem o próximo documento
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterProximoDocumento(Integer identificador){
	    return getDao().obterProximoDocumento(identificador);
	}

	/**
	 * Obtem o primeiro documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getDao().obterPrimeiroDocumentoSimilar(documento);
	}
	
	/**
	 * Obtem o documento similar anterior
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getDao().obterAnteriorDocumentoSimilar(documento);
	}

	/**
	 * Obtem o proximo documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getDao().obterProximoDocumentoSimilar(documento);
	}
	
	/**
	 * Obtem o documento anterior
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador){
	    return getDao().obterDocumentoAnterior(identificador);
	}

	/**
	 * Desativar  o documento
	 * @param entidade SaapDocAdmPresidencia
	 */
	public void desativar(SaapDocAdmPresidencia entidade){
		getDao().desativar(entidade);
	}

	/**
	 * Desativar todos os documentos
	 * @param entidades Collection<SaapDocAdmPresidencia>
	 */
	public void desativarTodas(Collection<SaapDocAdmPresidencia> entidades) {
		getDao().desativarTodas(entidades);
	}

	
	/**
	 * Consultar Documentos Similares
	 * @param documento SaapDocAdmPresidencia
	 * 
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(
			SaapDocAdmPresidencia documento){
		return  getDao().consultarDocumentosSimilares(documento);
	}
	
	/**
	 * Consultar os documentos
	 * @param consultarDocumentosTO ConsultarDocumentosTO
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO){
		return getDao().consultarDocumentos(consultarDocumentosTO);
	}

	/**
	 * Carregar o PDF
	 * @param path SaapArqDocAdm
	 * @return byte[]
	 */
    public byte[] carregarPDF(final SaapArqDocAdm path)  {
        return getDao().carregarPDF(path);
    }

	
	/**
	 * Sobrescrita do método para injeção da implementação da Dao.
	 * 
	 * @param _dao DAO a ser injetada.
	 */
	@Resource(name = "saapDocAdmPresidenciaDaoImpl")
	protected void setDao(SaapDocAdmPresidenciaDAO _dao) {
		this.dao = _dao;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#getDao()
	 */
	@Override
	protected SaapDocAdmPresidenciaDAO getDao() {
		return dao;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.bo.BO#inserir(br.
	 * jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	public Serializable inserir(SaapDocAdmPresidencia entidade) {
		entidade.setTsEntradaDoc(new Timestamp(new Date().getTime()));
		return super.inserir(entidade);
	}

	/**
	 * Obtem o documento através do identificador
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia obterDocumento(Integer identificador){
	    return getDao().obterDocumento(identificador);
	}	
	
}
