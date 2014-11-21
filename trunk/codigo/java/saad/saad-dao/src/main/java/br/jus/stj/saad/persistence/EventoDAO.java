package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Evento;

public interface EventoDAO extends GenericDAO<Evento> {
	public Evento obterPorDocumento(Documento documento);
}
