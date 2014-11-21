package br.jus.stj.saad.view.controller.documento;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;

import br.jus.stj.saad.business.AnexoDocumentBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class ConsultaDocumentoAnexoController extends GenericController {

	private static final long serialVersionUID = 6994881327314526741L;
	
//    @Inject
//    private LoginBean loginBean;
    
    @EJB
    private TipoDocumentoBean tipoDocumentoBean;
    
    @EJB
    private AnexoDocumentBean anexoDocumentoBean;
    
    private List<TipoDocumento> tipoDocumentoList;
    
    private TipoDocumento tipoDocumentoSelecionado;
   
    private List<Anexo> listaAnexosDocumentoPesquisa;
    
    private List<AndamentoDocumento> andamentosDocumentoConsulta;
    
    private DefaultStreamedContent download;

    @PostConstruct
    private void initialize() {
    	
    	tipoDocumentoList = tipoDocumentoBean.listarTodosTiposDocumentos();
    	
    }
    
	public void consultarAnexos() {

        if (tipoDocumentoSelecionado != null) {

			listaAnexosDocumentoPesquisa = anexoDocumentoBean
					.obterDocumentosAnexo(tipoDocumentoSelecionado);

		} else {

			adicionarFacesMessage("Preencha um tipo de documento.", "");

		}
		
		if(listaAnexosDocumentoPesquisa == null) {
			
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Nenhum registro encontrado.");
			
		}
		

	}
	
	public boolean isPossuiResultadoPesquisa() {

		return listaAnexosDocumentoPesquisa != null
				&& !listaAnexosDocumentoPesquisa.isEmpty();

	}
	
	public String obterFormatoIdentificador(Documento documento) {

		String sigla = documento.getTipo().getSigla();
		String identificacaoDoc = documento.getNumeroControleIdentificador();
		Date dataInclusaoDoc = documento.getDataInclusao();

		return IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla,
				identificacaoDoc, dataInclusaoDoc);

	}
	
	public static SituacaoDocumentoEnum obterSituacaoDocumentoEnumPorCharacter(
			Character character) {
		
		SituacaoDocumentoEnum[] situacoes = SituacaoDocumentoEnum.values();
		
		for (SituacaoDocumentoEnum situacao : situacoes) {
			
			if (situacao.getValor().equals(character)) {
				
				return situacao;
				
			}
			
		}
		
		return null;
		
	}
	

	public void setDownload(DefaultStreamedContent download) {
	    this.download = download;
	}

	public DefaultStreamedContent getDownload() throws Exception {

		if (download != null) {

			try {

				download.getStream().available();

			} catch (Exception e) {

				adicionarFacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
						"Arquivo não encontrado no diretório.");
				return null;

			}

		} 

		return download;

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
	
	public DefaultStreamedContent visualizarArquivo() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            
        	// So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
            
        } else {
        	
        	try {
        		
        		String caminhoArquivo = "d:\\pdfTeste.pdf";
        		
        		Path path = Paths.get(caminhoArquivo);
        		byte[] data = Files.readAllBytes(path);
        		
        		return new DefaultStreamedContent(new ByteArrayInputStream(data),
        				"application/pdf", caminhoArquivo);
        		
        	} catch (Exception e) {
        		
        		e.printStackTrace();
        		
        	}

        }
        
        return new DefaultStreamedContent();
		
	}
    
	public List<TipoDocumento> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

	public TipoDocumento getTipoDocumentoSelecionado() {
		return tipoDocumentoSelecionado;
	}

	public void setTipoDocumentoSelecionado(TipoDocumento tipoDocumento) {
		this.tipoDocumentoSelecionado = tipoDocumento;
	}
	
	public List<Anexo> getListaAnexosDocumentoPesquisa() {
		return listaAnexosDocumentoPesquisa;
	}

	public void setListaAnexosDocumentoPesquisa(
			List<Anexo> listaAnexosDocumentoPesquisa) {
		this.listaAnexosDocumentoPesquisa = listaAnexosDocumentoPesquisa;
	}

	public List<AndamentoDocumento> getAndamentosDocumentoConsulta() {
		return andamentosDocumentoConsulta;
	}

	public void setAndamentosDocumentoConsulta(List<AndamentoDocumento> andamentosDocumentoConsulta) {
		this.andamentosDocumentoConsulta = andamentosDocumentoConsulta;
	}
	
}
