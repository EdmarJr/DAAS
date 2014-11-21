package br.jus.stj.saad.view.controller;

import java.util.List;

import org.primefaces.context.RequestContext;

import br.jus.stj.saad.business.DestinatarioDocumentoBean;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoDestinatarioDocumento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.enumerators.SexoEnum;

public class EnderecamentoDestinatarioDocumentoUtil {

	private DestinatarioDocumentoBean destinatarioDocumentoBean;

	private SexoEnum[] listaSexos;

	private Enderecamento enderecamento;
	
	public EnderecamentoDestinatarioDocumentoUtil (DestinatarioDocumentoBean destinatarioDocumentoBean, Enderecamento enderecamento){
		this.destinatarioDocumentoBean = destinatarioDocumentoBean;
		this.enderecamento = enderecamento;
		this.listaSexos = SexoEnum.values();
		if(enderecamento.getId() == null){
			resetarAtributos();
		}else{
			if(enderecamento instanceof EnderecamentoInterno || enderecamento instanceof EnderecamentoExterno){
				resetarAtributos();
			}
		}
	}
	
	public void resetarAtributos(){
		getEnderecamento().setEnderecamentoDestinatarioDocumento(new EnderecamentoDestinatarioDocumento());
		setDestinatarioDocumento(new DestinatarioDocumento());		
	}
	
	public void salvarDestinatarioDocumento(String painelPai){
		ativarEnderecamento();
		getDestinatarioDocumento().setCep(getDestinatarioDocumento().getCep().replaceAll("-", ""));
		if(getDestinatarioDocumento().getId() == null){
			destinatarioDocumentoBean.incluir(getDestinatarioDocumento());
		}else{
			destinatarioDocumentoBean.alterar(getDestinatarioDocumento());
		}
		
		RequestContext.getCurrentInstance().update(painelPai + "remetentePessoaFisicaPanel");
		
	}
	
	public void cancelarDestinatarioDocumento(String painelPai){
		if(getDestinatarioDocumento().getId() == null){
			resetarAtributos();
		}
		RequestContext.getCurrentInstance().update(painelPai + "remetentePessoaFisicaPanel");
	}
	
	public List<DestinatarioDocumento> autoCompletePessoaFisica(String query) {
		List<DestinatarioDocumento> list = destinatarioDocumentoBean.listarPorNome(query);
		
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	public void ativarEnderecamento(){
		getEnderecamento().setUtilizado(true);
	}

	public DestinatarioDocumento getDestinatarioDocumento() {
		return enderecamento.getEnderecamentoDestinatarioDocumento().getDestinatarioDocumento();
	}

	public void setDestinatarioDocumento(DestinatarioDocumento destinatarioDocumento) {
		enderecamento.getEnderecamentoDestinatarioDocumento().setDestinatarioDocumento(destinatarioDocumento);
	}

	public Enderecamento getEnderecamento() {
		return enderecamento;
	}

	public void setEnderecamento(Enderecamento enderecamento) {
		this.enderecamento = enderecamento;
	}

	public SexoEnum[] getListaSexos() {
		return listaSexos;
	}

	public void setListaSexos(SexoEnum[] listaSexos) {
		this.listaSexos = listaSexos;
	}
}
