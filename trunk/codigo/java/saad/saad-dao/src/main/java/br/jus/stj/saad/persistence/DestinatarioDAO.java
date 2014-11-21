package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Orgao;

public interface DestinatarioDAO extends GenericDAO<Destinatario> {
	public List<Destinatario> destinatariosPorOrgao(Orgao orgao);
	public List<Destinatario> destinatariosPorCargoOrgao(Cargo cargo, Orgao orgao);

}
