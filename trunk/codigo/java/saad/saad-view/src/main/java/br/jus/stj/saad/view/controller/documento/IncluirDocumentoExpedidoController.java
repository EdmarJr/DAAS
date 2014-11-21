package br.jus.stj.saad.view.controller.documento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.enumerators.ClassificacaoEnum;

@ViewScoped
@ManagedBean
public class IncluirDocumentoExpedidoController extends DocumentoController {

	private static final long serialVersionUID = 1634619955361122287L;
	
	@PostConstruct
	public void init() {
		super.init();
		getDocumento().setTipoClassificacaoDocumento(ClassificacaoEnum.EXPEDIDO.getValor());
	}
	
	public void salvar(){
		getDocumento().setEnderecamentoRemetente(getEnderecamentoRemetenteUtilizado());
		getDocumento().setEnderecamentoDestinatario(getEnderecamentoDestinatarioUtilizado());

		try{
			getDocumentoService().incluir(getDocumento());
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			adicionarFacesMessage("Documento '"
					+ getDocumento().getIdentificadorComMascara()
					+ "' incluido com sucesso", "Documento '"
					+ getDocumento().getIdentificadorComMascara()
					+ "' incluido com sucesso");
			super.limparTela();
		}
	}
}
