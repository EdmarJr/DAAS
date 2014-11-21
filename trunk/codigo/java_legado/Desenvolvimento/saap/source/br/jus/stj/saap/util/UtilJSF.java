/*
 * UtilJSF.java
 *
 * Data de criação: 05/11/2008
 *
 * Desenvolvido por Politec Ltda.
 *
 */
package br.jus.stj.saap.util;

import java.io.Serializable;
import java.security.Principal;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.jus.stj.alp01.comum.string.UtilString;

/**
 * Classe utilitária para manipulação dos objetos mantidos pelo framework.
 * 
 * @author adrianop
 */
public final class UtilJSF {

	/**
	 * Construtor.
	 */
	private UtilJSF() {
		// Construtor.
	}

	/**
	 * Retorna um objeto do contexto do JSF.
	 * 
	 * @param <T> Tipo do objeto de retorno.
	 * @param id Identificador do objeto. Ex: #{BuildView}
	 * @param tipo Tipo do objeto que será retornado. Ex: BuildView.class
	 * @return objeto do contexto JSF.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T getObjetoDoContexto(String id,
			Class<?> tipo) {
		// T resultado = null;
		id = converterID(id);
		ValueExpression ve = novoValueExpression(id, tipo);
		return (T) ve.getValue(getELContext());
	}

	/**
	 * @return FacesContext.
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Retorna Principal.
	 * 
	 * @return Principal
	 */
	public static Principal getPrincipal() {
		return getRequest().getUserPrincipal();
	}

	/**
	 * Retorna o ValueExpression para recuperação de um objeto disponibilizado
	 * no contexto do JSF.
	 * 
	 * @param id ID do objeto disponibilizado no contexto do JSF
	 * @param tipo Tipo do objeto que será retornado.
	 * @return ValueExpression
	 */
	private static ValueExpression novoValueExpression(String id, Class<?> tipo) {
		ELContext el = getELContext();
		return getExpressionFactory().createValueExpression(el, id, tipo);
	}

	/**
	 * @return ELContext
	 */
	private static ELContext getELContext() {
		return getFacesContext().getELContext();
	}

	/**
	 * @return ExpressionFactory
	 */
	private static ExpressionFactory getExpressionFactory() {
		return getApplication().getExpressionFactory();
	}

	/**
	 * @return Application
	 */
	private static Application getApplication() {
		return getFacesContext().getApplication();
	}

	/**
	 * Converte o ID solicitado para um identificador válido para consulta no
	 * contexto JSF. Ou seja, o ID deverá iniciar com "#{" e finalizar com "}".
	 * 
	 * @param id Identificador
	 * @return id com prefixo "#{" e sufixo "}".
	 */
	private static String converterID(String id) {
		if (!UtilString.isVazio(id) && !id.startsWith("#")) {
			id = "#{" + id + "}";
		}
		return id;
	}

	/**
	 * @return external context
	 */
	private static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * @return request
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}
}
