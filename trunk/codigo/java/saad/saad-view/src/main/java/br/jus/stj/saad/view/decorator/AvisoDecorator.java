package br.jus.stj.saad.view.decorator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean(name = "avisoDecorator")
@ViewScoped
public class AvisoDecorator extends GenericController{

	private static final long serialVersionUID = 4413845464007746842L;
	
	@EJB
	private UsuarioBean usuarioBean;
	private Usuario usuarioLogado;

	public String getNotificationStyleClass(Aviso aviso) {
		if (aviso == null) {
			return "to_undefined";
		}

		if (TipoEnderecamentoAvisoEnum.TODOS.getChave().equals(
				aviso.getTipoEnderecamento())) {
			return "to_all";
		}
		
		if(aviso.getDestinatariosAviso().size() > 1){
			return "to_many_without_me";
		}else{
			return "to_many_with_me";
		}
	}

	private Usuario obterUsuarioLogado() {

		if (usuarioLogado == null) {

			usuarioLogado = usuarioBean.obterUsuarioLogado();

		}

		return usuarioLogado;

	}

}
