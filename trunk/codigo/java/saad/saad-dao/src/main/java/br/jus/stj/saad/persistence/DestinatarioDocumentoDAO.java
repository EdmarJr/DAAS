package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.DestinatarioDocumento;

public interface DestinatarioDocumentoDAO extends GenericDAO<DestinatarioDocumento> {
	List<DestinatarioDocumento> listarPorNome(String query);
}
