package br.jus.stj.saad.view.controller.aviso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class DetalhaAvisoController extends GenericController {

	private static final long serialVersionUID = -4660995245052548930L;

	private Aviso avisoDetalhar;
	
	private Aviso avisoRequest;
	
	private List<Aviso> avisoResultadoRequest;
	
	private String paginaPai;

	private Object c;

	@PostConstruct
	private void init() {
		avisoRequest = (Aviso) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoRequest");
		
		avisoResultadoRequest = (List<Aviso>) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoResultadoRequest");
		
		paginaPai = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("paginaPai");
		
		c = obterObjetoRequestMap(ConsultarAvisoController.class);

		avisoDetalhar = (Aviso) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoSelecionado");

	}
	
	public String voltar(){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("avisoRequest", avisoRequest);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("avisoResultadoRequest", avisoResultadoRequest);

		return paginaPai;
	}

	public Aviso getAvisoDetalhar() {
		return avisoDetalhar;
	}

	public void setAvisoDetalhar(Aviso avisoDetalhar) {
		this.avisoDetalhar = avisoDetalhar;
	}

	public String getRelacionadoDocumento() {

		if (avisoDetalhar.getDocumento() == null)
			return "Não";
		return "Sim";

	}

	public SituacaoAvisoEnum getEnumAviso() {

		return SituacaoAvisoEnum.getEnumPorChave(avisoDetalhar.getSituacao());

	}

	public String alterarAviso() {

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("avisoSelecionado", avisoDetalhar);
		return "/pages/aviso/alterarAviso";

	}

	public boolean isEnderecamentoEspecifico() {

		if (avisoDetalhar.getTipoEnderecamento().equals(
				TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO.getChave()))
			return true;
		return false;

	}

	public String getNomeTipoEnderecamento(Character character) {

		for (TipoEnderecamentoAvisoEnum tipoEnderecamentoAvisoEnum : TipoEnderecamentoAvisoEnum
				.values()) {

			if (character.equals(tipoEnderecamentoAvisoEnum.getChave().equals(
					character))) {

				return tipoEnderecamentoAvisoEnum.getDescricao();

			}

		}

		return "NÃO DEFINIDO";

	}
}
