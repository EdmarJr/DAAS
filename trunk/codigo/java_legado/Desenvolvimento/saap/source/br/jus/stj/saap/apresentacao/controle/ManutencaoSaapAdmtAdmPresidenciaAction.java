/*
 * ManutencaoSaapAdmtAdmPresidenciaAction.java
 * 
 * Data de criação: 08/05/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.DataScrollerEvent;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeInteger;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeString;
import br.jus.stj.alp01.comum.conversor.instancia.ConversorAbstrato;
import br.jus.stj.alp01.comum.conversor.instancia.IConversor;
import br.jus.stj.alp01.comum.excecao.ConversorException;
import br.jus.stj.alp01.comum.formatador.UtilFormatadorDeData;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.comum.PaginadorList;
import br.jus.stj.alp01.jsf.relatorios.Relatorio;
import br.jus.stj.saap.apresentacao.visao.ManutencaoAndamentosVisao;
import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para controle das acoes do caso de uso de Manter Andamentos.
 *
 * @author Politec Informática
 */
public class ManutencaoSaapAdmtAdmPresidenciaAction extends 
		SaapActionAbstrato<SaapAdmtAdmPresidencia> {

	private static final String CAMINHO_RELATORIO = "/relatorios/manterAndamentos.jasper";
	private SaapFacade facade;

	/**
	 * Retorna SaapFacade.
	 * 
	 * @return SaapFacade fachada para acesso aos metodos de negocio
	 */
	@Override
	protected SaapFacade getFachada() {
		if (facade == null) {
			facade = getSaapFacade();
		}
		return facade;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.Action#getVisao()
	 */
	@Override
	public ManutencaoAndamentosVisao getVisao() {
		return getObjetoDoContexto(ManutencaoAndamentosVisao.class);
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#iniciar()
	 */
	@Override
	public String iniciar() {
		getVisao().setConsultaTO(TOFactory.getInstancia().novoConsultarAndamentosTO());
		getVisao().setLista(null);
		//getConsultaTO().getSaapDocAdmPresidencia().setIndTipoDoc(
		//		UtilConversorDeString.converterParaShort("1"));
		getVisao().setDisabledBtnImprimir(true);
		retirarRelatorioSessao();
		trataCampoNumeroDoRegistro();
		return getTelaInicial();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#detalhar()
	 */
	@Override
	public String detalhar() {
		ManutencaoAndamentosVisao visao = getVisao();
		retirarRelatorioSessao();
		removerValoresDePaginacao();
		ConsultarAndamentosTO to = getFachada().consultarAndamentosPeloDocumento(
				visao.getDocSelecionado());
		if(isReferencia(to)) {
			Collection<SaapAdmtAdmPresidenciaTO> col = to.getListaAndamentos();
			if(!isVazio(col)) {
				PaginacaoConsultaHolder.setTotalRegistros(col.size());
				PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
				PaginacaoConsultaHolder.setNumeroPagina(1);
				visao.setListaAndamentosDetalhe(configurarPaginacaoDetalhe(col));
			}else {
				visao.setListaAndamentosDetalhe(null);
			}
			visao.setResultadoTO(to);
		}
		return getTelaDetalhe();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#carregarConsulta()
	 */
	@Override
	protected void carregarConsulta() {
		ManutencaoAndamentosVisao visao = getVisao();
		retirarRelatorioSessao();
		removerValoresDePaginacao();
		Collection<SaapAdmtAdmPresidencia> col = getFachada().
				consultarAndamentosPeloFiltro(getConsultaTO());
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaVoltar());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.setLista(configurarPaginacao(col));
		}else {
			visao.setLista(null);
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#consultar()
	 */
	@Override
	public String consultar() {
		ManutencaoAndamentosVisao visao = getVisao();
		if (isFiltroPesquisaValidado()) {
			carregarConsulta();
			if (isVazio(visao.getLista())) {
				addMensagemInformativaChave("framework.mensagem.nenhumregistro");
			}
		} else {
			visao.setLista(null);
		}
		trataCampoNumeroDoRegistro();
		return getTelaInicial();
	}

	/**
	 * Trata campo número do registro.
	 */
	protected void trataCampoNumeroDoRegistro() {
		Integer numeroRegistro = getConsultaTO().getSaapDocAdmPresidencia().getId();
		if(!isReferencia(numeroRegistro) ||	numeroRegistro.intValue() == 0) {
			getConsultaTO().getSaapDocAdmPresidencia().setId(null);
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		ManutencaoAndamentosVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaVoltar(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapAdmtAdmPresidencia> col = getFachada().
				consultarAndamentosPeloFiltro(getConsultaTO());
		if(!isVazio(col)) {
			getVisao().setLista(configurarPaginacao(col));
		}
	}

	/**
	 * Consultar lista de andamentos do detalhe. (Paginação)
	 * 
	 * @param event ActionEvent
	 */
	public void consultarListaAndamentosDetalhe(ActionEvent event) {
		ManutencaoAndamentosVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		ConsultarAndamentosTO to = getFachada().consultarAndamentosPeloDocumento(
				visao.getDocSelecionado());
		if(isReferencia(to)) {
			Collection<SaapAdmtAdmPresidenciaTO> col = to.getListaAndamentos();
			if(!isVazio(col)) {
				visao.setListaAndamentosDetalhe(configurarPaginacaoDetalhe(col));
			}
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#voltarInicio()
	 */
	@Override
	public String voltarInicio() {
		ManutencaoAndamentosVisao visao = getVisao();
		if(!isVazio(visao.getLista())) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaVoltar());
			PaginacaoConsultaHolder.setTotalRegistros(visao.getLista().size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.setLista(configurarPaginacao(visao.getLista()));
		}else {
			visao.setLista(null);
		}
		return getTelaInicial();
	}

	/**
	 * Prepara para a edição da descrição do andamento selecionado.
	 * 
	 * @return String
	 */
	public String iniciarEditarAndamentoDetalhe() {
		ManutencaoAndamentosVisao visao = getVisao();
		visao.setDescricaoAndamentoSelecionado(visao.getAndamentoSelecionado().getDescAdmtAdm());
		visao.setApresentaBtnAlterar(Boolean.TRUE);
		return getTelaDetalhe();
	}

	/**
	 * Cancela a edição da descrição do andamento selecionado.
	 * 
	 * @return String
	 */
	public String cancelarEdicaoAndamentoDetalhe() {
		ManutencaoAndamentosVisao visao = getVisao();
		visao.setAndamentoSelecionado(null);
		visao.setDescricaoAndamentoSelecionado(null);
		visao.setApresentaBtnAlterar(Boolean.FALSE);
		return getTelaDetalhe();
	}

	/**
	 * Incluir andamento do detalhe.
	 * 
	 * @return String
	 */
	public String incluirAndamentoDetalhe() {
		ManutencaoAndamentosVisao visao = getVisao();
		if(isDescricaoAndamentoDetalhePreenchido(visao)) {
			getFachada().inserir(getSaapAdmtPresidenciaInclusao());
			visao.setAndamentoSelecionado(null);
			visao.setDescricaoAndamentoSelecionado(null);
			visao.setApresentaBtnAlterar(Boolean.FALSE);
			addMensagemSucessoChave("MP024");
		}
		return detalhar();
	}

	/**
	 * Retorna SaapAdmtAdmPresidencia carregado para inclusão.
	 *
	 * @return SaapAdmtAdmPresidencia
	 */
	private SaapAdmtAdmPresidencia getSaapAdmtPresidenciaInclusao() {
		ManutencaoAndamentosVisao visao = getVisao();
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidencia();
		SaapAdmtAdmPresidenciaPk saapAdmtAdmPresidenciaPk = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidenciaPk();
		saapAdmtAdmPresidenciaPk.setIndTipoAdmtAdm(Constante.TIPO_ANDAMENTO_DOCUMENTO);
		saapAdmtAdmPresidencia.setId(saapAdmtAdmPresidenciaPk);
		saapAdmtAdmPresidencia.setSaapDocAdmPresidencia(visao.getDocSelecionado());
		saapAdmtAdmPresidencia.setDescAdmtAdm(visao.getDescricaoAndamentoSelecionado());
		saapAdmtAdmPresidencia.setSaapHistAdmtAdms(getHistoricoAndamento());
		
		return saapAdmtAdmPresidencia;
	}

	/**
	 * Alterar andamento do detalhe.
	 * 
	 * @return String
	 */
	public String alterarAndamentoDetalhe() {
		ManutencaoAndamentosVisao visao = getVisao();
		if(isDescricaoAndamentoDetalhePreenchido(visao)) {
			SaapAdmtAdmPresidencia admtSelecionado = visao.getAndamentoSelecionado();
			admtSelecionado.setDescAdmtAdm(visao.getDescricaoAndamentoSelecionado());
			admtSelecionado.setSaapHistAdmtAdms(getHistoricoAndamento());
			getFachada().alterar(admtSelecionado);
			visao.setAndamentoSelecionado(null);
			visao.setDescricaoAndamentoSelecionado(null);
			visao.setApresentaBtnAlterar(Boolean.FALSE);
			addMensagemSucessoChave("MP025");
		}
		return getTelaDetalhe();
	}
	
	/**
	 * @return SaapHistAdmtAdm
	 */
	private Collection<SaapHistAdmtAdm> getHistoricoAndamento() {
		SaapHistAdmtAdm saapHistAdmtAdm = EntidadeFactory.getInstancia().novoSaapHistAdmtAdm();
		saapHistAdmtAdm.setSeqUsuario(getUsuarioLogado().getId());
		Collection<SaapHistAdmtAdm> colecao = new ArrayList<SaapHistAdmtAdm>();
		colecao.add(saapHistAdmtAdm);
		
		return colecao;
	}

	/**
	 * Verifica se a descrição do andamento do detalhe foi preenchido.
	 * @param visao ManutencaoAndamentosVisao
	 *
	 * @return boolean
	 */
	protected boolean isDescricaoAndamentoDetalhePreenchido(ManutencaoAndamentosVisao visao) {
		boolean retorno = true;
		if(UtilString.isVazio(visao.getDescricaoAndamentoSelecionado())) {
			adicionaMensagemError("MA001", "Descrição do andamento");
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Atribui o documento selecionado.
	 * 
	 * @param e ActionEvent
	 */
	public void carregarDetalhe(ActionEvent e) {
		ManutencaoAndamentosVisao visao = getVisao();
		SaapDocAdmPresidencia docSelecionado = (SaapDocAdmPresidencia) e.getComponent().
				getAttributes().get("visao.docSelecionado");
		visao.setDocSelecionado(docSelecionado);
		SaapAdmtAdmPresidencia andamentoSelecionado = (SaapAdmtAdmPresidencia) e.getComponent().
				getAttributes().get("visao.andamentoSelecionadoConsulta");
		visao.setAndamentoSelecionadoConsulta(andamentoSelecionado);
		String caminhoRetorno = (String) e.getComponent().getAttributes().get("caminhoRetorno");
		visao.setCaminhoRetorno(caminhoRetorno);		
	}

	/**
	 * Atribui o documento selecionado.
	 * 
	 * @param e ActionEvent
	 */
	public void atribuirAndamento(ActionEvent e) {
		SaapAdmtAdmPresidencia andamentoSelecionado = (SaapAdmtAdmPresidencia) e.getComponent().
				getAttributes().get("visao.andamentoSelecionado");
		getVisao().setAndamentoSelecionado(andamentoSelecionado);
	}

	/**
	 * Método responsável pelo o carregamento da combo tipo de documento.
	 * @return Collection<SelectItem>
	 */
	public Collection<SelectItem> getListaTiposDocumentos() {
		ManutencaoAndamentosVisao visao = getVisao();
		if (UtilColecao.isVazio(visao.getListaTiposDocumentos())) {
			removerValoresDePaginacao();
			Collection<Dominio> col = getFachada().consultarTipoDocumento();
			visao.setListaTiposDocumentos(conversorDeDominioParaSelectItem(col));
		}
		return visao.getListaTiposDocumentos();
	}

	/**
	 * Retorna uma coleção de selectItem de dominio.
	 * 
	 * @param colecao : Coleção de selectItem de dominio
	 * @return Collection<SelectItem>
	 */
	protected Collection<SelectItem> conversorDeDominioParaSelectItem(
			final Collection<Dominio> colecao) {
		IConversor<Dominio,SelectItem> conversor = new ConversorAbstrato<Dominio,SelectItem>() {
			public SelectItem converter(Dominio objeto)
					throws ConversorException {
				SelectItem selectItem = new SelectItem(); 
				selectItem.setValue("" + objeto.getId().getDescCodigo() );				
				selectItem.setLabel(objeto.getDescEstado());
				return selectItem;
			}
		};
		return UtilColecao.aplicarConversor(colecao, conversor);
	}

	/**
	 * Retorna true se o período for válido.
	 * 
	 * @return boolean
	 */
	private boolean isPeriodoInvalido() {
		boolean retorno = false;
		ConsultarAndamentosTO to = getConsultaTO();
		Date dataInicio = to.getDataInicio();
		Date dataFim = to.getDataFim();
		if (isReferencia(dataInicio) && isReferencia(dataFim)) {
			retorno = dataInicio.after(dataFim);
		}
		return retorno;
	}

	/**
	 * Retorna ConsultarAndamentosTO.
	 * @return ConsultarAndamentosTO
	 */
	protected ConsultarAndamentosTO getConsultaTO() {
		return getVisao().getConsultaTO();
	}

	/**
	 * Retorna true se o filtro da pesquisa for válido.
	 * 
	 * @return boolean
	 */
	protected boolean isFiltroPesquisaValidado() {
		boolean retorno = true;
		if (isNenhumCampoPreenchido()) {
			adicionaMensagemError("MA021");
			retorno = false;
		}
		if (retorno && isPeriodoInvalido()) {
			adicionaMensagemError("MA008");
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Verifica se nunhum campo foi preenchido.
	 * 
	 * @return boolean
	 */
	protected boolean isNenhumCampoPreenchido() {
		ConsultarAndamentosTO to = getConsultaTO();
		SaapDocAdmPresidencia doc = to.getSaapDocAdmPresidencia();
    	return (!isReferencia(doc.getId()) || doc.getId().intValue() == 0) &&
    			isVazio(doc.getNomeLocalFisicoDoc()) &&
    	    	isVazio(doc.getDescDoc()) &&
    	    	isVazio(to.getDescAdmtAdm()) &&
    	    	!isReferencia(to.getDataInicio()) &&
    	    	!isReferencia(to.getDataFim());
	}

	/**
	 * Configura paginação do detalhe.
	 * @param <O> tipo
	 * 
	 * @param colecao Collection<O>
	 * @return Collection<O>
	 */
	protected <O> Collection<O> configurarPaginacaoDetalhe(Collection<O> colecao) {
		PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
		Integer total = PaginacaoConsultaHolder.getTotalRegistros();
		getVisao().setPaginaDetalhe(PaginacaoConsultaHolder.getNumeroPagina());
		if (total != null && total.intValue() > 0) {
			PaginadorList<O> novaLista = new PaginadorList<O>();
			novaLista.addAll(colecao);
			novaLista.setSize(total.intValue());
			novaLista.setRegistrosPorPagina(PaginacaoConsultaHolder.getLimiteRegistro().intValue());
			colecao = novaLista;
		}
		return colecao;
	}

	/**
	 * Limpa os campos referente ao filtro.
	 * 
	 * @return String
	 */
	public String limpar() {
		ManutencaoAndamentosVisao visao = getVisao();
		retirarRelatorioSessao();
		visao.setConsultaTO(TOFactory.getInstancia().novoConsultarAndamentosTO());
		getConsultaTO().getSaapDocAdmPresidencia().setIndTipoDoc(
				UtilConversorDeString.converterParaShort("1"));
		visao.setLista(null);
		return getTelaInicial();
	}

	/**
	 * Gera o relatório correspondente a lista de andamentos cadastrados.
	 * 
	 * @return String
	 */
	public String imprimir() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO);
		carregarParametrosRelatorio(relatorio);
		relatorio.gerarRelatorioPDF();
		trataCampoNumeroDoRegistro();
		return null;
	}

	/**
	 * Carrega os parametros enviados ao relatório de andamentos.
	 * 
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorio(Relatorio relatorio) {
		ConsultarAndamentosTO to = getConsultaTO();
		Integer limiteRegistro = PaginacaoConsultaHolder.getLimiteRegistro();
		Integer numeroPagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer totalRegistros = PaginacaoConsultaHolder.getTotalRegistros();
		removerValoresDePaginacao();
		Collection<SaapAdmtAdmPresidencia> col = getFachada().consultarAndamentosPeloFiltro(to);
		relatorio.setDados(col);
		relatorio.adicionaParametro("numeroRegistro", getNumeroRegistro(to));
		relatorio.adicionaParametro("arquivadoPasta", to.getSaapDocAdmPresidencia().
				getNomeLocalFisicoDoc());
		relatorio.adicionaParametro("tipoDocumento", getTipoDocumento(to));
		relatorio.adicionaParametro("numeroDocumentoDescricao", to.getSaapDocAdmPresidencia().
				getDescDoc());
		relatorio.adicionaParametro("andamento", to.getDescAdmtAdm());
		relatorio.adicionaParametro("dataInicio", getDataFormatada(to.getDataInicio()));
		relatorio.adicionaParametro("dataFim", getDataFormatada(to.getDataFim()));
		PaginacaoConsultaHolder.setLimiteRegistro(limiteRegistro);
		PaginacaoConsultaHolder.setNumeroPagina(numeroPagina);
		PaginacaoConsultaHolder.setTotalRegistros(totalRegistros);
	}

	/**
	 * Retorna a data formatada.
	 * 
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
	 * Retorna a descrição do tipo de documento.
	 * 
	 * @param to ConsultarAndamentosTO
	 * @return String
	 */
	protected String getTipoDocumento(ConsultarAndamentosTO to) {
		String retorno = "";
		Short indTipo = to.getSaapDocAdmPresidencia().getIndTipoDoc();
		if(isReferencia(indTipo) && indTipo.shortValue() > 0) {
			retorno = (String)getMapaTipoDocumento().get(indTipo.intValue());
		}
		return retorno;
	}

	/**
	 * Retorna um mapa referente aos tipos de documentos.
	 * 
	 * @return Map
	 */
	protected Map getMapaTipoDocumento() {
		Map mapTipo = getColecaoFactory().novoHashMap();
		mapTipo.put(Constante.TIPO_DOCUMENTO_OFICIO, "Ofício");
		mapTipo.put(Constante.TIPO_DOCUMENTO_AVISO, "Aviso");
		mapTipo.put(Constante.TIPO_DOCUMENTO_CARTA, "Carta");
		mapTipo.put(Constante.TIPO_DOCUMENTO_CONVITE, "Convite");
		mapTipo.put(Constante.TIPO_DOCUMENTO_EMAIL, "Email");
		mapTipo.put(Constante.TIPO_DOCUMENTO_MEMORANDO, "Memorando");
		mapTipo.put(Constante.TIPO_DOCUMENTO_PROCESSO, "Processo");
		return mapTipo;
	}

	/**
	 * Retorna o número do registro.
	 * 
	 * @param to ConsultarAndamentosTO
	 * @return String
	 */
	protected String getNumeroRegistro(ConsultarAndamentosTO to) {
		String retorno = "";
		Integer numeroRegistro = to.getSaapDocAdmPresidencia().getId();
		if(isReferencia(numeroRegistro) && numeroRegistro.intValue() > 0) {
			retorno = UtilConversorDeInteger.converterParaString(to.
					getSaapDocAdmPresidencia().getId());
		}
		return retorno;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#getTelaInicial()
	 */
	@Override
	protected String getTelaInicial() {
		return "saapAdmtAdmPresidenciaAction.iniciar";
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#getTelaDetalhe()
	 */
	@Override
	protected String getTelaDetalhe() {
		return "saapAdmtAdmPresidenciaAction.detalhar";
	}
}
