package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.related.Orgao;

public interface OrgaoDAO extends GenericDAO<Orgao> {
	public List<Orgao> listarPorNome(String nome);
}
