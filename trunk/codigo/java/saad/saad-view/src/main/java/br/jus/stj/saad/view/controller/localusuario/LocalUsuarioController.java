package br.jus.stj.saad.view.controller.localusuario;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.triagem.ArquivoUtils;
import br.jus.stj.saad.util.ConstantesSaad;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@SessionScoped
public class LocalUsuarioController extends GenericController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginBean loginBean;
	@EJB
	private UsuarioBean usuarioBean;
		
	@PostConstruct
	public void inicializar() {
		loginBean.setUsuarioLogado(usuarioBean.inicializar(loginBean.getUsuarioLogado(), "locaisUsuarios"));
	}
	
	
	public void comandoEntrar() {
		try {
			verificarExistenciaDaPastaDaUnidade();
			adicionarCookie(ConstantesSaad.dsCookieLocalDeTrabalho, loginBean.getUnidadeUsuario().getId().toString());
			adicionarCookie(ConstantesSaad.dsCookieUsuarioLogado, loginBean.getUsuarioLogado().getId().toString());
			FacesContext.getCurrentInstance().getExternalContext().redirect(obterContextPath() + "/pages/notification/minhasNotificacoes.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }
	
	public void adicionarCookie(String name, String value){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();  
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();  
		
		Cookie cookieAntigo = getCookie(request, name);
		
		if(cookieAntigo != null){
			cookieAntigo.setMaxAge(0);
			cookieAntigo.setValue(null);
			cookieAntigo.setPath("/");
		    response.addCookie(cookieAntigo);
		}
		
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 365);  
		response.addCookie(cookie); 
	}
	
	public void verificarExistenciaDaPastaDaUnidade(){
		String caminhoPastaUnidade = ArquivoUtils.obterEnderecoPastaTriagem() + loginBean.getUnidadeUsuario().getNomePasta();
		System.out.println(caminhoPastaUnidade);
		ArquivoUtils.criarCaminho(new File(caminhoPastaUnidade).toPath());
		System.out.println(caminhoPastaUnidade + " 2 ");
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public void comandoAlterarLocal() {
		comandoEntrar();
	}

}
