package br.jus.stj.saad.view.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.jus.stj.commons.view.enums.SimNaoEnum;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Evento;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.service.IdentificadorDocumentoService;

@ManagedBean
@ViewScoped
public class DocumentoControllerBehavior extends GenericController {

	@EJB
	private IdentificadorDocumentoService identificadorDocumentoService;
	
	@Inject
	private LoginBean loginBean;
	
	private Documento documento;
	
	private static final long serialVersionUID = 1L;

	public void aoMudarTipoDocumento() {
		if (documento.getTipo() == null) {
			documento.setNumeroControleIdentificador("");
		} else {
			documento.setIdentificadorComMascara(obterNumeroDoDocumento(documento));
		}
	}

	public void aoMudarRelacionamentoAEvento() {
		Boolean relacionadoAEvento = documento.getRelacionadoAEvento();
		documento.setSituacaoDocumentoEvento(SimNaoEnum.obterSimNaoEnum(
				relacionadoAEvento).getValor());
		if (relacionadoAEvento) {
			Evento evento = new Evento();
			evento.setDocumento(documento);
			documento.setEvento(evento);
		} else {
			documento.setEvento(null);
		}
	}

	public void aoMudarOrigemDoDocumento() {
		if (documento.getTipoOrigem().equals(
				TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			documento.setEnderecamentoRemetente(TipoOrigemDocumentoEnum.INTERNO
					.getEnderecamento());
		} else {
			documento.setEnderecamentoRemetente(TipoOrigemDocumentoEnum.EXTERNO
					.getEnderecamento());
		}
	}

	private String obterNumeroDoDocumento(Documento documento) {
		return identificadorDocumentoService.gerarIdentificadorDocumentoPorTipoDocumento(documento.getTipo(), loginBean.getUnidadeUsuario());
	}

	public Boolean seEntradaDocumentoIgualInterno() {
		if (documento.getTipoOrigem().equals(
				TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
