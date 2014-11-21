package br.jus.stj.saad.view.controller.aviso;

import java.text.SimpleDateFormat;
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

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import br.jus.stj.saad.business.AvisoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.RelacionadoDocumentoEnum;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.view.controller.lazy.AvisoLazyList;
import br.jus.stj.saad.vo.FiltroConsultaAvisoVO;


@ViewScoped
@ManagedBean
public class ConsultarAvisoControllerOld extends GenericController {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private AvisoBean avisoServico;
	
	@EJB
	private UsuarioBean usuarioBean;
	
	@EJB
	private DocumentoBean documentoBean;
	
	@Inject
	private LoginBean loginBean;
	
	private Aviso aviso;
		
	private TipoEnderecamentoAvisoEnum tipoEnderecamentoAviso;
	
	private SituacaoAvisoEnum situacaoAvisoEnum;
	
	private RelacionadoDocumentoEnum relacionadoDocumentoEnum;
	
	private List<TipoEnderecamentoAvisoEnum> listTipoEnderecamentoAviso;
	
	private SituacaoAvisoEnum[] listSituacaoAvisoEnum;
	
	private List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum;
	
	private List<TipoDocumento> listTipoDocumento;
	
	private TipoDocumento tipoDocumento;
	
	private Aviso avisoSelecionado;
	
	private LazyDataModel<Aviso> listaAviso;

	private Documento documento;
	
	private Boolean destinatarioEspecificioSelecionado;
	
	private Boolean documentoRelacionadoSelecionado;
	
	private List<Usuario> listDestinatario;
	
	private Usuario destinatario;
	
	private FacesMessage message;
	
	private Date dataDeInclusao;
	
	private Date dataDeSolucao;
	
	private String numeroDocumento;
	
	private Boolean tableView;
	
	private String identificadorDocumento;
	
	private final FiltroConsultaAvisoVO filtro;
	
	public ConsultarAvisoControllerOld() {
		filtro = new FiltroConsultaAvisoVO();
	}
	
