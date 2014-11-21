package br.jus.stj.saad.view.controller.session;


import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.jus.stj.saad.view.controller.GenericController;

@SessionScoped
@Named
public class SessionUtilsController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void invalidarSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		externalContext.invalidateSession();
		try {
			externalContext.redirect(obterContextPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
