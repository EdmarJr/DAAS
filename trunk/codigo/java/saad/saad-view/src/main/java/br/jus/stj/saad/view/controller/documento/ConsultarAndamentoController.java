package br.jus.stj.saad.view.controller.documento;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.util.ConstantesSaad;

@ManagedBean
@ViewScoped
public class ConsultarAndamentoController extends ConsultarDocumentoController {

	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
		super.init();
	}
	
	@Override
	public void consultar() {
		setConsultado(true);
		setListDocumentoResultado(new ArrayList<Documento>());
		getDocumento().setEnderecamentoRemetente(getEnderecamentoRemetenteUtilizado());
		getDocumento().setEnderecamentoDestinatario(getEnderecamentoDestinatarioUtilizado());
		
		if (StringUtils.validaStringComRegex(getIdentificadorDocumento(),
				ConstantesSaad.regexIdentificadorDocumento)) {
			Documento documento = getDocumentoBean().buscarDocumento(null,
					getIdentificadorDocumento(),
					getLoginBean().getUnidadeUsuario());
			if (documento != null) {
				getListDocumentoResultado().add(
						getDocumentoBean().inicializar(documento,
								"andamentosDocumento"));
			}
		} else {
			if(!StringUtils.isEmpty(getFiltroConsultarDocumentoVO().getDescricaoAndamento())){
				getFiltroConsultarDocumentoVO().setAndamento(true);
			}else{
				getFiltroConsultarDocumentoVO().setAndamento(false);
			}
			setListDocumentoResultado(getDocumentoBean().inicializarLista(
					getDocumentoBean()
							.buscarDocumentoPorFiltroConsultarDocumentoVO(
									getFiltroConsultarDocumentoVO()),
					"andamentosDocumento"));
		}
	}
}
