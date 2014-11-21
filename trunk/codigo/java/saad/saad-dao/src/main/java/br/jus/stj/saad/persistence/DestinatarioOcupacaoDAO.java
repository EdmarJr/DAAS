package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Orgao;

public interface DestinatarioOcupacaoDAO extends GenericDAO<DestinatarioOcupacao> {
	DestinatarioOcupacao buscar(Orgao orgao, Cargo cargo, Destinatario destinatario);
}
