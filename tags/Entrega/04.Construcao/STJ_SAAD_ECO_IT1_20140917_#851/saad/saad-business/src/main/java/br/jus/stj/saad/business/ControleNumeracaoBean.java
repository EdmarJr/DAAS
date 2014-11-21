package br.jus.stj.saad.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.ControleNumeracao;
import br.jus.stj.saad.persistence.ControleNumeracaoDAO;

@Stateless
@javax.ejb.LocalBean
public class ControleNumeracaoBean extends Bean<ControleNumeracao> {

	@Inject
	private ControleNumeracaoDAO controleNumeracaoDAO;
//	@EJB
//	private TipoDocumentoBean tipoDocumentoBean;

	@Override
	public ControleNumeracaoDAO getDao() {
		return controleNumeracaoDAO;
	}

//	public void incrementarControleNumeracao(Local unidade,
//			TipoDocumento tipoDocumento, String identificacaoDocumentoSTJ) {
//		ControleNumeracao controleNumeracao = obterControleNumeracaoPorDocumento(
//				tipoDocumento, identificacaoDocumentoSTJ);
//		controleNumeracao.setLocal(unidade);
//		ControleNumeracao controleNumeracaoBanco = getDao().obterPorId(
//				controleNumeracao.getTipoDocumento(),
//				controleNumeracao.getLocal(), controleNumeracao.getAno());
//		if (controleNumeracaoBanco == null) {
//			super.incluir(controleNumeracao);
//		} else {
//			controleNumeracaoBanco.setUltimoNumero(controleNumeracao
//					.getUltimoNumero());
//			super.alterar(controleNumeracaoBanco);
//		}
//	}
//
//	public ControleNumeracao obterControleNumeracaoPorDocumento(
//			TipoDocumento tipoDocumento, String identificacaoDocumentoSTJ) {
//		ControleNumeracao controleNumeracao = new ControleNumeracao();
//		controleNumeracao.setTipoDocumento(tipoDocumento);
//		controleNumeracao.setAno(new Long(IdentificadorUtils
//				.obterAno(identificacaoDocumentoSTJ)));
//		controleNumeracao.setUltimoNumero(new Long(IdentificadorUtils
//				.obterIdentificacaoDocumentoSTJ(identificacaoDocumentoSTJ)));
//		return controleNumeracao;
//	}

}
