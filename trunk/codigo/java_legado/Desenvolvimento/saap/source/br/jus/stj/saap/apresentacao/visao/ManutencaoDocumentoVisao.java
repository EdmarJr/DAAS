/*
 * ManutencaoDocumentoVisao.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.visao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.model.UploadItem;

import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;

/**
 * Responsável pela representação da visão do caso de uso Manter Documento.
 * 
 * @author Politec Informática
 */
public class ManutencaoDocumentoVisao  extends ManutencaoVisao<SaapDocAdmPresidencia>{

	private SelectItem[] gestoes;
	private SaapDocAdmPresidencia[] docsSelecionados;
    private Integer gestao;
    private Date dataInicial;
    private Date dataFinal;    
    private Date dataInicialConvite;
    private Date dataFinalConvite;
    private String horaInicialConvite;
    private Date horaInicialConviteAlteracao;
    private String horaFinalConvite;
    private String nomeMinistroGestao;
    private ConsultarDocumentosTO consultarDocumentosTO;
    private DocumentoDetalhadoTO documentoDetalhadoTO;
    private Integer idDocumento;
    private String descricaoAndamento;
    private List<UploadItem> anexos;
    private List<UploadItem> anexosGravados;
    private List<SaapDocAdmPresidencia> docsSimilares;
    private List<UploadItem> anexosExcluidos;
    private String nomeArquivo; 
    private HtmlDataTable dataTable2;
    private HtmlDataTable dataTable2Alteracao;
    private HtmlDataTable dataTableAndamentos;
    private HtmlDataTable dataTableAndamentosAlteracao;
    private boolean operacaoInclusao=true;
    private boolean exibirPop=false;
    private String flagHaSimilaridades="N";
    private String selecionado;
    private boolean apresentaProximoSimilar;
    private boolean apresentaAnteriorSimilar;
    private boolean apresentaProximoDetalhe;
    private boolean apresentaAnteriorDetalhe;
    private boolean apresentaProximoAlteracao;
    private boolean apresentaAnteriorAlteracao;
    private boolean desabilitarPeriodo;
    private Integer paginaVoltar;
    private Integer paginaDetalhe;
    private String voltarPara;
    private SaapDocAdmPresidencia consultaClone;
    private String limpaUpload;

    /**
     * String de selecionados
     * @return selecionado String
     */
	public String getSelecionado() {
		return selecionado;
	}


	/**
	 * String de selecionados
	 * @param selecionado String
	 */
	public void setSelecionado(String selecionado) {
		this.selecionado = selecionado;
	}


	/**
	 * Controla a popup
	 * @return boolean
	 */
	public boolean isExibirPop() {
		return exibirPop;
	}


	/**
	 * Controla a popup
	 * @param exibirPop boolean
	 */
	public void setExibirPop(boolean exibirPop) {
		this.exibirPop = exibirPop;
	}


	/**
	 * Controla a popup de documentos similares
	 * @return String
	 */
	public String getFlagHaSimilaridades() {
		return flagHaSimilaridades;
	}


	/**
	 * Controla a popup de documentos similares
	 * @param flagHaSimilaridades String
	 */
	public void setFlagHaSimilaridades(String flagHaSimilaridades) {
		this.flagHaSimilaridades = flagHaSimilaridades;
	}


	/**
	 * Verifica se é inclusão
	 * @return boolean
	 */
	public boolean isOperacaoInclusao() {
		return operacaoInclusao;
	}


	/**
	 * Verifica se é inclusão
	 * @param operacaoInclusao boolean
	 */
	public void setOperacaoInclusao(boolean operacaoInclusao) {
		this.operacaoInclusao = operacaoInclusao;
	}


	/**
	 * Vetor de Documentos selecionados
	 * @return SaapDocAdmPresidencia[]
	 */
	public SaapDocAdmPresidencia[] getDocsSelecionados() {
		return docsSelecionados;
	}


