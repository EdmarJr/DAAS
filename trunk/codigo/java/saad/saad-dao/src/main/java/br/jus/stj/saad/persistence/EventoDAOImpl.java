package br.jus.stj.saad.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Evento;

public class EventoDAOImpl extends GenericDAOMssqlImpl<Evento> implements EventoDAO {

	@Override
	public Evento obterPorDocumento(Documento documento) {
		Criteria criteria = obterCriteria(Evento.class);
		criteria.add(Restrictions.eq("documento", documento));
		return (Evento) criteria.uniqueResult();
	}

}
