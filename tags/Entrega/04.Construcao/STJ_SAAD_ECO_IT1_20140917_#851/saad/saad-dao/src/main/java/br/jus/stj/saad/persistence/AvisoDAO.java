package br.jus.stj.saad.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.vo.FiltroConsultaAvisoVO;

public class AvisoDAO extends GenericDAOMssqlImpl<Aviso> {
	
	@SuppressWarnings("unchecked")
	public List<Aviso> buscarAviso(Aviso aviso) {
		Criteria criteria = obterCriteria(Aviso.class);
		
		if(aviso.getLocal() != null){
			criteria.add(Restrictions.eq("local", aviso.getLocal()));
		}
		if(aviso.getTipoEnderecamento() != null){
			criteria.add(Restrictions.eq("tipoEnderecamento", aviso.getTipoEnderecamento()));
		}
		if(aviso.getDataInclusao() != null){
			criteria.add(Restrictions.eq("dataInclusao", aviso.getDataInclusao()));
		}
		if(aviso.getSituacao() != null){
			criteria.add(Restrictions.eq("situacao", aviso.getSituacao()));
		}
		if(aviso.getDataResolucao() != null){
			criteria.add(Restrictions.eq("dataResolucao", aviso.getDataResolucao()));
		}
		if(aviso.getDocumento() != null){
			criteria.add(Restrictions.eq("documento", aviso.getDocumento()));
		}		
		if(aviso.getDestinatariosAviso() != null && aviso.getDestinatariosAviso().size() > 0){
			Criteria criteriaDestinatario = criteria.createCriteria("destinatariosAviso");
			criteriaDestinatario.add(getDisjuctionUsuarioDestinatario(aviso.getDestinatariosAviso()));
		}
		
		return (List<Aviso>) criteria.list();
	}
	
