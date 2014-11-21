package br.jus.stj.saad.persistence;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Local;

public class LocalDAO extends GenericDAOMssqlImpl<Local> {

	public Local getByName(String nome) {
		Query query = manager.createQuery("from Local where nome = :nome");
		query.setParameter("nome", nome);
		return (Local)query.getSingleResult();
	}
	
	public Local obterPrimeiro() {
		Criteria criteria = obterCriteria(Local.class);
		criteria.setMaxResults(1);
		return (Local) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Local> obterLocaisAtivosDisponiveisNoSTJ(String... camposEager) {
		Criteria criteria = obterCriteria(Local.class);
		criteria.add(Restrictions.eq("ativo",new Character('S')));
		criteria.add(Restrictions.eq("escaninho",new Character('N')));
		criteria.add(Restrictions.isNull("localSuperior"));
		criteria.add(Restrictions.eq("organograma",new Character('S')));
		criteria.addOrder(Order.asc("nome"));
		if(camposEager.length > 0) {
			for(String campo : camposEager) {
				criteria.setFetchMode(campo, FetchMode.JOIN);
			}
		}
		return criteria.list();
	}
	
}
