package br.jus.stj.saad.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController extends GenericController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7422483376271646582L;

	public String go() {
		
		System.out.println("go...");
		
		return "success";
	}

}
