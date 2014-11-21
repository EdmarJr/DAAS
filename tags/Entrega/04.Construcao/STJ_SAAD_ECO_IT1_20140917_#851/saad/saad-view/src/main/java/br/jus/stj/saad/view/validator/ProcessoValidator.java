package br.jus.stj.saad.view.validator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.jus.stj.saad.business.ProcessoBean;
import br.jus.stj.saad.entity.local.Processo;

@ManagedBean
public class ProcessoValidator implements Validator {
	@EJB
	private ProcessoBean processoBean;
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return ; 
		}
		
		String numeroRegistro = (String) value;

		Processo processo = processoBean.buscarPorNumeroRegistro(numeroRegistro);
		
		if(processo == null){
			throw new ValidatorException(new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				"Processo não encontrado.", null));
		}
	}
	
	

}