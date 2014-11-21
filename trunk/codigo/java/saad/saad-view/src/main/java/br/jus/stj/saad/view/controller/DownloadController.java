package br.jus.stj.saad.view.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import br.jus.stj.saad.entity.local.Anexo;

@ManagedBean
@RequestScoped
public class DownloadController extends GenericController {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultStreamedContent download;
	
	
	public DefaultStreamedContent getDownload() {
		return download;
	}

	public void setDownload(DefaultStreamedContent download) {
		this.download = download;
	}

	public void prepDownload(Anexo anexo) throws Exception {
		
		File file = new File(anexo.caminhoArquivo());
		
		if(!file.exists()) {
			
			adicionarFacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
					"Arquivo não encontrado no diretório.");
			return;
			
		}
		
	    InputStream input = new FileInputStream(file);
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	
	}
	
	public String goToStepSearch() {
		
		return "/pages/document/stepSearch?faces-redirect=true";
		
	}
	
	
}
