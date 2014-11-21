package br.jus.stj.saad.view.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.login.LoginBean;

@WebFilter("/pages/*")
public class UnidadeFilter implements Filter {

	@Inject
	private LoginBean loginBean;
	@EJB
	private UsuarioBean usuarioBean;
 	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (loginBean.getUnidadeUsuario() != null) {
			arg2.doFilter(request, response);
			return;
		}
		Usuario usuarioLogado = obterUsuarioLogadoComListaDeUnidades();
		if(usuarioLogado == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			invalidarSessao(req);
			res.sendRedirect(req.getContextPath() + "/erroUsuarioNaoEncontrado.xhtml");
			return;
		}
		loginBean.setUsuarioLogado(usuarioLogado);
		if(usuarioLogado.getLocaisUsuarios() == null || usuarioLogado.getLocaisUsuarios().size() == 0) {
			HttpServletResponse res = (HttpServletResponse) response;
			invalidarSessao(req);
			res.sendRedirect(req.getContextPath() + "/erroUnidade.xhtml");
		} else if (usuarioLogado.getLocaisUsuarios().size() > 1) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/definirUnidade.xhtml");
		} else {
			loginBean.setUnidadeUsuario(usuarioLogado.getLocaisUsuarios()
					.get(0).getLocal());
			arg2.doFilter(request, response);
		}
	}

	private void invalidarSessao(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
	}

	private Usuario obterUsuarioLogadoComListaDeUnidades() {
		Usuario usuario = usuarioBean.obterUsuarioLogado();
		if(usuario == null) {
			return null;
		}
		Usuario usuarioLogado = usuarioBean.inicializar(
				usuario, "locaisUsuarios");
		return usuarioLogado;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
