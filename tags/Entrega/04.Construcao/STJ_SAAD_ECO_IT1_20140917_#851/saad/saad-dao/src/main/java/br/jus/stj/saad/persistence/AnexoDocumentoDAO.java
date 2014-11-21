package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.TipoDocumento;

public interface AnexoDocumentoDAO extends GenericDAO<Anexo>{

	public List<Anexo> obterDocumentosAnexo(TipoDocumento tipoDocumento);
	
}