	/**
	 * Vetor de Documentos selecionados
	 * @param docsSelecionados SaapDocAdmPresidencia[]
	 */
	public void setDocsSelecionados(SaapDocAdmPresidencia[] docsSelecionados) {
		this.docsSelecionados = docsSelecionados;
	}


	/**
	 * Hora inicial do convite
	 * @return Date
	 */
	public Date getHoraInicialConviteAlteracao() {
		return horaInicialConviteAlteracao;
	}


	/**
	 * Hora inicial do convite
	 * @param horaInicialConviteAlteracao Date
	 */
	public void setHoraInicialConviteAlteracao(Date horaInicialConviteAlteracao) {
		this.horaInicialConviteAlteracao = horaInicialConviteAlteracao;
	}


	/**
	 * Nome do Arquivo
	 * @return String
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}


	/**
	 * Nome do Arquivo
	 * @param nomeArquivo String
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	
	/**
	 * Contem lista de documentos similares
	 * @return List<SaapDocAdmPresidencia>
	 */
	public List<SaapDocAdmPresidencia> getDocsSimilares() {
		if (docsSimilares==null){
			docsSimilares = new ArrayList<SaapDocAdmPresidencia>();
		}
		return docsSimilares;
	}

	/**.
	 * Contem lista de documentos similares
	 * @param docsSimilares List<SaapDocAdmPresidencia>
	 */
	public void setDocsSimilares(List<SaapDocAdmPresidencia> docsSimilares) {
		this.docsSimilares = docsSimilares;
	}
	
	/**
	 * Lista de Arquivos
	 * @return List<UploadItem>
	 */
	public List<UploadItem> getAnexos() {
		if (anexos==null){
			anexos = new ArrayList<UploadItem>();
		}
		return anexos;
	}
	
	/**
	 * Lista de Arquivos
	 * @return List<UploadItem>
	 */
	public List<UploadItem> getAnexosGravados() {
		if (anexosGravados==null){
			anexosGravados = new ArrayList<UploadItem>();
		}
		return anexosGravados;
	}
	
	
	/**
	 * Lista de Arquivos
	 * @return  List<UploadItem>
	 */
	public List<UploadItem> getAnexosExcluidos() {
		if (anexosExcluidos==null){
			anexosExcluidos = new ArrayList<UploadItem>();
		}
		return anexosExcluidos;
	}

	/**
	 * Lista de Anexos excluídos
	 * @param anexosExcluidos List<UploadItem>
	 */
	public void setAnexosExcluidos(List<UploadItem> anexosExcluidos) {
		this.anexosExcluidos = anexosExcluidos;
	}


	/**
	 * Lista de Anexos excluídos
	 * @param anexos List<UploadItem>
	 */
	public void setAnexos(List<UploadItem> anexos) {
		this.anexos = anexos;
	}
	
	/**
	 * Lista de Anexos que já estão no banco
	 * @param anexosGravados List<UploadItem>
	 */
	public void setAnexosGravados(List<UploadItem> anexosGravados) {
		this.anexosGravados = anexosGravados;
	}	


	/**
	 * Descrição do andamento
	 * @return String
	 */
	public String getDescricaoAndamento() {
		return descricaoAndamento;
	}


	/**
	 * Descrição do andamento
	 * @param descricaoAndamento String
	 */
	public void setDescricaoAndamento(String descricaoAndamento) {
		this.descricaoAndamento = descricaoAndamento;
	}


	/**
	 * Nome do ministro da gestão
	 * @return String
	 */
	public String getNomeMinistroGestao() {
		return nomeMinistroGestao;
	}


	/**
	 * Nome do ministro da gestão
	 * @param nomeMinistroGestao String
	 */
	public void setNomeMinistroGestao(String nomeMinistroGestao) {
		this.nomeMinistroGestao = nomeMinistroGestao;
	}


