package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.local.Processo;

public interface ProcessoDAO extends GenericDAO<Processo> {
	public Processo buscarPorNumeroRegistro(String numeroRegistro);
}
