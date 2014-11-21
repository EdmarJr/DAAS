package br.jus.stj.saad.view.controller;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "languageController")
@RequestScoped
public class LanguageController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -464197314916250603L;

	public void setPortuguese() {

		System.out.println("setPortuguese...");

		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(new Locale("pt", "BR"));
	}

	public void setEnglish() {

		System.out.println("setEnglish...");

		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(new Locale("en", "US"));
	}

}
