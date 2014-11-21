/*
 * ManutencaoSaapDocAdmPresidenciaAction.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbFile;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.event.DataScrollerEvent;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.jus.stj.alp01.arquitetura.apresentacao.ServiceLocator;
import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.formatador.UtilFormatadorDeData;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.relatorios.Relatorio;
import br.jus.stj.alp01.jsf.util.ApresentacaoUtil;
import br.jus.stj.alp01.jsf.util.ContextoExternoUtil;
import br.jus.stj.saap.apresentacao.datasource.GeracaoRelatorioDocumentoDataSource;
import br.jus.stj.saap.apresentacao.visao.ManutencaoDocumentoVisao;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.to.ListarAndamentosTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.UtilDate;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.constante.Mensagem;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para controle das acoes do caso de uso de Manter SaapDocAdmPresidencia.
 *
 * @author Politec Informática
 */
public class ManutencaoSaapDocAdmPresidenciaAction extends 
		SaapActionAbstrato<SaapDocAdmPresidencia> {

	private static final String CAMINHO_RELATORIO = 
			"/relatorios/listarDocumentosConsultados.jasper";
	private static final String CAMINHO_RELATORIO_DOCUMENTO = 
		"/relatorios/detalharDocumento.jasper";
	private static final String CAMINHO_RELATORIO_DOCUMENTO_SUB = 
		"/relatorios/detalharDocumento-Arquivos.jasper";
	private static final String CAMINHO_RELATORIO_DOCUMENTO_SUB_AND = 
		"/relatorios/detalharDocumento-Andamentos.jasper";
	private SaapFacade facade;
	
	private static final String TELA_INCLUIR_INICIO = ".incluirInicio";
	
	private List<UploadItem> arquivos = new ArrayList<UploadItem>();
	private String arquivo;

	/**
	 * A fachada para acesso aos metodos de negocio
	 * @return SaapFacade
	 */
	@Override
	protected SaapFacade getFachada() {
		if (facade == null) {
			ServiceLocator locator = ServiceLocator.getInstancia();
			facade = locator.getFachada(SaapFacade.class);
		}
		return facade;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#getVisao()
	 */
	@Override
    public ManutencaoDocumentoVisao getVisao()  {
		return getObjetoDoContexto(ManutencaoDocumentoVisao.class);
    }


	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#iniciar()
	 */
	@Override
	public String iniciar() {
		iniciarDados();
		getVisao().setConsulta(null);
		getVisao().setGestoes(carregarGestoes());
		getVisao().setLista(null);
		java.lang.Short tpDoc = Constante.TIPO_DOCUMENTO_OFICIO;
		getVisao().getConsulta().setIndTipoDoc(tpDoc);
		getVisao().getConsultarDocumentosTO().setDataInicial(null);
		getVisao().getConsultarDocumentosTO().setDataFinal(null);
		getVisao().getConsultarDocumentosTO().setDataInicialConvite(null);
		getVisao().getConsultarDocumentosTO().setDataFinalConvite(null);
		getVisao().getConsultarDocumentosTO().setHoraInicialConvite(null);
		getVisao().getConsultarDocumentosTO().setHoraFinalConvite(null);
		SaapGestaoPresidencia gestao = getFachada().obterGestaoPeloPeriodo(new Date());
		getVisao().setGestao(gestao.getId());
		carregarDatas();
		return getTelaInicial();
	}

	/**
	 * Limpa todo o formulário
	 * 
	 */
	public void limparFormulario(){
		getManutencaoVisao().getConsulta().setDescDoc(null);
		getManutencaoVisao().setConsulta(null);
		getVisao().setDescricaoAndamento(null);
		getVisao().setAnexos(null);
		getVisao().setAnexosGravados(null);
		getVisao().setDataInicialConvite(null);
		getVisao().setHoraInicialConvite(null);
		java.lang.Short tpDoc = Constante.TIPO_DOCUMENTO_OFICIO;
		getVisao().getConsultarDocumentosTO().setDataInicial(null);
		getVisao().getConsultarDocumentosTO().setDataFinal(null);
		getVisao().getConsultarDocumentosTO().setDataInicialConvite(null);
		getVisao().getConsultarDocumentosTO().setDataFinalConvite(null);
		getVisao().getConsultarDocumentosTO().setHoraInicialConvite(null);
		getVisao().getConsultarDocumentosTO().setHoraFinalConvite(null);
		getVisao().getConsulta().setIndTipoDoc(tpDoc);
		acaoRemoverTodos();
		setDataGestao();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#iniciarIncluir()
	 */ 
	@Override	
	public String iniciarIncluir() {
		iniciarDadosInclusao();
		getVisao().setConsulta(null);
		getVisao().setOperacaoInclusao(true);
		getVisao().setExibirPop(false);
		java.lang.Short tpDoc = Constante.TIPO_DOCUMENTO_OFICIO;
		getVisao().getConsulta().setIndTipoDoc(tpDoc);
		getVisao().setVoltarPara("saapDocAdmPresidenciaAction.iniciar");
		setDataGestao();
		return getTelaInclusao();
	}
	
	/**
	 * 
	 * Rotina criada para ser chamada pelo menu
	 * Vai chamar a pagina inclusaoInicio.xhtml
	 * @return String
	 */
	public String iniciarIncluirInicio() {
		iniciarDadosInclusao();
		getVisao().setConsulta(null);
		getVisao().setOperacaoInclusao(true);
		getVisao().setExibirPop(false);
		java.lang.Short tpDoc = Constante.TIPO_DOCUMENTO_OFICIO;
		getVisao().getConsulta().setIndTipoDoc(tpDoc);
		setDataGestao();
		return getEntidadeClazz().getSimpleName().toLowerCase() + TELA_INCLUIR_INICIO;
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#iniciarEditar()
	 */
	@Override	
	public String iniciarEditar() {

		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());	

		//Caso a alteração venha de um registro selecionado na tela de consulta
		if (getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId()==null){
			getVisao().setDocumentoDetalhadoTO(getFachada().obterDocumentoDetalhado(carregarId()));	
		}else {
			getVisao().setDocumentoDetalhadoTO(getFachada().obterDocumentoDetalhado(
					getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId()));
		}

		if (getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getDtHrEventoConvite()!=null){
			getVisao().setHoraInicialConvite(UtilDate.extraiHoraMinuto(getVisao().
					getDocumentoDetalhadoTO().getDocumentoBasico().getDtHrEventoConvite()));		
		}

		//Carrega arquivos do upload para edição na tela 
		if (getVisao().getDocumentoDetalhadoTO().getArquivosDocumento()!=null){
			getVisao().setAnexos(carregarListaArquivos(getVisao().getDocumentoDetalhadoTO().
					getArquivosDocumento()));
			getVisao().setAnexosGravados(carregarListaArquivos(getVisao().getDocumentoDetalhadoTO().
					getArquivosDocumento()));
		}
	    habilitaDesabilitaBotoes();
	    getVisao().setVoltarPara("saapDocAdmPresidenciaAction.iniciarEditar");
		return getTelaAlteracao();
	}
	
	/**
	 * Imprimir relatório de Listagem de Documentos Cadastrados
	 * 
	 * @return String
	 */
	public String imprimir() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO);
		trataCampoNumeroDoRegistro();
		carregarParametrosRelatorio(relatorio);
		relatorio.gerarRelatorioPDF();
		return getTelaInicial();
	}
	
	/**
	 * Imprimir relatório de Listagem de Documentos Cadastrados
	 * 
	 * @return String
	 */
	public String imprimirDocumento() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO_DOCUMENTO);
		trataCampoNumeroDoRegistroDoc();
		relatorio.setDataSource(carregarDataSource());
		carregarParametrosRelatorioDocumento(relatorio);
		relatorio.gerarRelatorioPDF();
		return getTelaAlteracao();
	}	
	
	/**
	 * Imprimir relatório de Listagem de Documentos Cadastrados para tela de detalhe
	 * 
	 * @return String
	 */
	public String imprimirDocumentoDetalhe() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO_DOCUMENTO);
		trataCampoNumeroDoRegistroDoc();
		relatorio.setDataSource(carregarDataSource());
		carregarParametrosRelatorioDocumento(relatorio);
		relatorio.gerarRelatorioPDF();
		return getTelaDetalhe();
	}		
	
	/**
	 * Trata campo número do registro.
	 */
	protected void trataCampoNumeroDoRegistro() {
		Integer numeroRegistro = getVisao().getConsulta().getId();
		if(!isReferencia(numeroRegistro) ||	numeroRegistro.intValue() == 0) {
			getVisao().getConsulta().setId(null);
		}
	}	
	
	/**
	 * Trata campo número do registro.
	 */
	protected void trataCampoNumeroDoRegistroDoc() {
		Integer numeroRegistro = getVisao().getDocumentoDetalhadoTO().getId();
		if(!isReferencia(numeroRegistro) ||	numeroRegistro.intValue() == 0) {
			getVisao().getDocumentoDetalhadoTO().setId(null);
		}
	}		
	
	/**
	 * Carrega os parametros enviados ao relatório de documentos cadastrados
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorio(Relatorio relatorio) {
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		if(isItenSelecionado()) {
			relatorio.setDados(getListaDocumentos(Arrays.asList(getVisao().
					getEntidades()), true));
		}else {
			removerValoresDePaginacao();
			ConsultarDocumentosTO to = getVisao().getConsultarDocumentosTO();
			to.setDocEntidade(getVisao().getConsulta());
			Collection<SaapDocAdmPresidencia> col = getFachada().consultarDocumentos(to);			
			relatorio.setDados(getListaDocumentos(col,false));
		}
		preencherCabecalhoRelatorio(relatorio);
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}
	
	/**
	 * Carrega os parametros enviados ao relatório de documento
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorioDocumento(Relatorio relatorio) {
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();

		preencherCabecalhoRelatorioDocumento(relatorio);
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}	
	
	/**
	 * Preenche o Cabeçalho do Relatório
	 * @param relatorio Relatorio
	 */
	private void preencherCabecalhoRelatorio(Relatorio relatorio) {
		String numeroRegistro = "";
		if (getVisao().getConsulta().getId() != null) {
			numeroRegistro = getVisao().getConsulta().getId().toString(); 
		}
		relatorio.adicionaParametro("numeroRegistroFiltro", numeroRegistro);
		relatorio.adicionaParametro("situacao", 
				getVisao().getConsulta().getIndSituacaoDocFormatado());
		relatorio.adicionaParametro("arquivadoPasta", 
				getVisao().getConsulta().getNomeLocalFisicoDoc());
		relatorio.adicionaParametro("tipoDocumento", 
				getVisao().getConsulta().getIndTipoDocFormatado());
		relatorio.adicionaParametro("numeroDocumentoDescricao", 
				getVisao().getConsulta().getDescDoc());
		relatorio.adicionaParametro("assuntoEvento", 
				getVisao().getConsulta().getTxtAssuntoDoc());
		relatorio.adicionaParametro("dataInicio", 
				getDataFormatada(getVisao().getConsultarDocumentosTO().getDataInicial()));
		relatorio.adicionaParametro("dataFim", 
				getDataFormatada(getVisao().getConsultarDocumentosTO().getDataFinal()));
		relatorio.adicionaParametro("dataInicioEvento", 
				getDataFormatada(getVisao().getConsultarDocumentosTO().getDataInicialConvite()));
		relatorio.adicionaParametro("dataFimEvento", 
				getDataFormatada(getVisao().getConsultarDocumentosTO().getDataFinalConvite()));
		relatorio.adicionaParametro("horaInicio",
				getVisao().getConsultarDocumentosTO().getHoraInicialConvite());
		relatorio.adicionaParametro("horaFim", 
				getVisao().getConsultarDocumentosTO().getHoraFinalConvite());

	}
	
	/**
	 * Preenche o Cabeçalho do Relatório do Documento
	 * @param relatorio Relatorio
	 */
	private void preencherCabecalhoRelatorioDocumento(Relatorio relatorio) {
		
		DocumentoDetalhadoTO doc =getVisao().getDocumentoDetalhadoTO();
		
		String numeroRegistro = "";
		if (doc.getId() != null) {
			numeroRegistro = doc.getId().toString(); 
		}
		relatorio.adicionaParametro("numeroRegistroFiltro", numeroRegistro);
		relatorio.adicionaParametro("dataEntrada",getDataFormatada(doc.getTsEntradaDoc()));
		relatorio.adicionaParametro("gestao", doc.getNomeMinistroPresidente());
		relatorio.adicionaParametro("tipoDocumento",doc.getIndTipoDocFormatado());
		relatorio.adicionaParametro("arquivadoPasta",doc.getNomeLocalFisicoDoc());
		relatorio.adicionaParametro("situacao",doc.getIndSituacaoDocFormatado());
		relatorio.adicionaParametro("alteradoPor",doc.getNomeUsuarioAlteracao());
		relatorio.adicionaParametro("numeroDocumentoDescricao",doc.getDescDoc());
		relatorio.adicionaParametro("assuntoEvento",doc.getTxtAssuntoDoc());
		relatorio.adicionaParametro("observacoes", doc.getTxtObsDoc());
		relatorio.adicionaParametro("quemConvidou",doc.getNomeRespConvite());
		relatorio.adicionaParametro("dataEvento",getDataFormatada(doc.getDtHrEventoConvite()));
		relatorio.adicionaParametro("horaEvento",getHoraFormatada(doc.getDtHrEventoConvite()));
		relatorio.adicionaParametro("subRelatorioArquivos",CAMINHO_RELATORIO_DOCUMENTO_SUB);
		relatorio.adicionaParametro("subRelatorioAndamentos",CAMINHO_RELATORIO_DOCUMENTO_SUB_AND);
	}	
	
	/**
	 * Gerar o data source para a criação do sub relatório
	 * @return GeracaoRelatorioDocumentoDataSource
	 */
	private GeracaoRelatorioDocumentoDataSource carregarDataSource() {
		
		GeracaoRelatorioDocumentoDataSource dataSource = 
				new GeracaoRelatorioDocumentoDataSource();
		DocumentoDetalhadoTO to = new DocumentoDetalhadoTO();
		to.setArquivosDocumento(getVisao().getDocumentoDetalhadoTO().getArquivosDocumento());
		
		Collection<ListarAndamentosTO> listaDados = new ArrayList<ListarAndamentosTO>();
		for (Iterator iterator = getVisao().getDocumentoDetalhadoTO().
				getListaAndamentos().iterator(); iterator.hasNext();) {
			SaapAdmtAdmPresidenciaTO saapAdmtAdmPresidenciaTO = 
					(SaapAdmtAdmPresidenciaTO) iterator.next();
			ListarAndamentosTO listaAndamentos = TOFactory.getInstancia().novoListarAndamentosTO();
			listaAndamentos.setDescAdmtAdm(saapAdmtAdmPresidenciaTO.
					getSaapAdmtAdmPresidencia().getDescAdmtAdm());
			listaAndamentos.setTsEntradaAdmt(saapAdmtAdmPresidenciaTO.
					getSaapAdmtAdmPresidencia().getTsEntradaAdmt());
			listaAndamentos.setResponsavel(saapAdmtAdmPresidenciaTO.getResponsavel());
			listaDados.add(listaAndamentos);
		}			
		to.setListaAndamentosTO(listaDados);
		dataSource.setObjeto(to);
		
		return dataSource;
	}	
	
	/**
	 * Retorna a data formatada.
	 * @param data Date
	 * @return String
	 */
	protected String getDataFormatada(Date data) {
		String retorno = "";
		if(isReferencia(data)) {
			retorno = UtilFormatadorDeData.formatar_ddMMyyyy(data);
		}
		return retorno;
	}	

	/**
	 * Retorna a hora formatada.
	 * @param data Date
	 * @return String
	 */
	protected String getHoraFormatada(Date data) {
		String retorno = "";
		if(isReferencia(data)) {
			retorno = UtilFormatadorDeData.formatar_HHmm(data);
		}
		return retorno;
	}
	/**
	 * Retorna lista de documentos consultados
	 * @param entidades SaapDocAdmPresidencia[]
	 * @param isSelecao boolean
	 * @return Collection<SaapDocAdmPresidencia>
	 * 
	 */
	protected Collection<SaapDocAdmPresidencia> getListaDocumentos(
			Collection<SaapDocAdmPresidencia> entidades, boolean isSelecao) {

		Collection<SaapDocAdmPresidencia> colDocumentos = getColecaoFactory().novoArrayList();
		
		SaapDocAdmPresidencia saapDocAdmPresidencia = null;
		for (SaapDocAdmPresidencia entidade : entidades) {
			if(isSelecao) {
				DocumentoDetalhadoTO documento = getFachada().
						obterDocumentoDetalhado(entidade.getId());
				entidade = documento.getDocumentoBasico();
			}
			saapDocAdmPresidencia = entidade;
			colDocumentos.add(saapDocAdmPresidencia);
		}
		return colDocumentos;
	}	
	
	/**
	 * Retorna true se tiver algum item selecionado na lista, caso contrário retorna false.
	 * @return boolean
	 */
	protected boolean isItenSelecionado() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapDocAdmPresidencia[] entidades = getVisao().getEntidades();
		return entidades != null && entidades.length > 0;
	}
	
	
	/**
	 * Carrega lista de arquivos.
	 * @param arquivos
	 * @return List<UploadItem>
	 */
	private List<UploadItem> carregarListaArquivos(Collection<SaapArqDocAdm> arquivos){

		List<UploadItem> listaArquivos = new ArrayList<UploadItem>();

		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			SaapArqDocAdm saapArqDocAdm = (SaapArqDocAdm) iterator.next();
			UploadItem item = new UploadItem(saapArqDocAdm.getNomeArqDoc(), 
					saapArqDocAdm.getId(), null, null);
			listaArquivos.add(item);			
		}

		return listaArquivos;
	}

	/**
	 * Carrega os parâmetros do registro selecionado na tela de consulta (por check box)
	 * @return Integer
	 */
	private Integer carregarId(){
		Integer id = null;
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());	

		if (getVisao().getEntidades() == null) {
			id = (Integer) getVisao().getEntidade().getIdentificador();
		} else {
			id = (Integer) getVisao().getEntidades()[0].getIdentificador();
		}

		return id;
	}

	/**
	 * Carrega a data atual e a gestão vigente na tela.
	 */
	private void setDataGestao(){
		getManutencaoVisao().getConsulta().setTsEntradaDoc(new Date());
		SaapGestaoPresidencia gestao = getFachada().obterGestaoPeloPeriodo(new Date());
		if (gestao!=null){
			getVisao().setNomeMinistroGestao(gestao.getNomeMinistroPresidente());			
		}
	}

	/**
	 * Reseta o formulário após a inclusão
	 */
	private void resetAposInclusao(){
		getManutencaoVisao().setConsulta(null);
		getVisao().setDescricaoAndamento(null);
		getVisao().setAnexos(null);
		getVisao().setAnexosGravados(null);
		getVisao().setHoraInicialConvite(null);
		getVisao().setDataInicialConvite(null);
		java.lang.Short tpDoc = Constante.TIPO_DOCUMENTO_OFICIO;
		getVisao().getConsulta().setIndTipoDoc(tpDoc);
		setDataGestao();
	}

	/**
	 * Verifica a similaridade do documento a ser incluído, com outros documentos no banco.
	 */
	public void verificarSimilaridade(){
		SaapDocAdmPresidencia docSimilar = null;
		SaapDocAdmPresidencia docAux = null;
		SaapDocAdmPresidencia documentoAtual = getVisao().getConsulta();
		setDataGestao();

		if (documentoAtual.getDescDoc()!=null && 
				documentoAtual.getDescDoc().length()>=15){
			docSimilar = getFachada().obterPrimeiroDocumentoSimilar(documentoAtual);

		}

		if (docSimilar!=null){
			
			docAux = getFachada().obterProximoDocumentoSimilar(docSimilar);
			getVisao().setApresentaAnteriorSimilar(false);
			if (docAux!=null){
				getVisao().setApresentaProximoSimilar(true);
			}else {
				getVisao().setApresentaProximoSimilar(false);
			}
			
			getVisao().setFlagHaSimilaridades("S");
			getVisao().setExibirPop(true);
			getVisao().setDocumentoDetalhadoTO(getFachada().
					obterDocumentoDetalhado(docSimilar.getId()));
		}else{
			incluir();
		}
	}

	/**
	 * Carrega os dados do próximo documento similar ao corrente
	 */
	public void carregarProximoSimilar() {
		DocumentoDetalhadoTO documentoDetalhadoTO = getVisao().getDocumentoDetalhadoTO();
		SaapDocAdmPresidencia docSimilar = null;
		SaapDocAdmPresidencia docAux = null;
		SaapDocAdmPresidencia docEntrada = documentoDetalhadoTO.getDocumentoBasico();
		
		String descAux = docEntrada.getDescDoc(); 

		docEntrada.setDescDoc(getVisao().getConsulta().getDescDoc());
		docSimilar = getFachada().obterProximoDocumentoSimilar(docEntrada);

		if (docSimilar!=null){
			getVisao().setDocumentoDetalhadoTO(getFachada().
					obterDocumentoDetalhado(docSimilar.getId()));
			
			docAux = getFachada().obterProximoDocumentoSimilar(docSimilar);
			getVisao().setApresentaAnteriorSimilar(true);
			if (docAux!=null){
				getVisao().setApresentaProximoSimilar(true);
			}else {
				getVisao().setApresentaProximoSimilar(false);
			}
		}else {
			docEntrada.setDescDoc(descAux);
		}

		getVisao().setExibirPop(true);
		getVisao().setFlagHaSimilaridades("S");
	}

	/**
	 * Carrega os dados do documento anterior similar ao corrente
	 */
	public void carregarAnteriorSimilar() {
		DocumentoDetalhadoTO documentoDetalhadoTO = getVisao().getDocumentoDetalhadoTO();
		SaapDocAdmPresidencia docSimilar = null;
		SaapDocAdmPresidencia docAux = null;
		
		String descAux = documentoDetalhadoTO.getDocumentoBasico().getDescDoc(); 

		documentoDetalhadoTO.getDocumentoBasico().setDescDoc(getVisao().getConsulta().getDescDoc());
		docSimilar = getFachada().obterAnteriorDocumentoSimilar(
				documentoDetalhadoTO.getDocumentoBasico());

		if (docSimilar!=null){
			getVisao().setDocumentoDetalhadoTO(getFachada().
					obterDocumentoDetalhado(docSimilar.getId()));
			
			docAux = getFachada().obterAnteriorDocumentoSimilar(
					docSimilar);
			getVisao().setApresentaProximoSimilar(true);
			if (docAux!=null){
				getVisao().setApresentaAnteriorSimilar(true);
			}else {
				getVisao().setApresentaAnteriorSimilar(false);
			}
		}else {
			documentoDetalhadoTO.getDocumentoBasico().setDescDoc(descAux);
		}
		
		getVisao().setExibirPop(true);
		getVisao().setFlagHaSimilaridades("S");
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#incluir()
	 */
	@Override	
	public String incluir() {

		String mensagemValidacao=validaFormularioInclusao();

	    if (mensagemValidacao==null) { 
				Integer chaveDocumento = getFachada().incluirDocumento(gerarDocumentoDetalhado());

				if (getVisao().getAnexos()!=null && getVisao().getAnexos().size()!=0){
					//Faz o upload dos arquivos para o servidor e inclui no banco 
					//o nome dos arquivos
					if (carregarArquivos(chaveDocumento.intValue())){
						getFachada().incluirArquivosDocumento(
								obterColecaoNomesArquivos(chaveDocumento));
					}else{
						addMensagemAlertaChave("MA027");
					}
				}
				addMensagemSucessoChave("MA002",chaveDocumento.intValue());
				getVisao().setFlagHaSimilaridades("N");
				getVisao().setExibirPop(false);
				getVisao().setLimpaUpload("Sim");
				resetAposInclusao();

		}else{
			if (!mensagemValidacao.equals(Mensagem.getMA001())) {
				addMensagemAlertaChave(mensagemValidacao);				
			}
			getVisao().setLimpaUpload("Não");
			getVisao().setFlagHaSimilaridades("N");
			getVisao().setExibirPop(false);
		}
	    return null;
	}
	
	/**
	 * Obtem a URL do servidor de aquivos
	 * @return URL
	 */
	private String getUrlServidor() {
		
		ResourceBundle properties = ResourceBundle.getBundle("multimidia");
		
		String protocolo = properties.getObject("share.protocolo").toString();
		String host = properties.getObject("host").toString();
		String usuario = properties.getObject("share.usuario").toString();
		String senha = properties.getObject("share.senha").toString();
		String servidor = properties.getObject("servidor").toString();
		String diretorio = properties.getObject("share.diretorio").toString();
		
		return protocolo + host + ";" + usuario + ":" + senha + "@" + servidor + diretorio;
	}	

	/**
	 * Gerar o documento detalhado.
	 * @return DocumentoDetalhadoTO 
	 */
	private DocumentoDetalhadoTO gerarDocumentoDetalhado() {

		DocumentoDetalhadoTO documentoDetalhadoTO = new DocumentoDetalhadoTO();
		SaapAdmtAdmPresidencia andamentoTO = new SaapAdmtAdmPresidencia();

		getManutencaoVisao().getConsulta().setIndSituacaoDoc(Constante.DOCUMENTO_ATIVO);

		//Caso o tipo de documento não seja um convite, atribuir nulo na data do evento
		if (getManutencaoVisao().getConsulta().getIndTipoDoc() != 
			Constante.TIPO_DOCUMENTO_CONVITE){
			getManutencaoVisao().getConsulta().setDtHrEventoConvite(null);
		} else{
			getManutencaoVisao().getConsulta().setDtHrEventoConvite(
					UtilDate.formatarDataConvite(getVisao().
							getDataInicialConvite(), getVisao().getHoraInicialConvite()));
		}
		andamentoTO.setDescAdmtAdm(getVisao().getDescricaoAndamento());

		documentoDetalhadoTO.setAndamentoDocumento(andamentoTO);
		documentoDetalhadoTO.setDocumentoBasico(getManutencaoVisao().getConsulta());
		documentoDetalhadoTO.getHistoricoDocumento().setSeqUsuario(getUsuarioLogado().getId());

		return documentoDetalhadoTO;
	}

	/**
	 * Consulta similiaridades.
	 * @param documentoDetalhadoTO DocumentoDetalhadoTO
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	protected Collection<SaapDocAdmPresidencia> consultarSimilaridades(
			DocumentoDetalhadoTO documentoDetalhadoTO){

		Collection<SaapDocAdmPresidencia> docsSimilares = null;

		if (documentoDetalhadoTO.getDocumentoBasico().getDescDoc()!=null && 
				documentoDetalhadoTO.getDocumentoBasico().getDescDoc().length()>=15){
			getFachada().obterPrimeiroDocumentoSimilar(documentoDetalhadoTO.getDocumentoBasico());
		}

		return docsSimilares;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#editar()
	 */
	@Override	
	public String editar() {
		String retorno = null;
		String mensagemValidacao=validaFormularioAlteracao();
	    if (mensagemValidacao==null) { 
	    	concluirEditar();
	    	// Esta atribuição é executada devido o fato do usuário andar com o paginador
	    	//na tela de alteração, neste caso eu tenho que atualizar o id.
	    	if ((getVisao().getConsulta().getId()!=null) && 
	    	(getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId() != null)) {
	    		getVisao().getConsulta().setId(
	    				getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId());
	    	}
	    	retorno = consultar();
	    }else{
			if (!mensagemValidacao.equals(Mensagem.getMA001())) {
				addMensagemAlertaChave(mensagemValidacao);				
			}
		}
		return retorno;
	}
	
	/**
	 * Efetivar a alteração
	 */
	private void concluirEditar() {
		Integer chaveDocumento = getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId();
		alterarDocumento();
		if (getVisao().getAnexos()!=null && getVisao().getAnexos().size()!=0){
			//Faz o upload dos arquivos para o servidor e inclui no banco o nome dos arquivos
			if (carregarArquivos(chaveDocumento.intValue())){
				getFachada().incluirArquivosDocumento(
						obterColecaoNomesArquivos(chaveDocumento));
			}else{
				addMensagemAlertaChave("MA027");
			}
		}
		
		//Faz a exclusão dos arquivos no servidor e exclui a referência no banco de dados
		if (apagarArquivos(chaveDocumento.intValue())){
			getFachada().excluirArquivosDocumento(
					obterColecaoArquivosExcluidos(chaveDocumento));
		}else{
			addMensagemAlertaChave("MA029");
		}
		
		addMensagemSucessoChave("MA003");
		getVisao().setDocumentoDetalhadoTO(getFachada().obterDocumentoDetalhado(
				getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getId()));		
	}

	/**
	 * Altera documento.
	 */
	private void alterarDocumento() {
		//Caso o tipo de documento não seja um convite, atribuir nulo na data do evento
		if (getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().getIndTipoDoc()!=
			Constante.TIPO_DOCUMENTO_CONVITE){
			getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().setDtHrEventoConvite(null);
		}else{
			getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().setDtHrEventoConvite(
					UtilDate.formatarDataConvite(
							getVisao().getDocumentoDetalhadoTO().
							getDocumentoBasico().getDtHrEventoConvite(), 
							getVisao().getHoraInicialConvite()));
		}

		getVisao().getDocumentoDetalhadoTO().getHistoricoDocumento().
				setSeqUsuario(getUsuarioLogado().getId());
		getFachada().alterarDocumento(getVisao().getDocumentoDetalhadoTO());
	}

	/**
	 * Sava como.
	 * @return String
	 */
	public String salvarComo() {

		getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().setSaapArqDocAdms(null);
		getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().setSaapHistDocAdms(null);
		getVisao().getDocumentoDetalhadoTO().getDocumentoBasico().setSaapAdmtAdmPresidencias(null);
		getVisao().setConsulta(getVisao().getDocumentoDetalhadoTO().getDocumentoBasico());
		getVisao().setDataInicialConvite(getVisao().getDocumentoDetalhadoTO().
				getDocumentoBasico().getDtHrEventoConvite());

		getVisao().setAnexos(null);
		getVisao().setAnexosGravados(null);
		setDataGestao();

		return getTelaInclusao();
	}

	/**
	 * Desativa documento(s).
	 * @return String
	 */
	public String desativar() {

		String retorno = null;

		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());

		if (getVisao().getEntidades() != null) {
			retorno =  desativarTodos();
		}

		return retorno;
	}

	/**
	 * Desativa documento na alteração.
	 * @return String
	 */
	public String desativarNaAlteracao() {

		String retorno = null;

		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());

		if (getVisao().getDocumentoDetalhadoTO().getDocumentoBasico() != null) {
			retorno =  desativarIndividual();
		}

		return retorno;
	}

	/**
	 * Desativa documento individualmente.
	 * 
	 * @return String
	 */
	protected String desativarIndividual() {
		incluirUsuarioDesativacaoIndividual(
				getVisao().getDocumentoDetalhadoTO().getDocumentoBasico());
		getFachada().desativar(getVisao().getDocumentoDetalhadoTO().getDocumentoBasico());
		getVisao().getDocumentoDetalhadoTO().
			getDocumentoBasico().setIndSituacaoDoc(Constante.DOCUMENTO_INATIVO);
		addMensagemSucessoChave("MA030");
		return consultar();

	}

	/**
	 * Desativa todos documentos selecionados.
	 * 
	 * @return String
	 */
	protected String desativarTodos() {
		List<SaapDocAdmPresidencia> list = Arrays.asList(getVisao().getEntidades());
		Collection<SaapDocAdmPresidencia> entidades = new ArrayList<SaapDocAdmPresidencia>(list);
		incluirUsuarioDesativacaoTodos(entidades);
		getFachada().desativarTodas(entidades);
		addMensagemSucessoChave("MA005");
		consultar();
		return getTelaInicial();
	}

	/**
	 * 
	 * Inclui o usuário logado na lista para geração de histórico
	 * 
	 * @param entidades Collection<SaapDocAdmPresidencia>
	 */
	private void incluirUsuarioDesativacaoTodos(Collection<SaapDocAdmPresidencia> entidades ) {
		Iterator<SaapDocAdmPresidencia> iterator = entidades.iterator();
		Collection<SaapHistDocAdm> lista = new ArrayList<SaapHistDocAdm>();
		while (iterator.hasNext()) {
			SaapDocAdmPresidencia doc = iterator.next();
			SaapHistDocAdm historico = EntidadeFactory.getInstancia().novoSaapHistDocAdm();
			historico.setSeqUsuario(getUsuarioLogado().getId());
			lista.add(historico);
			doc.setSaapHistDocAdms(lista);
		}
	}
	
	/**
	 * 
	 * Inclui o usuário logado na lista para geração de histórico
	 * 
	 * @param SaapDocAdmPresidencia doc
	 */
	private void incluirUsuarioDesativacaoIndividual(SaapDocAdmPresidencia doc) {
		Collection<SaapHistDocAdm> lista = new ArrayList<SaapHistDocAdm>();
		SaapHistDocAdm historico = EntidadeFactory.getInstancia().novoSaapHistDocAdm();
		historico.setSeqUsuario(getUsuarioLogado().getId());
		lista.add(historico);
		doc.setSaapHistDocAdms(lista);
	}	

	/**
	 * Obtem uma coleção de SaapArqDocAdm com o nome de todos os arquivos incluídos para o 
	 * Documento.
	 * @param chaveDocumento
	 * @return  Collection<SaapArqDocAdm>
	 */
	private Collection<SaapArqDocAdm> obterColecaoNomesArquivos(Integer chaveDocumento){
		Collection<UploadItem> anexos = getVisao().getAnexos(); 

		Collection<SaapArqDocAdm> nomesAnexos = new LinkedList<SaapArqDocAdm>();
		Iterator<UploadItem> iterator = anexos.iterator();
		while (iterator.hasNext()) {
			UploadItem uploadItem = iterator.next();

			if (uploadItem.getFile()!=null){
				SaapArqDocAdm doc = new SaapArqDocAdm();
				doc.setNomeArqDoc(obterNome(uploadItem.getFileName()));
				doc.setSaapDocAdmPresidencia(new SaapDocAdmPresidencia());
				doc.getSaapDocAdmPresidencia().setId(chaveDocumento);
				nomesAnexos.add(doc);
			}
		}

		return nomesAnexos;
	}

	/**
	 * Obtém coleção dos arquivos excluídos.
	 * 
	 * @param chaveDocumento
	 * @return Collection<SaapArqDocAdm>
	 */
	private Collection<SaapArqDocAdm> obterColecaoArquivosExcluidos(Integer chaveDocumento){
		Collection<UploadItem> anexos = getVisao().getAnexosExcluidos();

		Collection<SaapArqDocAdm> nomesAnexos = new LinkedList<SaapArqDocAdm>();
		Iterator<UploadItem> iterator = anexos.iterator();
		while (iterator.hasNext()) {
			UploadItem uploadItem = iterator.next();
			SaapArqDocAdm doc = new SaapArqDocAdm();
			doc.setSaapDocAdmPresidencia(new SaapDocAdmPresidencia());
			doc.getSaapDocAdmPresidencia().setId(chaveDocumento);
			doc.setId(uploadItem.getFileSize());
			nomesAnexos.add(doc);
		}

		return nomesAnexos;
	}

	/**
	 * Evento chamado ao adicionar um arquivo para upload.
	 * 
	 * @param event UploadEvent
	 */
	public void upload(UploadEvent event){
		getVisao().getAnexos().add(event.getUploadItem());
	}
	
	/**
	 * Apaga arquivo(s).
	 * 
	 * @param chaveDocumento
	 * @return boolean
	 */
	private boolean apagarArquivos(int chaveDocumento) {
		Collection<UploadItem> anexos = getVisao().getAnexosExcluidos();
		Iterator<UploadItem> iterator = anexos.iterator();
		boolean retorno = true;

		while (iterator.hasNext()) {
			UploadItem deletedItem = iterator.next();
			String url = getUrlServidor()+Constante.PREFIXO_DIRETORIO+
				chaveDocumento + "/" + deletedItem.getFileName();
			try {

				SmbFile file = new SmbFile(url);
				if (file.exists()){
					file.delete(); 
				}
			} catch (Exception e) {
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * Envia os arquivos selecionados para upload para o servidor de arquivos.
	 * Uma pasta com o número do documento será criada, e os arquivos serão copiados 
	 * para dentro dela.
	 * 
	 * @param chaveDocumento
	 * @return boolean
	 */
	private boolean carregarArquivos(int chaveDocumento) {

		boolean retorno = true;
		
		String url = getUrlServidor()+Constante.PREFIXO_DIRETORIO+chaveDocumento;
		
		try {
			SmbFile diretorio = new SmbFile(url);
			if (!diretorio.exists()) {
				diretorio.mkdir();
				retorno = geraArqSaida(chaveDocumento);
			}
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Gera arquivo de saída.
	 * 
	 * @param chaveDocumento
	 * @return boolean
	 */
	private boolean geraArqSaida(int chaveDocumento) {
 		Collection<UploadItem> anexos = getVisao().getAnexos();
		Iterator<UploadItem> iterator = anexos.iterator();
		boolean retorno = true;
		while (iterator.hasNext()) {
			UploadItem uploadItem = iterator.next();

			if (uploadItem.getFile()!=null){
				
				try {
					
					SmbFile file = new SmbFile(getUrlServidor()+
				    		Constante.PREFIXO_DIRETORIO+chaveDocumento+"/", 
				    		obterNome(uploadItem.getFileName()));
					
				    FileInputStream fis;
					
					fis = new FileInputStream(uploadItem.getFile());

					OutputStream out = file.getOutputStream(); 
				    int bytes = 0; 
				    byte[] bteFile = new byte[1024]; 
				    while ((bytes = fis.read(bteFile)) != -1) { 
				    	out.write(bteFile, 0, bytes); 
			    }
				//Ocorreu um erro ao copiar os arquivos    
				} catch (Exception e) {
					retorno = false;
				}
		    }
		}

		return retorno;
	}

	/**
	 * Obtém o nome do arquivo referente ao endereço completo do arquivo informado como parametro.
	 * 
	 * @param path String
	 * @return String
	 */
	private String obterNome(String path) {
		int indice = path.lastIndexOf('\\');
		if (indice < 0) {
			indice = path.lastIndexOf('/');
		}
		if (indice > -1) {
			path = path.substring(indice + 1);
		}

		return path;
	}

    /** 
     * Retorna true se o período for válido. 
	 *
     * @return boolean 
     */ 
    private boolean isPeriodoInvalido() { 
    	boolean retorno = false;
    	if (!isVazio(getVisao().getConsultarDocumentosTO().getDataInicial()) 
    			&& !isVazio(getVisao().getConsultarDocumentosTO().getDataFinal())) { 

            Date dataInicial = getVisao().
            getConsultarDocumentosTO().getDataInicial();

            Date dataFinal = getVisao().
            getConsultarDocumentosTO().getDataFinal();
            retorno = dataInicial.after(dataFinal);
    	}

    	return retorno;
    }
    
    /** 
     * Retorna true se o período for válido. 
	 *
     * @return boolean 
     */ 
    private boolean isPeriodoInvalidoConvite() { 
    	boolean retorno = false;
    	if (!isVazio(getVisao().getConsultarDocumentosTO().getDataInicialConvite()) 
    			&& !isVazio(getVisao().getConsultarDocumentosTO().getDataFinalConvite())) { 

            Date dataInicial = getVisao().
            getConsultarDocumentosTO().getDataInicialConvite();

            Date dataFinal = getVisao().
            getConsultarDocumentosTO().getDataFinalConvite();
            retorno = dataInicial.after(dataFinal);
    	}

    	return retorno;
    }
    

    /**
     * Verifica se a hora é válida.
     * 
     * @param hora
     * @return boolean
     */
    private boolean isHoraValida(String hora){
    	boolean retorno = true; 
        if (hora!=null && !hora.equals("")){
        	retorno = UtilDate.isHoraValida(hora);
        }

        return retorno;
    }

    /**
     * Verifica se a data é nula.
     * 
     * @param dataInicial
     * @return boolean
     */
    private boolean isVazio(Date dataInicial) {
        return dataInicial == null;
    }

	/**
	 * Pagina o próximo registro na tela de detalhe.
	 * 
	 * @return String
	 */
	public String carregarDocProximo(){
	    SaapDocAdmPresidencia proximoDoc =  getFachada().
	    obterProximoDocumento(getVisao().getDocumentoDetalhadoTO().getId());
	    if (proximoDoc!=null){
	    	getVisao().setDocumentoDetalhadoTO(null);
	    	getVisao().setDocumentoDetalhadoTO(getFachada().
	    			obterDocumentoDetalhado(proximoDoc.getId()));
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
					configurarPaginacao(getVisao().
							getDocumentoDetalhadoTO().getListaAndamentos()));
			
			proximoDoc =  getFachada().obterProximoDocumento(
						getVisao().getDocumentoDetalhadoTO().getId());
			getVisao().setApresentaAnteriorDetalhe(true);
		    if (proximoDoc != null) {
		    	getVisao().setApresentaProximoDetalhe(true);
		    }else {
		    	getVisao().setApresentaProximoDetalhe(false);
		    }
			
	    }else{
	    	addMensagemInformativaChave("framework.mensagem.nenhumregistro");
	    }

	    return null;
	}

	/**
	 * Pagina o registro anterior na tela de detalhe.
	 * 
	 * @return String
	 */
	public String carregarDocAnterior(){
	    SaapDocAdmPresidencia proximoDoc =  getFachada().
	    obterDocumentoAnterior(getVisao().getDocumentoDetalhadoTO().getId());
	    if (proximoDoc!=null){
	    	getVisao().setDocumentoDetalhadoTO(null);
	    	getVisao().setAnexos(null);
	    	getVisao().setAnexosGravados(null);
	    	getVisao().setDocumentoDetalhadoTO(getFachada().
	    			obterDocumentoDetalhado(proximoDoc.getId()));
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
					configurarPaginacao(getVisao().getDocumentoDetalhadoTO().getListaAndamentos()));

			SaapDocAdmPresidencia anteriorDoc =  getFachada().obterDocumentoAnterior(
					getVisao().getDocumentoDetalhadoTO().getId());
			getVisao().setApresentaProximoDetalhe(true);
		    if (anteriorDoc != null) {
		    	getVisao().setApresentaAnteriorDetalhe(true);
		    }else {
		    	getVisao().setApresentaAnteriorDetalhe(false);
		    }
	    }else{
	    	addMensagemInformativaChave("framework.mensagem.nenhumregistro");
	    }

	    return null;
	}

	/**
	 * Carrega próximo documento na alteração.
	 * 
	 * @return String
	 */
	public String carregarDocProximoNaAlteracao(){
	    SaapDocAdmPresidencia proximoDoc =  getFachada().obterProximoDocumento(
	    		getVisao().getDocumentoDetalhadoTO().getId());
	    if (proximoDoc!=null){
	    	getVisao().setDocumentoDetalhadoTO(null);
	    	getVisao().setAnexos(null);
	    	getVisao().setAnexosGravados(null);
	    	getVisao().setDocumentoDetalhadoTO(getFachada().
	    			obterDocumentoDetalhado(proximoDoc.getId()));
			getVisao().setAnexos(carregarListaArquivos(getVisao().
					getDocumentoDetalhadoTO().getArquivosDocumento()));
			getVisao().setAnexosGravados(carregarListaArquivos(getVisao().
					getDocumentoDetalhadoTO().getArquivosDocumento()));
			
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
					configurarPaginacao(getVisao().
							getDocumentoDetalhadoTO().getListaAndamentos()));
			
			proximoDoc =  getFachada().obterProximoDocumento(
					getVisao().getDocumentoDetalhadoTO().getId());
			getVisao().setApresentaAnteriorAlteracao(true);
		    if (proximoDoc != null) {
		    	getVisao().setApresentaProximoAlteracao(true);
		    }else {
		    	getVisao().setApresentaProximoAlteracao(false);
		    }			

	    }else{
	    	addMensagemInformativaChave("framework.mensagem.nenhumregistro");
	    }

	    return null;
	}

	/**
	 * Carrega documento anterior na alteração.
	 * 
	 * @return String
	 */
	public String carregarDocAnteriorNaAlteracao(){
	    SaapDocAdmPresidencia proximoDoc =  getFachada().obterDocumentoAnterior(
	    		getVisao().getDocumentoDetalhadoTO().getId());
	    if (proximoDoc!=null){
	    	getVisao().setDocumentoDetalhadoTO(null);
	    	getVisao().setAnexos(null);
	    	getVisao().setAnexosGravados(null);
	    	getVisao().setDocumentoDetalhadoTO(getFachada().
	    			obterDocumentoDetalhado(proximoDoc.getId()));
			getVisao().setAnexos(carregarListaArquivos(getVisao().getDocumentoDetalhadoTO().
					getArquivosDocumento()));
			getVisao().setAnexosGravados(carregarListaArquivos(getVisao().getDocumentoDetalhadoTO().
					getArquivosDocumento()));

			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
					configurarPaginacao(getVisao().
							getDocumentoDetalhadoTO().getListaAndamentos()));
			
			SaapDocAdmPresidencia anteriorDoc =  getFachada().obterDocumentoAnterior(
					getVisao().getDocumentoDetalhadoTO().getId());
			getVisao().setApresentaProximoAlteracao(true);
		    if (anteriorDoc != null) {
		    	getVisao().setApresentaAnteriorAlteracao(true);
		    }else {
		    	getVisao().setApresentaAnteriorDetalhe(false);
		    }			
			
	    }else{
	    	addMensagemInformativaChave("framework.mensagem.nenhumregistro");
	    }	    
	    return null;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#detalhar()
	 */
	@Override
	public String detalhar() {
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().
       getExternalContext().getRequest(); 
       Integer idDocumento = null;

       //Caso o ID venha do link da consulta
       if (request.getParameter("selecionado")!=null && 
    		   !request.getParameter("selecionado").equals("") &&
    		   !request.getParameter("selecionado").equals("0")){
           idDocumento = Integer.parseInt(request.getParameter("selecionado"));
         //Caso o ID venha da tela de inclusão, na situação de apresentar similaridades           
       }else if(getVisao().getSelecionado()!=null && !getVisao().getSelecionado().equals("")){
    	   idDocumento = new Integer(getVisao().getSelecionado());
    	 //Caso o ID venha do campo N. Registro da tela de consulta
       }else{
           idDocumento = getVisao().getConsulta().getId();
       }

		getVisao().setDocumentoDetalhadoTO(getFachada().obterDocumentoDetalhado(idDocumento));
		
		PaginacaoConsultaHolder.setNumeroPagina(getVisao().getPaginaDetalhe());
		PaginacaoConsultaHolder.setTotalRegistros(
				getVisao().getDocumentoDetalhadoTO().getListaAndamentos().size());
		PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
		getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
					configurarPaginacao(getVisao().getDocumentoDetalhadoTO().getListaAndamentos()));
		getVisao().setIdDocumento(idDocumento);
		habilitaDesabilitaBotoes();
		
		return getTelaDetalhe();
	}
	/**
	 * Função utilizada para habilitar e desabilitar o anterior e proximo botão
	 */
	private void habilitaDesabilitaBotoes() {

		SaapDocAdmPresidencia anteriorDoc =  getFachada().
	    obterDocumentoAnterior(getVisao().getDocumentoDetalhadoTO().getId());
	    
	    if (anteriorDoc != null) {
	    	getVisao().setApresentaAnteriorDetalhe(true);
	    	getVisao().setApresentaAnteriorAlteracao(true);
	    }else {
	    	getVisao().setApresentaAnteriorDetalhe(false);
	    	getVisao().setApresentaAnteriorAlteracao(false);
	    }
	    
		SaapDocAdmPresidencia proximoDoc =  getFachada().
		obterProximoDocumento(getVisao().getDocumentoDetalhadoTO().getId());
	    if (proximoDoc != null) {
	    	getVisao().setApresentaProximoDetalhe(true);
	    	getVisao().setApresentaProximoAlteracao(true);
	    }else {
	    	getVisao().setApresentaProximoDetalhe(false);
	    	getVisao().setApresentaProximoAlteracao(false);
	    }
	}
	
	/**
	 * Pagina lista de andamentos da página de detalhe de documento.
	 * 
	 * @param event ActionEvent
	 */
	public void consultarDetalharDocumento(ActionEvent event) {
		ManutencaoDocumentoVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaDetalhe(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapAdmtAdmPresidenciaTO> col = 
				getVisao().getDocumentoDetalhadoTO().getListaAndamentos();
		if(!isVazio(col)) {
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(configurarPaginacao(col));
		}
	}	
	
	/**
	 * Detalha o documento 
	 * @param event ActionEvent
	 */
	public void detalhar(ActionEvent event) {
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		 getVisao().setPagina(PaginacaoConsultaHolder.getNumeroPagina());
		
		getVisao().setDocumentoDetalhadoTO(
				getFachada().obterDocumentoDetalhado(getVisao().getIdDocumento()));
		getVisao().getDocumentoDetalhadoTO().setListaAndamentos(
				configurarPaginacao(getVisao().getDocumentoDetalhadoTO().getListaAndamentos()));
	}
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar()
	 */
	@Override
	public String consultar() {
		String retorno = null;
		//Se o filtro N. do Registro estiver preenchido, carregar diretamente a tela de detalhe
		if (getVisao().getConsulta().getId()!=null && 
				getVisao().getConsulta().getId().intValue() != 0){
			getVisao().setLista(null);			
			detalhar();
			retorno = getTelaDetalhe();
		}else{
			String mensagemValidacao=validaFormulario();
		    if (mensagemValidacao==null) {
		    	
		    	getVisao().setPaginaVoltar(null);
				carregarConsultaDocumento();
				getConsultaVisao().setConsultaExecutada(true);		
				if (getConsultaVisao().getLista() == null || 
						getConsultaVisao().getLista().isEmpty()) {
					getVisao().setLista(null);
					addMensagemInformativaChave("framework.mensagem.nenhumregistro");
				}
		    }else{
	            addMensagemAlertaChave(mensagemValidacao);
	            getVisao().setLista(null);
		    }
		    
		    Integer gestaoSelecionada = getVisao().getGestao();
		    if (gestaoSelecionada > 0) {
		    	getVisao().setDesabilitarPeriodo(true);
		    }else {
		    	getVisao().setDesabilitarPeriodo(false);
		    }
		    
		    getVisao().getConsulta().setId(null);
		    retorno = getTelaInicial();
		}

		return retorno;
	}
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		ManutencaoDocumentoVisao visao = getVisao();
		ConsultarDocumentosTO to = getVisao().getConsultarDocumentosTO();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaVoltar(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapDocAdmPresidencia> col = getFachada().consultarDocumentos(to);
		if(!isVazio(col)) {
			getVisao().setLista(configurarPaginacao(col));
		}
	}
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#voltarInicio()
	 */
	@Override
	public String voltarInicio() {
		getVisao().setConsulta(getVisao().getConsultaClone());
		if(!isVazio(getVisao().getLista())) {
			PaginacaoConsultaHolder.setNumeroPagina(getVisao().getPaginaVoltar());
			PaginacaoConsultaHolder.setTotalRegistros(getVisao().getLista().size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			getVisao().setLista(configurarPaginacao(getVisao().getLista()));
		}else {
			getVisao().setLista(null);
		}
		return getTelaInicial();
	}
	
	
	/**
	 * Volta para tela de detalhe do documento.
	 * 
	 * @return String
	 */
	public String voltarInicioDetalhe() {
		getVisao().setDocumentoDetalhadoTO(getFachada().obterDocumentoDetalhado(getVisao().
				getDocumentoDetalhadoTO().getId()));
		Collection<SaapAdmtAdmPresidenciaTO> col = getVisao().
			getDocumentoDetalhadoTO().getListaAndamentos();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(getVisao().getPaginaDetalhe());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(configurarPaginacao(col));
		}else {
			getVisao().getDocumentoDetalhadoTO().setListaAndamentos(null);
		}
		return getTelaDetalhe();
	}	
	
	/**
	 * Usado para voltar da tela de detalhe para a tela de consulta
	 * @return String
	 */
	public String voltarTelaConsulta() {
		getVisao().setConsulta(getVisao().getConsultaClone());
	    return getTelaInicial();
	}

	/**
	 * Valida formulário inclusão
	 * 
	 * @return String
	 */
	private String validaFormularioInclusao(){
		String mensagemRetorno = null;
		if (UtilString.isVazio(getVisao().getConsulta().getDescDoc())) {
			adicionaMensagemError(Mensagem.getMA001(), "Número do documento / Descrição");
			mensagemRetorno = Mensagem.getMA001();
		}
		if (UtilString.isVazio(getVisao().getConsulta().getTxtAssuntoDoc())) {
			adicionaMensagemError(Mensagem.getMA001(), "Assunto / Evento");
			mensagemRetorno = Mensagem.getMA001();
		}
		if (UtilString.isVazio(getVisao().getDescricaoAndamento())) {
			adicionaMensagemError(Mensagem.getMA001(), "Descrição do andamento");
			mensagemRetorno = Mensagem.getMA001();
		}
		String retorno = validaConviteInclusao();
		if (!UtilString.isVazio(retorno)) {
			mensagemRetorno = retorno;
		}
		if (UtilString.isVazio(getVisao().getNomeMinistroGestao())) {
			mensagemRetorno = Mensagem.getMA013();
		}
		return mensagemRetorno;
	}
	
	/**
	 * Valida formulário alteração.
	 * 
	 * @return String
	 */
	private String validaFormularioAlteracao(){
		String mensagemRetorno = null;
		if (UtilString.isVazio(getVisao().getDocumentoDetalhadoTO().getDescDoc())) {
			adicionaMensagemError(Mensagem.getMA001(), "Número do documento / Descrição");
			mensagemRetorno = Mensagem.getMA001();
		}
		if (UtilString.isVazio(getVisao().getDocumentoDetalhadoTO().getTxtAssuntoDoc())) {
			adicionaMensagemError(Mensagem.getMA001(), "Assunto / Evento");
			mensagemRetorno = Mensagem.getMA001();
		}
		String retorno = validaConviteAlteracao();
		if (!UtilString.isVazio(retorno)) {
			mensagemRetorno = retorno;
		}
		return mensagemRetorno;
	}
	
	
	/**
	 * Valida formulário inclusão
	 * 
	 * @return String
	 */	
	private String validaConviteInclusao(){
		String mensagemRetorno = null;
		if (getVisao().getConsulta().getIndTipoDoc().intValue() == 
			Constante.TIPO_DOCUMENTO_CONVITE) {
			if (UtilString.isVazio(getVisao().getConsulta().getNomeRespConvite())) {
				adicionaMensagemError(Mensagem.getMA001(), "Quem convidou");
				mensagemRetorno = Mensagem.getMA001();
			}
			if (getVisao().getDataInicialConvite() == null) {
				adicionaMensagemError(Mensagem.getMA001(), "Data do evento");
				mensagemRetorno = Mensagem.getMA001();
			}
			if (UtilString.isVazio(getVisao().getHoraInicialConvite())) {
				adicionaMensagemError(Mensagem.getMA001(), "Hora do evento");
				mensagemRetorno = Mensagem.getMA001();
			}
		}
		
		if (!isHoraValida(getVisao().getHoraInicialConvite())) {
			mensagemRetorno = Mensagem.getMA009();
		}		
		
		return mensagemRetorno;
	}
	
	/**
	 * Valida formulário alteração.
	 * 
	 * @return String
	 */	
	private String validaConviteAlteracao(){
		String mensagemRetorno = null;
		if (getVisao().getDocumentoDetalhadoTO().getIndTipoDoc().intValue() == 
			Constante.TIPO_DOCUMENTO_CONVITE) {
			if (UtilString.isVazio(getVisao().getDocumentoDetalhadoTO().getNomeRespConvite())) {
				adicionaMensagemError(Mensagem.getMA001(), "Quem convidou");
				mensagemRetorno = Mensagem.getMA001();
			}
			if (getVisao().getDocumentoDetalhadoTO().getDtHrEventoConvite() == null) {
				adicionaMensagemError(Mensagem.getMA001(), "Data do evento");
				mensagemRetorno = Mensagem.getMA001();
			}
			if (UtilString.isVazio(getVisao().getHoraInicialConvite())) {
				adicionaMensagemError(Mensagem.getMA001(), "Hora do evento");
				mensagemRetorno = Mensagem.getMA001();
			}
		}
		
		if (!isHoraValida(getVisao().getHoraInicialConvite())) {
			mensagemRetorno = Mensagem.getMA009();
		}		
		
		return mensagemRetorno;
	}
	

	/**
	 * Valida formulário.
	 * 
	 * @return String
	 */
	private String validaFormulario(){
		String mensagemRetorno = null;

		if (isPeriodoInvalido()){
			mensagemRetorno = Mensagem.getMA008();
		}
		
		if (isPeriodoInvalidoConvite()){
			mensagemRetorno = Mensagem.getMA008();
		}

		if (!isHoraValida(getVisao().getConsultarDocumentosTO().getHoraInicialConvite())){
			mensagemRetorno = Mensagem.getMA009();
		}

		if (!isHoraValida(getVisao().getConsultarDocumentosTO().getHoraFinalConvite())){
			mensagemRetorno = Mensagem.getMA009();
		}

		return mensagemRetorno;
	}

	/**
	 * Carregar a consulta de documentos
	 */
	protected void carregarConsultaDocumento() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());

		ConsultarDocumentosTO to = getVisao().getConsultarDocumentosTO();
		to.setDocEntidade(getVisao().getConsulta());
		removerValoresDePaginacao();
		Collection<SaapDocAdmPresidencia> colecao = getFachada().consultarDocumentos(to);

		if (getVisao().getGestoes()==null){
			getVisao().setGestoes(carregarGestoes());
		}
		if(!isVazio(colecao)) {
			PaginacaoConsultaHolder.setNumeroPagina(getVisao().getPaginaVoltar());
			PaginacaoConsultaHolder.setTotalRegistros(colecao.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			colecao = configurarPaginacao(colecao);
		}
		getVisao().setConsultaClone(getVisao().getConsulta());
		getVisao().setLista(colecao);
	}

	/**
	 * Carregar as Datas
	 */
	public void carregarDatas() {
		Integer gestaoSelecionada = getVisao().getGestao();
		SaapGestaoPresidencia entidade = new SaapGestaoPresidencia();
		entidade.setId(gestaoSelecionada);
		if (entidade.getId() != 0) {
			entidade = getFachada().obterSaapGestaoPresidencia(entidade.getId());
		}else {
			entidade = null;
		}

		if (entidade!=null){
			getVisao().getConsultarDocumentosTO().setDataInicial(entidade.getDtInicialVigGestao());
			getVisao().getConsultarDocumentosTO().setDataFinal(
					entidade.getDtFinalVigGestao());				
			getVisao().setDesabilitarPeriodo(true);
		}else {
			getVisao().getConsultarDocumentosTO().setDataInicial(null);
			getVisao().getConsultarDocumentosTO().setDataFinal(null);
			getVisao().setDesabilitarPeriodo(false);
		}
	}

	/**
	 * Carrega gestões.
	 * 
	 * @return SelectItem[]
	 */
	private SelectItem[] carregarGestoes()	{

		SelectItem[] retorno = new SelectItem[0];

		Collection<SaapGestaoPresidencia> gestoes = getFachada().consultarSaapGestaoPresidencia();
		
		//Ordenar a coleção de gestões
		UtilColecao.ordenar(gestoes, 
				new Comparator<SaapGestaoPresidencia>(){
			public int compare(SaapGestaoPresidencia a1, SaapGestaoPresidencia a2) {
				return a1.getNomeMinistroPresidente().toUpperCase().compareTo(
						a2.getNomeMinistroPresidente().toUpperCase());
			}
		});		
		
		if(!UtilColecao.isVazio(gestoes)){
           int i = 0;
           SelectItem[] itens = new SelectItem[gestoes.size()];
           	for (SaapGestaoPresidencia t : gestoes) {
           		itens[i] = new SelectItem(t.getId(), t.getNomeMinistroPresidente(), 
           				t.getNomeMinistroPresidente());
           		i++;
           	}
           	retorno= itens;
		}

		return retorno;
	}

	/**
	 * Efetua o download do arquivo selecionado na visao.
	 * 
	 * @param e ActionEvent
	 * @throws IOException Falha na leitura ou envio do arquivo
	 */
	public void baixarArquivo(ActionEvent e) throws IOException {
		Object nome = e.getComponent().getAttributes().get("nomeArquivo");
		getVisao().setNomeArquivo(nome.toString());
		UploadItem uploadItem = obterArquivo();

		ApresentacaoUtil.getResponse().setContentType(null);
		String fileName = "attachment; filename=" + obterNome(uploadItem.getFileName());
		ApresentacaoUtil.getResponse().setHeader("Content-Disposition", fileName); 
		ApresentacaoUtil.getResponse().getOutputStream().write(uploadItem.getData());
		ApresentacaoUtil.getResponse().getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete();   
	}

	/**
	 * Remove o arquivo dos anexos da visao.
	 * 
	 * @param e ActionEvent
	 */
	public void removerArquivo(ActionEvent e) {
		Object nome = e.getComponent().getAttributes().get("nomeArquivo");
		getVisao().setNomeArquivo(nome.toString());
		UploadItem uploadItem = obterArquivo();
		UploadItem uploadItemGravado = obterArquivoGravados();
		if (uploadItem != null) {
			getVisao().getAnexos().remove(uploadItem);
			getVisao().getAnexosGravados().remove(uploadItemGravado);
			if (uploadItem.getFile()==null){
				//Caso o arquivo excluído seja um arquivo já existente no documento, adiciona-lo
				//na lista para a exclusão física posterior.
				getVisao().getAnexosExcluidos().add(uploadItem);
			}
		}
	}

	/**
	 * Obtém arquivo.
	 * 
	 * @return UploadItem O arquivo selecionado dos anexos da visao
	 */
	private UploadItem obterArquivo() {
		UploadItem retorno = null;
		Iterator<UploadItem> iterator = getVisao().getAnexos().iterator();
		while (iterator.hasNext()) {
			UploadItem uploadItem = iterator.next();
			if (uploadItem.getFileName().equals(getVisao().getNomeArquivo())) {
				retorno = uploadItem;
				break;
			}
		}

		return retorno;
	}
	

	/**
	 * Obtém arquivo.
	 * 
	 * @return UploadItem O arquivo selecionado dos anexos da visao
	 */
	private UploadItem obterArquivoGravados() {
		UploadItem retorno = null;
		Iterator<UploadItem> iterator = getVisao().getAnexosGravados().iterator();
		while (iterator.hasNext()) {
			UploadItem uploadItem = iterator.next();
			if (uploadItem.getFileName().equals(getVisao().getNomeArquivo())) {
				retorno = uploadItem;
				break;
			}
		}

		return retorno;
	}	

    /**
     * ActionListener para o Download de arquivos
     */
    public void downloadArquivoPDF() {
    	HtmlDataTable dataTable = getVisao().getDataTable2();
    	SaapArqDocAdm to = (SaapArqDocAdm) dataTable.getRowData();
        downloadPDF(to);
    }

    /**
     * Executa a operação de baixar o arquivo.
     * @param resultado
     */
    private void downloadPDF(final SaapArqDocAdm resultado)  {
        byte[] conteudo = getFachada().carregarPDF(resultado);
        if(conteudo != null){
            HttpServletResponse response = (HttpServletResponse)FacesContext.
                getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", 
                    "attachment;filename=" + resultado.getNomeArqDoc());
            response.setContentLength(conteudo.length);
            try{
                response.getOutputStream().write(conteudo);
                response.getOutputStream().flush();
                FacesContext.getCurrentInstance().responseComplete();
            }catch(IOException ioe){
                addMensagemAlertaChave(Mensagem.getMA028());
            }
        } else { 
        	addMensagemAlertaChave(Mensagem.getMA028());
        }
    }
    
	/**
	 * @return ação do botão remover todos.
	 */
	public String acaoRemoverTodos() {
		getVisao().getAnexos().clear();
		getArquivos().clear();
		return null;
	}

	/**
	 * @return ação para remoção de um arquivo enviado.
	 */
	public String acaoRemover() {
		if (getArquivo() != null && !getArquivo().equals("")) {
			for (int indice = 0; indice < getArquivos().size(); indice++) {
				UploadItem arq = getArquivos().get(indice);
				String temp = arq.getFileName();
				if (getArquivo().equals(temp)) {
					getArquivos().remove(indice);
					getVisao().getAnexos().remove(indice);
					indice--;
				}				
			}
		}
		return null;
	}    
    
    /**
     * Retorna a lista de Arquivos 
     * @return  List<UploadItem>
     */
	public List<UploadItem> getArquivos() {
		return arquivos;
	}

	/**
	 * Atribui a Lista de Arquivos
	 * @param arquivos List<UploadItem>
	 */
	public void setArquivos(List<UploadItem> arquivos) {
		this.arquivos = arquivos;
	}

	/**
	 * Retorna o nome do arquivo
	 * @return String
	 */
	public String getArquivo() {
		return arquivo;
	}

	/**
	 * Atribui o nome do arquivo
	 * @param nome String
	 */
	public void setArquivo(String nome) {
		this.arquivo = nome;
	}
    
    
}
