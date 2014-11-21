package br.jus.stj.saad.view.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.login.LoginBean;

public class LazyDocumentoPendenteDataModel extends LazyDataModel<Documento> {

	private static final long serialVersionUID = 1L;

	private DocumentoBean documentoBean;

	private LoginBean loginBean;

	private List<Documento> documentos;
	
	private Integer rowCount;

	public LazyDocumentoPendenteDataModel(DocumentoBean bean,
			LoginBean loginBean) {
		this.documentoBean = bean;
		this.loginBean = loginBean;
	}

	@Override
	public List<Documento> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		documentos = documentoBean.documentosPendentesLocalLazy(first,
				pageSize, loginBean.getUnidadeUsuario());
		documentos = documentoBean.inicializarLista(documentos, "tarefas");
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
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public int getRowCount() {
		if(null == rowCount){
			rowCount = documentoBean.documentosPendentesLocalLazyCount(loginBean
					.getUnidadeUsuario());
		}
		
		return rowCount;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
}
