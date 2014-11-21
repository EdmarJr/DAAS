package br.jus.stj.saad.view.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.TarefaDemandaDocumentoBean;
import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.login.LoginBean;

public class LazyDocumentoTarefaPendenteDataModel extends
		LazyDataModel<TarefaDemandaDocumento> {

	private static final long serialVersionUID = 1L;

	private TarefaDemandaDocumentoBean tarefaDemandaDocumentoBean;

	private DocumentoBean documentoBean;
	
	private LoginBean loginBean;

	private List<TarefaDemandaDocumento> tarefas;
	
	private Integer rowCount;

	public LazyDocumentoTarefaPendenteDataModel(DocumentoBean documentoBean, TarefaDemandaDocumentoBean bean,
			LoginBean loginBean) {
		this.tarefaDemandaDocumentoBean = bean;
		this.documentoBean = documentoBean;
		this.loginBean = loginBean;
	}

	@Override
	public List<TarefaDemandaDocumento> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		tarefas = tarefaDemandaDocumentoBean.tarefasPendentesLocalUsuarioLazy(first,
				pageSize, loginBean.getUnidadeUsuario(),
				loginBean.getUsuarioLogado());

		setPageSize(pageSize);
		return tarefas;
	}

	@Override
	public TarefaDemandaDocumento getRowData(String rowKey) {
		Long idRowKey = Long.parseLong(rowKey);
		for (TarefaDemandaDocumento tarefa : tarefas) {
			if (idRowKey.equals(tarefa.getId())) {
				return tarefa;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(TarefaDemandaDocumento tarefaDemandaDocumento) {
		return tarefaDemandaDocumento.getId();
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
			rowCount = tarefaDemandaDocumentoBean.tarefasPendentesLocalUsuarioLazyCount(
					loginBean.getUnidadeUsuario(), loginBean.getUsuarioLogado());
		}
		
		return rowCount;
	}

	public List<TarefaDemandaDocumento> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaDemandaDocumento> tarefas) {
		this.tarefas = tarefas;
	}
}