	/**
	 * Identificador do documento
	 * @return Integer
	 */
	public Integer getIdDocumento() {
		return idDocumento;
	}


	/**
	 * Identificador do documento
	 * @param idDocumento Integer
	 */
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}


	/**
	 * Documento detalhado TO
	 * @return DocumentoDetalhadoTO
	 */ 
	public DocumentoDetalhadoTO getDocumentoDetalhadoTO() {
		if (documentoDetalhadoTO == null) {
			documentoDetalhadoTO = new DocumentoDetalhadoTO();
		}
		return documentoDetalhadoTO;
	}

    
	/**
	 * Consultar Documento TO
	 * @return ConsultarDocumentosTO
	 */
	public ConsultarDocumentosTO getConsultarDocumentosTO() {
		if (consultarDocumentosTO == null) {
			consultarDocumentosTO = new ConsultarDocumentosTO();
		}
		return consultarDocumentosTO;
	}

	
	/**
	 * Documento detalhado TO
	 * @param documentoDetalhadoTO DocumentoDetalhadoTO
	 */
	public void setDocumentoDetalhadoTO(DocumentoDetalhadoTO documentoDetalhadoTO) {
		this.documentoDetalhadoTO = documentoDetalhadoTO;
	}	
	
	/**
	 * Consultar Documento TO
	 * @param consultarDocumentosTO ConsultarDocumentosTO
	 */
	public void setConsultarDocumentosTO(ConsultarDocumentosTO consultarDocumentosTO) {
		this.consultarDocumentosTO = consultarDocumentosTO;
	}

	/**
	 * Hora inicial do convite
	 * @return String
	 */
	public String getHoraInicialConvite() {
		return horaInicialConvite;
	}

	/**
	 * Hora inicial do convite
	 * @param horaInicialConvite String
	 */
	public void setHoraInicialConvite(String horaInicialConvite) {
		this.horaInicialConvite = horaInicialConvite;
	}
	
	/**
	 *  Hora final do convite
	 * @return String
	 */
	public String getHoraFinalConvite() {
		return horaFinalConvite;
	}

	/**
	 *   Hora final do convite
	 * @param horaFinalConvite String
	 */
	public void setHoraFinalConvite(String horaFinalConvite) {
		this.horaFinalConvite = horaFinalConvite;
	}

	/**
	 * Data inicial do convite
	 * @return Date
	 */ 
	public Date getDataInicialConvite() {
		return dataInicialConvite;
	}
	
	/**
	 * Data inicial do convite
	 * @param dataInicialConvite Date
	 */
	public void setDataInicialConvite(Date dataInicialConvite) {
		this.dataInicialConvite = dataInicialConvite;
	}

	/**
	 * Data final do convite
	 * @return Date
	 */
	public Date getDataFinalConvite() {
		return dataFinalConvite;
	}

	/**
	 * Data final do convite
	 * @param dataFinalConvite Date
	 */
	public void setDataFinalConvite(Date dataFinalConvite) {
		this.dataFinalConvite = dataFinalConvite;
	}

	/**
	 * Data inicial
	 * @return Date
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * Data inicial
	 * @param dataInicial Date
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * Data final
	 * @return Date
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * Data final
	 * @param dataFinal Date
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * Gestores
	 * @return  SelectItem[]
	 */
	public SelectItem[] getGestoes() {
		if(gestoes == null) {
			gestoes = new SelectItem[0];
		}
		return gestoes;
	}

	/**
	 * Gestores
	 * @param gestoes SelectItem[]
	 */
	public void setGestoes(SelectItem[] gestoes) {
		this.gestoes = gestoes;
	}

	/**
	 * Codigo da Gestão
	 * @return Integer
	 */
	public Integer getGestao() {
		return gestao;
	}

	/**
	 * * Codigo da Gestão
	 * @param gestao Integer
	 */
	public void setGestao(Integer gestao) {
		this.gestao = gestao;
	}

	/**
	 * Segunda Data table 
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTable2(){
		if (dataTable2 == null) {
			dataTable2 = new HtmlDataTable();
		}
		return dataTable2;
	}

	/**
	 * Segunda Data table alteração
	 * @return HtmlDataTable
	 */ 
	public HtmlDataTable getDataTable2Alteracao()	{
		if (dataTable2Alteracao == null) {
			dataTable2Alteracao = new HtmlDataTable();
		}
		return dataTable2Alteracao;
	}

	/**
	 * Data table de andamentos
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTableAndamentos()	{
		if (dataTableAndamentos == null) {
			dataTableAndamentos = new HtmlDataTable();
		}
		return dataTableAndamentos;
	}

	/**
	 * Data table de andamentos
	 * @return HtmlDataTable
	 */
	public HtmlDataTable getDataTableAndamentosAlteracao(){
		if (dataTableAndamentosAlteracao == null) {
			dataTableAndamentosAlteracao = new HtmlDataTable();
		}
		return dataTableAndamentosAlteracao;
	}

	/**
	 * Data table de andamentos na alteração
	 * @param dataTableAndamentosAlteracao HtmlDataTable
	 */
	public void setDataTableAndamentosAlteracao(HtmlDataTable dataTableAndamentosAlteracao)	{
		this.dataTableAndamentosAlteracao = dataTableAndamentosAlteracao;
	}


	/**
	 * Data table de andamentos na alteração
	 * @param dataTableAndamentos HtmlDataTable
	 */
	public void setDataTableAndamentos(HtmlDataTable dataTableAndamentos)	{
		this.dataTableAndamentos = dataTableAndamentos;
	}


	/**
	 * Segunda Data table 
	 * @param dataTable2 HtmlDataTable
	 */
	public void setDataTable2(HtmlDataTable dataTable2)	{
		this.dataTable2 = dataTable2;
	}

	/**
	 * Segunda Data table alteração
	 * @param dataTable2Alteracao HtmlDataTable
	 */
	public void setDataTable2Alteracao(HtmlDataTable dataTable2Alteracao)	{
		this.dataTable2Alteracao = dataTable2Alteracao;
	}

	/**
	 * Retorna apresentaProximoSimilar.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaProximoSimilar() {
		return apresentaProximoSimilar;
	}

	/**
	 * Atribui apresentaProximoSimilar.
	 * 
	 * @param apresentaProximoSimilar apresentaProximoSimilar
	 */
	public void setApresentaProximoSimilar(boolean apresentaProximoSimilar) {
		this.apresentaProximoSimilar = apresentaProximoSimilar;
	}

	/**
	 * Retorna apresentaAnteriorSimilar.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaAnteriorSimilar() {
		return apresentaAnteriorSimilar;
	}

	/**
	 * Atribui apresentaAnteriorSimilar.
	 * 
	 * @param apresentaAnteriorSimilar apresentaAnteriorSimilar
	 */
	public void setApresentaAnteriorSimilar(boolean apresentaAnteriorSimilar) {
		this.apresentaAnteriorSimilar = apresentaAnteriorSimilar;
	}


	
	/**
	 * Retorna apresentaProximoDetalhe.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaProximoDetalhe() {
		return apresentaProximoDetalhe;
	}
	
	/**
	 * Atribui apresentaProximoDetalhe.
	 * 
	 * @param apresentaProximoDetalhe apresentaProximoDetalhe
	 */
	public void setApresentaProximoDetalhe(boolean apresentaProximoDetalhe) {
		this.apresentaProximoDetalhe = apresentaProximoDetalhe;
	}

	/**
	 * Retorna apresentaAnteriorDetalhe.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaAnteriorDetalhe() {
		return apresentaAnteriorDetalhe;
	}
	
	/**
	 * Atribui apresentaAnteriorDetalhe.
	 * 
	 * @param apresentaAnteriorDetalhe apresentaAnteriorDetalhe
	 */
	public void setApresentaAnteriorDetalhe(boolean apresentaAnteriorDetalhe) {
		this.apresentaAnteriorDetalhe = apresentaAnteriorDetalhe;
	}


	
	/**
	 * Retorna apresentaProximoAlteracao.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaProximoAlteracao() {
		return apresentaProximoAlteracao;
	}


	
	/**
	 * Atribui apresentaProximoAlteracao.
	 * 
	 * @param apresentaProximoAlteracao apresentaProximoAlteracao
	 */
	public void setApresentaProximoAlteracao(boolean apresentaProximoAlteracao) {
		this.apresentaProximoAlteracao = apresentaProximoAlteracao;
	}

	/**
	 * Retorna apresentaAnteriorAlteracao.
	 * 
	 * @return boolean
	 */
	public boolean isApresentaAnteriorAlteracao() {
		return apresentaAnteriorAlteracao;
	}

	/**
	 * Atribui apresentaAnteriorAlteracao.
	 * 
	 * @param apresentaAnteriorAlteracao apresentaAnteriorAlteracao
	 */
	public void setApresentaAnteriorAlteracao(boolean apresentaAnteriorAlteracao) {
		this.apresentaAnteriorAlteracao = apresentaAnteriorAlteracao;
	}

	/**
	 * Retorna paginaVoltar.
	 * 
	 * @return Integer
	 */
	public Integer getPaginaVoltar() {
		return paginaVoltar;
	}

	/**
	 * Atribui paginaVoltar.
	 * 
	 * @param paginaVoltar paginaVoltar
	 */
	public void setPaginaVoltar(Integer paginaVoltar) {
		this.paginaVoltar = paginaVoltar;
	}

	/**
	 * Retorna paginaDetalhe.
	 * 
	 * @return Integer
	 */
	public Integer getPaginaDetalhe() {
		return paginaDetalhe;
	}

	/**
	 * Atribui paginaDetalhe.
	 * 
	 * @param paginaDetalhe paginaDetalhe
	 */
	public void setPaginaDetalhe(Integer paginaDetalhe) {
		this.paginaDetalhe = paginaDetalhe;
	}


	
	/**
	 * Retorna voltarPara.
	 * 
	 * @return String
	 */
	public String getVoltarPara() {
		return voltarPara;
	}


	
	/**
	 * Atribui voltarPara.
	 * 
	 * @param voltarPara voltarPara
	 */
	public void setVoltarPara(String voltarPara) {
		this.voltarPara = voltarPara;
	}


	
	/**
	 * Retorna consultaClone.
	 * 
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia getConsultaClone() {
		if (consultaClone == null) {
			consultaClone = new SaapDocAdmPresidencia();
		}
		return consultaClone;
	}


	
	/**
	 * Atribui consultaClone.
	 * 
	 * @param consultaClone consultaClone
	 */
	public void setConsultaClone(SaapDocAdmPresidencia consultaClone) {
		this.consultaClone = consultaClone;
	}


	
	/**
	 * Retorna limpaUpload.
	 * 
	 * @return String
	 */
	public String getLimpaUpload() {
		return limpaUpload;
	}


	
	/**
	 * Atribui limpaUpload.
	 * 
	 * @param limpaUpload limpaUpload
	 */
	public void setLimpaUpload(String limpaUpload) {
		this.limpaUpload = limpaUpload;
	}

	/**
	 * Retorna desabilitarPeriodo.
	 * 
	 * @return boolean
	 */
	public boolean isDesabilitarPeriodo() {
		return desabilitarPeriodo;
	}

	/**
	 * Atribui desabilitarPeriodo.
	 * 
	 * @param desabilitarPeriodo desabilitarPeriodo
	 */
	public void setDesabilitarPeriodo(boolean desabilitarPeriodo) {
		this.desabilitarPeriodo = desabilitarPeriodo;
	}

}
