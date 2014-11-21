package br.jus.stj.saad.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.persistence.DestinatarioOcupacaoDAO;

/**
 * Session Bean implementation class Audit
 */
@Stateless
public class DestinatarioOcupacaoBean extends Bean<DestinatarioOcupacao> {

	@Inject
	private DestinatarioOcupacaoDAO dao;
	
	@Override
	public DestinatarioOcupacaoDAO getDao() {
		return dao;
	}
	
	public DestinatarioOcupacao buscar(Orgao orgao, Cargo cargo, Destinatario destinatario){
		return getDao().buscar(orgao, cargo, destinatario);
	}

}
