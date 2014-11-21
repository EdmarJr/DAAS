/*
 * ManutenaoSaapAdmtPendenciaAdmAction.java
 * 
 * Data de criação: 20/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.richfaces.event.DataScrollerEvent;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeString;
import br.jus.stj.alp01.comum.formatador.UtilFormatadorDeData;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.comum.PaginadorList;
import br.jus.stj.alp01.jsf.relatorios.Relatorio;
import br.jus.stj.saap.apresentacao.visao.ManutencaoAndamentosPendenciaVisao;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;


/**
 * Classe para controle das acoes do caso de uso de Manter Andamentos da Pendência
 *
 * @author Politec Informática
 */
public class ManutencaoSaapAdmtPendenciaAdmAction extends 
	SaapActionAbstrato<SaapAdmtAdmPresidencia> {
	
	private static final String CAMINHO_RELATORIO = "/relatorios/manterAndamentosPendencia.jasper";	
	private SaapFacade facade;
	
	/**
	 * @return A fachada para acesso aos metodos de negocio
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
	public ManutencaoAndamentosPendenciaVisao getVisao() {
		return getObjetoDoContexto(ManutencaoAndamentosPendenciaVisao.class);
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#iniciar()
	 */
	@Override
	public String iniciar() {
		getVisao().setConsultaTO(TOFactory.getInstancia().novoConsultarAndamentosPendenciaTO());
		getVisao().setLista(null);
		getVisao().setDisabledBtnImprimir(true);
		retirarRelatorioSessao();
		return getTelaInicial();
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#consultar()
	 */
	@Override
	public String consultar() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		if (isFiltroPesquisaValidado()) {
			carregarConsulta();
			if (isVazio(visao.getLista())) {
				addMensagemInformativaChave("framework.mensagem.nenhumregistro");
			}
		} else {
			visao.setLista(null);
		}
		return getTelaInicial();
	}	
	
	/**
	 * Limpa os campos referente ao filtro.
	 * 
	 * @return String
	 */
	public String limpar() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		visao.setConsultaTO(TOFactory.getInstancia().novoConsultarAndamentosPendenciaTO());
		visao.setLista(null);
		return getTelaInicial();
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#carregarConsulta()
	 */
	@Override
	protected void carregarConsulta() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		retirarRelatorioSessao();
		removerValoresDePaginacao();
		Collection<SaapAdmtAdmPresidencia> col = getFachada().
				consultarAndamentosPendenciaPeloFiltro(getConsultaTO());
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaVoltar());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.setLista(configurarPaginacao(col));
		}else {
			getVisao().setLista(null);
		}
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#voltarInicio()
	 */
	@Override
	public String voltarInicio() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
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
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaVoltar(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapAdmtAdmPresidencia> col = getFachada().
		consultarAndamentosPendenciaPeloFiltro(getConsultaTO());
		if(!isVazio(col)) {
			getVisao().setLista(configurarPaginacao(col));
		}
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
		ConsultarAndamentosPendenciaTO to = getConsultaTO();
		SaapPendenciaAdmPresidencia pendencia = to.getSaapPendenciaAdmPresidencia();
		AaUsuario usuario = to.getAaUsuario();
		SaapAdmtAdmPresidencia andamento = to.getSaapAdmtAdmPresidencia();
		return isVazio(pendencia.getDescEnderecoPendencia()) &&
				isVazio(pendencia.getTxtAssuntoPendencia()) &&
				isVazio(usuario.getNomeNicknameUsuario()) &&
				isVazio(andamento.getDescAdmtAdm()) &&
    	    	!isReferencia(to.getDataInicio()) &&
    	    	!isReferencia(to.getDataFim());				
		
	}	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#getTelaInicial()
	 */
	@Override
	protected String getTelaInicial() {
		return "saapAdmtPendenciaAdmAction.iniciar";
	}	
	
	
	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#getTelaDetalhe()
	 */
	@Override
	protected String getTelaDetalhe() {
		return "saapAdmtPendenciaAdmAction.detalhar";
	}	
	
	
	/**
	 * Retorna true se o período for válido.
	 * 
	 * @return boolean
	 */
	private boolean isPeriodoInvalido() {
		boolean retorno = false;
		ConsultarAndamentosPendenciaTO to = getConsultaTO();
		Date dataInicio = to.getDataInicio();
		Date dataFim = to.getDataFim();
		if (isReferencia(dataInicio) && isReferencia(dataFim)) {
			retorno = dataInicio.after(dataFim);
		}
		return retorno;
	}	
	
	/**
	 * Retorna ConsultarAndamentosPendenciaTO.
	 * @return ConsultarAndamentosPendenciaTO
	 */
	protected ConsultarAndamentosPendenciaTO getConsultaTO() {
		return getVisao().getConsultaTO();
	}	
	
	/**
	 * Atribui o documento selecionado.
	 * 
	 * @param e ActionEvent
	 */
	public void carregarDetalhe(ActionEvent e) {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		
		SaapPendenciaAdmPresidencia pendSelecionada = (SaapPendenciaAdmPresidencia) 
			e.getComponent().getAttributes().get("visao.pendenciaSelecionada");
		visao.setPendenciaSelecionada(pendSelecionada);
		String caminhoRetorno = (String) e.getComponent().getAttributes().get("caminhoRetorno");
		visao.setCaminhoRetorno(caminhoRetorno);
	}
	
	
	/**
	 * Alterar andamento do detalhe.
	 * 
	 * @return String
	 */
	public String alterarAndamentoDetalhe() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
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
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#detalhar()
	 */
	@Override
	public String detalhar() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		Integer paginaDetalhe = visao.getPaginaDetalhe();
		retirarRelatorioSessao();
		removerValoresDePaginacao();
		Integer id = visao.getPendenciaSelecionada().getId();
		if(!UtilObjeto.isReferencia(id) || id.intValue() == 0) {
			SaapPendenciaAdmPresidencia spap = getFachada().obterPendencia(UtilConversorDeString.
					converterParaInteger(visao.getIdPendenciaSelecionada()));
			visao.setPendenciaSelecionada(spap);
		}
		ConsultarAndamentosPendenciaTO to = getFachada().
				consultarAndamentosPelaPendencia(visao.getPendenciaSelecionada());
		if(isReferencia(to)) {
			Collection<SaapAdmtAdmPresidenciaTO> col = to.getListaAndamentos();
			if(!isVazio(col)) {
				PaginacaoConsultaHolder.setNumeroPagina(paginaDetalhe);
				PaginacaoConsultaHolder.setTotalRegistros(col.size());
				PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
				visao.setListaAndamentosDetalhe(configurarPaginacaoDetalhe(col));
			}else {
				visao.setListaAndamentosDetalhe(null);
			}
			visao.setResultadoTO(to);
		}
		return getTelaDetalhe();
	}

	/**
	 * Recupera o parâmetro id da pendência selecionada no caso de uso Manter Pendência.
	 * 
	 * @param evt ActionEvent
	 */
	public void recuperaParametro(ActionEvent evt) {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		UIParameter id = (UIParameter) evt.getComponent().findComponent("idPendenciaSelecionada");
		visao.setIdPendenciaSelecionada(id.getValue().toString());
		UIParameter caminhoRetorno = (UIParameter) evt.getComponent().
				findComponent("caminhoRetorno");
		visao.setCaminhoRetorno(caminhoRetorno.getValue().toString());
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
	 * Incluir andamento do detalhe.
	 * 
	 * @return String
	 */
	public String incluirAndamentoDetalhe() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
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
	 * Verifica se a descrição do andamento do detalhe foi preenchido.
	 * @param visao ManutencaoAndamentosPendenciaVisao
	 *
	 * @return boolean
	 */
	protected boolean isDescricaoAndamentoDetalhePreenchido(
				ManutencaoAndamentosPendenciaVisao visao) {
		boolean retorno = true;
		if(UtilString.isVazio(visao.getDescricaoAndamentoSelecionado())) {
			adicionaMensagemError("MA001", "Descrição do andamento");
			retorno = false;
		}
		return retorno;
	}	
	
	
	/**
	 * Retorna SaapAdmtAdmPresidencia carregado para inclusão.
	 *
	 * @return SaapAdmtAdmPresidencia
	 */
	private SaapAdmtAdmPresidencia getSaapAdmtPresidenciaInclusao() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidencia();
		SaapAdmtAdmPresidenciaPk saapAdmtAdmPresidenciaPk = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidenciaPk();
		saapAdmtAdmPresidenciaPk.setIndTipoAdmtAdm(Constante.TIPO_ANDAMENTO_PENDENCIA);
		
		saapAdmtAdmPresidencia.setId(saapAdmtAdmPresidenciaPk);
		saapAdmtAdmPresidencia.setSaapPendenciaAdmPresidencia(visao.getPendenciaSelecionada());
		saapAdmtAdmPresidencia.setDescAdmtAdm(visao.getDescricaoAndamentoSelecionado());
		saapAdmtAdmPresidencia.setSaapHistAdmtAdms(getHistoricoAndamento());
		return saapAdmtAdmPresidencia;
	}
	
	/**
	 * Cancela a edição da descrição do andamento selecionado.
	 * 
	 * @return String
	 */
	public String cancelarEdicaoAndamentoDetalhe() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		visao.setAndamentoSelecionado(null);
		visao.setDescricaoAndamentoSelecionado(null);
		visao.setApresentaBtnAlterar(Boolean.FALSE);
		return getTelaDetalhe();
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
	 * Prepara para a edição da descrição do andamento selecionado.
	 * 
	 * @return String
	 */
	public String iniciarEditarAndamentoDetalhe() {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		visao.setDescricaoAndamentoSelecionado(visao.getAndamentoSelecionado().getDescAdmtAdm());
		visao.setApresentaBtnAlterar(Boolean.TRUE);
		return getTelaDetalhe();
	}	
	
	/**
	 * Consultar lista de andamentos do detalhe. (Paginação)
	 * 
	 * @param event ActionEvent
	 */
	public void consultarListaAndamentosDetalhe(ActionEvent event) {
		ManutencaoAndamentosPendenciaVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaDetalhe(PaginacaoConsultaHolder.getNumeroPagina());
		ConsultarAndamentosPendenciaTO to = getFachada().consultarAndamentosPelaPendencia(
				visao.getPendenciaSelecionada());
		if(isReferencia(to)) {
			Collection<SaapAdmtAdmPresidenciaTO> col = to.getListaAndamentos();
			if(!isVazio(col)) {
				visao.setListaAndamentosDetalhe(configurarPaginacaoDetalhe(col));
			}
		}
	}
	
	/**
	 * Carrega os parametros enviados ao relatório de melhorias.
	 * 
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorio(Relatorio relatorio) {
		ConsultarAndamentosPendenciaTO to = getConsultaTO();
		Integer limiteRegistro = PaginacaoConsultaHolder.getLimiteRegistro();
		Integer numeroPagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer totalRegistros = PaginacaoConsultaHolder.getTotalRegistros();
		removerValoresDePaginacao();
		
		Collection<SaapAdmtAdmPresidencia> col = getFachada().
						consultarAndamentosPendenciaPeloFiltro(to);
		relatorio.setDados(col);
		relatorio.adicionaParametro("enderecadoA", to.getSaapPendenciaAdmPresidencia().
				getDescEnderecoPendencia());
		relatorio.adicionaParametro("assuntoDaPendencia", to.
				getSaapPendenciaAdmPresidencia().getTxtAssuntoPendencia());
		relatorio.adicionaParametro("acompanhamento", to.getAaUsuario().getNomeNicknameUsuario());
		relatorio.adicionaParametro("andamento", to.getSaapAdmtAdmPresidencia().getDescAdmtAdm());
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
	 * Gera o relatório correspondente a lista de andamentos cadastrados.
	 * 
	 * @return String
	 */
	public String imprimir() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO);
		carregarParametrosRelatorio(relatorio);
		relatorio.gerarRelatorioPDF();
		return null;
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

}
