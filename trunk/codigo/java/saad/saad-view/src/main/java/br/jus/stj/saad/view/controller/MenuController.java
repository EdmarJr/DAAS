package br.jus.stj.saad.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController extends GenericController {

	private static final long serialVersionUID = 8717446156372421821L;
	
	private static final Logger LOGGER = Logger.getLogger(MenuController.class);

	public MenuController() {

	}

	
	public String addDocument() {
		System.out.println("Menu addDocument()..........");
		return "success";
	}

	public String searchDocument() {
		System.out.println("Menu consultarDocumento()..........");
		return "success";
	}
	
	public String stepSearchDocument() {
		System.out.println("Menu stepSearchDocument()..........");
		return "success";
	}
	
	public String transferDocument() {
		System.out.println("Menu transferDocument()..........");
		return "success";
	}
	
	public String addDocumentType() {
		System.out.println("Menu addDocumentType()..........");
		return "success";
	}
	
	public String searchDocumentType() {
		System.out.println("Menu searchDocumentType()..........");
		return "success";
	}
	
	public String auditDocument() {
		System.out.println("Menu auditDocument()..........");
		return "success";
	}
	
	public String localSystemPreferences() {
		System.out.println("Menu localSystemPreferences()..........");
		return "success";
	}
	
	public String includeNotification() {
		LOGGER.debug("includeNotification()..........");
		return "pages/aviso/incluirAviso.xhtml";
	}
	
	public String listNotification() {
		LOGGER.debug("listNotification()..........");
		return "success";
	}
	
	public String consultNotification() {
		LOGGER.debug("consultNotification()..........");
		return "success";
	}
	
	public String consultNotificationCourse() {
		LOGGER.debug("consultNotificationCourse()..........");
		return "success";
	}

}
