package br.jus.stj.saad.entity.listener;

import java.text.SimpleDateFormat;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.identificador.IdentificadorUtils;

public class IdentificadorDocumentoListener {

	@PrePersist
	@PreUpdate
	public void ajustarIndentificador(Documento documento) {
		String identificacaoDocumentoSTJ = documento
				.getIdentificadorComMascara();
		int indexOf = identificacaoDocumentoSTJ.indexOf("/");
		int inicio = indexOf - 6;
		documento.setNumeroControleIdentificador(identificacaoDocumentoSTJ
				.substring(inicio, indexOf));
	}

	@PostLoad
	public void ajustarIdentificadorCarregado(Documento documento) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String ano = documento.getDataInclusao() != null ? simpleDateFormat.format(documento
				.getDataInclusao()) : "2014";
		documento.setIdentificadorComMascara(IdentificadorUtils
				.obterIdentificadorComMascaraPorDocumento(documento.getTipo()
						.getSigla(), documento.getNumeroControleIdentificador(),
						ano));
	}

}
