package br.jus.stj.saad.persistence;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.LocalUsuario;
import br.jus.stj.saad.entity.related.Usuario;

public class UsuarioDAO extends GenericDAOMssqlImpl<Usuario> {

	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		Query query = manager.createQuery("from Usuario");
		return query.getResultList();
	}

	public Usuario getByName(String nome) {
		StringBuilder sb = new StringBuilder()
				.append("	from											")
				.append("		Usuario usuario								")
				// .append("		join usuario.locaisUsuarios locaisUsuario	")
				.append("	left join fetch									")
				.append("		usuario.locaisUsuarios						")
				.append("	where											")
				.append("		usuario.nome like :nome						");
		Query query = manager.createQuery(sb.toString());
		query.setParameter("nome", "%" + nome + "%");
		return (Usuario) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuariosPorNome(String nome) {
		Criteria criteria = obterCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public Usuario buscarUsuarioPorNome(String nome) {
		Criteria criteria = obterCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		
		List<Usuario> list = criteria.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuariosPorNomeUnidade(String nome,
			List<Local> locais) {
		Criteria criteria = obterCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		Criteria criteriaLocaisUsuario = criteria
				.createCriteria("locaisUsuarios");
		criteriaLocaisUsuario.add(Restrictions.in("local", locais));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuariosPorNomeUnidade(String nome,
			Local local) {
		
		Query query = manager.createQuery("select usuario from Usuario usuario"
				+ " join fetch usuario.locaisUsuarios locaisUser"
				+ " where usuario.nome like :likeNome"
				+ " and locaisUser.indUsuarioBloqueado = 'N'"
				+ " and locaisUser.local = :local", Usuario.class);
		
		query.setParameter("likeNome", "%"+nome.toUpperCase()+"%");
		query.setParameter("local", local);
		
		return query.getResultList();

	}

	public Usuario obterPorId(Long idUsuario) {
		Criteria criteria = obterCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		return (Usuario) criteria.uniqueResult();
	}

	public Usuario obterPorLogin(String login) {
		Criteria criteria = obterCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		List list = criteria.list();
		return list.size() > 0 ? (Usuario) list.get(0) : null;
	}

	public List<Usuario> obterPorLocalUsuario(List<LocalUsuario> locais) {
		Local[] locaisTemp = new Local[locais.size()];
		int i = 0;
		for (LocalUsuario local : locais) {
			locaisTemp[i] = local.getLocal();
			i++;
		}
		return obterPorLocal(locaisTemp);
	}

	public List<Usuario> obterPorLocal(Local... locais) {
		return obterPorLocal(Arrays.asList(locais));
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obterPorLocal(List<Local> locais) {
		Criteria criteria = obterCriteria(Usuario.class);
		Criteria criteriaLocaisUsuario = criteria
				.createCriteria("locaisUsuarios");
		criteriaLocaisUsuario.add(Restrictions.in("local", locais));
		return criteria.list();
	}

}
