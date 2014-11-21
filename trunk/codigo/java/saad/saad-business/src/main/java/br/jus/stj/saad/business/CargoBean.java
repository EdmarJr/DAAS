package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.persistence.CargoDAO;

@Stateless
@LocalBean
public class CargoBean extends Bean<Cargo> {

	@Inject
	private CargoDAO dao;

	@Override
	public CargoDAO getDao() {
		return dao;
	}
	
	public List<Cargo> listarPorNome(String query){
		return getDao().listarPorNome(query);
	}
	
	public List<Cargo> cargosPorOrgao(Orgao orgao){
		return getDao().cargosPorOrgao(orgao);
	}

}
