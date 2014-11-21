package br.jus.stj.saad.view.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class InformacoesSistema {

	private Manifest manifest;
	private String version;
	
	@PostConstruct
	public void init() {

		try {

			InputStream is = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/META-INF/MANIFEST.MF");
			manifest = new Manifest();
			manifest.read(is);
			
			iniciarlizarAtributos();

		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}
	
	private void iniciarlizarAtributos() {
		this.version = manifest.getMainAttributes().getValue("version");
	}
	
	public Manifest getManifest() {
		return this.manifest;
	}
	
	public String getVersion() {
		return this.version;
	}
	
}
