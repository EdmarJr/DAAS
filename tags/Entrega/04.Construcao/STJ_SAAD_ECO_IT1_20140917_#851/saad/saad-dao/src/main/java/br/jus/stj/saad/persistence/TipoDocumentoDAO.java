package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.TipoDocumento;

public interface TipoDocumentoDAO extends GenericDAO<TipoDocumento> {

	public List<TipoDocumento> list();

	public List<Object[]> listByFilter(TipoDocumento documentType);

	public TipoDocumento find(TipoDocumento documentType);

}
