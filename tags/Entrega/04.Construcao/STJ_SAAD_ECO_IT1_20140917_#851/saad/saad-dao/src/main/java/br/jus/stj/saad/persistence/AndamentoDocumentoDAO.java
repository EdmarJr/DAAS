package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;

public interface AndamentoDocumentoDAO extends GenericDAO<AndamentoDocumento> {

	public List<Documento> obterDocumentosComAndamentos(String sigla,
			String identificacaoDocumentoSTJ, String ano, Local local);

	public List<Documento> obterDocumentosComAndamentos(
			TipoDocumento tipoDocumento, Local local);

}