	public Disjunction getDisjuctionUsuarioDestinatario(List<DestinatarioAviso> lista){
		Disjunction disjunction = Restrictions.disjunction();
		
		for (DestinatarioAviso destinatarioAviso : lista) {
			disjunction.add(Restrictions.eq("usuario", destinatarioAviso.getUsuario()));
		}
		
		return disjunction;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aviso> obterAvisosPorUsuario(Usuario usuario, Local local) {

		String selectAvisosUsuario = "select aviso from Aviso aviso"
				+ " join fetch aviso.destinatariosAviso avisoDest"
				+ " join fetch avisoDest.usuario"
				+ " where aviso.situacao <> 'R'"
				+ " and aviso.documento is null"
				+ " and aviso.id in (select aviso_2.id from Aviso aviso_2 "
				+ " inner join aviso_2.destinatariosAviso avisoDest_2"
				+ " where avisoDest_2.usuario = :usuario and aviso_2.local = :local)";

		Query query = null;

		if (usuario != null) {

			String selectComplemento = " order by aviso.dataInclusao desc";
			
			selectAvisosUsuario = selectAvisosUsuario.concat(selectComplemento);
			
			query = manager.createQuery(selectAvisosUsuario, Aviso.class);
			query.setParameter("usuario", usuario);
			query.setParameter("local", local);

		} else {

			query = manager.createQuery(selectAvisosUsuario);

		}

		return removerItensDuplicados(query.getResultList());

	}
	
	private List<Aviso> removerItensDuplicados(List<Aviso> avisos) {
		
		List<Aviso> avisosDistintos = null;
		
		if(avisos != null && !avisos.isEmpty()) {
			
			avisosDistintos = new ArrayList<Aviso>();
			
			for (Aviso aviso : avisos) {
				
				if(!avisosDistintos.contains(aviso))
					avisosDistintos.add(aviso);
				
			}

		}
		
		return avisosDistintos;
		
	}
	
    @SuppressWarnings("unchecked")
	public List<Aviso> obterAvisosPorUnidade(Local local) {

		String selectAvisosPorUnidade = "select aviso from Aviso aviso"
				+ " where aviso.situacao <> 'R'"
			    + " and aviso.tipoEnderecamento = '" + TipoEnderecamentoAvisoEnum.TODOS.getChave()+"'";

		Query query = null; 

		if (local != null) {


			String complementoConsulta = " and aviso.local.id = :idLocal order "
					+ " by aviso.dataInclusao desc";
			
			selectAvisosPorUnidade = selectAvisosPorUnidade
					.concat(complementoConsulta);
			
			query = manager.createQuery(selectAvisosPorUnidade,
					Aviso.class);
			
			query.setParameter("idLocal", local.getId());
			

		} else {
			
			query = manager.createQuery(selectAvisosPorUnidade,
					Aviso.class);
			
		}

		return query.getResultList();

	}
    
    public Aviso obterPorId(Long idAviso) {
		Criteria criteria = obterCriteria(Aviso.class);
		criteria.add(Restrictions.eq("id", idAviso));
		return (Aviso)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<DestinatarioAviso> listarAvisosDoUsuario(int first,
			int pageSize, String[] sortFields, boolean[] ordersAscending,
			Map<String, Object> filters, String userName) {

		StringBuilder sb = new StringBuilder().append("	select													")
				.append("		destinatarioAviso									")
				.append("	from													")
				.append("		DestinatarioAviso destinatarioAviso 				")
				.append("		join fetch destinatarioAviso.aviso aviso			")
				.append("		join fetch destinatarioAviso.usuario usuario		")
				.append("	left join fetch	aviso.local								")
				.append("	left join fetch	aviso.destinatariosAviso				")
				.append("	where usuario.nome like :userName1						");

		if (sortFields != null && sortFields.length > 0) {
			sb.append("	order by	");
			for (int i = 0; i < sortFields.length; i++) {
				String sortField = sortFields[i];
				boolean ascending = ordersAscending[i];
				sb.append(" aviso.").append(sortField)
						.append(ascending ? " asc" : " desc")
						.append(i < sortFields.length - 1 ? ", " : " ");
			}
		}

		Query query = manager.createQuery(sb.toString());
		query.setParameter("userName1", "%" + userName + "%");
		query.setFirstResult(first);
		query.setMaxResults(pageSize);

		List<DestinatarioAviso> avisos = query.getResultList();

		return avisos;
	}

	/*
	 * public List<Aviso> consultaAvisoPorUsuarios(List<Usuario> usuarios, Aviso
	 * aviso) {
	 * 
	 * Criteria criteria = obterCriteria(Aviso.class); Criteria
	 * criteriaDestinatarioAviso =
	 * criteria.createCriteria("destinatariosAviso");
	 * 
	 * 
	 * StringBuilder sb = new StringBuilder()
	 * .append(" select aviso , destinatariosAviso ")
	 * .append(" from Aviso aviso ")
	 * .append(" join fetch aviso.destinatariosAviso destinatariosAviso ")
	 * .append(" where ") .append(" aviso.dataInclusao = :dataInclusao ")
	 * .append(" and aviso.dataResolucao = :dataResolucao ")
	 * .append(" and destinatariosAviso.compositeId in (:ids) ");
	 * 
	 * Query query = manager.createQuery(sb.toString());
	 * query.setParameter("dataInclusao", aviso.getDataInclusao());
	 * query.setParameter("dataResolucao", aviso.getDataResolucao());
	 * 
	 * 
	 * return null; }
	 */

	public Integer consultaAvisoPorUsuariosCount(FiltroConsultaAvisoVO avisoVO) {
		Criteria criteria = obterCriteriaComFiltro(avisoVO);
		criteria.setProjection(Projections.rowCount());
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<Aviso> consultaAvisoPorUsuarios(Integer posicaoPrimeiraLinha,
			Integer maximoPorPagina, FiltroConsultaAvisoVO avisoVO) {

		Criteria criteria = obterCriteriaComFiltro(avisoVO);
		
		criteria.setFirstResult(posicaoPrimeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
		return criteria.list();

	}

	private Criteria obterCriteriaComFiltro(FiltroConsultaAvisoVO avisoVO) {
		Criteria criteria = obterCriteria(Aviso.class);
		Criteria criteriaDocumento = null;
		
		if (avisoVO.getIdentificadorDocumento() != null &&
				avisoVO.getListDestinatario().size() != 0) {
			Criteria criteriaDestinatarioAviso = criteria
					.createCriteria("destinatariosAviso");
			criteriaDestinatarioAviso.add(Restrictions.in("usuario",
					avisoVO.getListDestinatario()));
		}
		
		if(avisoVO.getAviso().getDataInclusao() != null){
			
			criteria.add(Restrictions.eq("dataInclusao", avisoVO.getAviso()
						.getDataInclusao()));
		}
		if(avisoVO.getAviso().getSituacao() != null){
				
			criteria.add(Restrictions.eq("situacao", avisoVO.getAviso()
					.getSituacao()));
		}
		if (avisoVO.getAviso().getDataResolucao() != null) {

			criteria.add(Restrictions.eq("dataResolucao", avisoVO.getAviso()
					.getDataResolucao()));
		}

		if (avisoVO.getAviso().getDocumento().getNumero() != null) {

			criteriaDocumento = criteria.createCriteria("documento");
			criteriaDocumento.add(Restrictions.eq("numero", avisoVO.getAviso()
					.getDocumento().getNumero()));

		}
		if (avisoVO.getAviso().getDocumento().getTipo().getNome() != null) {
			
			if ( criteriaDocumento == null)
				criteriaDocumento = criteria.createCriteria("documento");
			
			Criteria criteriaTipo = criteriaDocumento.createCriteria("tipo");
			
			criteriaTipo.add(Restrictions.eq("nome", avisoVO.getAviso()
					.getDocumento().getTipo().getNome()));

		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<DestinatarioAviso> listarAvisosDoUsuario(int first,
			int pageSize, String[] sortFields, boolean[] ordersAscending,
			Map<String, Object> filters, Long idUsuario) {

		StringBuilder sb = new StringBuilder().append(" select ")
				.append(" destinatarioAviso ").append(" from ")
				.append(" DestinatarioAviso destinatarioAviso ")
				.append(" join fetch destinatarioAviso.aviso aviso ")
				.append(" join fetch destinatarioAviso.usuario usuario ")
				.append(" left join fetch aviso.local ")
				.append(" left join fetch aviso.destinatariosAviso ")
				.append(" where usuario.nome = :idUsuario ");

		if (sortFields != null && sortFields.length > 0) {

			sb.append("	order by ");

			for (int i = 0; i < sortFields.length; i++) {
				String sortField = sortFields[i];
				boolean ascending = ordersAscending[i];
				sb.append(" aviso.").append(sortField)
						.append(ascending ? " asc" : " desc")
						.append(i < sortFields.length - 1 ? ", " : " ");
			}

		}

		Query query = manager.createQuery(sb.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);

		List<DestinatarioAviso> avisos = query.getResultList();

		return avisos;

	}

	@SuppressWarnings("unchecked")
	public List<Aviso> listarAvisosDaUnidadePorUsuario(Long idUsuario) {

		StringBuilder sb = new StringBuilder().append(" select ")
				.append(" aviso ").append(" from ").append(" Aviso aviso ")
				.append(" join fetch aviso.local local ")
				.append(" join fetch local.locaisUsuarios locaisUsrs ")
				.append(" join fetch locaisUsrs.usuario usuario ")
				.append(" where ").append(" usuario.id = :idUsuario ");

		Query query = manager.createQuery(sb.toString());
		query.setParameter("idUsuario", idUsuario);

		List<Aviso> avisos = query.getResultList();

		return avisos;

	}
	
	@SuppressWarnings("unchecked")
	public List<Aviso> avisosDaUnidadeLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local){		
		Criteria criteria = obterCriteria(Aviso.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
		return criteria.list();
	}
	
	public int avisosDaUnidadeLazyCount(Local local) {
		Criteria criteria = obterCriteria(Aviso.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aviso> avisosUnidadeUsuarioLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local, Usuario usuario){		
		Criteria criteria = obterCriteria(Aviso.class);
		
		if(local != null){
			criteria.add(Restrictions.eq("local", local));
			
			if(usuario == null){
				criteria.add(Restrictions.eq("tipoEnderecamento", TipoEnderecamentoAvisoEnum.TODOS.getChave()));
			}
		}
		
		Criteria criteriaDestinatarios = criteria.setFetchMode("destinatariosAviso", FetchMode.JOIN)
				.createAlias("destinatariosAviso", "da");
		
		if(usuario != null){
			criteriaDestinatarios.add(Restrictions.eq("da.usuario", usuario));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
		return criteria.list();
	}
	
	public int avisosUnidadeUsuarioLazyCount(Local local, Usuario usuario) {
		Criteria criteria = obterCriteria(Aviso.class);
		
		if(local != null){
			criteria.add(Restrictions.eq("local", local));
			
			if(usuario == null){
				criteria.add(Restrictions.eq("tipoEnderecamento", TipoEnderecamentoAvisoEnum.TODOS.getChave()));
			}
		}
		
		Criteria criteriaDestinatarios = criteria.setFetchMode("destinatariosAviso", FetchMode.JOIN)
				.createAlias("destinatariosAviso", "da");
		
		if(usuario != null){
			criteriaDestinatarios.add(Restrictions.eq("da.usuario", usuario));
		}
		
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	public int totalAvisosUnidadeUsuarioPendentes(Local local, Usuario usuario) {
		Criteria criteria = obterCriteria(Aviso.class);
		
		if(local != null){
			criteria.add(Restrictions.eq("local", local));
			
			if(usuario == null){
				criteria.add(Restrictions.eq("tipoEnderecamento", TipoEnderecamentoAvisoEnum.TODOS.getChave()));
			}
		}
		
		Criteria criteriaDestinatarios = criteria.setFetchMode("destinatariosAviso", FetchMode.JOIN)
				.createAlias("destinatariosAviso", "da");
		
		if(usuario != null){
			criteriaDestinatarios.add(Restrictions.eq("da.usuario", usuario));
		}
		
		criteria.add(Restrictions.eq("situacao", SituacaoAvisoEnum.PENDENTE.getValor()));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}

}
