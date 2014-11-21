package br.jus.stj.saad.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.commons.view.enums.SimNaoEnum;
import br.jus.stj.saad.date.DateUtils;
import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.EnderecamentoDestinatarioDocumento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.local.Evento;
import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.persistence.DocumentoDAO;
import br.jus.stj.saad.service.DocumentoService;
import br.jus.stj.saad.service.TriagemService;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;
import br.jus.stj.saad.vo.FiltroDocumentoConsultaAndamentoVO;

/**
 * Session Bean implementation class Document
 */
@Stateless
@javax.ejb.LocalBean
public class DocumentoBean extends Bean<Documento> {

	@Inject
	private DocumentoDAO documentoDAO;
	@Inject
	private LoginBean loginBean;
	@EJB
	private ControleNumeracaoBean controleNumeracaoBean;
	@EJB
	private LocalBean localBean;
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	@EJB
	private AuditoriaDocumentoBean auditoriaDocumentoBean;
	@EJB
	private UsuarioBean usuarioBean;
	@EJB
	private DestinatarioDocumentoBean destinatarioDocumentoBean;
	@EJB
	private TriagemService triagemService;
	@EJB
	private AnexoDocumentBean anexoDocumentBean;
	@EJB
	private EventoBean eventoBean;
	@EJB
	private DocumentoService documentoService;

	@Override
	public DocumentoDAO getDao() {
		return documentoDAO;
	}

	public List<Documento> buscarDocumentoPorDocumento(Documento documento){
		return getDao().buscarDocumentoPorDocumento(documento);
	}
	
	public List<Documento> buscarDocumentoPorFiltroConsultarDocumentoVO(FiltroConsultarDocumentoVO filtroConsultarDocumentoVO){
		return getDao().buscarDocumentoPorFiltroConsultarDocumentoVO(filtroConsultarDocumentoVO);
	}
	
	public void inicializarNovoDocumento(Documento documento) {
		documento.setSituacaoDocumentoEvento(SimNaoEnum.SIM.getValor());
		documento.setTarefas(new ArrayList<TarefaDemandaDocumento>());
		documento.setTipoOrigem(TipoOrigemDocumentoEnum.INTERNO.getValor());
		documento.setEnderecamentoRemetente(TipoOrigemDocumentoEnum.INTERNO
				.getEnderecamento());
		documento.setSituacao(SituacaoDocumentoEnum.PENDENTE.getValor());
	}

	public List<Documento> filtrar(Integer primeiraLinha,
			Integer maximoPorPagina, FiltroDocumentoConsultaAndamentoVO filtro) {

		if (filtro.getIdentificador() != null
				&& !"".equalsIgnoreCase(filtro.getIdentificador())) {
			filtro.setIdentificador(filtro.getIdentificador().toUpperCase());

			filtro.setSigla(IdentificadorUtils.obterSigla(filtro
					.getIdentificador()));
			filtro.setIdentificacaoDocumentoSTJ(IdentificadorUtils
					.obterIdentificacaoDocumentoSTJ(filtro.getIdentificador()));
			filtro.setAno(IdentificadorUtils.obterAno(filtro.getIdentificador()));

		}
		
		List<Documento> documentos = getDao().listarDocumento(primeiraLinha,
				maximoPorPagina, filtro);
		Calendar calendar = Calendar.getInstance();
		
		for (Documento documento : documentos) {

			if (documento.getTipo() != null
					&& documento.getDataInclusao() != null) {
				calendar.setTime(documento.getDataInclusao());
				Integer ano = calendar.get(Calendar.YEAR);
				documento
						.setIdentificadorComMascara(IdentificadorUtils
								.obterIdentificadorComMascaraPorDocumento(
										documento.getTipo().getSigla(),
										documento
												.getNumeroControleIdentificador(),
										ano.toString()));
			}
		}
		return documentos;
	}

	public Documento obterPorId(Long id, TipoDocumento tipoDocumento) {
		return getDao().obterPorId(id, tipoDocumento);
	}

