package br.jus.stj.saad.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.jus.stj.saad.business.ControleIdentificadorBean;
import br.jus.stj.saad.business.DestinatarioDocumentoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.entity.local.ControleIdentificador;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.EnderecamentoDestinatarioDocumento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;

@Singleton
@javax.ejb.LocalBean
public class DocumentoService {

	@EJB
	private DocumentoBean documentoBean;
	@EJB
	private LocalBean localBean;
	@Inject
	private LoginBean loginBean;
	@EJB
	private ControleIdentificadorBean controleIdentificadorBean;
	@EJB
	private DestinatarioDocumentoBean destinatarioDocumentoBean;
	
	@Lock(LockType.WRITE)
	public Documento incluir(Documento documento){
		Local unidade = localBean.sincronizar(loginBean.getUnidadeUsuario());
		documento.setLocal(unidade);
		
		documento.setNumeroControleIdentificador(
				StringUtils.obterNumeroComMascaraDeSeisCaracteres(
						obterUltimoNumeroPorLocal(unidade)));
		
		documento.setIdentificadorComMascara(
				IdentificadorUtils.obterIdentificadorComMascaraPorDocumento(
						documento.getTipo().getSigla(), documento.getNumeroControleIdentificador(), 
						obterAnoAtual().toString()));
		
		documentoBean.ajustarNumeroRegistro(documento);
		documentoBean.definirCargaHorariaTotal(documento);		
		documentoBean.ajustarAnexos(documento);		
		
		if(!(documento.getEnderecamentoDestinatario() instanceof EnderecamentoExterno || documento.getEnderecamentoDestinatario() instanceof EnderecamentoInterno)){
			documento.getEnderecamentoDestinatario().getEnderecamentoDestinatarioDocumento().setEnderecamento(documento.getEnderecamentoDestinatario());
			EnderecamentoDestinatarioDocumento e = documento.getEnderecamentoDestinatario().getEnderecamentoDestinatarioDocumento();
			e.setDestinatarioDocumento(destinatarioDocumentoBean.obterPorId(DestinatarioDocumento.class, e.getDestinatarioDocumento().getId()));
		}
		
		if(!(documento.getEnderecamentoRemetente() instanceof EnderecamentoExterno || documento.getEnderecamentoRemetente() instanceof EnderecamentoInterno)){
			documento.getEnderecamentoRemetente().getEnderecamentoDestinatarioDocumento().setEnderecamento(documento.getEnderecamentoRemetente());
			EnderecamentoDestinatarioDocumento e = documento.getEnderecamentoRemetente().getEnderecamentoDestinatarioDocumento();
			e.setDestinatarioDocumento(destinatarioDocumentoBean.obterPorId(DestinatarioDocumento.class, e.getDestinatarioDocumento().getId()));
		}
		
		documentoBean.incluir(documento);
		return documento;
	}
	
	public Long obterAnoAtual() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return Long.parseLong(format.format(new Date()));
	}
	
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

}
