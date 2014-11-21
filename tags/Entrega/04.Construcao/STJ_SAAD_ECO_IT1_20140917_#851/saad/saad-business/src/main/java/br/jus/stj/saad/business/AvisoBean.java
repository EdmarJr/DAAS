package br.jus.stj.saad.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.persistence.AvisoDAO;
import br.jus.stj.saad.persistence.TipoDocumentoDAO;
import br.jus.stj.saad.vo.FiltroConsultaAvisoVO;

@Stateless
public class AvisoBean extends Bean<Aviso> {
	
	@Resource
	private SessionContext sessionContext;
	
	@Inject
	private AvisoDAO avisoDAO;
	@EJB
	private UsuarioBean usuarioBean;

	@EJB
	private DestinatarioAvisoBean destinatarioAvisoBean;

	@Inject
	private TipoDocumentoDAO tipoDocumentoDAO;

	@PostConstruct
	private void criarAvisoServico() {

	}
	
	public List<Aviso> buscarAviso(Aviso aviso) {
		return avisoDAO.buscarAviso(aviso);
	}
	
	public List<TipoDocumento> listarTodosTiposDocumentos() {
		return tipoDocumentoDAO.list();
	}

	public List<Aviso> obterAvisosPorUsuario(Usuario usuario, Local local) {
		return avisoDAO.obterAvisosPorUsuario(usuario, local);
	}

	public List<Aviso> obterAvisosDoUsuarioLogado(Usuario usuario, Local local) {
		return obterAvisosPorUsuario(usuario, local);
	}

	@Override
	public void incluir(Aviso entidade) {
		for (DestinatarioAviso d : entidade.getDestinatariosAviso()) {
			d.setUsuario(usuarioBean.obterPorId(d.getUsuario().getId()));
		}

		List<DestinatarioAviso> list = entidade.getDestinatariosAviso();

		for (DestinatarioAviso destinatarioAviso : list) {
			if (destinatarioAviso.ativo) {
				destinatarioAvisoBean.incluir(destinatarioAviso);
			}
		}

		avisoDAO.incluir(entidade);
	}

	@Override
	public void alterar(Aviso entidade) {
		List<DestinatarioAviso> list = entidade.getDestinatariosAviso();

		for (DestinatarioAviso destinatarioAviso : list) {
			destinatarioAviso.setUsuario(usuarioBean
					.obterPorId(destinatarioAviso.getUsuario().getId()));
			destinatarioAviso.setAviso(obterPorId(entidade.getId()));
			if (destinatarioAviso.ativo) {
				destinatarioAvisoBean.incluir(destinatarioAviso);
			}
			if (destinatarioAviso.removido) {
				destinatarioAvisoBean.excluir(destinatarioAviso);
			}
		}

		avisoDAO.atualizar(entidade);
	}

	public List<Aviso> obterAvisosPorUnidade(Local local) {
		return getDao().obterAvisosPorUnidade(local);
	}

	public Aviso obterPorId(Long idAviso) {
		return getDao().obterPorId(Aviso.class, idAviso);
	}

	public List<Aviso> obterAvisosPorUnidade() {

		Usuario usuario = usuarioBean.obterUsuarioLogado();

		usuario = usuarioBean.inicializar(usuario, "locaisUsuarios");

		if (usuario != null && usuario.getLocaisUsuarios() != null
				&& !usuario.getLocaisUsuarios().isEmpty()) {

			return getDao().obterAvisosPorUnidade(
					usuario.getLocaisUsuarios().get(0).getLocal());

		} else {

			return getDao().obterAvisosPorUnidade(null);

		}

	}

	@Override
	public AvisoDAO getDao() {
		return avisoDAO;
	}

	public List<Aviso> consultaAvisoPorUsuarios(Integer primeiroRegistro,
			Integer quantidadeDeRegistros, FiltroConsultaAvisoVO avisoVO) {

		String identificador = avisoVO.getIdentificadorDocumento();
		if (identificador != null && !"".equalsIgnoreCase(identificador)) {
			identificador = identificador.toUpperCase();

			avisoVO.setSigla(IdentificadorUtils.obterSigla(identificador));
			avisoVO.setIdentificacaoDocumentoSTJ(IdentificadorUtils
					.obterIdentificacaoDocumentoSTJ(identificador));
			avisoVO.setAno(IdentificadorUtils.obterAno(identificador));

		}

		return getDao().consultaAvisoPorUsuarios(primeiroRegistro, quantidadeDeRegistros, avisoVO);

	}
	
	public Integer consultaAvisoPorUsuariosCount(FiltroConsultaAvisoVO vo) {
		return avisoDAO.consultaAvisoPorUsuariosCount(vo);
	}
	
	public List<Aviso> avisosUnidadeUsuarioLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local, Usuario usuario) {
		return avisoDAO.avisosUnidadeUsuarioLazy(primeiraLinha, maximoPorPagina, local, usuario);
	}
	
	public int avisosUnidadeUsuarioLazyCount(Local local, Usuario usuario) {
		return avisoDAO.avisosUnidadeUsuarioLazyCount(local, usuario);
	}
	
	public int totalAvisosUnidadeUsuarioPendentes(Local local, Usuario usuario) {
		return avisoDAO.totalAvisosUnidadeUsuarioPendentes(local, usuario);
	}

}
