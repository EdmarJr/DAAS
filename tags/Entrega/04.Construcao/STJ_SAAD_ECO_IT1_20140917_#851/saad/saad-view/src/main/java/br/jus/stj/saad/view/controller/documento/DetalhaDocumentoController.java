package br.jus.stj.saad.view.controller.documento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class DetalhaDocumentoController extends GenericController {

	private static final long serialVersionUID = 1L;
	private Documento documentoDetalhar;

    @PostConstruct
    private void init() {
    	
    	documentoDetalhar = (Documento)  obterObjetoRequestMap("documentoSelecionado");
	
    }

    public Documento getDocumentoDetalhar() {
        return documentoDetalhar;
    }

    public void setDocumentoDetalhar(Documento documentoDetalhar) {
        this.documentoDetalhar = documentoDetalhar;
    }
    
	public SituacaoAvisoEnum getEnumAviso() {

		return SituacaoAvisoEnum.getEnumPorChave(documentoDetalhar
				.getSituacao());

	}

    
	public String getNomeTipoEnderecamento(Character character) {

		for (TipoEnderecamentoAvisoEnum tipoEnderecamentoAvisoEnum : TipoEnderecamentoAvisoEnum
				.values()) {

			if (character.equals(tipoEnderecamentoAvisoEnum.getChave().equals(
					character))) {

				return tipoEnderecamentoAvisoEnum.getDescricao();

			}

		}

		return "NÃO DEFINIDO";

	}
	
    
}
