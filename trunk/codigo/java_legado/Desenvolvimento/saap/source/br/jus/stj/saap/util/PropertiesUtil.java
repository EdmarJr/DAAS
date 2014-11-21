/*
 * PropertiesUtil.java
 *
 * Data de criação: 10/09/2008
 *
 * Desenvolvido por Politec Informática S/A.
 *
 */
package br.jus.stj.saap.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * @author Politec Informática
 */
public class PropertiesUtil {

	/**
	 * Construtor padrao.
	 */
	private PropertiesUtil() {
		// Classe utilirária
	}

	/**
	 * @param defaultObject objetodefault
	 * @return ClassLoader
	 */
	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	/**
	 * @param bundleName bundleName
	 * @param key chave
	 * @param params parametros
	 * @param locale locale
	 * @return String mensagem
	 */
	protected static String getMessageResourceString(String bundleName,
			String key, Object[] params, Locale locale) {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale,
				getCurrentClassLoader(params));
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " not found ??";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}

	/**
	 * Retorna uma mensagem a partir da chave existente no arquivo de mensagens
	 * 
	 * @param key chave
	 * @param params parametro
	 * @return String mensagem
	 */
	public static String getMessageResource(String key, Object... params) {
		String msg = null;
		FacesContext context = FacesContext.getCurrentInstance();

		msg = PropertiesUtil.getMessageResourceString(context.getApplication()
				.getMessageBundle(), key, params, context.getViewRoot()
				.getLocale());
		return msg;
	}

	/**
	 * @param chave chave no boundle
	 * @return mensagem
	 */
	public static String getMensagem(String chave) {
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("recursos.mensagem");
		return resourceBundle.getString(chave);
	}

}
