package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.local.ControleIdentificador;
import br.jus.stj.saad.entity.related.Local;

public interface ControleIdentificadorDAO extends GenericDAO<ControleIdentificador> {

	public ControleIdentificador obterPorId(Local local, Long ano);
}
