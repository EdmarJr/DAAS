/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 01/05/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto com m�todos diversos, esse objeto � usado por todas as
 *		bibliotecas javascript.
 *******************************************************************************
 * Depend�ncias: 
 * 		Nenhuma
 *******************************************************************************
 * m�todos do objeto:
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
	 * retorna true se o objeto n�o for nulo.
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
	 * Retorna um objeto do formul�rio.
	 *
	 * @nomeFormulario Nome do formul�rio.
	 * @nomeObjeto Nome do objeto que ser� localizado dentro do formul�rio.
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
 * Fun��es privadas do objeto.
 ******************************************************************************/
}