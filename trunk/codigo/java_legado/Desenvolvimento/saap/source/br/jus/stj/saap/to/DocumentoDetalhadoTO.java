/*
 * DocumentoDetalhadoTO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
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
 * Responsável pela Alteração
 * @author lmartins
 */
public class DocumentoDetalhadoTO extends SaapTOAbstrato{
	
	/**
	 * Variáveis usadas no TO
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
	 * Atribui o histórico do documento
	 * @param historicoDocumento histórico do documento
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
	 * Recupera o histórico do documento
	 * @return historicoDocumento
	 */
	public SaapHistDocAdm getHistoricoDocumento() {
		if (historicoDocumento == null) {
			historicoDocumento = new SaapHistDocAdm();
		}
			return historicoDocumento;
		}

	
	
	/**
	 * Recupera o documento básico
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
	 * Atribui o documento básico
	 * @param documentoBasico documentoBasico
	 */
	public void setDocumentoBasico(SaapDocAdmPresidencia documentoBasico) {
		this.documentoBasico = documentoBasico;
	}
	

	/**
	 * Recupera a gestão do documento
	 * @return gestaoDocumento
	 */
	public SaapGestaoPresidencia getGestaoDocumento() {
		if (gestaoDocumento == null) {
			gestaoDocumento = new SaapGestaoPresidencia();
		}
		return gestaoDocumento;
	}

	/**
	 * Atribui a gestão do documento
	 * 
	 * @param gestaoDocumento gestaoDocumento
	 */
	public void setGestaoDocumento(SaapGestaoPresidencia gestaoDocumento) {
		this.gestaoDocumento = gestaoDocumento;
	}

	/**
	 * 
	 * Recupera o nome do usuário da alteração
	 * @return nomeUsuarioAlteracao
	 */
	public String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}
	
	/**
	 * Atribui o nome do usuário da alteração
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
	 * Recupera descrição do documento
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
	 * Recupera indicador de situação do documento
	 * @return getIndSituacaoDoc()
	 */
	public Character getIndSituacaoDoc() {
		return getDocumentoBasico().getIndSituacaoDoc();
	}

	
	/**
	 * Recupera indicador de situação do documento formatado
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
	 * Recupera nome do local físico do documento
	 * 
	 * @return getNomeLocalFisicoDoc()
	 */
	public String getNomeLocalFisicoDoc() {
		return getDocumentoBasico().getNomeLocalFisicoDoc();
	}

	/**
	 * Recupera nome do responsável pelo convite
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
	 * Recupera lista de histórico de documentos
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
	 * Recupera texto de observação do documento
	 * @return getTxtObsDoc()
	 */
	public String getTxtObsDoc() {
		return getDocumentoBasico().getTxtObsDoc();
	}

	/**
	 * 
	 * Verifica se o documento é COnvite
	 * @return isDocumentoConvite()
	 */
	public boolean isDocumentoConvite() {
		return getDocumentoBasico().isDocumentoConvite();
	}

	/**
	 * Atribui a descrição do documento
	 * @param doc descrição
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
	 * Atribui a situação do documento
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
	 * Atribui o local físico do documento
	 * 
	 * @param localFisicoDoc String
	 */
	public void setNomeLocalFisicoDoc(String localFisicoDoc) {
		getDocumentoBasico().setNomeLocalFisicoDoc(localFisicoDoc);
	}

	/**
	 * Atribui o nome do responsável pelo convite
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
	 * Atribui a lista de histórico de documentos
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
	 * Atribui o texto da observação do documento
	 * @param obsDoc String
	 */
	public void setTxtObsDoc(String obsDoc) {
		getDocumentoBasico().setTxtObsDoc(obsDoc);
	}

	/**
	 * Data final de vigência da gestão
	 * 
	 * @return getDtFinalVigGestao()
	 */
	public Date getDtFinalVigGestao() {
		return getGestaoDocumento().getDtFinalVigGestao();
	}

	/**
	 * Data inicial de vigência da gestão()
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
	 * Data final da vigência Gestão
	 * 
	 * @return isDtFinalVigGestaoPreenchido()
	 */
	public boolean isDtFinalVigGestaoPreenchido() {
		return getGestaoDocumento().isDtFinalVigGestaoPreenchido();
	}

	/**
	 * Recupera o usuário 
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
	 * Atribui data final da vigência da gestão
	 * @param finalVigGestao Date
	 */
	public void setDtFinalVigGestao(Date finalVigGestao) {
		getGestaoDocumento().setDtFinalVigGestao(finalVigGestao);
	}

	/**
	 * Atribui data inicial da vigência da gestão
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
	 * Recupera a lista de histórico de andamentos 
	 * @return getListaHistAndamentos()
	 */
	public Collection<ConsultarHistoricoAndamentosTO> getListaHistAndamentos() {
		if (listaHistAndamentos == null) {
			listaHistAndamentos = new ArrayList<ConsultarHistoricoAndamentosTO>();
		}
		return listaHistAndamentos;
	}

	/**
	 * Atribui a lista de histórico de andamentos 
	 * @param listaHistAndamentos Collection
	 */
	public void setListaHistAndamentos(
			Collection<ConsultarHistoricoAndamentosTO> listaHistAndamentos) {
		this.listaHistAndamentos = listaHistAndamentos;
	}
	
	
}
