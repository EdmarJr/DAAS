package br.jus.stj.saad.view.controller.documento;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import br.jus.stj.saad.business.DocumentoBean;
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
    
    @Inject
    private LoginBean loginBean;
    
    private List<Documento> documentosPendente;
    private List<Documento> documentoTarefasPendente;

    private LazyDataModel<Documento> lazyDocumentoPendenteDataModel;
    private LazyDataModel<Documento> lazyDocumentoTarefaPendenteDataModel;
    
    private Integer totalDocumentosTresDias;
    private Integer totalDocumentosSeisDias;
    private Integer totalDocumentosMaisDeSeisDias;
    
    @PostConstruct
    private void initialize() {
    	lazyDocumentoPendenteDataModel = new LazyDocumentoPendenteDataModel(documentoBean, loginBean);
    	lazyDocumentoTarefaPendenteDataModel = new LazyDocumentoTarefaPendenteDataModel(documentoBean, loginBean);
    	setTotalDocumentosTresDias(documentoBean.totalDocumentosPendentesPorDia(3, loginBean.getUnidadeUsuario(), null, false));
    	setTotalDocumentosSeisDias(documentoBean.totalDocumentosPendentesEntreDias(3, 6, loginBean.getUnidadeUsuario(), null));
    	setTotalDocumentosMaisDeSeisDias(documentoBean.totalDocumentosPendentesPorDia(6, loginBean.getUnidadeUsuario(), null, true) + documentoBean.totalDocumentosSemTarefas(loginBean.getUnidadeUsuario()));
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
		String identificacaoDoc = documento.getIdentificacaoDocumentoSTJ();
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

		Date ultimaDataTarefa = buscarDataDaUltimaTarefa(documento);
		
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
		
		incluirObjetoRequestMap("documentoSelecionado", documentoSelecionado);
		return "visualizarDocumentoRecebido";

	}
	
	public LazyDataModel<Documento> getLazyDocumentoPendenteDataModel() {
		return lazyDocumentoPendenteDataModel;
	}

	public void setLazyDocumentoPendenteDataModel(
			LazyDataModel<Documento> lazyDocumentoPendenteDataModel) {
		this.lazyDocumentoPendenteDataModel = lazyDocumentoPendenteDataModel;
	}

	public LazyDataModel<Documento> getLazyDocumentoTarefaPendenteDataModel() {
		return lazyDocumentoTarefaPendenteDataModel;
	}

	public void setLazyDocumentoTarefaPendenteDataModel(
			LazyDataModel<Documento> lazyDocumentoTarefaPendenteDataModel) {
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
	

}
