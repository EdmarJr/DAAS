/*
 * ListarPendenciasAction.java
 * 
 * Data de criação: 28/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.jsf.relatorios.Relatorio;
import br.jus.stj.alp01.jsf.util.ContextoExternoUtil;
import br.jus.stj.saap.apresentacao.visao.ListarPendenciasVisao;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ListarPendenciasTO;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para controle das acoes do caso de uso de Listar Pendências.
 *
 * @author Politec Informática
 */
public class ListarPendenciasAction extends SaapActionAbstrato<SaapPendenciaAdmPresidencia> {

	private static final String CAMINHO_RELATORIO = "/relatorios/listarPendenciasAtivas.jasper";
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
	public ListarPendenciasVisao getVisao() {
		return getObjetoDoContexto(ListarPendenciasVisao.class);
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#iniciar()
	 */
	@Override
	public String iniciar() {
		retirarRelatorioSessao();
		ListarPendenciasVisao visao = getVisao();
		visao.setLista(null);
		visao.setPagina(1);
		PaginacaoConsultaHolder.setNumeroPagina(visao.getPagina());
		carregaConsulta();
		if (isVazio(visao.getLista())) {
			addMensagemInformativaChave("framework.mensagem.nenhumregistro");
		}
		return getTelaInicial();
	}

	/**
	 * Consulta pendência(s) ativa(s).
	 */
	protected void carregaConsulta() {
		ListarPendenciasVisao visao = getVisao();
		Integer pagina = visao.getPagina();
		removerValoresDePaginacao();
		visao.setLista(getFachada().consultar());
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		visao.setPagina(pagina);
		Collection<SaapPendenciaAdmPresidencia> col = configurarPaginacao(visao.getLista());
		if(!isVazio(col)) {
			visao.setLista(col);
		}else {
			visao.setLista(null);
		}
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar()
	 */
	@Override
	public String consultar() {
		retirarRelatorioSessao();
		carregaConsulta();
		return getTelaInicial();
	}

	/**
	 * Registra o acompanhamento da(s) pendência(s). 
	 * 
	 * @return String
	 */
	public String acompanhar() {
		retirarRelatorioSessao();
		if(isItenSelecionado()) {
			getFachada().acompanharPendencias(getAcompPendencias(getVisao().getEntidades()));
		}
		addMensagemSucessoChave("MP017");
		return getTelaInicial();
	}

	/**
	 * Desativar pendência.
	 * 
	 * @return String
	 */
	public String desativar() {
		retirarRelatorioSessao();
		if(isItenSelecionado()) {
			carregaUsuarioLista();
			getFachada().desativarTodasPendencias(Arrays.asList(getVisao().getEntidades()));
		}
		carregaConsulta();
		addMensagemSucessoChave("MP018");
		return getTelaInicial();
	}

	/**
	 * Imprimir relatório referente a listagem.
	 * 
	 * @return String
	 */
	public String imprimir() {
		retirarRelatorioSessao();
		Relatorio relatorio = new Relatorio(CAMINHO_RELATORIO);
		carregarParametrosRelatorio(relatorio);
		relatorio.gerarRelatorioPDF();
		return getTelaInicial();
	}

	/**
	 * Carrega os parametros enviados ao relatório de pendências ativas.
	 * 
	 * @param relatorio Relatorio que será montado.
	 */
	protected void carregarParametrosRelatorio(Relatorio relatorio) {
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		if(isItenSelecionado()) {
			relatorio.setDados(getListaPendenciasTO(Arrays.asList(getVisao().
					getEntidades()), true));
		}else {
			removerValoresDePaginacao();
			Collection<SaapPendenciaAdmPresidencia> col = getFachada().consultar();
			relatorio.setDados(getListaPendenciasTO(col, false));
		}
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}

	/**
	 * Retorna lista de pendências to carregada para o relatório.
	 * 
	 * @param entidades SaapPendenciaAdmPresidencia[]
	 * @param isSelecao boolean
	 * @return Collection<ListarPendenciasTO>
	 */
	protected Collection<ListarPendenciasTO> getListaPendenciasTO(
			Collection<SaapPendenciaAdmPresidencia> entidades, boolean isSelecao) {
		Collection<ListarPendenciasTO> colPendencias = getColecaoFactory().novoArrayList();
		ListarPendenciasTO to = null;
		SaapAcompPendenciaAdm saapAcompPendenciaAdm = null;
		for (SaapPendenciaAdmPresidencia entidade : entidades) {
			if(isSelecao) {
				entidade = getFachada().obterPendencia(entidade.getId());
			}
			to = TOFactory.getInstancia().novoListarPendenciasTO();
			to.setAssunto(entidade.getTxtAssuntoPendencia());
			to.setEnderecado(entidade.getDescEnderecoPendencia());
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
	 *Coloca o usuário logado na lista para inclusão do registro
	 */
	protected void carregaUsuarioLista() {
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		SaapPendenciaAdmPresidencia[] entidades = getVisao().getEntidades();
		
		SaapHistPendenciaAdm saapHistPendenciaAdm = EntidadeFactory.getInstancia().
								novoSaapHistPendenciaAdm();
		saapHistPendenciaAdm.setSeqUsuario(getUsuarioLogado().getId());
		Collection<SaapHistPendenciaAdm> lista = new ArrayList<SaapHistPendenciaAdm>();
		lista.add(saapHistPendenciaAdm);
		
		if (entidades != null && entidades.length > 0) {
			for (int i = 0; i < entidades.length; i++) {
				SaapPendenciaAdmPresidencia pendencia = entidades[i];
				pendencia.setSaapHistPendenciaAdms(lista);
			}
		}
	}	

	/**
	 * Retorna lista de acompanhamento(s) pendência(s) correspondente(s) ao(s) iten(s) 
	 * 		selecionado(s).
	 * 
	 * @param entidades SaapPendenciaAdmPresidencia[]
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
	protected Collection<SaapAcompPendenciaAdm> getAcompPendencias(
			SaapPendenciaAdmPresidencia[] entidades) {
		Collection<SaapAcompPendenciaAdm> col = getColecaoFactory().novoArrayList();
		SaapAcompPendenciaAdm saapAcompPendenciaAdm = null;
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = null;
		for (SaapPendenciaAdmPresidencia entidade : entidades) {
			saapAcompPendenciaAdm = getEntidadeFactory().novoSaapAcompPendenciaAdm();
			saapPendenciaAdmPresidencia = getEntidadeFactory().novoSaapPendenciaAdmPresidencia();
			saapPendenciaAdmPresidencia.setId(entidade.getId());
			saapAcompPendenciaAdm.setSaapPendenciaAdmPresidencia(saapPendenciaAdmPresidencia);
			saapAcompPendenciaAdm.setSeqUsuario(getUsuarioLogado().getId());
			col.add(saapAcompPendenciaAdm);
		}
		return col;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#getTelaInicial()
	 */
	@Override
	protected String getTelaInicial() {
		return "listarPendenciasAction.iniciar";
	}
}
