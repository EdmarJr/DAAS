package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.local.auditoria.AuditoriaAndamento;
import br.jus.stj.saad.exception.IdentificadorInvalidoException;
import br.jus.stj.saad.identificador.IdentificadorUtils;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.persistence.AndamentoDocumentoDAO;

@Stateless
public class AndamentoDocumentoBean extends Bean<AndamentoDocumento>{

    @Inject
    private AndamentoDocumentoDAO andamentoDocumentoDAO;
    
    @Inject
    LoginBean loginBean;
    
    @EJB
    AuditoriaBean<AuditoriaAndamento> auditoriaBean;
    
    @Override
    public AndamentoDocumentoDAO getDao() {
    	return andamentoDocumentoDAO;
    }
    
    
	public List<Documento> obterDocumentosComAndamentos(String identificador)
			throws IdentificadorInvalidoException {

		IdentificadorUtils.validarIdentificadorDocumento(identificador);

		String sigla = IdentificadorUtils.obterSigla(identificador);
		String identificacaoDocumentoSTJ = IdentificadorUtils
				.obterIdentificacaoDocumentoSTJ(identificador);
		String ano = IdentificadorUtils.obterAno(identificador);

		return andamentoDocumentoDAO.obterDocumentosComAndamentos(sigla,
				identificacaoDocumentoSTJ, ano, loginBean.getUnidadeUsuario());

	}
	
	public List<Documento> obterDocumentosComAndamentos(
			TipoDocumento tipoDocumento) {

		return andamentoDocumentoDAO.obterDocumentosComAndamentos(
				tipoDocumento, loginBean.getUnidadeUsuario());

	}
	
	public void excluir(List<AndamentoDocumento> andamentoDocumentoList) {

		for (AndamentoDocumento andamentoDocumento : andamentoDocumentoList) {

			getDao().excluir(andamentoDocumento);

			// auditoriaBean.incluir(montarAuditoriaAndamento(andamentoDocumento,
			// OperacaoAuditoriaEnum.EXCLUIR.getValor()));

		}

	}
	
	public void incluir(AndamentoDocumento andamentoDocumento) {

		getDao().incluir(andamentoDocumento);

//		auditoriaBean.incluir(montarAuditoriaAndamento(andamentoDocumento,
//				OperacaoAuditoriaEnum.INCLUIR.getValor()));

	}
	
	public void alterar(AndamentoDocumento andamentoDocumentoAlterado,
			AndamentoDocumento andamentoDocAntigo) {

		getDao().atualizar(andamentoDocumentoAlterado);

//		auditoriaBean.incluir(montarAuditoriaAndamento(
//				andamentoDocumentoAlterado,
//				OperacaoAuditoriaEnum.ALTERAR.getValor()));

	}
	
	private AuditoriaAndamento montarAuditoriaAndamento(
			AndamentoDocumento andamentoDocumento, Character tpOperacao) {

		return new AuditoriaAndamento(loginBean.getUsuarioLogado(), tpOperacao,
				andamentoDocumento, andamentoDocumento.getDocumento());

	}
	

}
