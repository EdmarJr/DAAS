package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.auditoria.AuditoriaDocumento;

public interface AuditoriaDocumentoDAO  extends GenericDAO<AuditoriaDocumento>{

	
	public List<AuditoriaDocumento> consultaHistoricoDocumento(
			Documento documento);
}
