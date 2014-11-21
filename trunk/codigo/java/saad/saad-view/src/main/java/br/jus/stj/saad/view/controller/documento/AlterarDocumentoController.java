package br.jus.stj.saad.view.controller.documento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.entity.local.Enderecamento;

@ViewScoped
@ManagedBean
public class AlterarDocumentoController extends DocumentoController {

	private static final long serialVersionUID = 1634619955361122287L;
	
	@PostConstruct
	public void init() {
		super.init();
		setAlterar(true);
	}
	
	public void salvar(){
		
		Enderecamento remetente = getEnderecamentoRemetenteUtilizado();
		Enderecamento destinatario = getEnderecamentoDestinatarioUtilizado();
		
		if(remetente != null){
			getDocumento().setEnderecamentoRemetente(remetente);
		}
		
		if(destinatario != null){
			getDocumento().setEnderecamentoDestinatario(destinatario);
		}

		try{
			getDocumentoBean().alterar(getDocumento());
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			adicionarFacesMessage("Documento alterado com sucesso!", "");
		}
	}
}
