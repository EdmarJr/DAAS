package br.jus.stj.saad.view.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.jus.stj.saad.business.AvisoBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.login.LoginBean;

public class LazyAvisoDaUnidadeDataModel extends LazyDataModel<Aviso> {

	private static final long serialVersionUID = 1L;

	private AvisoBean avisoBean;

	private LoginBean loginBean;

	private List<Aviso> avisos;
	
	private Integer rowCount;

	public LazyAvisoDaUnidadeDataModel(AvisoBean bean,
			LoginBean loginBean) {
		this.avisoBean = bean;
		this.loginBean = loginBean;
	}

	@Override
	public List<Aviso> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		avisos = avisoBean.avisosUnidadeUsuarioLazy(first, pageSize, loginBean.getUnidadeUsuario(), null);
		setPageSize(pageSize);
		return avisos;
	}

	@Override
	public Aviso getRowData(String rowKey) {
		Long idRowKey = Long.parseLong(rowKey);
		for (Aviso aviso : avisos) {
			if (idRowKey.equals(aviso.getId())) {
				return aviso;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Aviso aviso) {
		return aviso.getId();
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
			rowCount = avisoBean.avisosUnidadeUsuarioLazyCount(loginBean.getUnidadeUsuario(), null);
		}
		
		return rowCount;
	}

	public List<Aviso> getDocumentos() {
		return avisos;
	}

	public void setDocumentos(List<Aviso> avisos) {
		this.avisos = avisos;
	}
}
