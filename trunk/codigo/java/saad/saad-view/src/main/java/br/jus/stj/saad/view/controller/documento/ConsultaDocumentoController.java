package br.jus.stj.saad.view.controller.documento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.TarefaDemandaDocumentoBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.view.controller.lazy.LazyDocumentoPendenteDataModel;
import br.jus.stj.saad.view.controller.lazy.LazyDocumentoTarefaPendenteDataModel;

@ManagedBean
@ViewScoped
public class ConsultaDocumentoController extends GenericController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String red = "#FF0000";
	private static final String black = "#000000";
	private static final String lawnGreen = "#7CFC00";

    @EJB
    private DocumentoBean documentoBean;
    
    @EJB
    private TarefaDemandaDocumentoBean tarefaDemandaDocumentoBean;
    
    @Inject
    private LoginBean loginBean;
    
    private List<Documento> documentosPendente;
    private List<Documento> documentoTarefasPendente;

    private LazyDataModel<Documento> lazyDocumentoPendenteDataModel;
    private LazyDataModel<TarefaDemandaDocumento> lazyDocumentoTarefaPendenteDataModel;
    
    private Integer totalDocumentosTresDias;
    private Integer totalDocumentosSeisDias;
    private Integer totalDocumentosMaisDeSeisDias;
    private Integer totalDocumentosUsuarioTresDias;
    private Integer totalDocumentosUsuarioSeisDias;
    private Integer totalDocumentosUsuarioDeSeisDias;
    
    @PostConstruct
    private void initialize() {
    	lazyDocumentoPendenteDataModel = new LazyDocumentoPendenteDataModel(documentoBean, loginBean);
    	lazyDocumentoTarefaPendenteDataModel = new LazyDocumentoTarefaPendenteDataModel(documentoBean, tarefaDemandaDocumentoBean, loginBean);
    	setTotalDocumentosTresDias(documentoBean.totalDocumentosPendentesPorDia(3, loginBean.getUnidadeUsuario(), null, false, false));
    	setTotalDocumentosSeisDias(documentoBean.totalDocumentosPendentesEntreDias(3, 6, loginBean.getUnidadeUsuario(), null));
    	setTotalDocumentosMaisDeSeisDias(documentoBean.totalDocumentosPendentesPorDia(6, loginBean.getUnidadeUsuario(), null, true, true) + documentoBean.totalDocumentosSemTarefas(loginBean.getUnidadeUsuario()));
    	setTotalDocumentosUsuarioTresDias(documentoBean.totalTarefasPendentesPorDia(3, loginBean.getUnidadeUsuario(), loginBean.getUsuarioLogado(), false, false));
    	setTotalDocumentosUsuarioSeisDias(documentoBean.totalTarefasPendentesEntreDias(3, 6, loginBean.getUnidadeUsuario(), loginBean.getUsuarioLogado()));
    	setTotalDocumentosUsuarioDeSeisDias(documentoBean.totalDocumentosPendentesPorDia(6, loginBean.getUnidadeUsuario(), loginBean.getUsuarioLogado(), true, true));
    }

	public List<Documento> getTarefasDocumentoPendenteUsuario() {
		
		if(documentoTarefasPendente == null) {
			
			documentoTarefasPendente = documentoBean.obterTarefasDocumentoPendente();
			
		}
		
		return documentoTarefasPendente;

	}

	public List<Documento> getDocumentosPendenteLocal() {
		
		if(documentosPendente == null) {
			
			documentosPendente = documentoBean.obterDocumentosPendentesLocal(loginBean.getUnidadeUsuario());
			
		}
		
		return documentosPendente;

	}
	
	public String getStringNumTarefas(Documento documento) {
		
		return documento.getTarefas().size() > 1 ? "*" : "";
		
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
	
	public String obterFormatoIdentificador(Documento documento) {

		String sigla = documento.getTipo().getSigla();
		String identificacaoDoc = documento.getNumeroControleIdentificador();
		Date dataInclusaoDoc = documento.getDataInclusao();

		return IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla, identificacaoDoc,
				dataInclusaoDoc);

	}
	
	public Date buscarDataDaUltimaTarefa(Documento documento){
		
		List<TarefaDemandaDocumento> lista = documento.getTarefas();
		
		Date date = null;
		
		for (TarefaDemandaDocumento tarefaDemandaDocumento : lista) {
			
			if(date == null){
				
				date = tarefaDemandaDocumento.getDataLimiteConclusao();
			
			} else {
			
				if(tarefaDemandaDocumento.getDataLimiteConclusao().after(date)){
					date = tarefaDemandaDocumento.getDataLimiteConclusao();
				}
				
			}
			
		}
		
		return date;		
	}
	
	public String getCorAvisoDias(Documento documento) {
		
		if (documento == null)
			return "";
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(dataAtual);
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
        
        dataAtual = cal.getTime();
		
		Date ultimaDataTarefa = buscarDataDaUltimaTarefa(documento);
		
		if(ultimaDataTarefa != null){
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(ultimaDataTarefa);
	        cal1.set(Calendar.HOUR_OF_DAY, 0);  
	        cal1.set(Calendar.MINUTE, 0);  
	        cal1.set(Calendar.SECOND, 0);  
	        cal1.set(Calendar.MILLISECOND, 0);

	        ultimaDataTarefa = cal1.getTime();
		}
        
		if(ultimaDataTarefa == null || dataAtual == null) {
			return "";
		}
		
		long diferencaEmDatas = ultimaDataTarefa.getTime() - dataAtual.getTime();
		
		int diferencaEmDias = obterDataEmDias(diferencaEmDatas);
		
		if(diferencaEmDias <= 3) {
			
			return red;
			
		} else if (diferencaEmDias > 3 && diferencaEmDias <= 6 ){
			
			return lawnGreen;
			
		} else {
			
			return black;
			
		}
		
		
	}
	
	public String getCorTarefa(TarefaDemandaDocumento tarefaDemandaDocumento) {
		if (tarefaDemandaDocumento == null)
			return "";
		
		Date dataAtual = new Date(System.currentTimeMillis());

		Date ultimaDataTarefa = tarefaDemandaDocumento.getDataLimiteConclusao();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(dataAtual);
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
        
        dataAtual = cal.getTime();
		
		if(ultimaDataTarefa != null){
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(ultimaDataTarefa);
	        cal1.set(Calendar.HOUR_OF_DAY, 0);  
	        cal1.set(Calendar.MINUTE, 0);  
	        cal1.set(Calendar.SECOND, 0);  
	        cal1.set(Calendar.MILLISECOND, 0);

	        ultimaDataTarefa = cal1.getTime();
		}
		
		if(ultimaDataTarefa == null || dataAtual == null) {
			return "";
		}
		
		long diferencaEmDatas = ultimaDataTarefa.getTime() - dataAtual.getTime();
		
		int diferencaEmDias = obterDataEmDias(diferencaEmDatas);
		
		if(diferencaEmDias <= 3) {
			
			return red;
			
		} else if (diferencaEmDias > 3 && diferencaEmDias <= 6 ){
			
			return lawnGreen;
			
		} else {
			
			return black;
			
		}
		
		
	}
	
	public Integer getTotalDocUsuarioDiasRestantesUltimaTarefa(
			int quantidadeDiasRestantes, int restricaoMaiorQue) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		Date ultimaDataTarefa = null;
		long diferencaEmMillis = 0;

		int contador = 0;
		int diferencaEmdias = 0;

		for (Documento documento : documentoTarefasPendente) {
			
			ultimaDataTarefa = buscarDataDaUltimaTarefa(documento);
			
			if(ultimaDataTarefa == null)
				continue;
			
			diferencaEmMillis = ultimaDataTarefa.getTime() - dataAtual.getTime();
			
			diferencaEmdias = obterDataEmDias(diferencaEmMillis);
			
			if (diferencaEmdias <= quantidadeDiasRestantes
					&& diferencaEmdias >= restricaoMaiorQue) {
				
				contador++;
			
			}

		}

		return contador;

	}
	
	public Integer getTotalDocDiasRestantesUltimaTarefa(
			int quantidadeDiasRestantes, int restricaoMaiorQue) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		Date ultimaDataTarefa = null;
		long diferencaEmMillis = 0;

		int contador = 0;
		int diferencaEmdias = 0;

		for (Documento documento : documentosPendente) {
			
			ultimaDataTarefa = buscarDataDaUltimaTarefa(documento);
			
			if(ultimaDataTarefa == null)
				continue;
			
			diferencaEmMillis = ultimaDataTarefa.getTime() - dataAtual.getTime();
			
			diferencaEmdias = obterDataEmDias(diferencaEmMillis);
			
			if (diferencaEmdias <= quantidadeDiasRestantes
					&& diferencaEmdias >= restricaoMaiorQue) {
				
				contador++;
			
			}

		}

		return contador;

	}
	
	public Integer getTotalDocumentosPendentes(){
		
		return documentosPendente.size();
		
	}
	
	private int obterDataEmDias(long millis) {
		
		return (int) (millis / (24 * 60 * 60 * 1000));
		
	}
	
	public String detalharDocumento(Documento documentoSelecionado) {
		documentoSelecionado = documentoBean.inicializar(documentoSelecionado, "anexos");
		incluirObjetoRequestMap("documentoSelecionado", documentoSelecionado);
		incluirObjetoRequestMap("paginaPai", "/pages/notification/minhasNotificacoes.xhtml");

		return "/pages/documento/visualizarDocumento.xhtml";

	}
	
	public LazyDataModel<Documento> getLazyDocumentoPendenteDataModel() {
		return lazyDocumentoPendenteDataModel;
	}

	public void setLazyDocumentoPendenteDataModel(
			LazyDataModel<Documento> lazyDocumentoPendenteDataModel) {
		this.lazyDocumentoPendenteDataModel = lazyDocumentoPendenteDataModel;
	}

	public LazyDataModel<TarefaDemandaDocumento> getLazyDocumentoTarefaPendenteDataModel() {
		return lazyDocumentoTarefaPendenteDataModel;
	}

	public void setLazyDocumentoTarefaPendenteDataModel(
			LazyDataModel<TarefaDemandaDocumento> lazyDocumentoTarefaPendenteDataModel) {
		this.lazyDocumentoTarefaPendenteDataModel = lazyDocumentoTarefaPendenteDataModel;
	}

	public Integer getTotalDocumentosTresDias() {
		return totalDocumentosTresDias;
	}

	public void setTotalDocumentosTresDias(Integer totalDocumentosTresDias) {
		this.totalDocumentosTresDias = totalDocumentosTresDias;
	}

	public Integer getTotalDocumentosSeisDias() {
		return totalDocumentosSeisDias;
	}

	public void setTotalDocumentosSeisDias(Integer totalDocumentosSeisDias) {
		this.totalDocumentosSeisDias = totalDocumentosSeisDias;
	}

	public Integer getTotalDocumentosMaisDeSeisDias() {
		return totalDocumentosMaisDeSeisDias;
	}

	public void setTotalDocumentosMaisDeSeisDias(
			Integer totalDocumentosMaisDeSeisDias) {
		this.totalDocumentosMaisDeSeisDias = totalDocumentosMaisDeSeisDias;
	}

	public Integer getTotalDocumentosUsuarioTresDias() {
		return totalDocumentosUsuarioTresDias;
	}

	public void setTotalDocumentosUsuarioTresDias(
			Integer totalDocumentosUsuarioTresDias) {
		this.totalDocumentosUsuarioTresDias = totalDocumentosUsuarioTresDias;
	}

	public Integer getTotalDocumentosUsuarioSeisDias() {
		return totalDocumentosUsuarioSeisDias;
	}

	public void setTotalDocumentosUsuarioSeisDias(
			Integer totalDocumentosUsuarioSeisDias) {
		this.totalDocumentosUsuarioSeisDias = totalDocumentosUsuarioSeisDias;
	}

	public Integer getTotalDocumentosUsuarioDeSeisDias() {
		return totalDocumentosUsuarioDeSeisDias;
	}

	public void setTotalDocumentosUsuarioDeSeisDias(
			Integer totalDocumentosUsuarioDeSeisDias) {
		this.totalDocumentosUsuarioDeSeisDias = totalDocumentosUsuarioDeSeisDias;
	}
	

}
