package br.jus.stj.saad.view.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.commons.view.enums.SimNaoEnum;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Evento;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.service.IdentificadorDocumentoService;

@ManagedBean
@ViewScoped
public class DocumentoControllerBehavior extends GenericController {

	@EJB
	private IdentificadorDocumentoService identificadorDocumentoService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void aoMudarTipoDocumento(Documento documento) {
		if (documento.getTipo() == null) {
			documento.setIdentificacaoDocumentoSTJ("");
		} else {
			documento
					.setIdentificadorComMascara(obterNumeroDoDocumento(documento));
		}
	}

	public void aoMudarRelacionamentoAEvento(Documento documento) {
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

	public void aoMudarOrigemDoDocumento(Documento documento) {
		if (documento.getTipoOrigem().equals(
				TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			documento.setEnderecamento(TipoOrigemDocumentoEnum.INTERNO
					.getEnderecamento());
		} else {
			documento.setEnderecamento(TipoOrigemDocumentoEnum.EXTERNO
					.getEnderecamento());
		}
	}

	private String obterNumeroDoDocumento(Documento documento) {
		return identificadorDocumentoService
				.gerarIdentificadorDocumentoPorTipoDocumento(documento
						.getTipo());
	}

	public Boolean seEntradaDocumentoIgualInterno(Documento documento) {
		if (documento.getTipoOrigem().equals(
				TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
