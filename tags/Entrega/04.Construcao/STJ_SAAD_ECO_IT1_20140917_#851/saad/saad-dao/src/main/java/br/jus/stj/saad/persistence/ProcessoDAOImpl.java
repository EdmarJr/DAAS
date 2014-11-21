package br.jus.stj.saad.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.Processo;

public class ProcessoDAOImpl extends GenericDAOMssqlImpl<Processo> implements ProcessoDAO {

	@Override
	public Processo buscarPorNumeroRegistro(String numeroRegistro) {
		Criteria criteria = obterCriteria(Processo.class);
		criteria.add(Restrictions.eq("numeroRegistro", numeroRegistro));
		return (Processo) criteria.uniqueResult();
	}

}
