/*
 * ManutencaoSaapGestaoPresidenciaAction.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.Date;

import br.jus.stj.alp01.arquitetura.apresentacao.ServiceLocator;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.util.constante.Mensagem;

/**
 * Classe para controle das acoes do caso de uso de Manter SaapGestaoPresidencia.
 *
 * @author Politec Informática
 */
public class ManutencaoSaapGestaoPresidenciaAction extends 
		SaapActionAbstrato<SaapGestaoPresidencia> {

	private SaapFacade facade;

	/**
	 * Retorna SaapFacade.
	 * 
	 * @return SaapFacade fachada para acesso aos metodos de negocio
	 */
	@Override
	protected SaapFacade getFachada() {
		if (facade == null) {
			ServiceLocator locator = ServiceLocator.getInstancia();
			facade = locator.getFachada(SaapFacade.class);
		}
		return facade;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar()
	 */
	@Override
	public String consultar() {
		if(!isNenhumCampoPreenchido()) {
		    if (!isPeriodoInvalido()) { 
	            carregarConsulta();
	            getConsultaVisao().setConsultaExecutada(true);
	            if (isVazio(getConsultaVisao().getLista())) {
	                addMensagemInformativaChave(
	                		"framework.mensagem.nenhumregistro");
	            }
		    }else{
		    	adicionaMensagemError(Mensagem.getMA008());
	            getConsultaVisao().setLista(null);
		    }
		}else {
			adicionaMensagemError("MA021");
			getConsultaVisao().setLista(null);
		}
        return getTelaInicial();
    }


	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#incluir()
	 */
	@Override
	public String incluir() {
		if(isCamposObrigatoriosPreechidos(true)) {
			getFachada().inserir(getSaapGestaoPresidencia());
			getManutencaoVisao().setEntidade(null);
			addMensagemSucessoChave("MP014");
		}
		return null;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ManutencaoAction#editar()
	 */
	@Override
	public String editar() {
		String retorno = null;
		if(isCamposObrigatoriosPreechidos(false)) {
			executarFachada("alterar", getSaapGestaoPresidencia());
			addMensagemSucessoChave("MP015");
			carregarConsulta();
			retorno = getTelaInicial();
		}else {
			retorno = getTelaAlteracao();
		}
		return retorno;
	}

	/**
	 * Limpa entidade.
	 */
	public void limpar() {
		getManutencaoVisao().setEntidade(null);
	}

	private boolean isNenhumCampoPreenchido() {
    	Date dataInicial = getManutencaoVisao().getConsulta().
    			getDtInicialVigGestao();
    	Date dataFinal = getManutencaoVisao().getConsulta().
    			getDtFinalVigGestao();
    	String nome = getManutencaoVisao().getConsulta().
    			getNomeMinistroPresidente();
		return !isReferencia(dataInicial) && !isReferencia(dataFinal) && 
				isVazio(nome);
	}

    /** 
     * Retorna true se o período for válido. 
     * 
     * @return boolean 
     */ 
    private boolean isPeriodoInvalido() { 
        boolean retorno = false;
    	Date dataInicial = getManutencaoVisao().getConsulta().
    			getDtInicialVigGestao(); 
    	Date dataFinal = getManutencaoVisao().getConsulta().
    			getDtFinalVigGestao(); 
        if (isReferencia(dataInicial) && isReferencia(dataFinal)) { 
        	retorno = dataInicial.after(dataFinal); 
        }
        return retorno; 
    }

	private SaapGestaoPresidencia getSaapGestaoPresidencia() {
		return getManutencaoVisao().getEntidade();
	}

	private boolean isCamposObrigatoriosPreechidos(boolean flag) {
		boolean retorno = true;
		Date dtInicial = getSaapGestaoPresidencia().getDtInicialVigGestao();
		Date dtFinal = getSaapGestaoPresidencia().getDtFinalVigGestao();
		String nome = getSaapGestaoPresidencia().getNomeMinistroPresidente();

		if(flag) {
			if(!isReferencia(dtInicial)) {
				adicionaMensagemError(Mensagem.getMA001(), 
						"Data início de vigência");
				retorno = false;
			}
		}else {
			if(!isReferencia(dtFinal) && !isReferencia(dtInicial)) {
				adicionaMensagemError(Mensagem.getMA001(), 
						"Data início de vigência");
				retorno = false;
			}
		}
		if(isVazio(nome)) {
			adicionaMensagemError(Mensagem.getMA001(), "Nome do ministro");
			retorno = false;
		}
		return retorno;
	}
}
