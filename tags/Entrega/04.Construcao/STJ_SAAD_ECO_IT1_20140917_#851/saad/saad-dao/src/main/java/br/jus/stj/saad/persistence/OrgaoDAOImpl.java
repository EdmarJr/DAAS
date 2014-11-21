package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Orgao;

public class OrgaoDAOImpl extends GenericDAOMssqlImpl<Orgao> implements OrgaoDAO {

	@SuppressWarnings("unchecked")
	public List<Orgao> listarPorNome(String nome){
		Criteria criteria = obterCriteria(Orgao.class);
		criteria.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		List<Orgao> list = criteria.list();
		return list;
	}
}
