package br.jus.stj.saad.view.controller.arquivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.service.TriagemService;
import br.jus.stj.saad.triagem.ArquivoUtils;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class ArquivoController extends GenericController{

	private static final long serialVersionUID = 1L;

	@EJB
	private TriagemService triagemService;
	
	private UploadedFile file;
	
	private Documento documento;
	
	private Map<String, String> nomesArquivos;
	
	@PostConstruct
	private void initialize() {
		nomesArquivos = new HashMap<String, String>();
	}
	
	public void uploadhandleFileUpload(FileUploadEvent event) { 
		FacesMessage msg;
		if(documento != null){
			if(documento.getAnexos() == null){
				documento.setAnexos(new ArrayList<Anexo>());
			}
			
			try {
				UploadedFile uploadedFile = event.getFile();
				
				byte ptext[] = uploadedFile.getFileName().getBytes("ISO_8859_1"); 
				String value = new String(ptext, "UTF-8"); 
				
				String nomeArquivo = ArquivoUtils.retornoAmigavel(value);
				
				Anexo anexo = new Anexo();
				anexo.setInputStream(uploadedFile.getInputstream());
				anexo.setNomeArquivo(nomeArquivo);
				
				if(!nomesArquivos.containsKey(anexo.getNomeArquivo())){
					documento.getAnexos().add(anexo);
					nomesArquivos.put(nomeArquivo, nomeArquivo);
					msg = new FacesMessage("Arquivo(s) anexado(s) com sucesso!", null);  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}else{
					msg = new FacesMessage("O arquivo já foi anexado ao documento!", null);  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}				
				
			} catch (IOException e) {
				msg = new FacesMessage("Não foi possível anexar o arquivo!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}         
    }
	
	public void removerAnexo(Anexo a1){
		for (int i = 0; i < documento.getAnexos().size(); i++) {
			Anexo a2 = documento.getAnexos().get(i);
			
			if(a1.getNomeArquivo().equalsIgnoreCase(a2.getNomeArquivo())){
				documento.getAnexos().remove(i);
				if(nomesArquivos.containsKey(a1.getNomeArquivo())){
					nomesArquivos.remove(a1.getNomeArquivo());
				}
			}
		}	
	}
	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}