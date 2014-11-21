/*
 * SaapDocAdmPresidenciaDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.util.UtilDate;

/**
 * Classe para persistência do objeto <code>SaapDocAdmPresidencia</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class SaapDocAdmPresidenciaDaoImpl extends HibernateDAOAbstrato<SaapDocAdmPresidencia> 
		implements SaapDocAdmPresidenciaDAO {
	// Classe gerada

	private static final Locale BRASIL = new Locale("pt", "BR");

	/**
	 * Obtem o documento detalhado
	 * @return SaapDocAdmPresidencia
	 * @param identificador Integer
	 */
	public SaapDocAdmPresidencia obterDocumentoDetalhado(Integer identificador) {
		Query query = getQuery("detalhar-documento");
		query.setInteger(0, identificador);
		return obter(query);
	}

	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * obterPrimeiroDocumentoSimilar(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento) {
        Query query = getQuery("consultar-documento-similar-primeiro");
		query.setShort(0, documento.getIndTipoDoc());
		if (documento.getDescDoc().length()>=15){
			query.setString(1, "%"+documento.getDescDoc().substring(0,15)+"%");			
		}else{
			query.setString(1, "%"+documento.getDescDoc()+"%");
		}

		query.setMaxResults(1);
        return obter(query);
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * obterProximoDocumentoSimilar(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento) {
        Query query = getQuery("consultar-documento-similar-proximo");
		query.setShort(0, documento.getIndTipoDoc());
		if (documento.getDescDoc().length()>=15){
			query.setString(1, "%"+documento.getDescDoc().substring(0,15)+"%");			
		}else{
			query.setString(1, "%"+documento.getDescDoc()+"%");
		}

		query.setInteger(2, documento.getId());
		query.setMaxResults(1);
        return obter(query);
	}
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * obterAnteriorDocumentoSimilar(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento) {
        Query query = getQuery("consultar-documento-similar-anterior");
		query.setShort(0, documento.getIndTipoDoc());
		if (documento.getDescDoc().length()>=15){
			query.setString(1, "%"+documento.getDescDoc().substring(0,15)+"%");			
		}else{
			query.setString(1, "%"+documento.getDescDoc()+"%");
		}

		query.setInteger(2, documento.getId());
		query.setMaxResults(1);
        return obter(query);
	}
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * obterProximoDocumento(java.lang.Integer)
	 */
	public SaapDocAdmPresidencia obterProximoDocumento(Integer identificador) {
	        Query query = getQuery("consultar-proximo-documento");
			query.setInteger(0, identificador);
			query.setMaxResults(1);
	        return obter(query);
	}

	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * obterDocumentoAnterior(java.lang.Integer)
	 */
	public SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador) {
        Query query = getQuery("consultar-documento-anterior");
        query.setInteger(0, identificador);
		query.setMaxResults(1);
        return obter(query);
	}

	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * desativar(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public void desativar(SaapDocAdmPresidencia entidade) {
		Query query = getQuery("desativar-documento");
		query.setInteger(0,entidade.getId());
		executar(query);
	}
	
	
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * desativarTodas(java.util.Collection)
	 */
	public void desativarTodas(Collection<SaapDocAdmPresidencia> entidades) {
		if (isReferencia(entidades)) {
			for (SaapDocAdmPresidencia entidade : entidades) {
				desativar(entidade);
			}
		}
	}

	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#
	 * consultarDocumentosSimilares(br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(
			SaapDocAdmPresidencia documento){
		Query query = getQuery("consultar-documentos-similares");
		query.setShort(0, documento.getIndTipoDoc());
		query.setString(1, "%"+documento.getDescDoc()+"%");
		return consultar(query);
	}
	
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#consultarDocumentos(
	 * br.jus.stj.saap.to.ConsultarDocumentosTO)
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO){
		Query query = getQuery("consultar-documentos");
		
		Date dataInicial = null;
		Date dataInicialConvite = null;
		String horaInicialConvite = null;
		Date dataFinalConvite = null;
		String horaFinalConvite = null;		
		
		if (consultarDocumentosTO.getDataInicial() != null) {
			dataInicial = consultarDocumentosTO.getDataInicial();
		}else {
			dataInicial = getDataInicial();
		}
		query.setDate(0,dataInicial);
		
		if (consultarDocumentosTO.getDataFinal()!=null){
			query.setTimestamp(1, UtilDate.formatarDataConvite(consultarDocumentosTO.getDataFinal(),"23:59"));
		}else{
			query.setTimestamp(1, UtilDate.formatarDataConvite(getDataFinal(),"23:59"));
		}

		if (dataInicial != null){
			query.setDate(2,dataInicial);		
		}else {
			query.setDate(2,null);
		}
		query.setString(3, "%"+consultarDocumentosTO.getDocEntidade().getDescDoc()+"%");
		query.setShort(4, consultarDocumentosTO.getDocEntidade().getIndTipoDoc());
		query.setShort(5, consultarDocumentosTO.getDocEntidade().getIndTipoDoc());
		query.setCharacter(6, consultarDocumentosTO.getDocEntidade().getIndSituacaoDoc());
		query.setCharacter(7, consultarDocumentosTO.getDocEntidade().getIndSituacaoDoc());
		query.setString(8, "%"+consultarDocumentosTO.getDocEntidade().getTxtAssuntoDoc()+"%");
		query.setString(9, "%"+consultarDocumentosTO.getDocEntidade().getNomeLocalFisicoDoc()+"%");
		if (consultarDocumentosTO.getDataInicialConvite() == null) {
			dataInicialConvite = getDataInicial();
		}else {
			dataInicialConvite = consultarDocumentosTO.getDataInicialConvite();
		}
		if (UtilString.isVazio(consultarDocumentosTO.getHoraInicialConvite())) {
			horaInicialConvite = "00:00";
		}else {
			horaInicialConvite = consultarDocumentosTO.getHoraInicialConvite();
		}
		query.setTimestamp(10,UtilDate.formatarDataConvite(dataInicialConvite,horaInicialConvite));
		
		if (consultarDocumentosTO.getDataFinalConvite() == null) {
			dataFinalConvite = getDataFinal();
		}else {
			dataFinalConvite = consultarDocumentosTO.getDataFinalConvite();
		}
		
		if (UtilString.isVazio(consultarDocumentosTO.getHoraFinalConvite())) {
			horaFinalConvite = "23:59";
		}else {
			horaFinalConvite = consultarDocumentosTO.getHoraFinalConvite();
		}
		
		query.setTimestamp(11,UtilDate.formatarDataConvite(dataFinalConvite,horaFinalConvite));
		
		if ((consultarDocumentosTO.getDocEntidade().getIndTipoDoc() != null) && 
				(consultarDocumentosTO.getDocEntidade().getIndTipoDoc().toString().equals("3"))){
			query.setTimestamp(12,UtilDate.formatarDataConvite(dataFinalConvite,horaFinalConvite));	
		}else {
			query.setTimestamp(12,null);
		}
		
		return consultar(query);
	}
	
	/**
	 * Crio uma data inicial possível
	 * @return Date 
	 */
    private Date getDataInicial() {
    	GregorianCalendar dataIni = new GregorianCalendar(BRASIL);
    	dataIni.set(Calendar.YEAR, 1800);
    	dataIni.set(Calendar.MONTH, 0);
    	dataIni.set(Calendar.DAY_OF_MONTH, 1);
    	return dataIni.getTime();    	
    }
    
	/**
	 * Crio uma data final possível
	 * @return Date 
	 */
    private Date getDataFinal() {
    	GregorianCalendar dataIni = new GregorianCalendar(BRASIL);
    	dataIni.set(Calendar.YEAR, 2900);
    	dataIni.set(Calendar.MONTH, 11);
    	dataIni.set(Calendar.DAY_OF_MONTH, 31);
    	return dataIni.getTime();    	
    }    
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#carregarPDF(
	 * br.jus.stj.saap.entidade.SaapArqDocAdm)
	 */
	public byte[] carregarPDF(final SaapArqDocAdm path){
		SmbFile pdf = null;
        byte[] retorno = null;
        try {
	        pdf = new SmbFile(path.getNomeArqDocComPath());
	        if(pdf.exists() && pdf.isFile() 
	                && pdf.canRead()){
	        	retorno = auxCarregarPDF(pdf,path);
	        }
		} catch (Exception e) {
			retorno = null;
		}
        return retorno;
	}

	/**
	 * @param pdf SmbFile
	 * @param path SaapArqDocAdm
	 * @return byte[]
	 */
	private byte[] auxCarregarPDF(SmbFile pdf, final SaapArqDocAdm path) {
		byte[] retorno = null;
		SmbFileInputStream fis = null;
        BufferedInputStream buffer = null;
        try{
            fis = new SmbFileInputStream(pdf);
            buffer = new BufferedInputStream(fis);
            if(pdf.getContentLength() > 0){
                byte[] ret = new byte[pdf.getContentLength()];
                if(buffer.read(ret) > 0) {
                	retorno = ret;
                }
            }
        }catch(FileNotFoundException fnfe){ logger.error(path, fnfe); 
        }catch(IOException ioe){ logger.error(path, ioe);
        }finally{
            try{
                if(buffer != null) {
                	buffer.close();
                }
            }catch(IOException ioe){ logger.error(path, ioe); }
            try{
                if(fis != null) {
                	fis.close();
                }
            }catch(IOException ioe){ logger.error(path, ioe); }
        }		
        return retorno;
	}
	
	/**
	 * @see br.jus.stj.saap.integracao.SaapDocAdmPresidenciaDAO#obterDocumento(java.lang.Integer)
	 */
	public SaapDocAdmPresidencia obterDocumento(Integer identificador) {
		Criteria crit = novoCriteria();
		crit.add(Restrictions.eq("id", identificador));
    	return obter(crit);
    }		
	
}
