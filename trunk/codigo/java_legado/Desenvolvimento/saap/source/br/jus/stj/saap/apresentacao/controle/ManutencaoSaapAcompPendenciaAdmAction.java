/*
 * ManutencaoSaapAcompPendenciaAdmAction.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.Collection;

import javax.faces.event.ActionEvent;

import br.jus.stj.alp01.arquitetura.apresentacao.ServiceLocator;
import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeInteger;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeString;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.saap.apresentacao.visao.ManutencaoSaapAcompPendenciaAdmVisao;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.util.UtilSelectItem;
import br.jus.stj.saap.util.constante.Mensagem;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;

/**
 * Classe para controle das acoes do caso de uso de Manter SaapAcompPendenciaAdm.
 *
 * @author Politec Informática
 */
public class ManutencaoSaapAcompPendenciaAdmAction extends 
	SaapActionAbstrato<SaapAcompPendenciaAdm> {

	private SaapFacade facade;

	/**
	 * @return A fachada para acesso aos metodos de negocio
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
	 * @see br.jus.stj.alp01.jsf.controle.Action#getVisao()
	 */
	@Override
	public ManutencaoSaapAcompPendenciaAdmVisao getVisao() {
		return getObjetoDoContexto(ManutencaoSaapAcompPendenciaAdmVisao.class);
	}

	/**
	 * Consulta o histórico do documento.
	 * @return String
	 */
	public String iniciarManutencaoAcomp() {
		//Stub
		//getVisao().setSeqPendenciaAdm("1");
		getVisao().getUsuarioSelecionado().setId(getUsuarioLogado().getId());
		//getVisao().setCaminhoRetorno("saapdocadmpresidencia.iniciar");
		getVisao().setUsuarioLogado(getVisao().getUsuarioSelecionado().getId());
		//Stub

		SaapAcompPendenciaAdm saapAcompPendenciaAdm = criarSaapAcompPendenciaAdm();
		saapAcompPendenciaAdm.setSeqUsuario(getVisao().getUsuarioLogado());
    	Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setLimiteRegistro(null);
		Collection<AaUsuario> list = getFachada().consultarUsuariosPendencia(saapAcompPendenciaAdm);

		getVisao().setListaUsuario(UtilSelectItem.createSelectItemList(list, "id",
				"nomeNicknameUsuario"));

		consultarAcompanhamento(getVisao().getUsuarioLogado());
    	PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
		getVisao().setApresentarSalvar(true);
		
		return getTelaInicial();
		
	}

	/**
	 * 
	 * Realiza a Consulta do Acompanhamento
	 * 
	 * @param idUsuario Integer
	 */
	private void consultarAcompanhamento(Integer idUsuario) {
		
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		
		SaapAcompPendenciaAdm saapAcompPendenciaAdm = criarSaapAcompPendenciaAdm();
		
		getVisao().getUsuarioSelecionado().setId(idUsuario);
		saapAcompPendenciaAdm.setSeqUsuario(getVisao().getUsuarioSelecionado().getId());
		
		visao.setSaapAcompPendenciaAdm(getFachada().
				consultarAcompanhamento(saapAcompPendenciaAdm));		
		
	}
	
	private SaapAcompPendenciaAdm criarSaapAcompPendenciaAdm() {
		
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = EntidadeFactory.
		getInstancia().novoSaapPendenciaAdmPresidencia();

		saapPendenciaAdmPresidencia.setId(UtilConversorDeString.converterParaInteger(
		visao.getSeqPendenciaAdm()));
		
		SaapAcompPendenciaAdm saapAcompPendenciaAdm = EntidadeFactory.
				getInstancia().novoSaapAcompPendenciaAdm();
		
		saapAcompPendenciaAdm.setSaapPendenciaAdmPresidencia(
		saapPendenciaAdmPresidencia);
		
		return saapAcompPendenciaAdm;
	}
	
	
	/**
	 * 
	 * Altera o usuário selecionado na combo de acompanhamento
	 */
	public void alterarUsuario() {
		getVisao().setApresentarSalvar(getVisao().getUsuarioSelecionado().
				getId().equals(getVisao().getUsuarioLogado()));
		consultarAcompanhamento(getVisao().getUsuarioSelecionado().getId());
	}
	
	/**
	 * 
	 * Salva o acompanhamento da pendência
	 *@return String
	 */
	public String salvarAcompanhamento() {
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		
		String retorno = null;
		
		//Cria a entidade para inclusão ou alteração do acompanhamento
		criaEntidadeInclusao();
		
		if (validarSalvar()) {
			emitirMensagemSucesso();
			getFachada().salvar(visao.getSaapAcompPendenciaAdm());
			iniciarManutencaoAcomp();
		}else {
			adicionaMensagemError(Mensagem.getMA001(), "Observação");
			consultarAcompanhamento(getVisao().getUsuarioSelecionado().getId());
			visao.getSaapAcompPendenciaAdm().setTxtObsAcompPendencia("");
			retorno = getTelaInicial();
		}
		
		return retorno;
		
	}
	/**
	 * Cria a Entidade para a inclusão do acompanhamento
	 */
	private void criaEntidadeInclusao() {
		
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = EntidadeFactory.
		getInstancia().novoSaapPendenciaAdmPresidencia();

		saapPendenciaAdmPresidencia.setId(UtilConversorDeString.converterParaInteger(
				visao.getSeqPendenciaAdm()));	
		
		visao.getSaapAcompPendenciaAdm().setSaapPendenciaAdmPresidencia(
				saapPendenciaAdmPresidencia);
		
		visao.getSaapAcompPendenciaAdm().setSeqUsuario(
				visao.getUsuarioSelecionado().getId());
	}
	
	/**
	 *Emite mensagem de alteração ou inclusão com sucesso
	 */
	private void emitirMensagemSucesso() {
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		
		if ((UtilObjeto.isReferencia(visao.getSaapAcompPendenciaAdm().getId())) 
				  && (visao.getSaapAcompPendenciaAdm().getId() != null)){
			addMensagemSucessoChave(Mensagem.getMP023());
		}else {
			addMensagemSucessoChave(Mensagem.getMP016());
		}
	}
	
	/**
	 * 
	 * Validar na hora de Salvar
	 * 
	 * @return boolean
	 */
	private boolean validarSalvar() {
		boolean retorno = true; 
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		if ((!UtilObjeto.isReferencia(visao.getSaapAcompPendenciaAdm().
				getTxtObsAcompPendencia())) || (UtilString.isVazio(visao.
				getSaapAcompPendenciaAdm().	getTxtObsAcompPendencia()))) {
			retorno = false; 
		}
		return retorno;
	}

	/**
	 * Carrega parâmetros para consulta e para retorno a página chamadora (Voltar).
	 * 
	 * @param e ActionEvent
	 */
	public void carregarParametros(ActionEvent e) {
		ManutencaoSaapAcompPendenciaAdmVisao visao = getVisao();
		Integer seqPendenciaAdm = (Integer) e.getComponent().getAttributes().get("seqPendenciaAdm");
		visao.setSeqPendenciaAdm(UtilConversorDeInteger.converterParaString(seqPendenciaAdm));
		String caminhoRetorno = (String) e.getComponent().getAttributes().get("caminhoRetorno");
		visao.setCaminhoRetorno(caminhoRetorno);
	}
}