	public Documento buscarDocumento(String numero, String identificador,
			Local local) {
		if (identificador != null && !"".equalsIgnoreCase(identificador)) {
			identificador = identificador.toUpperCase();

			String sigla = IdentificadorUtils.obterSigla(identificador);
			String identificacaoDocumentoSTJ = IdentificadorUtils
					.obterIdentificacaoDocumentoSTJ(identificador);
			String ano = IdentificadorUtils.obterAno(identificador);

			return getDao().buscarDocumento(numero, sigla,
					identificacaoDocumentoSTJ, ano, local);
		}

		return getDao().buscarDocumento(numero, "", "", "", local);
	}

	public List<Documento> obterDocumentosPendentesLocal(Local local) {
		return documentoDAO.obterDocumentosPendentes(local);
	}
	
	public List<Documento> obterTarefasDocumentoPendente() {

		Usuario usuarioLogado = loginBean.getUsuarioLogado();
		return documentoDAO.obterTarefasDocumentoPendente(usuarioLogado);

	}

	public Documento obterDocumentoPorIdentificador(String identificador,
			Local local) {
		identificador = identificador.toUpperCase();

		if (identificador != null && !"".equalsIgnoreCase(identificador)) {
			String sigla = IdentificadorUtils.obterSigla(identificador);
			String identificacaoDocumentoSTJ = IdentificadorUtils
					.obterIdentificacaoDocumentoSTJ(identificador);
			String ano = IdentificadorUtils.obterAno(identificador);

			return documentoDAO.buscarDocumentoPorIdentificador(sigla,
					identificacaoDocumentoSTJ, ano, local);
		} else {
			return null;
		}
	}

	private void ajustarEnderecamento(Documento documento) {
		if (documento.getTipoOrigem().equals(TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			documento.getEnderecamentoRemetente().setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_INTERNO.getValor());
		} else if (documento.getTipoOrigem().equals(TipoOrigemDocumentoEnum.EXTERNO.getValor())) {
			
		}
	}
	
	public void ajustarAnexos(Documento documento) {
		if(documento.getAnexos() == null) {
			return;
		}
		for (Anexo anexo : documento.getAnexos()) {
			if(anexo.getId() == null){
				documento.setLocal(localBean.sincronizar(documento.getLocal()));
				anexo.setDocumento(documento);
				
			}
		}
	}
	
	private void ajustarAnexosAlterar(Documento documento) {
		if(documento.getAnexos() == null) {
			return;
		}
		for (Anexo anexo : documento.getAnexos()) {
			if(anexo.getId() == null){
				documento.setLocal(localBean.sincronizar(documento.getLocal()));
				anexo.setDocumento(documento);
				anexoDocumentBean.incluir(anexo);
			}
		}
	}
			
	@Override
	public void excluir(Documento entidade) {
		Local unidade = localBean.sincronizar(loginBean.getUnidadeUsuario());
		ajustarNumeroRegistro(entidade);
		entidade.setLocal(unidade);
		ajustarEnderecamento(entidade);
		definirCargaHorariaTotal(entidade);
		super.excluir(entidade);
	}
	
	public void ajustarNumeroRegistro(Documento entidade) {
		entidade.setNumeroRegistro(entidade.getNumeroRegistro() != null
				&& entidade.getNumeroRegistro().equals("") ? null : entidade
				.getNumeroRegistro());
	}
	
