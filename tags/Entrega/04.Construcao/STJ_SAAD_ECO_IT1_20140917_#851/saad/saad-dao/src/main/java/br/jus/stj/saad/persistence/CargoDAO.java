package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.related.Cargo;

public interface CargoDAO extends GenericDAO<Cargo> {
	public List<Cargo> listarPorNome(String nome);
}
