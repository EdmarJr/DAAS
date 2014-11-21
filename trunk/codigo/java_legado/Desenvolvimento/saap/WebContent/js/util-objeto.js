/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto com métodos diversos, esse objeto é usado por todas as
 *		bibliotecas javascript.
 *******************************************************************************
 * Dependências: 
 * 		Nenhuma
 *******************************************************************************
 * métodos do objeto:
 *		isReferencia(objeto)
 *		getObjeto(nome)
 *		getObjetos(nome)
 *		getObjetoDoFormulario(nomeFormulario, nomeObjeto)
 *		getObjetoPeloID(id)
 *		isArray(objeto)
 *		isStringVazia(string)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilObjeto();
 *		alert(objeto.isStringVazia(""));
 *******************************************************************************
 */
UtilObjeto.prototype = new UtilBrowser();
 
function UtilObjeto() {
	
	/**
	 * retorna true se o objeto não for nulo.
	 */
	this.isReferencia = function(objeto) {
		return (objeto != null);
	}
	
	/**
	 * Retorna o objeto pelo nome.
	 *
	 * @veja document.getElementsByName
	 */
	this.getObjeto = function(nome) {
		var res = null;
		
		if (!this.isStringVazia(nome)) {
			res = document.getElementsByName(nome)[0];
		}
		return (this.isReferencia(res)? res: null);
	}
	
	/**
	 * Retorna um array dos objetos de mesmo nome.
	 *
	 * @veja document.getElementsByName
	 */
	this.getObjetos = function(nome) {
		var res = null;
		
		if (!this.isStringVazia(nome)) {
			res = document.getElementsByName(nome);
		}
		return (this.isReferencia(res)? res: null);
	}
	
	/**
	 * Retorna um objeto do formulário.
	 *
	 * @nomeFormulario Nome do formulário.
	 * @nomeObjeto Nome do objeto que será localizado dentro do formulário.
	 */
	this.getObjetoDoFormulario = function(nomeFormulario, nomeObjeto) {
		var res = null;
		
		if (!this.isStringVazia(nomeFormulario) && !this.isStringVazia(nomeObjeto)) {
			var formulario = this.getObjeto(nomeFormulario);
			
			if (this.isReferencia(formulario)) {
				res = formulario[nomeObjeto];
			}
		}
		return (this.isReferencia(res)? res: null);
	}

	/**
	 * Retorna o objeto pelo seu ID.
	 *
	 * @identificador ID do objeto. 
	 */
	this.getObjetoPeloID = function(identificador) {
		var res = null;
		
		if (!this.isStringVazia(identificador)) {
			res = document.getElementById(identificador);
		}
		return res;
	}
	
	/**
	 * Retorna true se o objeto for um array.
	 *
	 * @objeto
	 */	
	this.isArray = function(objeto) {
		var res = false;
		
		if (this.isReferencia(objeto) && this.isReferencia(objeto[0])) {
			res = true;
		}
		
		return res;
	}
	
	/**
	 * Retorna true se a string for vazia.
	 */
	this.isStringVazia = function(string) {
		return (string == null || string.toString() == "");
	}

/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/
}