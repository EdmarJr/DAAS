package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Cargo;

public class CargoDAOImpl extends GenericDAOMssqlImpl<Cargo> implements CargoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Cargo> listarPorNome(String nome){
		Criteria criteria = obterCriteria(Cargo.class);
		criteria.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		List<Cargo> list = criteria.list();
		return list;
	}	
}
