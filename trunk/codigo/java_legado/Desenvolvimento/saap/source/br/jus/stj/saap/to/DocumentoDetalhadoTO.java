/*
 * DocumentoDetalhadoTO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.to;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;

/**
 * Respons�vel pela Altera��o
 * @author lmartins
 */
public class DocumentoDetalhadoTO extends SaapTOAbstrato{
	
	/**
	 * Vari�veis usadas no TO
	 */
	private SaapDocAdmPresidencia documentoBasico;
	private SaapGestaoPresidencia gestaoDocumento;
	private SaapHistDocAdm historicoDocumento;
	private String nomeUsuarioAlteracao;
	private Collection<SaapAdmtAdmPresidencia> andamentosDocumento;
	private Collection<SaapArqDocAdm> arquivosDocumento;
	private SaapAdmtAdmPresidencia andamentoDocumento;
	private SaapArqDocAdm arquivoDocumento;
	
	private Collection<ConsultarHistoricoAndamentosTO> listaHistAndamentos;
	
	private Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos;
	private Collection<ListarAndamentosTO> listaAndamentosTO;
	
	/**
	 * Retorna listaAndamentos.
	 * 
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public Collection<SaapAdmtAdmPresidenciaTO> getListaAndamentos() {
		if (listaAndamentos == null) {
			listaAndamentos = new ArrayList<SaapAdmtAdmPresidenciaTO>();
		}
		return listaAndamentos;
	}

	
	/**
	 * Atribui listaAndamentos.
	 * 
	 * @param listaAndamentos listaAndamentos
	 */
	public void setListaAndamentos(
			Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos) {
		this.listaAndamentos = listaAndamentos;
	}
	
	
	/**
	 * Retorna listaAndamentosTO.
	 * 
	 * @return Collection<ListarAndamentosTO>
	 */
	public Collection<ListarAndamentosTO> getListaAndamentosTO() {
		if (listaAndamentosTO == null) {
			listaAndamentosTO = new ArrayList<ListarAndamentosTO>();
		}
		return listaAndamentosTO;
	}

	
	/**
	 * Atribui listaAndamentosTO.
	 * 
	 * @param listaAndamentosTO listaAndamentosTO
	 */
	public void setListaAndamentosTO(
			Collection<ListarAndamentosTO> listaAndamentosTO) {
		this.listaAndamentosTO = listaAndamentosTO;
	}


	/**
	 * Atribui o hist�rico do documento
	 * @param historicoDocumento hist�rico do documento
	 */
	public void setHistoricoDocumento(SaapHistDocAdm historicoDocumento) {
		this.historicoDocumento = historicoDocumento;
	}


	/**
	 * Retorna andamentoDocumento
	 * 
	 * @return andamentoDocumento
	 */
	public SaapAdmtAdmPresidencia getAndamentoDocumento() {
		return andamentoDocumento;
	}


	/**
	 * Atribui andamento do documento
	 * @param andamentoDocumento  andamentoDocumento
	 */
	public void setAndamentoDocumento(SaapAdmtAdmPresidencia andamentoDocumento) {
		this.andamentoDocumento = andamentoDocumento;
	}

	
	/**
	 * Recupera o arquivo do documento
	 * getArquivoDocumento
	 * @return arquivoDocumento
	 */
	public SaapArqDocAdm getArquivoDocumento() {
		return arquivoDocumento;
	}


	/**
	 * Atribui o arquivo do documento
	 * 
	 * @param arquivoDocumento arquivoDocumento
	 */
	public void setArquivoDocumento(SaapArqDocAdm arquivoDocumento) {
		this.arquivoDocumento = arquivoDocumento;
	}


	/**
	 * Recupera o hist�rico do documento
	 * @return historicoDocumento
	 */
	public SaapHistDocAdm getHistoricoDocumento() {
		if (historicoDocumento == null) {
			historicoDocumento = new SaapHistDocAdm();
		}
			return historicoDocumento;
		}

	
	
	/**
	 * Recupera o documento b�sico
	 * 
	 * @return documentoBasico
	 */
	public SaapDocAdmPresidencia getDocumentoBasico() {
	if (documentoBasico == null) {
		documentoBasico = new SaapDocAdmPresidencia();
	}
		return documentoBasico;
	}
	
	
	/**
	 * Atribui o documento b�sico
	 * @param documentoBasico documentoBasico
	 */
	public void setDocumentoBasico(SaapDocAdmPresidencia documentoBasico) {
		this.documentoBasico = documentoBasico;
	}
	

