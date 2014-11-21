package br.jus.stj.saad.view.controller.documento.andamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.jus.stj.saad.business.AndamentoDocumentoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.exception.IdentificadorInvalidoException;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class ConsultaAlteraAndamentoController extends GenericController {

	private static final long serialVersionUID = 6994881327314526741L;
	
    @Inject
    private LoginBean loginBean;
    @EJB
    private TipoDocumentoBean tipoDocumentoBean;
    @EJB
    private AndamentoDocumentoBean andamentoDocumentoBean;
    
    private List<TipoDocumento> tipoDocumentoList;
    
    private TipoDocumento tipoDocumentoSelecionado;
   
    private String identificadorDoc;
    
    private List<Documento> listaDocumentosPesquisa;
    
    private List<AndamentoDocumento> andamentosDocumentoConsulta;
    
    private Documento documentoAlterar;
    
    private String descNovoAndamentoDoc;
    
    private String descAlterarAndamentoDoc;
    
    private AndamentoDocumento andamentoDocumentoAlterar;
    
    private AndamentoDocumento andamentoDocumentoHistorico;
    
    private List<AndamentoDocumento> andamentosDocumentoAlterarRemover;

    @PostConstruct
    private void initialize() {
    	
    	tipoDocumentoList = tipoDocumentoBean.listarTodosTiposDocumentos();
    	andamentosDocumentoAlterarRemover = new ArrayList<AndamentoDocumento>();
    	andamentoDocumentoAlterar = new AndamentoDocumento();
    	
    }
    
	public void consultarDocumentos() {

		if ((identificadorDoc != null && !identificadorDoc.equals(""))
				&& tipoDocumentoSelecionado != null) {

			adicionarFacesMessage("Preencher apenas um dos campos para pesquisa.", "");

		} else if (identificadorDoc != null && !identificadorDoc.equals("")) {

			try {

				listaDocumentosPesquisa = andamentoDocumentoBean
						.obterDocumentosComAndamentos(identificadorDoc);

			} catch (IdentificadorInvalidoException ex) {

				adicionarFacesMessage("Número identificador inválido.", "");

			}

		} else if (tipoDocumentoSelecionado != null) {

			listaDocumentosPesquisa = andamentoDocumentoBean
					.obterDocumentosComAndamentos(tipoDocumentoSelecionado);

		} else {

			adicionarFacesMessage("Preencha um dos campos para pesquisa.", "");

		}
		
		if(listaDocumentosPesquisa == null) {
			
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Nenhum registro encontrado.");
			
		}
		
		resetarCamposPesquisar();

	}
	
	private void resetarCamposPesquisar() {
		
		identificadorDoc = null;
		tipoDocumentoSelecionado = null;
		
	}
	
	public void manterAndamentoDocumento(Documento documento) {
		
		this.andamentosDocumentoConsulta = documento.getAndamentosDocumento();
		
		this.documentoAlterar = documento;
		
		if(this.andamentosDocumentoConsulta == null) {
			
			this.andamentosDocumentoConsulta = new ArrayList<AndamentoDocumento>();
			
		}
		
	}
	
	public void adicionarAndamentoDocumento() {

		AndamentoDocumento andamentoDocumento = new AndamentoDocumento();
		andamentoDocumento.setAndamentoDocumento(new Date());
		andamentoDocumento.setDataInclusao(new Date());
		andamentoDocumento.setDescricaoAndamentoDocumento(descNovoAndamentoDoc);
		andamentoDocumento.setDocumento(documentoAlterar);
		andamentoDocumento.setUsuario(loginBean.getUsuarioLogado());
		
		if(StringUtils.isEmpty(andamentoDocumento.getDescricaoAndamentoDocumento())){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Campo 'Descrição do Andamento' não pode ficar em branco.", "Campo 'Descrição do Andamento' não pode ficar em branco."));
		}else{
		
			try {
				
				andamentoDocumentoBean.incluir(andamentoDocumento);
				andamentosDocumentoConsulta.add(andamentoDocumento);
	
			} catch (Exception ex) {
				
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erro na inclusão do registro", ex.getMessage()));
				
				ex.printStackTrace();
	
			}
	
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Andamento adicionado com sucesso.", ""));
			
			this.descNovoAndamentoDoc = null;
			
			descNovoAndamentoDoc = null;
		}

	}
	
	public void iniciarAlteracaoAndamento() {

		if (andamentosDocumentoAlterarRemover == null
				|| andamentosDocumentoAlterarRemover.isEmpty()) {

			adicionarFacesMessage("Selecionar um andamento para alteração.", "");
			return;

		} else if (andamentosDocumentoAlterarRemover != null
				&& andamentosDocumentoAlterarRemover.size() > 1) {

			adicionarFacesMessage("Selecionar apenas um andamento para alteração", "");
			return;

		}

		andamentoDocumentoAlterar = andamentosDocumentoAlterarRemover.get(0);

		RequestContext.getCurrentInstance().execute(
				"PF('dlgAlteraAndamento').show();");

	}
	
	public void finalizarAlteracaoAndamento() {
		
		andamentoDocumentoAlterar.setUsuario(loginBean.getUsuarioLogado());

		if(StringUtils.isEmpty(andamentoDocumentoAlterar.getDescricaoAndamentoDocumento())){
			andamentoDocumentoAlterar.setDescricaoAndamentoDocumento(andamentoDocumentoBean.inicializar(andamentoDocumentoAlterar, "usuario").getDescricaoAndamentoDocumento());
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Campo 'Descrição do Andamento' não pode ficar em branco.", "Campo 'Descrição do Andamento' não pode ficar em branco."));
	
		}else{
			andamentoDocumentoBean.alterar(andamentoDocumentoAlterar,
					andamentoDocumentoHistorico);

			adicionarFacesMessage("Andamento alterado com sucesso.", "");

			andamentoDocumentoAlterar = new AndamentoDocumento();
			andamentoDocumentoHistorico = null;
		}
	}
	

	public void removerAndamentosSelecionados() {

		if (andamentosDocumentoAlterarRemover == null
				|| andamentosDocumentoAlterarRemover.isEmpty()) {

			adicionarFacesMessage("Selecione pelo menos um andamento para ser removido", "");
			return;

		}
		
		try {

			andamentoDocumentoBean.excluir(andamentosDocumentoAlterarRemover);
			adicionarFacesMessage("Andamentos removidos com sucesso.", "");
			andamentosDocumentoConsulta.removeAll(andamentosDocumentoAlterarRemover);
			
			andamentosDocumentoAlterarRemover = null;

		} catch (Exception ex) {

			adicionarFacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro na exclusão.", ex.getMessage());

			ex.printStackTrace();

		}

	}
	
	public boolean isPossuiResultadoPesquisa() {

		return listaDocumentosPesquisa != null
				&& !listaDocumentosPesquisa.isEmpty();

	}
	
	public String obterFormatoIdentificador(Documento documento) {
		if(documento != null){
			String sigla = documento.getTipo().getSigla();
			String identificacaoDoc = documento.getNumeroControleIdentificador();
			Date dataInclusaoDoc = documento.getDataInclusao();
	
			return IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla,
					identificacaoDoc, dataInclusaoDoc);
		}else{
			return null;
		}

	}
	
	public static SituacaoDocumentoEnum obterSituacaoDocumentoEnumPorCharacter(
			Character character) {
		
		SituacaoDocumentoEnum[] situacoes = SituacaoDocumentoEnum.values();
		
		for (SituacaoDocumentoEnum situacao : situacoes) {
			
			if (situacao.getValor().equals(character)) {
				
				return situacao;
				
			}
			
		}
		
		return null;
		
	}
    
	public List<TipoDocumento> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

	public TipoDocumento getTipoDocumentoSelecionado() {
		return tipoDocumentoSelecionado;
	}

	public void setTipoDocumentoSelecionado(TipoDocumento tipoDocumento) {
		this.tipoDocumentoSelecionado = tipoDocumento;
	}

	public String getIdentificadorDoc() {
		return identificadorDoc;
	}

	public void setIdentificadorDoc(String identificadorDoc) {
		this.identificadorDoc = identificadorDoc;
	}

	public List<Documento> getListaDocumentosPesquisa() {
		return listaDocumentosPesquisa;
	}

	public void setListaDocumentosPesquisa(List<Documento> listaDocumentosPesquisa) {
		this.listaDocumentosPesquisa = listaDocumentosPesquisa;
	}
	public Documento getDocumentoAlterar() {
		return documentoAlterar;
	}

	public void setDocumentoAlterar(Documento documentoAlterar) {
		this.documentoAlterar = documentoAlterar;
	}
	public List<AndamentoDocumento> getAndamentosDocumentoConsulta() {
		return andamentosDocumentoConsulta;
	}

	public void setAndamentosDocumentoConsulta(List<AndamentoDocumento> andamentosDocumentoConsulta) {
		this.andamentosDocumentoConsulta = andamentosDocumentoConsulta;
	}
	public String getDescNovoAndamentoDoc() {
		return descNovoAndamentoDoc;
	}

	public void setDescNovoAndamentoDoc(String descNovoAndamentoDoc) {
		this.descNovoAndamentoDoc = descNovoAndamentoDoc;
	}
	public List<AndamentoDocumento> getAndamentosDocumentoAlterarRemover() {
		return andamentosDocumentoAlterarRemover;
	}

	public void setAndamentosDocumentoAlterarRemover(
			List<AndamentoDocumento> andamentosDocumentoAlterarRemover) {
		this.andamentosDocumentoAlterarRemover = andamentosDocumentoAlterarRemover;
	}

	public String getDescAlterarAndamentoDoc() {
		return descAlterarAndamentoDoc;
	}

	public void setDescAlterarAndamentoDoc(String descAlterarAndamentoDoc) {
		this.descAlterarAndamentoDoc = descAlterarAndamentoDoc;
	}

	public AndamentoDocumento getAndamentoDocumentoAlterar() {
		return andamentoDocumentoAlterar;
	}

	public void setAndamentoDocumentoAlterar(
			AndamentoDocumento andamentoDocumentoAlterar) {
		this.andamentoDocumentoAlterar = andamentoDocumentoAlterar;
	}
	
	
}
