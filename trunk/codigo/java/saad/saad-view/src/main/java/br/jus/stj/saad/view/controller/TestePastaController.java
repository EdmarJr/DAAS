package br.jus.stj.saad.view.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.stj.saad.service.TriagemService;
import br.jus.stj.saad.triagem.ArquivoUtils;

@ViewScoped
@ManagedBean
public class TestePastaController {
	
	@EJB
	private TriagemService triagemService;
	
	private List<String> arquivosTriagem;
	
	private String erro;
	
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	@PostConstruct
	public void init() {
		try {
			setArquivosTriagem(triagemService.obterLista(ArquivoUtils.obterEnderecoPastaTriagem()));
			
			for (String s : arquivosTriagem) {
				erro = erro + "\n " + s;
			}
		} catch (Exception e) {
			erro = e.getMessage();
			
			try {
				setArquivosTriagem(triagemService.obterLista("/usr/teste/"));
				
				for (String s : arquivosTriagem) {
					erro = erro + "\n " + s;
				}
			}catch (Exception b) {
				erro = erro + "\n" + b.getMessage();
			}
		}
	}
	
	public List<String> getArquivosTriagem() {
		return arquivosTriagem;
	}

	public void setArquivosTriagem(List<String> arquivosTriagem) {
		this.arquivosTriagem = arquivosTriagem;
	}
}