	/**
	 * Recupera a gest�o do documento
	 * @return gestaoDocumento
	 */
	public SaapGestaoPresidencia getGestaoDocumento() {
		if (gestaoDocumento == null) {
			gestaoDocumento = new SaapGestaoPresidencia();
		}
		return gestaoDocumento;
	}

	/**
	 * Atribui a gest�o do documento
	 * 
	 * @param gestaoDocumento gestaoDocumento
	 */
	public void setGestaoDocumento(SaapGestaoPresidencia gestaoDocumento) {
		this.gestaoDocumento = gestaoDocumento;
	}

	/**
	 * 
	 * Recupera o nome do usu�rio da altera��o
	 * @return nomeUsuarioAlteracao
	 */
	public String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}
	
	/**
	 * Atribui o nome do usu�rio da altera��o
	 * 
	 * @param nomeUsuarioAlteracao nomeUsuarioAlteracao
	 */
	public void setNomeUsuarioAlteracao(String nomeUsuarioAlteracao) {
		this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
	}
	
	/**
	 * Recupera andamentos dos documentos
	 * @return andamentosDocumento
	 */
	public Collection<SaapAdmtAdmPresidencia> getAndamentosDocumento() {
		return andamentosDocumento;
	}
	
	/**
	 * Atribui andamentos dos documentos
	 * 
	 * @param andamentosDocumento andamentosDocumento
	 */
	public void setAndamentosDocumento(
			Collection<SaapAdmtAdmPresidencia> andamentosDocumento) {
		this.andamentosDocumento = andamentosDocumento;
	}
	
	/**
	 * Recupera lista de arquivos do documento
	 * @return arquivosDocumento
	 */
	public Collection<SaapArqDocAdm> getArquivosDocumento() {
		return arquivosDocumento;
	}
	
	
	/**
	 * Atribui lista de arquivos do documento
	 * 
	 * @param arquivosDocumento arquivosDocumento
	 */
	public void setArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento) {
		this.arquivosDocumento = arquivosDocumento;
	}

	
	/**
	 * Recupera descri��o do documento
	 * 
	 * @return getDescDoc
	 */
	public String getDescDoc() {
		return getDocumentoBasico().getDescDoc();
	}

	
	/**
	 * Recupera Data e hora do evento
	 * @return getDtHrEventoConvite()
	 */
	public Date getDtHrEventoConvite() {
		return getDocumentoBasico().getDtHrEventoConvite();
	}
	
	/**
	 * Recupera Data do Convite
	 * @return String
	 */
	public String getDataConvite() {
		Date dtConvite =getDocumentoBasico().getDtHrEventoConvite();
		int dia = UtilData.getDia(dtConvite);
		int mes = UtilData.getMes(dtConvite);
		int ano = UtilData.getAno(dtConvite);
		String data = (UtilString.completarAEsquerda(dia+"" ,"0",2) + "/" + 
				UtilString.completarAEsquerda(mes+"" ,"0",2)+ "/" + ano);
		return data;
	}

	
	/**
	 * Recupera o ID
	 * @return getId()
	 */
	public Integer getId() {
		return getDocumentoBasico().getId();
	}



	/**
	 * Recupera indicador de situa��o do documento
	 * @return getIndSituacaoDoc()
	 */
	public Character getIndSituacaoDoc() {
		return getDocumentoBasico().getIndSituacaoDoc();
	}

	
	/**
	 * Recupera indicador de situa��o do documento formatado
	 * @return getIndSituacaoDocFormatado()
	 */
	public String getIndSituacaoDocFormatado() {
		return getDocumentoBasico().getIndSituacaoDocFormatado();
	}

	/**
	 * Recupera indicador de tipo do documento
	 * 
	 * @return getIndTipoDoc()
	 */
	public Short getIndTipoDoc() {
		return getDocumentoBasico().getIndTipoDoc();
	}

	
	/**
	 * 
	 *  Recupera indicador de tipo do documento fommatado
	 * @return getIndTipoDocFormatado()
	 */
	public String getIndTipoDocFormatado() {
		return getDocumentoBasico().getIndTipoDocFormatado();
	}

	/**
	 * Recupera nome do local f�sico do documento
	 * 
	 * @return getNomeLocalFisicoDoc()
	 */
	public String getNomeLocalFisicoDoc() {
		return getDocumentoBasico().getNomeLocalFisicoDoc();
	}

	/**
	 * Recupera nome do respons�vel pelo convite
	 * 
	 * @return getNomeRespConvite()
	 */
	public String getNomeRespConvite() {
		return getDocumentoBasico().getNomeRespConvite();
	}

	/**
	 * Recupera lista de Andamentos
	 * @return getSaapAdmtAdmPresidencias()
	 */
	public Collection<SaapAdmtAdmPresidencia> getSaapAdmtAdmPresidencias() {
		return getDocumentoBasico().getSaapAdmtAdmPresidencias();
	}

	/**
	 *  Recupera lista de arquivos
	 * @return getSaapArqDocAdms()
	 */
	public Collection<SaapArqDocAdm> getSaapArqDocAdms() {
		return getDocumentoBasico().getSaapArqDocAdms();
	}

	/**
	 * Recupera lista de hist�rico de documentos
	 * @return getSaapHistDocAdms()
	 */
	public Collection<SaapHistDocAdm> getSaapHistDocAdms() {
		return getDocumentoBasico().getSaapHistDocAdms();
	}

	/**
	 * Recupera data de entrada
	 * @return getTsEntradaDoc()
	 */
	public Date getTsEntradaDoc() {
		return getDocumentoBasico().getTsEntradaDoc();
	}

	/**
	 * Recupera texto do assunto do documento
	 * 
	 * @return getTxtAssuntoDoc()
	 */
	public String getTxtAssuntoDoc() {
		return getDocumentoBasico().getTxtAssuntoDoc();
	}

	/**
	 * Recupera texto de observa��o do documento
	 * @return getTxtObsDoc()
	 */
	public String getTxtObsDoc() {
		return getDocumentoBasico().getTxtObsDoc();
	}

	/**
	 * 
	 * Verifica se o documento � COnvite
	 * @return isDocumentoConvite()
	 */
	public boolean isDocumentoConvite() {
		return getDocumentoBasico().isDocumentoConvite();
	}

	/**
	 * Atribui a descri��o do documento
	 * @param doc descri��o
	 */
	public void setDescDoc(String doc) {
		getDocumentoBasico().setDescDoc(doc);
	}

	/**
	 * Atribui a data e hora do convite
	 * @param hrEventoConvite Data
	 */
	public void setDtHrEventoConvite(Date hrEventoConvite) {
		getDocumentoBasico().setDtHrEventoConvite(hrEventoConvite);
	}

	/**
	 * Atribui a chave do documento
	 * @param seqDocAdm Integer
	 */
	public void setId(Integer seqDocAdm) {
		getDocumentoBasico().setId(seqDocAdm);
	}

	
	/**
	 * Atribui a situa��o do documento
	 * 
	 * @param situacaoDoc Character
	 */
	public void setIndSituacaoDoc(Character situacaoDoc) {
		getDocumentoBasico().setIndSituacaoDoc(situacaoDoc);
	}

	/**
	 * Atribui o tipo do documento
	 * 
	 * @param tipoDoc Short
	 */
	public void setIndTipoDoc(Short tipoDoc) {
		getDocumentoBasico().setIndTipoDoc(tipoDoc);
	}

	/**
	 * Atribui o local f�sico do documento
	 * 
	 * @param localFisicoDoc String
	 */
	public void setNomeLocalFisicoDoc(String localFisicoDoc) {
		getDocumentoBasico().setNomeLocalFisicoDoc(localFisicoDoc);
	}

	/**
	 * Atribui o nome do respons�vel pelo convite
	 * @param respConvite String
	 */
	public void setNomeRespConvite(String respConvite) {
		getDocumentoBasico().setNomeRespConvite(respConvite);
	}

	/**
	 * Atribui a lista de andamentos
	 * @param admtAdmPresidencias Collection<SaapAdmtAdmPresidencia>
	 */
	public void setSaapAdmtAdmPresidencias(
			Collection<SaapAdmtAdmPresidencia> admtAdmPresidencias) {
		getDocumentoBasico().setSaapAdmtAdmPresidencias(admtAdmPresidencias);
	}

	
	/**
	 * Atribui a lista de arquivos
	 * 
	 * @param arqDocAdms Collection<SaapArqDocAdm> 
	 */
	public void setSaapArqDocAdms(Collection<SaapArqDocAdm> arqDocAdms) {
		getDocumentoBasico().setSaapArqDocAdms(arqDocAdms);
	}

	/**
	 * 
	 * Atribui a lista de hist�rico de documentos
	 * @param histDocAdms Collection<SaapHistDocAdm>
	 */
	public void setSaapHistDocAdms(Collection<SaapHistDocAdm> histDocAdms) {
		getDocumentoBasico().setSaapHistDocAdms(histDocAdms);
	}

	/**
	 * Atribui a data de entrada
	 * 
	 * @param entradaDoc Date
	 */
	public void setTsEntradaDoc(Date entradaDoc) {
		getDocumentoBasico().setTsEntradaDoc(entradaDoc);
	}

	/**
	 * Atribui o texto do assunto do documento
	 * @param assuntoDoc String
	 */
	public void setTxtAssuntoDoc(String assuntoDoc) {
		getDocumentoBasico().setTxtAssuntoDoc(assuntoDoc);
	}

	/**
	 * Atribui o texto da observa��o do documento
	 * @param obsDoc String
	 */
	public void setTxtObsDoc(String obsDoc) {
		getDocumentoBasico().setTxtObsDoc(obsDoc);
	}

	/**
	 * Data final de vig�ncia da gest�o
	 * 
	 * @return getDtFinalVigGestao()
	 */
	public Date getDtFinalVigGestao() {
		return getGestaoDocumento().getDtFinalVigGestao();
	}

	/**
	 * Data inicial de vig�ncia da gest�o()
	 * 
	 * @return getDtInicialVigGestao()
	 */
	public Date getDtInicialVigGestao() {
		return getGestaoDocumento().getDtInicialVigGestao();
	}

	/**
	 * Recupera o nome do Ministro presidente
	 * 
	 * @return getNomeMinistroPresidente()
	 */
	public String getNomeMinistroPresidente() {
		return getGestaoDocumento().getNomeMinistroPresidente();
	}

	/**
	 * Data final da vig�ncia Gest�o
	 * 
	 * @return isDtFinalVigGestaoPreenchido()
	 */
	public boolean isDtFinalVigGestaoPreenchido() {
		return getGestaoDocumento().isDtFinalVigGestaoPreenchido();
	}

	/**
	 * Recupera o usu�rio 
	 * @return retorno Integer
	 */
	public Integer getUsuario() {
		
		Integer retorno = null;
		if (historicoDocumento!=null){
			retorno = historicoDocumento.getSeqUsuario();
		}
		
		return retorno;
	}

	/**
	 * Atribui data final da vig�ncia da gest�o
	 * @param finalVigGestao Date
	 */
	public void setDtFinalVigGestao(Date finalVigGestao) {
		getGestaoDocumento().setDtFinalVigGestao(finalVigGestao);
	}

	/**
	 * Atribui data inicial da vig�ncia da gest�o
	 * @param inicialVigGestao Date
	 */
	public void setDtInicialVigGestao(Date inicialVigGestao) {
		getGestaoDocumento().setDtInicialVigGestao(inicialVigGestao);
	}

	/**
	 * Atribui o nome do Ministro presidente
	 * @param ministroPresidente String
	 */
	public void setNomeMinistroPresidente(String ministroPresidente) {
		getGestaoDocumento().setNomeMinistroPresidente(ministroPresidente);
	}

	/**
	 * Recupera a lista de hist�rico de andamentos 
	 * @return getListaHistAndamentos()
	 */
	public Collection<ConsultarHistoricoAndamentosTO> getListaHistAndamentos() {
		if (listaHistAndamentos == null) {
			listaHistAndamentos = new ArrayList<ConsultarHistoricoAndamentosTO>();
		}
		return listaHistAndamentos;
	}

	/**
	 * Atribui a lista de hist�rico de andamentos 
	 * @param listaHistAndamentos Collection
	 */
	public void setListaHistAndamentos(
			Collection<ConsultarHistoricoAndamentosTO> listaHistAndamentos) {
		this.listaHistAndamentos = listaHistAndamentos;
	}
	
	
}