	@PostConstruct
	public void init() {
		setNumeroDocumento(null);
		setTipoDocumento(new TipoDocumento());
		setDestinatarioEspecificioSelecionado(false);
		setDocumentoRelacionadoSelecionado(false);
		setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum.N);
		setListDestinatario(new ArrayList<Usuario>());
		setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum.TODOS);
		setListTipoEnderecamentoAviso(gerarListaTipoEnderecamentoAviso());
		setListSituacaoAvisoEnum(gerarListaSituacaoAvisoEnum());
		setListRelacionadoDocumentoEnum(gerarListaRelacionadoDocumentoEnum());
		setAviso(new Aviso());
		getAviso().setTipoEnderecamento(
				TipoEnderecamentoAvisoEnum.TODOS.getChave());
		setDocumento(new Documento());
		setListTipoDocumento(avisoServico.listarTodosTiposDocumentos());
		setTableView(false);
		filtro.setAviso(getAviso());
		setListaAviso(new AvisoLazyList( filtro, avisoServico));
		
		
	}
	
	public void salvar(){
		
	}
	
	public void limpar(){
		init();
	}
	
	public List<TipoEnderecamentoAvisoEnum> gerarListaTipoEnderecamentoAviso(){
		List<TipoEnderecamentoAvisoEnum> listTipoEnderecamentoAviso = 
				new ArrayList<TipoEnderecamentoAvisoEnum>();
		listTipoEnderecamentoAviso.add(TipoEnderecamentoAvisoEnum.TODOS);
		listTipoEnderecamentoAviso.add(
				TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO);
		return listTipoEnderecamentoAviso;
	}
	
	public SituacaoAvisoEnum[] gerarListaSituacaoAvisoEnum(){
		return SituacaoAvisoEnum.values();
	}
	
	public List<RelacionadoDocumentoEnum> gerarListaRelacionadoDocumentoEnum(){
		List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum 
		= new ArrayList<RelacionadoDocumentoEnum>();
		listRelacionadoDocumentoEnum.add(RelacionadoDocumentoEnum.S);
		listRelacionadoDocumentoEnum.add(RelacionadoDocumentoEnum.N);
		return listRelacionadoDocumentoEnum;
	}
	
	public List<Usuario> completeUsuarios(String query) {
		List<Local> listLocal = new ArrayList<Local>();
		listLocal.add(loginBean.getUnidadeUsuario());
		
		return usuarioBean.listarUsuariosPorNomeUnidade(query, listLocal);
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected"
        				, format.format(event.getObject())));
    }
	
	public void adicionarDestinatario(){
		if(destinatario != null && !listDestinatario.contains(destinatario)){
			listDestinatario.add(destinatario);
		}else{
			this.message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Endereçado já adicionado ou não existe."
					,"Não é possível adicionar o mesmo destinatário mais de uma vez.");

			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		destinatario = new Usuario();
	}
	
	public void removerDestinatario(Usuario destinatario){
		if(listDestinatario.contains(destinatario)){
			listDestinatario.remove(destinatario);
		}
	}
	
	public TipoEnderecamentoAvisoEnum getTipoEnderecamentoAviso() {
		return tipoEnderecamentoAviso;
	}

	public void setTipoEnderecamentoAviso(
			TipoEnderecamentoAvisoEnum tipoEnderecamentoAviso) {
		this.tipoEnderecamentoAviso = tipoEnderecamentoAviso;
		if(getAviso() != null){
			getAviso().setTipoEnderecamento(tipoEnderecamentoAviso.getChave());
		
			if(tipoEnderecamentoAviso.equals(
					TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO)){
				setDestinatarioEspecificioSelecionado(true);
			}else{
				setDestinatarioEspecificioSelecionado(false);
			}
		}
	}
	
	public void comandoConsultar() {

		
		getAviso().setDataInclusao(dataDeInclusao);
		getAviso().setDataResolucao(dataDeSolucao);
		getAviso().setDocumento(new Documento());
		filtro.setIdentificadorDocumento(getIdentificadorDocumento());
		getAviso().getDocumento().setTipo(getTipoDocumento());
		
		
		if(situacaoAvisoEnum != null)
			getAviso().setSituacao(situacaoAvisoEnum.getValor());
		
		filtro.setAviso(aviso);
		filtro.setListDestinatario(listDestinatario);
		

		if (tipoEnderecamentoAviso
				.equals(TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO)
				&& listDestinatario.size() == 0) {

			this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione \"Todos\"  ou adicione um ou mais endereçados",
						null);

			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {

			setTableView(true);

		}
	}
	
	public void comandoLimpar() {
		setNumeroDocumento(null);
		setTipoDocumento(new TipoDocumento());
		setDestinatarioEspecificioSelecionado(false);
		setDocumentoRelacionadoSelecionado(false);
		setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum.N);
		setAviso(new Aviso());
		getAviso().setTipoEnderecamento(
				TipoEnderecamentoAvisoEnum.TODOS.getChave());
		setDocumento(new Documento());
		setTableView(false);
		setDataDeInclusao(null);
		setDataDeSolucao(null);
		setNumeroDocumento(null);
		listDestinatario = null;
		listaAviso = null;
		setListDestinatario(new ArrayList<Usuario>());
		setListaAviso(new AvisoLazyList( filtro, avisoServico));
		setSituacaoAvisoEnum(null);
		setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum.TODOS);
		
	}
	
	public void setRelacionadoDocumentoEnum(
			RelacionadoDocumentoEnum relacionadoDocumentoEnum) {
		this.relacionadoDocumentoEnum = relacionadoDocumentoEnum;
		
		if(relacionadoDocumentoEnum.equals(RelacionadoDocumentoEnum.S)){
			setDocumentoRelacionadoSelecionado(true);
		}else{
			setDocumentoRelacionadoSelecionado(false);
		}
	}
	
	
	public String comandoAlterar() {
		if(avisoSelecionado != null){
			FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().put("avisoSelecionado", avisoSelecionado);
			return "alterarAviso";
		}else{
			this.message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Selecione um Registro.", "Não é possível alterar.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "";
	}

	public void comandoExcluir() {
		
		
		if(avisoSelecionado != null){
			
			avisoServico.excluir(avisoSelecionado);
		/*	listaAviso.remove(avisoSelecionado);*/
			
			this.message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Aviso", "excluído com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			this.message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Selecione um Registro.", "Não é possível excluir.");

			FacesContext.getCurrentInstance().addMessage(null, message);
			
		}
		
	}
	
	public String obterFormatoIdentificador(Documento documento) {
		
		if(documento != null){
		String sigla = documento.getTipo().getSigla();
		String identificacaoDoc = documento.getNumeroControleIdentificador();
		Date dataInclusaoDoc = documento.getDataInclusao();

		return IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla,
				identificacaoDoc, dataInclusaoDoc);
		}
		return "";
	}
	
	
	 public SituacaoAvisoEnum obterSituacaoAviso(Character situacaoAviso) {
			return SituacaoAvisoEnum
				.obterSituacaoAvisoEnumPorCharacter(situacaoAviso);
		    }
	public void comandoImprimir() {

	}
	public void consultaAvisoPorUsuarios(){
		
	}
	public List<TipoEnderecamentoAvisoEnum> getListTipoEnderecamentoAviso() {
		return listTipoEnderecamentoAviso;
	}

	public void setListTipoEnderecamentoAviso(List<TipoEnderecamentoAvisoEnum> 
										listTipoEnderecamentoAviso) {
		this.listTipoEnderecamentoAviso = listTipoEnderecamentoAviso;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Boolean getDestinatarioEspecificioSelecionado() {
		return destinatarioEspecificioSelecionado;
	}

	public void setDestinatarioEspecificioSelecionado(
			Boolean destinatarioEspecificioSelecionado) {
		this.destinatarioEspecificioSelecionado =
				destinatarioEspecificioSelecionado;
	}

	public List<Usuario> getListDestinatario() {
		return listDestinatario;
	}

	public void setListDestinatario(List<Usuario> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}

	public Aviso getAvisoSelecionado() {
		return avisoSelecionado;
	}

	public void setAvisoSelecionado(Aviso avisoSelecionado) {
		this.avisoSelecionado = avisoSelecionado;
	}

	public LazyDataModel<Aviso> getListaAviso() {
		return listaAviso;
	}

	public void setListaAviso(LazyDataModel<Aviso> listAviso) {
		this.listaAviso = listAviso;
	}
	
	public AvisoBean getAvisoServico() {
		return avisoServico;
	}

	public void setAvisoServico(AvisoBean avisoServico) {
		this.avisoServico = avisoServico;
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public SituacaoAvisoEnum getSituacaoAvisoEnum() {
		return situacaoAvisoEnum;
	}

	public void setSituacaoAvisoEnum(SituacaoAvisoEnum situacaoAvisoEnum) {
		this.situacaoAvisoEnum = situacaoAvisoEnum;
	}

	public RelacionadoDocumentoEnum getRelacionadoDocumentoEnum() {
		return relacionadoDocumentoEnum;
	}

	public SituacaoAvisoEnum[] getListSituacaoAvisoEnum() {
		return listSituacaoAvisoEnum;
	}

	public void setListSituacaoAvisoEnum(SituacaoAvisoEnum[] situacaoAvisoEnums) {
		this.listSituacaoAvisoEnum = situacaoAvisoEnums;
	}

	public List<RelacionadoDocumentoEnum> getListRelacionadoDocumentoEnum() {
		return listRelacionadoDocumentoEnum;
	}

	public void setListRelacionadoDocumentoEnum(
			List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum) {
		this.listRelacionadoDocumentoEnum = listRelacionadoDocumentoEnum;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Boolean getDocumentoRelacionadoSelecionado() {
		return documentoRelacionadoSelecionado;
	}

	public void setDocumentoRelacionadoSelecionado(
			Boolean documentoRelacionadoSelecionado) {
		this.documentoRelacionadoSelecionado = documentoRelacionadoSelecionado;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public Date getDataDeInclusao() {
		return dataDeInclusao;
	}

	public void setDataDeInclusao(Date dataDeInclusao) {
		this.dataDeInclusao = dataDeInclusao;
	}

	public Date getDataDeSolucao() {
		return dataDeSolucao;
	}

	public void setDataDeSolucao(Date dataDeSolucao) {
		this.dataDeSolucao = dataDeSolucao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Boolean getTableView() {
		return tableView;
	}

	public void setTableView(Boolean tableView) {
		this.tableView = tableView;
	}

	public String getIdentificadorDocumento() {
		return identificadorDocumento;
	}

	public void setIdentificadorDocumento(String identificadorDocumento) {
		this.identificadorDocumento = identificadorDocumento;
	}
}
