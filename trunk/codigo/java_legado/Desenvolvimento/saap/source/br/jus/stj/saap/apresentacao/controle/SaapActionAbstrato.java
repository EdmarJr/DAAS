/*
 * SaapActionAbstrato.java
 *
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Ltda.
 *
 */
package br.jus.stj.saap.apresentacao.controle;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jus.stj.alp01.arquitetura.apresentacao.ServiceLocator;
import br.jus.stj.alp01.arquitetura.entidade.Entidade;
import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.arquitetura.negocio.Facade;
import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.fabrica.ColecaoFactory;
import br.jus.stj.alp01.comum.fabrica.StringFactory;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.comum.string.UtilString;
import br.jus.stj.alp01.jsf.controle.ManutencaoAction;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.util.UtilFacesMensagem;
import br.jus.stj.saap.util.UtilJSF;
import br.jus.stj.saap.util.UtilSelectItem;
import br.jus.stj.saap.util.constante.Mensagem;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe responsável pela infra-estrutura necessária para as actions.
 * 
 * @param <E> Tipo da entidade
 * @author adrianop
 */
public abstract class SaapActionAbstrato<E extends Entidade> extends
		ManutencaoAction<E> implements Serializable {

	private static final String RELATORIO = "relatorio";

	/**
	 * Adiciona mensagem no facemensagem
	 * 
	 * @param chave chave do face message
	 * @param valor valor do face message
	 */
	protected void adicionaMensagemError(String chave, Object... valor) {
		UtilFacesMensagem.adicionaMensagemError(chave, valor);
	}

	/**
	 * Converte uma lista de entidades em uma lista de select item.
	 * 
	 * @param <T> Tipo da entidade
	 * @param lista Lista de entidade
	 * @param atributo O atributo dos beans que serão exibidos no label do
	 *            selectItem
	 * @return lista de selectitem
	 */
	protected <T extends Entidade> List<SelectItem> createSelectItemList(
			Collection<T> lista, String atributo) {
		return UtilSelectItem.createSelectItemList(lista, atributo);
	}

	/**
	 * Retorna saap facade.
	 * 
	 * @return saap facade.
	 */
	protected SaapFacade getSaapFacade() {
		SaapFacade facade = getFachadaSpring();
		return facade;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.Action#getFachada()
	 */
	@Override
	protected SaapFacade getFachada() {
		return getSaapFacade();
	}

	/**
	 * Retorna fábrica de coleção.
	 * 
	 * @return fábrica de coleção.
	 */
	protected ColecaoFactory getColecaoFactory() {
		return ColecaoFactory.getInstancia();
	}

	/**
	 * Retorna uma nova instância de FabricaEntidade.
	 * 
	 * @return FabricaEntidade
	 */
	protected EntidadeFactory getEntidadeFactory() {
		return EntidadeFactory.getInstancia();
	}

	/**
	 * @return ExternalContext
	 */
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * @return FacesContext
	 */
	protected FacesContext getFacesContext() {
		return UtilJSF.getFacesContext();
	}

	/**
	 * Retorna a fachada solicitada. <br/>
	 * Exemplo de uso: <br/>
	 * FachadaFulanoDeTal fachada = getFachada();
	 * 
	 * @param <T> Tipo da fachada que será retornada.
	 * @param t Tipo da fachada que será retornada.
	 * @return fachada
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Facade> T getFachadaSpring(T... t) {
		Class<T> classe = (Class<T>) t.getClass().getComponentType();
		return getServiceLocator().getFachada(classe);
	}

	/**
	 * Retorna um objeto do contexto do JSF. A identificação do objeto que será
	 * localizado no contexto JSF será o mesmo nome da classe. Ex: se a classe
	 * se chama br.com.MinhaClasseAction, será localizado o ID MinhaClasseAction
	 * 
	 * @param <T> Tipo do objeto de retorno.
	 * @param tipo Tipo do objeto que será retornado. Ex: BuildView.class
	 * @return objeto do contexto JSF.
	 */
	protected <T extends Serializable> T getObjetoDoContexto(Class<?> tipo) {
		String id = UtilObjeto.getNomeSemPacote(tipo);
		return (T) UtilJSF.getObjetoDoContexto(id, tipo);
	}

	/**
	 * Retorna um objeto do contexto do JSF.
	 * 
	 * @param <T> Tipo do objeto de retorno.
	 * @param id Identificador do objeto. Ex: #{BuildView}
	 * @param tipo Tipo do objeto que será retornado. Ex: BuildView.class
	 * @return objeto do contexto JSF.
	 */
	protected <T extends Serializable> T getObjetoDoContexto(String id,
			Class<?> tipo) {
		return (T) UtilJSF.getObjetoDoContexto(id, tipo);
	}

	/**
	 * @param chave :Chave do parâmwtro no request
	 * @return RequestParameter
	 */
	protected String getRequestParameter(String chave) {
		return getRequestParameterMap().get(chave);
	}

	/**
	 * @return RequestParameterMap
	 */
	protected Map<String, String> getRequestParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}

	/**
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * @return service locator.
	 */
	protected ServiceLocator getServiceLocator() {
		return ServiceLocator.getInstancia();
	}

	/**
	 * @return fábrica de TO
	 */
	protected TOFactory getTOFactory() {
		return TOFactory.getInstancia();
	}

	/**
	 * Retorna true se os objetos existirem.
	 * 
	 * @param objetos Objetos
	 * @return true se os objetos existirem.
	 */
	protected boolean isReferencia(Object... objetos) {
		return UtilObjeto.isReferenciaTodos(objetos);
	}

	/**
	 * @param colecao a ser verificado
	 * @return verdadeiro se o valor estiver vazio
	 */
	protected boolean isVazio(Collection<?> colecao) {
		return UtilColecao.isVazio(colecao);
	}

	/**
	 * @param valor a ser verificado
	 * @return verdadeiro se o valor estiver vazio
	 */
	protected boolean isVazio(String valor) {
		return UtilString.isVazio(valor);
	}

	/**
	 * Retorna true se o valor passado for zero ou nulo.
	 * 
	 * @param valor Valor que será validado
	 * @return true se o valor passado for zero ou nulo.
	 */
	@SuppressWarnings("boxing")
	protected boolean isZero(Number valor) {
		return !isReferencia(valor) || (valor.intValue() == 0);
	}

	/**
	 * Limpa as mensagens registradas no faces context.
	 */
	protected void limparListaDeMensagens() {
		for (Iterator iterator = getFacesContext().getMessages(); iterator
				.hasNext();) {
			iterator.next();
			iterator.remove();
		}
	}

	/**
	 * Remove os valores de paginação.
	 */
	protected void removerValoresDePaginacao() {
		PaginacaoConsultaHolder.setLimiteRegistro(null);
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setTotalRegistros(null);
	}

	/**
	 * getFacesContext().responseComplete();
	 */
	protected void responseComplete() {
		getFacesContext().responseComplete();
	}

	/**
	 * Verifica se encontrou algum registro
	 * 
	 * @param colecao Coleção validada.
	 */
	protected void validarNenhumRegistroEncontrado(Collection<?> colecao) {
		if (UtilColecao.isVazio(colecao)) {
			adicionaMensagemError(Mensagem.getMA001(), (Object[]) null);
		}
	}

	/**
	 * @return fábrica de String
	 */
	protected StringFactory getStringFactory() {
		return StringFactory.getInstancia();
	}

	/**
	 * Retorna o usuário logado.
	 * 
	 * @return usuário logado.
	 */
	protected AaUsuario getUsuarioLogado() {
		Principal principal = UtilJSF.getPrincipal();

		br.jus.stj.alp01.acessoareameio.entidade.AaUsuario aaUsuario = 
			getSaapFacade().obterUsuarioPorMatricula(principal.getName());

		AaUsuario usuario = getEntidadeFactory().novoAaUsuario();
		usuario.setNomeUsuario(principal.getName());
		if(isReferencia(aaUsuario) && isReferencia(aaUsuario.getId())) {
			usuario.setId(aaUsuario.getId());
		}
		return usuario;
	}

	/**
	 * Retira o objeto relatorio da sessão do servidor.
	 */
	protected void retirarRelatorioSessao() {
		HttpServletRequest request = (HttpServletRequest) 
				FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(RELATORIO);
	}
}
