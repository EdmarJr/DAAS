package br.jus.stj.saad.view.controller.documento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class VisualizarDocumentoController extends DocumentoController {

	private static final long serialVersionUID = 1634619955361122287L;
	
	@PostConstruct
	public void init() {
		super.init();
		setAlterar(false);
	}
}
