package br.jus.stj.saad.view.controller.lazy;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.jus.stj.saad.business.AvisoBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.vo.FiltroConsultaAvisoVO;


@ManagedBean
@ViewScoped
public class AvisoLazyList extends LazyDataModel<Aviso> {
	
	private static final long serialVersionUID = 1L;
	
	private AvisoBean avisoBean;
	
	private List<Aviso> avisos;
	
	private FiltroConsultaAvisoVO  avisoVO;
	
	public AvisoLazyList(){
		
	}
	
	public AvisoLazyList(FiltroConsultaAvisoVO avisoVO, AvisoBean avisoBean){
		
		this.avisoVO = avisoVO;
		this.avisoBean = avisoBean;
	}
	
	
	@Override
	public int getRowCount() {
		return avisoBean.consultaAvisoPorUsuariosCount(avisoVO);
}

	
	@Override
	public List<Aviso> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<Aviso> list = avisoBean.consultaAvisoPorUsuarios(first,
				pageSize, avisoVO);

		for (Aviso aviso : list) {
			if (aviso.getDestinatariosAviso().size() > 0) {
				String nomes = "";
				for (DestinatarioAviso destinatarioAviso : aviso
						.getDestinatariosAviso()) {

					aviso.setDestinatariosToString(nomes += destinatarioAviso
							.getUsuario().getNome() + ", \n");
				}

			} else {
				aviso.setDestinatariosToString("Todos");
			}
		}
		return list;
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
	
		if(rowIndex == -1 || getPageSize() == 0){
			
			super.setRowIndex(-1);
		}
		else{
			super.setRowIndex(rowIndex % getPageSize());
		}
	}
	
	public List<Aviso> getAvisos() {
		return avisos;
	}
	
	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}

	
}
