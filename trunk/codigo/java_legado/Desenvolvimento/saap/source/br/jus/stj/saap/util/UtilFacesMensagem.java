/*
 * UtilFacesMensagem.java
 *
 * Data de criação: Nov 10, 2008
 *
 * Desenvolvido por Politec Informática S/A.
 *
 */
package br.jus.stj.saap.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * @author Politec Informática
 */
public final class UtilFacesMensagem {

	/**
	 * Construtor
	 */
	private UtilFacesMensagem() {
		// Classe utilitária
	}

	/**
	 * Adiciona a mensagem no FacesContext.
	 * 
	 * @param chaveMensagem chave da mensagem
	 * @param params parametros
	 * @param severity nivel de severidade
	 * @return Mensagem adicionada.
	 */
	private static FacesMessage adicionaMensagem(String chaveMensagem,
			Severity severity, Object... params) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem, severity,
				params);
		FacesContext.getCurrentInstance().addMessage("", facesMessage);
		return facesMessage;
	}

	/**
	 * Adiciona a mensagem no FacesContext.
	 * 
	 * @param chaveMensagem chave da mensagem
	 * @param params parametros
	 * @param severity nivel de severidade
	 * @return Mensagem adicionada.
	 */
	public static FacesMessage getFacesMessage(String chaveMensagem,
			Severity severity, Object... params) {
		FacesMessage facesMessage = new FacesMessage(severity, PropertiesUtil
				.getMessageResource(chaveMensagem, params), PropertiesUtil
				.getMessageResource(chaveMensagem, params));
		return facesMessage;
	}

	/**
	 * Adicona mensagem de ERROR.
	 * 
	 * @param chaveMensagem
	 */
	/**
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemError(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_ERROR, param);
	}

	/**
	 * Adicona mensagem de ERROR na popUp.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemErrorPopup(String chaveMensagem,
			Object... param) {

		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_ERROR, param);
	}

	/**
	 * Adicona mensagem de FATAL.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemFatal(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_FATAL, param);
	}

	/**
	 * Adicona mensagem de FATAL na popUp.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemFatalPopup(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_FATAL, param);
	}

	/**
	 * Adicona mensagem de INFO.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemInfo(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_INFO, param);
	}

	/**
	 * Adicona mensagem de INFO na popUp.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemInfoPopup(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_INFO, param);
	}

	/**
	 * Adicona mensagem de WARN.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemWarn(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_WARN, param);
	}

	/**
	 * Adicona mensagem de WARN na popUp.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void adicionaMensagemWarnPopup(String chaveMensagem,
			Object... param) {
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_WARN, param);
	}

	/**
	 * Lanca um ValidatorException com a mensagem de erro.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorException(String chaveMensagem,
			Object... param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);

		throw new ValidatorException(facesMessage);
	}

	/**
	 * Lanca um ValidatorException com a mensagem de erro.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorExceptionPopup(String chaveMensagem,
			Object... param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);

		throw new ValidatorException(facesMessage);
	}

	/**
	 * Lanca um ValidatorException com a mensagem de erro.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorException(String chaveMensagem,
			String param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);

		throw new ValidatorException(facesMessage);
	}

	/**
	 * Lanca um ValidatorException com a mensagem de erro.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorExceptionPopup(String chaveMensagem,
			String param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);

		throw new ValidatorException(facesMessage);
	}

	/**
	 * Lanca um ValidatorException com a mensagem de erro.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorExceptionComMensagem(
			String chaveMensagem, Object... param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_ERROR, param);
		throw new ValidatorException(facesMessage);
	}

	/**
	 * Adicona mensagem de ERROR na popUp.
	 * 
	 * @param chaveMensagem chave da mensagem de erro
	 * @param param parametro da mensagem
	 */
	public static void lancarValidatorExceptionComMensagemPopup(
			String chaveMensagem, Object... param) {
		FacesMessage facesMessage = getFacesMessage(chaveMensagem,
				FacesMessage.SEVERITY_ERROR, param);
		adicionaMensagem(chaveMensagem, FacesMessage.SEVERITY_ERROR, param);
		throw new ValidatorException(facesMessage);
	}
}
