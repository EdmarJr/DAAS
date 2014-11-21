package br.jus.stj.saad.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.date.DateUtils;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.SituacaoTarefaEnum;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.util.ConstantesSaad;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;
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
		if (filtro.getLocal() != null) {

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

		if (filtro.getDocumento().getNumeroControleIdentificador() != null
				&& !filtro.getDocumento().getNumeroControleIdentificador()
						.equals("")) {

			criteria.add(Restrictions.eq("numeroControleIdentificador", filtro
					.getDocumento().getNumeroControleIdentificador()));

		}
		if (filtro.getDocumento() != null
				&& filtro.getDocumento().getTipo() != null) {

			criteriaTipo.add(Restrictions.eq("id", filtro.getDocumento()
					.getTipo().getId()));

		}
		if (filtro.getDocumento().getSituacao() != null) {

			criteria.add(Restrictions.eq("situacao", filtro.getDocumento()
					.getSituacao()));
		}
		if (filtro.getDocumento().getNumeroDocumentoOrigem() != null
				&& !filtro.getDocumento().getNumeroDocumentoOrigem().equals("")) {

			criteria.add(Restrictions.eq("numeroDocumentoOrigem", filtro.getDocumento()
					.getNumeroDocumentoOrigem()));
		}

		if (!StringUtils.isEmpty(filtro.getSigla())
				&& !StringUtils.isEmpty(filtro.getIdentificacaoDocumentoSTJ())
				&& !StringUtils.isEmpty(filtro.getAno())) {
			criteria.add(Restrictions.eq("numeroControleIdentificador",
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
				+ " join fetch doc.tarefas tarefas" + " where doc.situacao = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor()
				+ "' and doc.local = :local";

		Query query = manager.createQuery(selectDocumentos, Documento.class);
		query.setParameter("local", local);

		return removerItensDuplicados(query.getResultList());

	}

	private List<Documento> removerItensDuplicados(List<Documento> documentos) {

		List<Documento> documentosDistintos = null;

		if (documentos != null && !documentos.isEmpty()) {

			documentosDistintos = new ArrayList<Documento>();

			for (Documento documento : documentos) {

				if (!documentosDistintos.contains(documento))
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
		criteria.add(Restrictions.eq("situacao",
				SituacaoDocumentoEnum.PENDENTE.getValor()));
		criteria.addOrder(Order.asc("dataInclusao"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);

		return criteria.list();
	}

	public int documentosPendentesLocalLazyCount(Local local) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.add(Restrictions.eq("situacao",
				SituacaoDocumentoEnum.PENDENTE.getValor()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();

		return resultCount.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Documento> documentosTarefasPendentesLocalLazy(
			Integer primeiraLinha, Integer maximoPorPagina, Local local,
			Usuario usuario) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setFetchMode("tarefas", FetchMode.JOIN)
				.createAlias("tarefas", "t")
				.add(Restrictions.eq("t.usuario", usuario))
				.add(Restrictions.eq("t.situacaoTarefa",
						SituacaoTarefaEnum.PENDENTE.getValor()));
		criteria.addOrder(Order.asc("dataInclusao"));
		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);

		return criteria.list();
	}

	public int documentosTarefasPendentesLocalLazyCount(Local local,
			Usuario usuario) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.setFetchMode("tarefas", FetchMode.JOIN)
				.createAlias("tarefas", "t")
				.add(Restrictions.eq("t.usuario", usuario))
				.add(Restrictions.eq("t.situacaoTarefa",
						SituacaoTarefaEnum.PENDENTE.getValor()));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();

		return resultCount.intValue();
	}

	public int totalDocumentosPendentesPorDia(Integer dias, Local local,
			Usuario usuario, Boolean maior, Boolean atual) {

		String selectDocumentos = " select count(*) from Documento doc where ";

		if (local != null) {
			selectDocumentos = selectDocumentos + " doc.local = :local ";
		}

		String subSelect = " and (select max(td.dataLimiteConclusao) from TarefaDemandaDocumento td where td.documento = doc "
				+ "and td.situacaoTarefa = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor() + "' ";

		if (usuario != null) {
			subSelect = subSelect + " and td.usuario = :usuario ";
		}

		if (maior) {
			subSelect = subSelect + ") > :dataLimite ";
		} else {
			if (atual) {
				subSelect = subSelect + ") between :dataAtual and :dataLimite ";
			} else {
				subSelect = subSelect + ") < :dataLimite ";
			}
		}

		selectDocumentos = selectDocumentos + subSelect;

		Query query = manager.createQuery(selectDocumentos);

		if (local != null) {
			query.setParameter("local", local);
		}

		if (usuario != null) {
			query.setParameter("usuario", usuario);
		}

		if (maior) {
			query.setParameter("dataLimite",
					DateUtils.addDays(new Date(), dias + 1));
		} else {
			if (atual) {
				query.setParameter("dataAtual",
						DateUtils.addDays(new Date(), 0));
			}
			query.setParameter("dataLimite",
					DateUtils.addDays(new Date(), dias + 1));
		}

		Long l = (Long) query.getSingleResult();

		return l.intValue();
	}

	public int totalTarefasPendentesPorDia(Integer dias, Local local,
			Usuario usuario, Boolean maior, Boolean atual) {
		String subSelect = "select count(*) from TarefaDemandaDocumento td, Documento doc where td.documento = doc "
				+ "and td.situacaoTarefa = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor() + "' ";

		if (local != null) {
			subSelect = subSelect + "and doc.local = :local ";
		}

		if (usuario != null) {
			subSelect = subSelect + "and td.usuario = :usuario ";
		}

		subSelect = subSelect + " and td.dataLimiteConclusao ";

		if (maior) {
			subSelect = subSelect + " > :dataLimite ";
		} else {
			if (atual) {
				subSelect = subSelect + " between :dataAtual and :dataLimite ";
			} else {
				subSelect = subSelect + " < :dataLimite ";
			}
		}

		Query query = manager.createQuery(subSelect);

		if (local != null) {
			query.setParameter("local", local);
		}

		if (usuario != null) {
			query.setParameter("usuario", usuario);
		}

		if (maior) {
			query.setParameter("dataLimite",
					DateUtils.addDays(new Date(), dias + 1));
		} else {
			if (atual) {
				query.setParameter("dataAtual",
						DateUtils.addDays(new Date(), 0));
			}
			query.setParameter("dataLimite",
					DateUtils.addDays(new Date(), dias + 1));
		}

		Long l = (Long) query.getSingleResult();

		return l.intValue();
	}

	public int totalDocumentosSemTarefas(Local local) {
		Criteria criteria = obterCriteria(Documento.class);
		criteria.add(Restrictions.eq("local", local));
		criteria.add(Restrictions.isEmpty("tarefas"));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		return resultCount.intValue();
	}

	public int totalDocumentosPendentesEntreDias(Integer diaInicial,
			Integer diaFinal, Local local, Usuario usuario) {
		String selectDocumentos = " select count(*) from Documento doc where ";

		if (local != null) {
			selectDocumentos = selectDocumentos + " doc.local = :local ";
		}

		String subSelect = " and (select max(td.dataLimiteConclusao) from TarefaDemandaDocumento td where td.documento = doc "
				+ "and td.situacaoTarefa = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor() + "' ";

		if (usuario != null) {
			subSelect = subSelect + " and td.usuario = :usuario ";
		}

		if (diaInicial != null && diaFinal != null) {
			subSelect = subSelect + ") between :diaInicial and :diaFinal ";
		}

		selectDocumentos = selectDocumentos + subSelect;

		Query query = manager.createQuery(selectDocumentos);

		if (local != null) {
			query.setParameter("local", local);
		}

		if (usuario != null) {
			query.setParameter("usuario", usuario);
		}

		if (diaInicial != null && diaFinal != null) {
			query.setParameter("diaInicial",
					DateUtils.addDays(new Date(), diaInicial + 1));
			query.setParameter("diaFinal",
					DateUtils.addDays(new Date(), diaFinal + 1));

		}

		Long l = (Long) query.getSingleResult();

		return l.intValue();
	}

	public int totalTarefasPendentesEntreDias(Integer diaInicial,
			Integer diaFinal, Local local, Usuario usuario) {
		String subSelect = "select count(*) from TarefaDemandaDocumento td, Documento doc where td.documento = doc "
				+ "and td.situacaoTarefa = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor() + "' ";

		if (local != null) {
			subSelect = subSelect + "and doc.local = :local ";
		}

		if (usuario != null) {
			subSelect = subSelect + " and td.usuario = :usuario ";
		}

		subSelect = subSelect + " and td.dataLimiteConclusao ";

		if (diaInicial != null && diaFinal != null) {
			subSelect = subSelect + " between :diaInicial and :diaFinal ";
		}

		Query query = manager.createQuery(subSelect);

		if (local != null) {
			query.setParameter("local", local);
		}

		if (usuario != null) {
			query.setParameter("usuario", usuario);
		}

		if (diaInicial != null && diaFinal != null) {
			query.setParameter("diaInicial",
					DateUtils.addDays(new Date(), diaInicial + 1));
			query.setParameter("diaFinal",
					DateUtils.addDays(new Date(), diaFinal + 1));

		}

		Long l = (Long) query.getSingleResult();

		return l.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Documento> obterTarefasDocumentoPendente(Usuario usuario) {

		String selectDocumentos = "select doc from Documento doc join fetch"
				+ " doc.tarefas tarefas where tarefas.usuario = :usuario"
				+ " and tarefas.situacaoTarefa = '"
				+ SituacaoTarefaEnum.PENDENTE.getValor() + "'";

		Query query = manager.createQuery(selectDocumentos, Documento.class);
		query.setParameter("usuario", usuario);

		return query.getResultList();

	}

	@Override
	public Documento buscarDocumento(String numero, String sigla,
			String numeroControleIdentificador, String ano, Local local) {
		Criteria criteria = obterCriteria(Documento.class);

		if (!(numero == null || "".equalsIgnoreCase(numero))) {
			criteria.add(Restrictions.eq("numeroDocumentoOrigem", numero));
		}

		if (!StringUtils.isEmpty(sigla)
				&& !StringUtils.isEmpty(numeroControleIdentificador)
				&& !StringUtils.isEmpty(ano)) {
			criteria.add(Restrictions.eq("numeroControleIdentificador",
					numeroControleIdentificador));
			criteria.add(Restrictions.eq("local", local));
			Criteria criteriaTipoDocumento = criteria.createCriteria("tipo");
			criteriaTipoDocumento.add(Restrictions.eq("sigla", sigla));
			Criteria criteriaControleNumeracao = criteriaTipoDocumento
					.createCriteria("controlesNumeracao");
			criteriaControleNumeracao.add(Restrictions.eq("ano",
					Long.parseLong(ano)));
			criteriaControleNumeracao.add(Restrictions.eq("local", local));
		}

		return (Documento) criteria.uniqueResult();
	}

	public Documento buscarDocumentoPorIdentificador(String sigla,
			String numeroControleIdentificador, String ano, Local local) {
		if (!(sigla == null || "".equalsIgnoreCase(sigla))
				&& !(numeroControleIdentificador == null || ""
						.equalsIgnoreCase(numeroControleIdentificador))
				&& !(ano == null || "".equalsIgnoreCase(ano))) {

			Criteria criteria = obterCriteria(Documento.class);
			criteria.add(Restrictions.eq("numeroControleIdentificador",
					numeroControleIdentificador));
			Criteria criteriaTipoDocumento = criteria.createCriteria("tipo");
			criteriaTipoDocumento.add(Restrictions.eq("sigla", sigla));
			Criteria criteriaControleNumeracao = criteriaTipoDocumento
					.createCriteria("controlesNumeracao");
			criteriaControleNumeracao.add(Restrictions.eq("ano",
					Long.parseLong(ano)));
			Criteria criteriaLocal = criteriaControleNumeracao
					.createCriteria("local");
			criteriaLocal.add(Restrictions.eq("id", local.getId()));

			return (Documento) criteria.uniqueResult();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> buscarDocumentoPorDocumento(Documento documento) {
		Criteria criteria = obterCriteria(Documento.class);
		adicionarRestricaoDeDocumento(criteria, documento);
		return (List<Documento>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> buscarDocumentoPorFiltroConsultarDocumentoVO(
			FiltroConsultarDocumentoVO filtroConsultarDocumentoVO) {
		Documento documento = filtroConsultarDocumentoVO.getDocumento();
		Criteria criteria = obterCriteria(Documento.class);

		adicionarRestricaoDeDocumento(criteria, documento);
		
		adicionarRestricaoDeData(criteria, "dataOrigemDocumento", filtroConsultarDocumentoVO.getDataOrigemInicial(), filtroConsultarDocumentoVO.getDataOrigemFinal());
		adicionarRestricaoDeData(criteria, "dataRecebimentoExpedicao", filtroConsultarDocumentoVO.getDataRecebimentoInicial(), filtroConsultarDocumentoVO.getDataRecebimentoFinal());
		
		if(documento.getEnderecamentoDestinatario() != null){
			adicionarRestricaoDeEnderecamento(criteria, documento, ConstantesSaad.enderecamentoDestinatario);
		}
		
		if(documento.getEnderecamentoRemetente() != null){
			adicionarRestricaoDeEnderecamento(criteria, documento, ConstantesSaad.enderecamentoRemetente);
		}
		
		if (documento.getTipo() != null
				&& documento.getTipo().getSigla()
						.equalsIgnoreCase(ConstantesSaad.siglaConvite)) {
			criteria.setFetchMode("evento", FetchMode.JOIN).createAlias("evento", "e");
			adicionarRestricaoDeData(criteria, "e.dataInicioEvento", filtroConsultarDocumentoVO.getDataEventoInicial(), filtroConsultarDocumentoVO.getDataEventoFinal());
		}
		
		if(filtroConsultarDocumentoVO.getAndamento()){
			Criteria criteriaAndamentos = criteria.createCriteria("andamentosDocumento");
			criteriaAndamentos.add(Restrictions.ilike("descricaoAndamentoDocumento", filtroConsultarDocumentoVO.getDescricaoAndamento(), MatchMode.ANYWHERE));
		}
		
		criteria.addOrder(Order.asc("dataInclusao"));

		return (List<Documento>) criteria.list();
	}
	
	private void adicionarRestricaoDeEnderecamento(Criteria criteria, Documento documento, String tipoEnderecamento){
		Enderecamento enderecamento;
		String nomeAtributo = "";
		
		if(ConstantesSaad.enderecamentoDestinatario.equalsIgnoreCase(tipoEnderecamento)){
			enderecamento = documento.getEnderecamentoDestinatario();
			nomeAtributo = "enderecamentoDestinatario";
		}else{
			enderecamento = documento.getEnderecamentoRemetente();
			nomeAtributo = "enderecamentoRemetente";
		}
		
		if(enderecamento instanceof EnderecamentoInterno){
			adicionarRestricaoDeEnderecamentoInterno(criteria, enderecamento, nomeAtributo);
		}else if(enderecamento instanceof EnderecamentoExterno){
			adicionarRestricaoDeEnderecamentoExterno(criteria, enderecamento, nomeAtributo);
		}else{
			adicionarRestricaoDeEnderecamentoOutroDestinatario(criteria, enderecamento, nomeAtributo);
		}
	}
	
	private void adicionarRestricaoDeEnderecamentoInterno(Criteria criteria, Enderecamento enderecamento, String nomeAtributo){
		Usuario usuario = enderecamento.getUsuario();
		Local local = enderecamento.getLocal();
		
		if(usuario != null && local != null){
			Criteria c = criteria.createCriteria(nomeAtributo);
			c.add(Restrictions.eq("usuario", usuario));
			c.add(Restrictions.eq("local", local));
		}
		
	}
	
	private void adicionarRestricaoDeEnderecamentoExterno(Criteria criteria, Enderecamento enderecamento, String nomeAtributo) {
		DestinatarioOcupacao ocupacao = enderecamento.getOcupacao();
		Orgao orgao = enderecamento.getOrgao();
		Cargo cargo = enderecamento.getCargo();
		Destinatario destinatario = enderecamento.getDestinatario();

		if(ocupacao != null && orgao != null && cargo != null && destinatario != null){
			Criteria c = criteria.createCriteria(nomeAtributo);
			c.add(Restrictions.eq("ocupacao", ocupacao));
			c.add(Restrictions.eq("orgao", orgao));
			c.add(Restrictions.eq("cargo", cargo));
			c.add(Restrictions.eq("destinatario", destinatario));
		}
	}
	
	private void adicionarRestricaoDeEnderecamentoOutroDestinatario(Criteria criteria, Enderecamento enderecamento, String nomeAtributo) {
		DestinatarioDocumento destinatarioDocumento = enderecamento.getEnderecamentoDestinatarioDocumento().getDestinatarioDocumento();
		
		if(destinatarioDocumento != null){
			Criteria c = criteria.createCriteria(nomeAtributo);
			Criteria c2 = c.createCriteria("enderecamentoDestinatarioDocumento");
			c2.add(Restrictions.eq("destinatarioDocumento", destinatarioDocumento));
		}
	}
	
	public void adicionarRestricaoDeData(Criteria criteria, String campo,
			Date dataInicial, Date dataFinal) {
		if (dataInicial != null || dataFinal != null) {
			if (dataInicial != null && dataFinal != null) {
				criteria.add(Restrictions
						.between(campo, dataInicial, dataFinal));
			} else {
				if (dataInicial != null) {
					criteria.add(Restrictions.eq(campo, dataInicial));
				} else {
					criteria.add(Restrictions.eq(campo, dataFinal));
				}
			}
		}
	}

	public void adicionarRestricaoDeDocumento(Criteria criteria,
			Documento documento) {
		if (documento.getLocal() != null) {
			criteria.add(Restrictions.eq("local", documento.getLocal()));
		}
		if (documento.getTipo() != null) {
			criteria.add(Restrictions.eq("tipo", documento.getTipo()));
		}
		if (documento.getSituacao() != null
				&& !StringUtils.charIsEmpty(documento.getSituacao())) {
			criteria.add(Restrictions.eq("situacao", documento.getSituacao()));
		}
		if (documento.getTipoClassificacaoDocumento() != null
				&& !StringUtils.charIsEmpty(documento
						.getTipoClassificacaoDocumento())) {
			criteria.add(Restrictions.eq("tipoClassificacaoDocumento",
					documento.getTipoClassificacaoDocumento()));
		}
		if (documento.getTipoOrigem() != null
				&& !StringUtils.charIsEmpty(documento.getTipoOrigem())) {
			criteria.add(Restrictions.eq("tipoOrigem",
					documento.getTipoOrigem()));
		}
		if (!StringUtils.isEmpty(documento.getNumeroDocumentoOrigem())) {
			criteria.add(Restrictions.eq("numeroDocumentoOrigem", documento.getNumeroDocumentoOrigem()));
		}
		if (!StringUtils.isEmpty(documento.getAssuntoDocumento())) {
			criteria.add(Restrictions.ilike("assuntoDocumento",
					documento.getAssuntoDocumento(), MatchMode.ANYWHERE));
		}
	}
}
