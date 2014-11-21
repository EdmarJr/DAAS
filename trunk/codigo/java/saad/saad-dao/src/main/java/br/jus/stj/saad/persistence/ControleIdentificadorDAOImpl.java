package br.jus.stj.saad.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.ControleIdentificador;
import br.jus.stj.saad.entity.related.Local;

public class ControleIdentificadorDAOImpl extends GenericDAOMssqlImpl<ControleIdentificador> implements ControleIdentificadorDAO {

	@Override
	public ControleIdentificador obterPorId(Local local, Long ano) {
		Criteria criteria = obterCriteria(ControleIdentificador.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.add(Restrictions.eq("ano", ano));
		return (ControleIdentificador) criteria.uniqueResult();
	}
	
}
