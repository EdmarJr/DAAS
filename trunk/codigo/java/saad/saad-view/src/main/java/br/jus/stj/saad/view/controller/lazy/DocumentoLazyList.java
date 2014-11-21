package br.jus.stj.saad.view.controller.lazy;

import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.vo.FiltroDocumentoConsultaAndamentoVO;

@ManagedBean
@ViewScoped
public class DocumentoLazyList extends LazyDataModel<Documento>{
	
	
	private static final long serialVersionUID = 1L;

	private DocumentoBean documentoBean;
	
	private List<Documento> documentos;

	private FiltroDocumentoConsultaAndamentoVO consultaAndamentoVO;

	public DocumentoLazyList() {

	}

	public DocumentoLazyList(
			FiltroDocumentoConsultaAndamentoVO consultaAndamentoVO, DocumentoBean bean) {

		this.consultaAndamentoVO = consultaAndamentoVO;
		this.documentoBean = bean;

	}
	
	@Override
	public List<Documento> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		documentos = documentoBean
				.filtrar(first, pageSize, consultaAndamentoVO);

		setPageSize(pageSize);

		return documentos;
	}
	
	@Override
	public Documento getRowData(String rowKey) {
		
		Long idRowKey = Long.parseLong(rowKey);
		for (Documento documento : documentos) {
			if (idRowKey.equals(documento.getId())) {
				return documento;
			}
		}

		return null;
	}
	
	
	
	@Override
	public Object getRowKey(Documento documento) {
		
		return documento.getId();
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
	
		if(rowIndex == -1 || getPageSize() == 0){
			
			super.setRowIndex(-1);
		}
		else{
			super.setRowIndex(rowIndex % getPageSize());
		}
	}
	
	@Override
	public int getRowCount() {
		return documentoBean.listarDocumentoAndamentoCount(consultaAndamentoVO);
	}
	
	public List<Documento> getDocumentos() {
		return documentos;
	}
	
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
}
