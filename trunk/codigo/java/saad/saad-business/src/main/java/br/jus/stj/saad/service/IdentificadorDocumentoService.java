package br.jus.stj.saad.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.jus.stj.saad.business.ControleIdentificadorBean;
import br.jus.stj.saad.business.ControleNumeracaoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.entity.local.ControleIdentificador;
import br.jus.stj.saad.entity.local.ControleNumeracao;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;

@Singleton
@javax.ejb.LocalBean
public class IdentificadorDocumentoService {

	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	
	@EJB
	private ControleNumeracaoBean controleNumeracaoBean;
	
	@EJB
	private ControleIdentificadorBean controleIdentificadorBean;

	@EJB
	private DocumentoBean documentoBean;
	
	@EJB
	private LocalBean localBean;
	
	@Inject
	private LoginBean loginBean;
	
	
	public String gerarIdentificadorDocumentoPorTipoDocumento(
			TipoDocumento tipoDocumento, Local local) {
		
		String ultimoNumero = obterUltimoNumeroPorLocal(local);
		return IdentificadorUtils.obterIdentificadorComMascaraPorDocumento(tipoDocumento.getSigla(), ultimoNumero, obterAnoAtual().toString());
	}
	
	@Lock(LockType.WRITE)
	private String obterUltimoNumeroPorTipoDocumento(TipoDocumento tipoDocumento) {
		tipoDocumento = tipoDocumentoBean.inicializar(tipoDocumento, "controlesNumeracao");
		ControleNumeracao ultimoControle = tipoDocumento.getUltimoControle();
		if(ultimoControle == null) {
			ultimoControle = new ControleNumeracao();
			ultimoControle.setAno(obterAnoAtual());
			ultimoControle.setLocal(localBean.sincronizar(loginBean.getUnidadeUsuario()));
			ultimoControle.setTipoDocumento(tipoDocumento);
			ultimoControle.setUltimoNumero(0L);
			controleNumeracaoBean.incluir(ultimoControle);
		} else {
			ultimoControle.setUltimoNumero(ultimoControle.getUltimoNumero() + 1L);
			controleNumeracaoBean.alterar(ultimoControle);
		}
		return ultimoControle.getUltimoNumero().toString();
	}
	
	@Lock(LockType.WRITE)
	private String obterUltimoNumeroPorLocal(Local local) {
		local = localBean.inicializar(local, "controlesIdentificadores");
		ControleIdentificador ultimoControle = local.getUltimoControle();
		if(ultimoControle == null) {
			ultimoControle = new ControleIdentificador();
			ultimoControle.setAno(obterAnoAtual());
			ultimoControle.setLocal(localBean.sincronizar(loginBean.getUnidadeUsuario()));
			ultimoControle.setUltimoNumero(0L);
			controleIdentificadorBean.incluir(ultimoControle);
		} else {
			ultimoControle.setUltimoNumero(ultimoControle.getUltimoNumero() + 1L);
			controleIdentificadorBean.alterar(ultimoControle);
		}
		return ultimoControle.getUltimoNumero().toString();
	}
	
	private Long obterAnoAtual() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return Long.parseLong(format.format(new Date()));
	}

}
