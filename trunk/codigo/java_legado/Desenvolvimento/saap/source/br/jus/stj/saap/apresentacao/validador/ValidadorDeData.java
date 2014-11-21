/*
 * ValidadorDeData.java
 * 
 * Data de criação: 12/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.validador;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.jsf.util.MensagemUtil;

/**
 * Responsável pela validação dos campos data.
 * 
 * @author Politec Informática S/A
 */
public class ValidadorDeData implements Validator {

	/**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@SuppressWarnings("unused")
	public void validate( FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String id = component.getAttributes().get("id").toString();
		UIInput campoData = (UIInput) component.findComponent(id);
		Integer data = UtilData.getAno((Date) value);
		if (data < 1900 || data > 2500) {
			String resultado = MensagemUtil.getMensagem("MA007", component
					.getAttributes().get("label"));
			campoData.setValid(false);
			campoData.setSubmittedValue(null);
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, component.getAttributes()
							.get("label")
							+ ": " + resultado, resultado);
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		}
	}
}
