package br.jus.stj.saad.view.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("intervaloDataTempValidator")
public class IntervaloDataTempValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return ; 
		}

		UIInput startDateTempComponent = (UIInput) component.getAttributes().get(
				"startDateTempComponent");
		
		if (!startDateTempComponent.isValid()) {
			return; 
		}

		Date startDate = (Date) startDateTempComponent.getValue();

		if (startDate == null) {
			return; 
		}

		Date endDate = (Date) value;

		if (startDate.after(endDate)) {
			startDateTempComponent.setValid(false);
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Intervalo de datas incorreto", null));
		}
	}
	
	

}