	@Override
	public void alterar(Documento entidade) {
		definirCargaHorariaTotal(entidade);
		ajustarNumeroRegistro(entidade);
		ajustarEnderecamento(entidade);
		entidade.setLocal(localBean.sincronizar(entidade.getLocal()));
		ajustarAnexosAlterar(entidade);
		
		Evento evento = eventoBean.obterPorDocumento(entidade);
		
		if(entidade.getRelacionadoAEvento() != null && entidade.getRelacionadoAEvento() && evento == null){
			entidade.getEvento().setDocumento(entidade);
			eventoBean.incluir(entidade.getEvento());
		}
		
		if(!(entidade.getEnderecamentoDestinatario() instanceof EnderecamentoExterno || entidade.getEnderecamentoDestinatario() instanceof EnderecamentoInterno)){
			entidade.getEnderecamentoDestinatario().getEnderecamentoDestinatarioDocumento().setEnderecamento(entidade.getEnderecamentoDestinatario());
			EnderecamentoDestinatarioDocumento e = entidade.getEnderecamentoDestinatario().getEnderecamentoDestinatarioDocumento();
			e.setDestinatarioDocumento(destinatarioDocumentoBean.obterPorId(DestinatarioDocumento.class, e.getDestinatarioDocumento().getId()));
		}
		
		if(!(entidade.getEnderecamentoRemetente() instanceof EnderecamentoExterno || entidade.getEnderecamentoRemetente() instanceof EnderecamentoInterno)){
			entidade.getEnderecamentoRemetente().getEnderecamentoDestinatarioDocumento().setEnderecamento(entidade.getEnderecamentoRemetente());
			EnderecamentoDestinatarioDocumento e = entidade.getEnderecamentoRemetente().getEnderecamentoDestinatarioDocumento();
			e.setDestinatarioDocumento(destinatarioDocumentoBean.obterPorId(DestinatarioDocumento.class, e.getDestinatarioDocumento().getId()));
		}
		
		super.alterar(entidade);
	}

	public void definirCargaHorariaTotal(Documento entidade) {
		if (entidade.getEvento() == null) {
			return;
		}

		Calendar calendar = Calendar.getInstance();
		Date dataHoraInicio;
		Date dataHoraTermino;
		long time;

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		time = calendar.getTimeInMillis();

		if (entidade.getEvento().getHoraInicio() == null) {

			entidade.getEvento().setHoraInicio(new Date(time));
		}
		if (entidade.getEvento().getHoraTermino() == null) {

			entidade.getEvento().setHoraTermino(new Date(time));
		}
		entidade.getEvento().setHoraInicio(
				DateUtils.converteDateToTime(entidade.getEvento()
						.getHoraInicio()));
		entidade.getEvento().setHoraTermino(
				DateUtils.converteDateToTime(entidade.getEvento()
						.getHoraTermino()));
		dataHoraInicio = new Date(entidade.getEvento().getDataInicioEvento()
				.getTime()
				+ entidade.getEvento().getHoraInicio().getTime());

		dataHoraTermino = new Date(entidade.getEvento().getDataTerminoEvento()
				.getTime()
				+ entidade.getEvento().getHoraTermino().getTime());
		entidade.getEvento()
				.setCargaHorariaTotal(
						DateUtils.calculaDiferencaHora(dataHoraInicio,
								dataHoraTermino));
	}
	
	public Integer listarDocumentoAndamentoCount(
			FiltroDocumentoConsultaAndamentoVO filtro){
		
		return documentoDAO.listarDocumentoCount(filtro);
	}

	public List<Documento> documentosPendentesLocalLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local) {
		return documentoDAO.documentosPendentesLocalLazy(primeiraLinha, maximoPorPagina, local);
	}
	
	public int documentosPendentesLocalLazyCount(Local local) {
		return documentoDAO.documentosPendentesLocalLazyCount(local);
	}
	
	public int totalDocumentosPendentesPorDia(Integer dias, Local local, Usuario usuario, Boolean maior, Boolean atual) {
		return documentoDAO.totalDocumentosPendentesPorDia(dias, local, usuario, maior, atual);
	}
	
	public int totalDocumentosPendentesEntreDias(Integer diaInicial, Integer diaFinal, Local local, Usuario usuario) {
		return documentoDAO.totalDocumentosPendentesEntreDias(diaInicial, diaFinal, local, usuario);
	}
	
	public int totalTarefasPendentesPorDia(Integer dias, Local local, Usuario usuario, Boolean maior, Boolean atual) {
		return documentoDAO.totalTarefasPendentesPorDia(dias, local, usuario, maior, atual);
	}
	
	public int totalTarefasPendentesEntreDias(Integer diaInicial, Integer diaFinal, Local local, Usuario usuario) {
		return documentoDAO.totalTarefasPendentesEntreDias(diaInicial, diaFinal, local, usuario);
	}
	
	public int totalDocumentosSemTarefas(Local local){
		return documentoDAO.totalDocumentosSemTarefas(local);
	}	
}
