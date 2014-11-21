package br.jus.stj.saad.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.date.DateUtils;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoTarefaEnum;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.vo.FiltroDocumentoConsultaAndamentoVO;


public class DocumentoDAOImpl extends GenericDAOMssqlImpl<Documento> implements
		DocumentoDAO {

	@SuppressWarnings("unchecked")
	public List<Documento> listarDocumento(Integer primeiraLinha,
			Integer maximoPorPagina, FiltroDocumentoConsultaAndamentoVO filtro) {

		Criteria criteria = obterCriteria(Documento.class);
		Criteria criteriaTipo = criteria.createCriteria("tipo");
		Criteria criteriaLocal = criteria.createCriteria("local");
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		obterCriteriaComFiltro(filtro, criteria, criteriaTipo, criteriaLocal);
		
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		criteria.setFetchMode("andamentosDocumento", FetchMode.JOIN);
		return criteria.list();

	}
	
	public Integer listarDocumentoCount(
			FiltroDocumentoConsultaAndamentoVO filtro) {
		
		Criteria criteria = obterCriteria(Documento.class);
		Criteria criteriaTipo = criteria.createCriteria("tipo");
		Criteria criteriaLocal = criteria.createCriteria("local");
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		obterCriteriaComFiltro(filtro, criteria, criteriaTipo, criteriaLocal);
		criteria.setFetchMode("andamentosDocumento", FetchMode.JOIN);
		criteria.setProjection(Projections.rowCount());
		
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	private void obterCriteriaComFiltro(
			FiltroDocumentoConsultaAndamentoVO filtro, Criteria criteria,
			Criteria criteriaTipo, Criteria criteriaLocal) {
		if(filtro.getLocal() != null){
			
			 criteriaLocal.add(Restrictions.ge("id", filtro.getLocal().getId()));
		}
		
		if (filtro.getDataOrigemAte() != null
				&& filtro.getDataOrigemDe() != null) {
			
			criteria.add(Restrictions.ge("dataOrigemDocumento",
					filtro.getDataOrigemDe()));
			criteria.add(Restrictions.lt("dataOrigemDocumento",
					filtro.getDataOrigemAte()));
		}

		if (filtro.getDataRecebimentoAte() != null
				&& filtro.getDataRecebimentoDe() != null) {

			criteria.add(Restrictions.ge("dataRecebimentoExpedicao",
					filtro.getDataRecebimentoDe()));
			criteria.add(Restrictions.lt("dataRecebimentoExpedicao",
					filtro.getDataRecebimentoAte()));
		}

		if (filtro.getDocumento().getIdentificacaoDocumentoSTJ() != null
				&& !filtro.getDocumento().getIdentificacaoDocumentoSTJ()
						.equals("")) {

			criteria.add(Restrictions.eq("identificacaoDocumentoSTJ", filtro
					.getDocumento().getIdentificacaoDocumentoSTJ()));

		}
		if (filtro.getDocumento() != null
				&& filtro.getDocumento().getTipo() != null) {

			criteriaTipo.add(Restrictions.eq("id", filtro.getDocumento()
					.getTipo().getId()));

		}
		if (filtro.getDocumento().getSituacao() != null) {

			criteria.add(Restrictions.eq("situacao", filtro
					.getDocumento().getSituacao()));
		}
		if (filtro.getDocumento().getNumero() != null
				&& !filtro.getDocumento().getNumero().equals("")) {

			criteria.add(Restrictions.eq("numero", filtro.getDocumento()
					.getNumero()));
		}
		
		if (!StringUtils.isEmpty(filtro.getSigla())
				&& !StringUtils.isEmpty(filtro.getIdentificacaoDocumentoSTJ())
				&& !StringUtils.isEmpty(filtro.getAno())) {
			criteria.add(Restrictions.eq("identificacaoDocumentoSTJ",
					filtro.getIdentificacaoDocumentoSTJ()));
			criteriaTipo.add(Restrictions.eq("sigla", filtro.getSigla()));
			Criteria criteriaControleNumeracao = criteriaTipo
					.createCriteria("controlesNumeracao");
			criteriaControleNumeracao.add(Restrictions.eq("ano",
					Long.parseLong(filtro.getAno())));
		}
	}
	
	
	public Documento obterPorId(Long id, TipoDocumento tipoDocumento) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("id", id));
		Criteria criteriaTipoDocumento = criteria.createCriteria("tipo");
		criteriaTipoDocumento.add(Restrictions.eq("id", tipoDocumento.getId()));
		return (Documento) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> obterDocumentosPendentes(Local local) {
		
		String selectDocumentos = "select doc from Documento doc"
				+ " join fetch doc.tarefas tarefas"
				+ " where doc.situacao = '" 
				+ SituacaoTarefaEnum.PENDENTE.getValor()
				+ "' and doc.local = :local";
		
		Query query = manager.createQuery(selectDocumentos, Documento.class);
		query.setParameter("local", local);
		
		return removerItensDuplicados(query.getResultList());

	}
	
	private List<Documento> removerItensDuplicados(List<Documento> documentos) {
		
		List<Documento> documentosDistintos = null;
		
		if(documentos != null && !documentos.isEmpty()) {
			
			documentosDistintos = new ArrayList<Documento>();
			
			for (Documento documento : documentos) {
				
				if(!documentosDistintos.contains(documento))
					documentosDistintos.add(documento);
				
			}

		}
		
		return documentosDistintos;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> documentosPendentesLocalLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setFetchMode("tarefas", FetchMode.JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
		return criteria.list();
	}
	
	public int documentosPendentesLocalLazyCount(Local local) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> documentosTarefasPendentesLocalLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local, Usuario usuario){
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setFetchMode("tarefas", FetchMode.JOIN)
				.createAlias("tarefas", "t")
				.add(Restrictions.eq("t.usuario", usuario));
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
//		String selectDocumentos = " select doc from Documento doc join fetch"
//				+ " doc.tarefas tarefas where doc.local = :local and tarefas.usuario = :usuario"
//				+ " and tarefas.situacaoTarefa = '" +  SituacaoTarefaEnum.PENDENTE.getValor() + "'";
//		
//		Query query = manager.createQuery(selectDocumentos, Documento.class);
//		query.setParameter("usuario", usuario);
//		query.setParameter("local", local);
//		query.setFirstResult(primeiraLinha);
//		query.setMaxResults(maximoPorPagina);
		
		return criteria.list();
	}
	
	public int documentosTarefasPendentesLocalLazyCount(Local local, Usuario usuario){
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setFetchMode("tarefas", FetchMode.JOIN)
				.createAlias("tarefas", "t")
				.add(Restrictions.eq("t.usuario", usuario));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	public int totalDocumentosPendentesPorDia(Integer dias, Local local, Usuario usuario, Boolean maior){
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		Criteria criteriaTarefas = criteria.createCriteria("tarefas");
		if(usuario != null){
			criteriaTarefas.add(Restrictions.eq("usuario", usuario));
		}
		if(maior){
			criteriaTarefas.add(Restrictions.gt("dataLimiteConclusao", DateUtils.addDays(new Date(), dias + 1)));
		}else{
			criteriaTarefas.add(Restrictions.lt("dataLimiteConclusao", DateUtils.addDays(new Date(), dias + 1)));
		}
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	public int totalDocumentosSemTarefas(Local local){
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.add(Restrictions.isEmpty("tarefas"));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	public int totalDocumentosPendentesEntreDias(Integer diaInicial, Integer diaFinal, Local local, Usuario usuario){
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		Criteria criteriaTarefas = criteria.createCriteria("tarefas");
		
		if(usuario != null){
			criteriaTarefas.add(Restrictions.eq("usuario", usuario));
		}
		criteriaTarefas.add(Restrictions
				.conjunction()
				.add(Restrictions.ge("dataLimiteConclusao",
						DateUtils.addDays(new Date(), diaInicial + 1)))
				.add(Restrictions.lt("dataLimiteConclusao",
						DateUtils.addDays(new Date(), diaFinal + 1))));		
		
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> obterTarefasDocumentoPendente(Usuario usuario) {
		
		String selectDocumentos = "select doc from Documento doc join fetch"
				+ " doc.tarefas tarefas where tarefas.usuario = :usuario"
				+ " and tarefas.situacaoTarefa = '" +  SituacaoTarefaEnum.PENDENTE.getValor() + "'";
		
		Query query = manager.createQuery(selectDocumentos, Documento.class);
		query.setParameter("usuario", usuario);
		
		return query.getResultList();
		
	}

 
	@Override
	public Documento buscarDocumento(String numero, String sigla, String identificacaoDocumentoSTJ, String ano, Local local) {
		Criteria criteria = obterCriteria(Documento.class);
		
		if(!(numero == null || "".equalsIgnoreCase(numero))){
			criteria.add(Restrictions.eq("numero", numero));
		}
		
		if(!StringUtils.isEmpty(sigla) && !StringUtils.isEmpty(identificacaoDocumentoSTJ) && !StringUtils.isEmpty(ano)){			
			criteria.add(Restrictions.eq("identificacaoDocumentoSTJ", identificacaoDocumentoSTJ));
			criteria.add(Restrictions.eq("local", local));
			Criteria criteriaTipoDocumento = criteria.createCriteria("tipo");
			criteriaTipoDocumento.add(Restrictions.eq("sigla", sigla));			
			Criteria criteriaControleNumeracao = criteriaTipoDocumento.createCriteria("controlesNumeracao");
			criteriaControleNumeracao.add(Restrictions.eq("ano", Long.parseLong(ano)));
			criteriaControleNumeracao.add(Restrictions.eq("local", local));
		}		
		
		return (Documento) criteria.uniqueResult();
	}
	
	public Documento buscarDocumentoPorIdentificador(String sigla, String identificacaoDocumentoSTJ, String ano, Local local) {
		if(!(sigla == null || "".equalsIgnoreCase(sigla)) &&
				!(identificacaoDocumentoSTJ == null || "".equalsIgnoreCase(identificacaoDocumentoSTJ)) &&
				!(ano == null || "".equalsIgnoreCase(ano)) ){
			
			Criteria criteria = obterCriteria(Documento.class);
			criteria.add(Restrictions.eq("identificacaoDocumentoSTJ", identificacaoDocumentoSTJ));
			Criteria criteriaTipoDocumento = criteria.createCriteria("tipo");
			criteriaTipoDocumento.add(Restrictions.eq("sigla", sigla));			
			Criteria criteriaControleNumeracao = criteriaTipoDocumento.createCriteria("controlesNumeracao");
			criteriaControleNumeracao.add(Restrictions.eq("ano", Long.parseLong(ano)));
			Criteria criteriaLocal = criteriaControleNumeracao.createCriteria("local");
			criteriaLocal.add(Restrictions.eq("id", local.getId()));
			
			return (Documento) criteria.uniqueResult();
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> buscarDocumentoPorDocumento(Documento documento) {
		Criteria criteria = obterCriteria(Documento.class);
		
		if(documento.getLocal() != null){
			criteria.add(Restrictions.eq("local", documento.getLocal()));
		}
		if(documento.getTipo() != null){
			criteria.add(Restrictions.eq("tipo", documento.getTipo()));
		}
		if(documento.getSituacao() != null && !StringUtils.charIsEmpty(documento.getSituacao())){
			criteria.add(Restrictions.eq("situacao", documento.getSituacao()));
		}
		if(documento.getTipoClassificacaoDocumento() != null && !StringUtils.charIsEmpty(documento.getTipoClassificacaoDocumento())){
			criteria.add(Restrictions.eq("tipoClassificacaoDocumento", documento.getTipoClassificacaoDocumento()));
		}
		if(documento.getTipoOrigem() != null && !StringUtils.charIsEmpty(documento.getTipoOrigem())){
			criteria.add(Restrictions.eq("tipoOrigem", documento.getTipoOrigem()));
		}
		if(!StringUtils.isEmpty(documento.getNumero())){
			criteria.add(Restrictions.eq("numero", documento.getNumero()));
		}
		
		return (List<Documento>) criteria.list();
	}
}
