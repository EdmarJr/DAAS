package br.jus.stj.saad.view.controller.documento;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;

@ManagedBean
@ViewScoped
public class InclusaoDocumentoExpedidoController extends DocumentoControllerOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		super.inicializar();
		setDocument(new Documento());
		documentoBean.inicializarNovoDocumento(getDocument());
		getDocument().setTipoClassificacaoDocumento(
				ClassificacaoEnum.EXPEDIDO.getValor());
	}

	public void comandoSalvar() {
		documentoBean.incluir(getDocument());
		limpar();
		adicionarFacesMessage("Dados inseridos com sucesso.", "");
	}

}
