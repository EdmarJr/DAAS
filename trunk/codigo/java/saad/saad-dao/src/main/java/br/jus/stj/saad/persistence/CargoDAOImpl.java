package br.jus.stj.saad.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Orgao;

public class CargoDAOImpl extends GenericDAOMssqlImpl<Cargo> implements CargoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Cargo> listarPorNome(String nome){
		Criteria criteria = obterCriteria(Cargo.class);
		criteria.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		List<Cargo> list = criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> cargosPorOrgao(Orgao orgao) {
		if(orgao != null){
			
			String select = " select c from Cargo c "
					+ " left outer join fetch c.destinatariosOcupacao do where"
					+ " do.orgao = :orgao";

			Query query = manager.createQuery(select, Cargo.class);
			query.setParameter("orgao",	orgao);
			
			return query.getResultList(); 
		}
		
		return null;
	}	
}
