/*
 * ManutencaoSaapPendenciaAdmPresidenciaAction.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.richfaces.event.DataScrollerEvent;

import br.jus.stj.alp01.arquitetura.apresentacao.ServiceLocator;
import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeCharacter;
import br.jus.stj.alp01.comum.formatador.UtilFormatadorDeData;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.comum.PaginadorList;
import br.jus.stj.alp01.jsf.relatorios.Relatorio;
import br.jus.stj.alp01.jsf.util.ContextoExternoUtil;
import br.jus.stj.saap.apresentacao.visao.ManutencaoPendenciaVisao;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.ListarPendenciasTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para controle das acoes do caso de uso Manter Pendência.
 * @author Politec Informática
 */
public class ManutencaoSaapPendenciaAdmPresidenciaAction extends 
		SaapActionAbstrato<SaapPendenciaAdmPresidencia>{

	private static final String CAMINHO_RELATORIO = "/relatorios/manterPendencia.jasper";
	private SaapFacade facade;

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#iniciar()
	 */
	@Override
	public String iniciar() {
		ManutencaoPendenciaVisao visao = getVisao();
		visao.setConsultarPendenciasTO(TOFactory.getInstancia().
				novoConsultarPendenciasTO());
		visao.setLista(null);
		visao.setDisabledBtnImprimir(true);
		visao.getConsultarPendenciasTO().getEntidadePendencia().
				setIndSituacaoPendencia(Constante.PENDENCIA_ATIVA);
		return getTelaInicial();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.Action#getVisao()
	 */
	@Override
	public ManutencaoPendenciaVisao getVisao() {
		return getObjetoDoContexto(ManutencaoPendenciaVisao.class);
	}

	/**
	 * Retorna SaapFacade.
	 * 
	 * @return SaapFacade fachada para acesso aos metodos de negocio
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
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar()
	 */
	@Override
	public String consultar() {
		ManutencaoPendenciaVisao visao = getVisao();
		if(isFiltroPesquisaValidado()) {
			carregarConsultaPendencias();
			visao.setConsultaExecutada(true);	
			if (isVazio(visao.getLista())) {
				visao.setLista(null);
				addMensagemInformativaChave("framework.mensagem.nenhumregistro");
			}
		}else {
			visao.setLista(null);
		}
		return getTelaInicial();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#voltarInicio()
	 */
	@Override
	public String voltarInicio() {
		ManutencaoPendenciaVisao visao = getVisao();
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
	 * Carrega consulta pendências.
	 */
	protected void carregarConsultaPendencias() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		removerValoresDePaginacao();
		Collection <SaapPendenciaAdmPresidencia> col = getFachada().consultarPendencias(to);
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
	 * Carrega consulta pendências.
	 */
	protected void carregarConsultaAposAlterarDesativar() {
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaVoltar());
		Collection <SaapPendenciaAdmPresidencia> col = getFachada().consultarPendencias(to);
		if(!isVazio(col)) {
			visao.setLista(configurarPaginacao(col));
		}else {
			visao.setLista(null);
			addMensagemInformativaChave("framework.mensagem.nenhumregistro");
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#incluir()
	 */
	@Override
	public String incluir() {
		if(isCamposObrigatoriosPreenchidos()) {
			ManutencaoPendenciaVisao visao = getVisao();
			SaapPendenciaAdmPresidencia spap = visao.getEntidade();
			spap.setIndSituacaoPendencia(Constante.PENDENCIA_ATIVA);
			spap.setSaapHistPendenciaAdms(getHistPendenciaAdmPreenchido());
			if(!UtilString.isVazio(visao.getDescAndamento())) {
				Collection<SaapAdmtAdmPresidencia> lista = new ArrayList<SaapAdmtAdmPresidencia>();
				lista.add(getSaapAdmtPresidenciaInclusao());
				spap.setSaapAdmtAdmPresidencias(lista) ;
			}
			getFachada().inserir(spap);
			limpar();
			addMensagemSucessoChave("MP019");
		}
		return null;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#iniciarDadosInclusao()
	 */
	@Override
	protected void iniciarDadosInclusao() {
		limpar();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#iniciarEditar()
	 */
	@Override
	public String iniciarEditar() {
		ManutencaoPendenciaVisao visao = getVisao();
		if(isItenSelecionado()) {
			removerValoresDePaginacao();
			visao.setPendenciaDetalhadaTO(getFachada().obterPendenciaDetalhada(
					getIdPendenciaSelecionada()));
		}
		atribuiEntidade();
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaEdicao());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoAlterar(col));
		}else {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(null);
		}
		return getTelaAlteracao();
	}
	
	/**
	 * 
	 * Voltar a Tela de Alteração pela Tela de Andamentos
	 * 
	 * @return String
	 */
	public String voltarEditar() {
		ManutencaoPendenciaVisao visao = getVisao();
		removerValoresDePaginacao();
		visao.setPendenciaDetalhadaTO(getFachada().obterPendenciaDetalhada(
				visao.getPendenciaDetalhadaTO().getPendenciaBasica().getId()));
		atribuiEntidade();
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaEdicao());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoAlterar(col));
		}else {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(null);
		}
		return getTelaAlteracao();
	}
	
	/**
	 * 
	 * Restaura a tela de alteração
	 * 
	 * @return String
	 */
	public String restaurar() {
		ManutencaoPendenciaVisao visao = getVisao();
	
		visao.setPendenciaDetalhadaTO(getFachada().obterPendenciaDetalhada(
				getVisao().getPendenciaDetalhadaTO().getPendenciaBasica().getId()));
		
		visao.setEntidade(getVisao().getPendenciaDetalhadaTO().getPendenciaBasica());
		
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaEdicao());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoAlterar(col));
		}else {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(null);
		}
		return getTelaAlteracao();		
	}

	/**
	 * Atribui entidade.
	 */
	protected void atribuiEntidade() {
		ManutencaoPendenciaVisao visao = getVisao();
		if(!isReferencia(visao.getEntidade().getId()) || 
				visao.getEntidade().getId().intValue() == 0) {
			SaapPendenciaAdmPresidencia spap = visao.getPendenciaDetalhadaTO().getPendenciaBasica();
			visao.setEntidade(spap);
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#editar()
	 */
	@Override
	public String editar() {
		String retorno = getTelaInicial();
		if(isCamposObrigatoriosPreenchidos()) {
			SaapPendenciaAdmPresidencia spap = getVisao().getEntidade();
			spap.setSaapHistPendenciaAdms(getHistPendenciaAdmPreenchido());
			getFachada().alterar(spap);
			addMensagemSucessoChave("MP020");
			carregarConsultaAposAlterarDesativar();
		}else {
			retorno = getTelaAlteracao();
		}
		return retorno;
	}

	/**
	 * Retorna o id da pendência selecionada.
	 *
	 * @return Serializable 
	 */
	protected Integer getIdPendenciaSelecionada() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapPendenciaAdmPresidencia[] entidades = getVisao().getEntidades();
		return (Integer)entidades[0].getIdentificador();
	}

	/**
	 * Destativa pendência(s) selecionada(s)na listagem.
	 * 
	 * @return String
	 */
	public String desativar() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		carregaUsuarioLista();
		List<SaapPendenciaAdmPresidencia> list = Arrays.asList(getVisao().getEntidades());
		Collection<SaapPendenciaAdmPresidencia> entidades = new 
				ArrayList<SaapPendenciaAdmPresidencia>(list);
		getFachada().desativarTodasPendencias(entidades);
		addMensagemSucessoChave("MA018");
		carregarConsultaAposAlterarDesativar();
		return getTelaInicial();
	}

	/**
	 * Destativa pendência para as telas de detalhar/alterar.
	 * 
	 * @return String
	 */
	public String desativarPendencia() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapPendenciaAdmPresidencia spap = getVisao().getEntidade();
		spap.setSaapHistPendenciaAdms(getHistPendenciaAdmPreenchido());
		Collection<SaapPendenciaAdmPresidencia> entidades = new 
				ArrayList<SaapPendenciaAdmPresidencia>();
		entidades.add(spap);
		getFachada().desativarTodasPendencias(entidades);
		addMensagemSucessoChave("MA018");
		carregarConsultaAposAlterarDesativar();
		return getTelaInicial();
	}

	/**
	 * Coloca o usuário logado na lista para inclusão do registro
	 */
	protected void carregaUsuarioLista() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapPendenciaAdmPresidencia[] entidades = getVisao().getEntidades();
		Collection<SaapHistPendenciaAdm> lista = getHistPendenciaAdmPreenchido();
		if (entidades != null && entidades.length > 0) {
			for (int i = 0; i < entidades.length; i++) {
				SaapPendenciaAdmPresidencia pendencia = entidades[i];
				pendencia.setSaapHistPendenciaAdms(lista);
			}
		}
	}

	/**
	 * Retorna SaapHistPendenciaAdm preenchido.
	 * 
	 * @return Collection<SaapHistPendenciaAdm>
	 */
	private Collection<SaapHistPendenciaAdm> getHistPendenciaAdmPreenchido() {
		SaapHistPendenciaAdm saapHistPendenciaAdm = EntidadeFactory.getInstancia().
				novoSaapHistPendenciaAdm();
		saapHistPendenciaAdm.setSeqUsuario(getUsuarioLogado().getId());
		Collection<SaapHistPendenciaAdm> lista = new ArrayList<SaapHistPendenciaAdm>();
		lista.add(saapHistPendenciaAdm);
		return lista;
	}

	/**
	 * Retorna SaapHistAdmtAdm preenchido.
	 * 
	 * @return Collection<SaapHistAdmtAdm>
	 */
	private Collection<SaapHistAdmtAdm> getSaapHistAdmtAdmPreenchido() {
		SaapHistAdmtAdm saapHistAdmtAdm = EntidadeFactory.getInstancia().
				novoSaapHistAdmtAdm();
		saapHistAdmtAdm.setSeqUsuario(getUsuarioLogado().getId());
		Collection<SaapHistAdmtAdm> lista = new ArrayList<SaapHistAdmtAdm>();
		lista.add(saapHistAdmtAdm);
		return lista;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#detalhar()
	 */
	@Override
	public String detalhar() {
		ManutencaoPendenciaVisao visao = getVisao();
		HttpServletRequest request = (HttpServletRequest)FacesContext.
				getCurrentInstance().getExternalContext().getRequest(); 
		Integer idPendencia = null;
		//Caso o ID venha do link da consulta
		
		if (request.getParameter("selecionado")!= null && 
				!request.getParameter("selecionado").equals("")){
			idPendencia = Integer.parseInt(request.getParameter("selecionado"));
			removerValoresDePaginacao();
			visao.setPendenciaDetalhadaTO(getFachada().obterPendenciaDetalhada(idPendencia));
		}else {
			return null;
		}
		atribuiEntidade();
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(visao.getPaginaDetalhe());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoDetalhe(col));
		}else {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(null);
		}
		return getTelaDetalhe();
	}
	/**
	 * 
	 * Voltar a tela de detalhe
	 * 
	 * @return String
	 */
	public String voltarDetalhe() {
		getVisao().setPendenciaDetalhadaTO(getFachada().obterPendenciaDetalhada(
				getVisao().getPendenciaDetalhadaTO().getPendenciaBasica().getId()));
		atribuiEntidade();
		Collection<SaapAdmtAdmPresidenciaTO> col = getVisao().getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setNumeroPagina(getVisao().getPaginaDetalhe());
			PaginacaoConsultaHolder.setTotalRegistros(col.size());
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
			getVisao().getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoDetalhe(col));
		}else {
			getVisao().getPendenciaDetalhadaTO().setAndamentosPendencia(null);
		}
		return getTelaDetalhe();		
	}

	/**
	 * Retorna SaapAdmtAdmPresidencia carregado para inclusão.
	 *
	 * @return SaapAdmtAdmPresidencia
	 */
	private SaapAdmtAdmPresidencia getSaapAdmtPresidenciaInclusao() {
		ManutencaoPendenciaVisao visao = getVisao();
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidencia();
		SaapAdmtAdmPresidenciaPk saapAdmtAdmPresidenciaPk = EntidadeFactory.getInstancia().
				novoSaapAdmtAdmPresidenciaPk();
		saapAdmtAdmPresidenciaPk.setIndTipoAdmtAdm(Constante.TIPO_ANDAMENTO_PENDENCIA);
		saapAdmtAdmPresidencia.setId(saapAdmtAdmPresidenciaPk);
		saapAdmtAdmPresidencia.setDescAdmtAdm(visao.getDescAndamento());
		saapAdmtAdmPresidencia.setSaapHistAdmtAdms(getSaapHistAdmtAdmPreenchido());
		return saapAdmtAdmPresidencia;
	}

	/**
	 * Limpa os campos.
	 */
	public void limpar() {
		ManutencaoPendenciaVisao visao = getVisao();
		visao.setEntidade(null);
		visao.setDescAndamento(null);
		retirarRelatorioSessao();
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaVoltar(PaginacaoConsultaHolder.getNumeroPagina());
		Collection <SaapPendenciaAdmPresidencia> col = getFachada().consultarPendencias(to);
		if(!isVazio(col)) {
			visao.setLista(configurarPaginacao(col));
		}
	}

	/**
	 * Pagina lista de andamentos da tela detalhar pendência.
	 * 
	 * @param event ActionEvent
	 */
	public void consultarDetalharPendencia(ActionEvent event) {
		ManutencaoPendenciaVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaDetalhe(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoDetalhe(col));
		}
	}

	/**
	 * Pagina lista de andamentos da tela de alterarpendência.
	 * 
	 * @param event ActionEvent
	 */
	public void consultarEdicaoPendencia(ActionEvent event) {
		ManutencaoPendenciaVisao visao = getVisao();
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		visao.setPaginaEdicao(PaginacaoConsultaHolder.getNumeroPagina());
		Collection<SaapAdmtAdmPresidenciaTO> col = visao.getPendenciaDetalhadaTO().
				getAndamentosPendencia();
		if(!isVazio(col)) {
			visao.getPendenciaDetalhadaTO().setAndamentosPendencia(configurarPaginacaoAlterar(col));
		}
	}

	/**
	 * Retorna true se os campos obrigatórios estiverem preenchidos caso 
	 * contrário, retorna false.
	 * 
	 * @return boolean
	 */
	protected boolean isCamposObrigatoriosPreenchidos() {
		boolean retorno = true;
		ManutencaoPendenciaVisao visao = getVisao();
		String desc = visao.getEntidade().getDescEnderecoPendencia();
		String assunto = visao.getEntidade().getTxtAssuntoPendencia();
		if(UtilString.isVazio(desc)) {
			adicionaMensagemError("MA001", "Endereçado a");
			retorno = false;
		}
		if(UtilString.isVazio(assunto)) {
			adicionaMensagemError("MA001", "Assunto da pendência");
			retorno = false;
		}
		return retorno;
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
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		SaapPendenciaAdmPresidencia spap = to.getEntidadePendencia();

    	return isVazio(spap.getDescEnderecoPendencia()) &&
    			isVazio(spap.getTxtAssuntoPendencia()) &&
    	    	isVazio(to.getDescAcompanhamentoPendencia()) &&
    	    	isVazio(UtilConversorDeCharacter.converterParaString(spap.
    	    			getIndSituacaoPendencia())) &&
    	    	!isReferencia(to.getDataInicial()) &&
    	    	!isReferencia(to.getDataFinal());
	}

	/**
	 * Retorna true se o período for válido.
	 * 
	 * @return boolean
	 */
	private boolean isPeriodoInvalido() {
		boolean retorno = false;
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		Date dataInicio = to.getDataInicial();
		Date dataFim = to.getDataFinal();
		if (isReferencia(dataInicio) && isReferencia(dataFim)) {
			retorno = dataInicio.after(dataFim);
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
	 * Carrega os parametros enviados ao relatório de andamentos.
	 * 
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorio(Relatorio relatorio) {
		ManutencaoPendenciaVisao visao = getVisao();
		ConsultarPendenciasTO to = visao.getConsultarPendenciasTO();
		SaapPendenciaAdmPresidencia ep = to.getEntidadePendencia();
		Integer limiteRegistro = PaginacaoConsultaHolder.getLimiteRegistro();
		Integer numeroPagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer totalRegistros = PaginacaoConsultaHolder.getTotalRegistros();
		if(isItenSelecionado()) {
			relatorio.setDados(getListaPendenciasTO(Arrays.asList(visao.getEntidades())));
		}else {
			removerValoresDePaginacao();
			Collection <SaapPendenciaAdmPresidencia> col= getFachada().consultarPendencias(to);
			relatorio.setDados(getListaPendenciasTO(col));
		}
		relatorio.adicionaParametro("enderecadoA", ep.getDescEnderecoPendencia());
		relatorio.adicionaParametro("assuntoDaPendencia", ep.getTxtAssuntoPendencia());
		relatorio.adicionaParametro("acompanhamento", to.getDescAcompanhamentoPendencia());
		relatorio.adicionaParametro("situacao", getSituacaoFormatada(to));
		relatorio.adicionaParametro("dataInicio", getDataFormatada(to.getDataInicial()));
		relatorio.adicionaParametro("dataFim", getDataFormatada(to.getDataFinal()));
		PaginacaoConsultaHolder.setLimiteRegistro(limiteRegistro);
		PaginacaoConsultaHolder.setNumeroPagina(numeroPagina);
		PaginacaoConsultaHolder.setTotalRegistros(totalRegistros);
	}

	/**
	 * Retorna lista de pendências to carregada para o relatório.
	 * 
	 * @param entidades SaapPendenciaAdmPresidencia[]
	 * @return Collection<ListarPendenciasTO>
	 */
	protected Collection<ListarPendenciasTO> getListaPendenciasTO(
			Collection<SaapPendenciaAdmPresidencia> entidades) {
		
		Collection<ListarPendenciasTO> colPendencias = getColecaoFactory().novoArrayList();
		ListarPendenciasTO to = null;
		SaapAcompPendenciaAdm saapAcompPendenciaAdm = null;
		colPendencias = getColecaoFactory().novoArrayList();

		for (SaapPendenciaAdmPresidencia entidade : entidades) {
			entidade = getFachada().obterPendencia(entidade.getId());
			SaapHistPendenciaAdm shpa = getFachada().obtemPelaPkETsHistPendencia(entidade);
			if(isReferencia(shpa)) {
				AaUsuario usuario = getFachada().obterUsuario(shpa.getSeqUsuario());
				entidade.setRegistradoPor(usuario.getNomeNicknameUsuario());
			}
			
			to = TOFactory.getInstancia().novoListarPendenciasTO();
			to.setTsEntradaPendencia(entidade.getTsEntradaPendencia());
			to.setAssunto(entidade.getTxtAssuntoPendencia());
			to.setRegistradoPor(entidade.getRegistradoPor());
			to.setEnderecado(entidade.getDescEnderecoPendencia());
			to.setSituacao(entidade.getIndSituacaoPendenciaFormatado());
			saapAcompPendenciaAdm = getEntidadeFactory().novoSaapAcompPendenciaAdm();
			saapAcompPendenciaAdm.setSaapPendenciaAdmPresidencia(entidade);
			atribuiAcompanhamentoFormatado(to, saapAcompPendenciaAdm);
			colPendencias.add(to);
			
		}

		return colPendencias;
	}
	
	/**
	 * Atribui a to o acompanhamento formatado.
	 * 
	 * @param to ListarPendenciasTO
	 * @param saapAcompPendenciaAdm SaapAcompPendenciaAdm
	 */
	protected void atribuiAcompanhamentoFormatado(ListarPendenciasTO to,
			SaapAcompPendenciaAdm saapAcompPendenciaAdm) {
		Collection<SaapAcompPendenciaAdm> col = getFachada().consultarAcompanhamentos(
				saapAcompPendenciaAdm);
		if(!isVazio(col)) {
			int size = 1;
			StringBuffer str = new StringBuffer();
			for (SaapAcompPendenciaAdm acomp : col) {
				AaUsuario aaUsuario = getFachada().obterUsuario(acomp.getSeqUsuario());
				if(UtilObjeto.isReferencia(aaUsuario)) {
					str.append(aaUsuario.getNomeNicknameUsuario());
					if(size < col.size()) {
						str.append(", ");
					}
					size++;
				}
			}
			to.setAcompanhamento(str.toString());
		}
	}	

	/**
	 * Retorna situação formatada.
	 * 
	 * @param to
	 * @return String
	 */
	private String getSituacaoFormatada(ConsultarPendenciasTO to) {
		String retorno = "";
		Character ind = to.getEntidadePendencia().getIndSituacaoPendencia();
		if(isReferencia(ind)) {
			if(ind.charValue() == Constante.PENDENCIA_ATIVA) {
				retorno = "Ativo";
			}else if(ind.charValue() == Constante.PENDENCIA_DESATIVADA) {
				retorno = "Inativo";
			}
		}
		return retorno;
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
	 * Retorna true se tiver algum item selecionado na lista, caso contrário retorna false.
	 * 
	 * @return boolean
	 */
	protected boolean isItenSelecionado() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapPendenciaAdmPresidencia[] entidades = getVisao().getEntidades();
		return entidades != null && entidades.length > 0;
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
	 * Configura paginação do alterar.
	 * @param <O> tipo
	 * 
	 * @param colecao Collection<O>
	 * @return Collection<O>
	 */
	protected <O> Collection<O> configurarPaginacaoAlterar(Collection<O> colecao) {
		PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS);
		Integer total = PaginacaoConsultaHolder.getTotalRegistros();
		getVisao().setPaginaEdicao(PaginacaoConsultaHolder.getNumeroPagina());
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
	 * Recupera o parâmetro caminhoRetornoInicial.
	 * 
	 * @param evt ActionEvent
	 */
	public void recuperaParametro(ActionEvent evt) {
		ManutencaoPendenciaVisao visao = getVisao();
		UIParameter caminhoRetorno = (UIParameter) evt.getComponent().
				findComponent("caminhoRetornoInicial");
		visao.setCaminhoRetornoInicial(caminhoRetorno.getValue().toString());
	}
}
