/*
 * ManutencaoSaapDocAdmPresidenciaService.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapArqDocAdmBO;
import br.jus.stj.saap.negocio.bo.SaapDocAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapGestaoPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapHistDocAdmBO;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de 
 * Manter SaapDocAdmPresidencia.
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapDocAdmPresidenciaService 
		extends ManutencaoServiceImpl<SaapDocAdmPresidencia, SaapDocAdmPresidenciaBO > {
	
	private SaapDocAdmPresidenciaBO bo;
	
	@Resource(name = "saapGestaoPresidenciaBO")
	private SaapGestaoPresidenciaBO boGestao;
	private SaapArqDocAdmBO boAnexo;
	private SaapAdmtAdmPresidenciaBO boAndamento;
	private SaapHistAdmtAdmBO boHistoricoAndamento;
	private SaapHistDocAdmBO boHistoricoDocumento;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapDocAdmPresidenciaService() {
		// Construtor default
	}
	
	/**
	 * Consultar os documentos
	 * @param consultarDocumentosTO ConsultarDocumentosTO
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	@Transactional(readOnly = true)
	public Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO) {
		Collection<SaapDocAdmPresidencia> retorno = null;
		
		retorno = bo.consultarDocumentos(consultarDocumentosTO);

		return retorno;
		
	}	

	/**
	 * Consultar Documentos Similares
	 * @param documento SaapDocAdmPresidencia
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	@Transactional(readOnly = true)
	public Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(
			SaapDocAdmPresidencia documento){
		return bo.consultarDocumentosSimilares(documento);
	}
	
	/**
	 * Desativar todos os documentos
	 * @param entidades Collection<SaapDocAdmPresidencia>
	 */
	@Transactional	
	public void desativarTodas(Collection<SaapDocAdmPresidencia> entidades){

		if (entidades != null) {
			for (SaapDocAdmPresidencia entidade : entidades) {
				getBo().desativar(entidade);
				//Insere na entidade de histórico do documento
				getBoHistoricoDocumento().inserir(preencherEntidadeHistoricoDocumento(
						entidade,Constante.OPERACAO_DESATIVACAO));				
			}
		}
	}

	/**
	 * Desativar  o documento
	 * @param entidade SaapDocAdmPresidencia
	 */
	@Transactional	
	public void desativar(SaapDocAdmPresidencia entidade){
		getBo().desativar(entidade);
		//Insere na entidade de histórico do documento
		getBoHistoricoDocumento().inserir(preencherEntidadeHistoricoDocumento(
				entidade,Constante.OPERACAO_DESATIVACAO));				
	}

	/**
	 * Obtem o primeiro documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	@Transactional(readOnly = true)
	public SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getBo().obterPrimeiroDocumentoSimilar(documento);
	}

	/**
	 * Obtem o proximo documento similar
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	@Transactional(readOnly = true)	
	public SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getBo().obterProximoDocumentoSimilar(documento);
	}
	
	/**
	 * Obtem o documento similar anterior
	 * @param documento SaapDocAdmPresidencia
	 * @return SaapDocAdmPresidencia
	 */
	@Transactional(readOnly = true)	
	public SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento){
		return getBo().obterAnteriorDocumentoSimilar(documento);
	}

	/**
	 * Inclui lista de arquivo do documento
	 * @param arquivosDocumento Collection<SaapArqDocAdm>
	 */
	@Transactional
	public void incluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento){
		
		Iterator<SaapArqDocAdm> iterator = arquivosDocumento.iterator();
		while (iterator.hasNext()) {
			SaapArqDocAdm arquivo = iterator.next();
			getBoAnexo().inserir(arquivo);
		}	
	}

	/**
	 * Exclui lista de arquivos de documento
	 * @param arquivosDocumento Collection<SaapArqDocAdm>
	 */
	@Transactional
	public void excluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento){
		
		Iterator<SaapArqDocAdm> iterator = arquivosDocumento.iterator();
		while (iterator.hasNext()) {
			SaapArqDocAdm arquivo = iterator.next();
			getBoAnexo().remover(arquivo);
		}	
	}


	/**
	 * Alterar o documento
	 * @param to DocumentoDetalhadoTO
	 */
	@Transactional	
	public void alterarDocumento(DocumentoDetalhadoTO to){
		try {
			getBo().alterar(to.getDocumentoBasico());	
			
			//Insere na entidade de histórico do documento
			getBoHistoricoDocumento().inserir(preencherEntidadeHistoricoDocumento(
					to,Constante.OPERACAO_ALTERACAO));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Realiza a inclusão do Documento
	 * @param to DocumentoDetalhadoTO
	 * @return Integer
	 */
	@Transactional
	public Integer incluirDocumento(DocumentoDetalhadoTO to){
		Integer idDocumento = 0;
		SaapAdmtAdmPresidenciaPk chaveAndamento = new SaapAdmtAdmPresidenciaPk();
		int pkAndamento = getBoAndamento().obterProximaChave();
		
		//Insere o documento
		idDocumento = (Integer) inserir(to.getDocumentoBasico());
		
		//Insere o andamento
		to.getAndamentoDocumento().setSaapDocAdmPresidencia(to.getDocumentoBasico());
		chaveAndamento.setIndTipoAdmtAdm(Constante.TIPO_ANDAMENTO_DOCUMENTO);
		chaveAndamento.setSeqAdmtAdm(pkAndamento);
		to.getAndamentoDocumento().setId(chaveAndamento);
		to.getAndamentoDocumento().setTsEntradaAdmt(new Date());
		getBoAndamento().inserir(to.getAndamentoDocumento());	

		//Insere na entidade de histórico do andamento
		getBoHistoricoAndamento().inserir(preencherEntidadeHistoricoAndamento(
				to,Constante.OPERACAO_INCLUSAO));			
		
		//Insere na entidade de histórico do documento
		getBoHistoricoDocumento().inserir(preencherEntidadeHistoricoDocumento(
				to,Constante.OPERACAO_INCLUSAO));
		
		return idDocumento;
	}
	
	
	
	/**
	 * Preenche a entidade de Historico do Andamento
	 * @param to
	 * @return SaapHistAdmtAdm
	 */
	private SaapHistAdmtAdm preencherEntidadeHistoricoAndamento(
			DocumentoDetalhadoTO to,String operacao){
		SaapHistAdmtAdm toHist = new SaapHistAdmtAdm();
		
		toHist.setSaapAdmtAdmPresidencia(to.getAndamentoDocumento());
		toHist.setTsHistAdmt(new Date());
		toHist.setNomeAcaoHistAdmt(operacao);

		toHist.setSeqUsuario(to.getHistoricoDocumento().getSeqUsuario());
		toHist.setDescAdmtAtual(to.getAndamentoDocumento().getDescAdmtAdm());
		
		return toHist;
	}
	
	

	/**
	 * Preenche a entidade de Histórico do Documento
	 * @param to
	 * @param operacao
	 * @return
	 */
	private SaapHistDocAdm preencherEntidadeHistoricoDocumento(
			DocumentoDetalhadoTO to,String operacao){
		SaapHistDocAdm toHist = new SaapHistDocAdm();
		
		toHist.setSaapDocAdmPresidencia(to.getDocumentoBasico());
		toHist.setTsHistDoc(new Date());
		toHist.setNomeAcaoHistDoc(operacao);
		toHist.setSeqUsuario(to.getHistoricoDocumento().getSeqUsuario());
		
		return toHist;
	}
	
	/**
	 * Preenche a entidade de Histórico do Documento
	 * @param doc
	 * @param operacao
	 * @return
	 */
	private SaapHistDocAdm preencherEntidadeHistoricoDocumento(
			SaapDocAdmPresidencia doc,String operacao){
		SaapHistDocAdm toHist = new SaapHistDocAdm();
		
		toHist.setSaapDocAdmPresidencia(doc);
		toHist.setTsHistDoc(new Date());
		toHist.setNomeAcaoHistDoc(operacao);
		
		Iterator<SaapHistDocAdm> iterator =  doc.getSaapHistDocAdms().iterator();
		if (iterator.hasNext()) {	
			SaapHistDocAdm historico = iterator.next();
			toHist.setSeqUsuario(historico.getSeqUsuario());
		}
		
		return toHist;
	}	
	
	
	/**
	 * Obtem o documento detalhado
	 * @param identificador Integer
	 * @return DocumentoDetalhadoTO
	 */
	@Transactional(readOnly = true)
	public DocumentoDetalhadoTO obterDocumentoDetalhado(Integer identificador) {
		DocumentoDetalhadoTO documentoDetalhadoTO = new DocumentoDetalhadoTO();
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		
		//Obtendo informações básicas do documento
		SaapDocAdmPresidencia informacoesBasicasDocumento = getBo().obter(identificador);
		SaapGestaoPresidencia gestaoDocumento = getBoGestao().obterGestaoPeloPeriodo(
				informacoesBasicasDocumento.getTsEntradaDoc());
		Collection<SaapArqDocAdm> colecaoArquivos = getBoAnexo().consultarAnexos(identificador);
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		Collection<SaapAdmtAdmPresidencia> colecaoAndamentos = getBoAndamento().
		consultarAndamentos(identificador);
		Integer total = PaginacaoConsultaHolder.getTotalRegistros();
		SaapHistDocAdm historicoDocumento = getBoHistoricoDocumento().
		obterUltimoHistorico(identificador);
		
		if (historicoDocumento != null) {
			AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(historicoDocumento.getSeqUsuario());
			documentoDetalhadoTO.setNomeUsuarioAlteracao(aaUsuario.getNomeUsuario());
		}
		documentoDetalhadoTO.setHistoricoDocumento(historicoDocumento);
		documentoDetalhadoTO.setAndamentosDocumento(colecaoAndamentos);
		documentoDetalhadoTO.setDocumentoBasico(informacoesBasicasDocumento);
		documentoDetalhadoTO.setGestaoDocumento(gestaoDocumento);
		documentoDetalhadoTO.setArquivosDocumento(colecaoArquivos);
		documentoDetalhadoTO.setListaAndamentos(criarAndamentosTO(colecaoAndamentos));
		
		PaginacaoConsultaHolder.setTotalRegistros(total);
		return documentoDetalhadoTO;
	}
	
	/**
	 * Criar andamentos pela lista
	 * @param colecaoAndamentos Collection<SaapAdmtAdmPresidencia>
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	private Collection<SaapAdmtAdmPresidenciaTO>
			criarAndamentosTO(Collection<SaapAdmtAdmPresidencia> colecaoAndamentos) {
	
		Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos = 
			new ArrayList<SaapAdmtAdmPresidenciaTO>();
	
		for (Iterator iterator = colecaoAndamentos.iterator(); iterator.hasNext();) {
			SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = 
					(SaapAdmtAdmPresidencia) iterator.next();
			
			SaapAdmtAdmPresidenciaTO saapAdmtAdmPresidenciaTO = 
					TOFactory.getInstancia().novoSaapAdmtAdmPresidenciaTO();
			saapAdmtAdmPresidenciaTO.setSaapAdmtAdmPresidencia(saapAdmtAdmPresidencia);
			
			SaapHistAdmtAdm histAdmtAdm = getBoHistoricoAndamento().
			obtemPelaPkETsEntradaAdmt(saapAdmtAdmPresidencia);
			if (histAdmtAdm != null) {
				AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(histAdmtAdm.getSeqUsuario());
				saapAdmtAdmPresidenciaTO.setResponsavel(aaUsuario.getNomeUsuario());
			}
			listaAndamentos.add(saapAdmtAdmPresidenciaTO);
		}
		
		return listaAndamentos;
		
	}
	
	/**
	 * Carregar o PDF
	 * @param path SaapArqDocAdm
	 * @return byte[]
	 */
    @Transactional(readOnly = true)
    public byte[] carregarPDF(final SaapArqDocAdm path) {
        return getBo().carregarPDF(path);
    }
	
	/**
	 * Obtem o próximo documento
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	@Transactional(readOnly = true)
	public SaapDocAdmPresidencia obterProximoDocumento(Integer identificador){
	    return getBo().obterProximoDocumento(identificador);
	}
	
	/**
	 * Obtem o documento anterior
	 * @param identificador Integer
	 * @return SaapDocAdmPresidencia
	 */
	@Transactional(readOnly = true)
	public SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador){
	    return getBo().obterDocumentoAnterior(identificador);
	}

	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapDocAdmPresidenciaBO")
	protected void setBo(SaapDocAdmPresidenciaBO _bo) {
		this.bo = _bo;
	}

	/**
	 * Atribui valores no BO
	 * @param boGestao SaapGestaoPresidenciaBO
	 */
	protected void setBoGestao(SaapGestaoPresidenciaBO boGestao) {
		this.boGestao = boGestao;
	}
	
	/**
	 *  Sobrescrita do método para injeção da implementação do Bo.
	 * @param boAnexo SaapArqDocAdmBO
	 */
	@Resource(name = "saapArqDocAdmBO")
    protected void setBoAnexo(SaapArqDocAdmBO boAnexo) {
        this.boAnexo = boAnexo;
    }

	/**
	 *  Sobrescrita do método para injeção da implementação do Bo.
	 * @param boAndamento SaapAdmtAdmPresidenciaBO
	 */
	@Resource(name = "saapAdmtAdmPresidenciaBO")
    protected void setBoAndamento(SaapAdmtAdmPresidenciaBO boAndamento) {
        this.boAndamento = boAndamento;
    }	
	
	/**
	 *  Sobrescrita do método para injeção da implementação do Bo.
	 * @param boHistoricoAndamento SaapHistAdmtAdmBO
	 */
	@Resource(name = "saapHistAdmtAdmBO")
    protected void setBoHistoricoAndamento(SaapHistAdmtAdmBO boHistoricoAndamento) {
        this.boHistoricoAndamento = boHistoricoAndamento;
    }	
	
	/**
	 *  Sobrescrita do método para injeção da implementação do Bo.
	 * @param boHistoricoDocumento SaapHistDocAdmBO
	 */
	@Resource(name = "saapHistDocAdmBO")
    protected void setBoHistoricoDocumento(SaapHistDocAdmBO boHistoricoDocumento) {
        this.boHistoricoDocumento = boHistoricoDocumento;
    }	

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapDocAdmPresidenciaBO getBo() {
		return bo;
	}
	
	/**
	 * Recupera o BO
	 * @return SaapGestaoPresidenciaBO
	 */
	protected SaapGestaoPresidenciaBO getBoGestao() {
		return boGestao;
	}

	/**
	 * Recupera o BO
	 * @return SaapAdmtAdmPresidenciaBO
	 */
	protected SaapAdmtAdmPresidenciaBO getBoAndamento() {
		return boAndamento;
	}

	/**
	 * Recupera o BO
	 * @return SaapArqDocAdmBO
	 */
	protected SaapArqDocAdmBO getBoAnexo() {
	   return boAnexo;
	}
	
	/**
	 * Recupera o BO
	 * @return SaapHistAdmtAdmBO
	 */
	protected SaapHistAdmtAdmBO getBoHistoricoAndamento() {
		   return boHistoricoAndamento;
		}
	
	/**
	 * Recupera o BO
	 * @return SaapHistDocAdmBO
	 */
	protected SaapHistDocAdmBO getBoHistoricoDocumento() {
		   return boHistoricoDocumento;
	}
	
	/**
	 * Retorna aaUsuarioSaapBO.
	 * 
	 * @return AaUsuarioSaapBO
	 */
	public AaUsuarioSaapBO getAaUsuarioSaapBO() {
		return aaUsuarioSaapBO;
	}

	/**
	 * Atribui aaUsuarioSaapBO.
	 * 
	 * @param aaUsuarioSaapBO aaUsuarioSaapBO
	 */
	@Resource(name = "aaUsuarioSaapBO")
	public void setAaUsuarioSaapBO(AaUsuarioSaapBO aaUsuarioSaapBO) {
		this.aaUsuarioSaapBO = aaUsuarioSaapBO;
	}	
	
	
}
