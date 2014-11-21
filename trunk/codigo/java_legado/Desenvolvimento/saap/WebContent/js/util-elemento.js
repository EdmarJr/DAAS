/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 05/12/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipula��o de elementos HTML.
 *******************************************************************************
 * Depend�ncias: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *******************************************************************************
 * m�todos do objeto:
 * 		exibir(idDoObjeto)
 * 		ocultar(idDoObjeto)
 * 		alternarExibicao(idDoObjeto)
 *		novoElemento(tag)
 *		novoAtributo(nome, valor)
 *		setConteudo(idDoObjeto, valor)
 *		removerPai(objeto, tag);
 *		remover(objeto)
 *		getElementosFilho(objeto, nomeDaTag);
 *		getElementoPai(objeto, tag);
 *		limparConteudo(objeto);
 *******************************************************************************
 * Exemplo:
 * 		var utilElemento = new UtilElemento();
 *		utilElemento.exibir("divTabela");
 *******************************************************************************
 */

UtilElemento.prototype = new UtilString();
function UtilElemento() {
	/**
	 * Exibe o elemento com o ID informado.
	 *
	 * @idDoObjeto ID do objeto que ser� exibido.
	 */
	this.exibir = function(idDoObjeto) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		this.exibirObjeto(objeto);
	};

	/**
	 * Exibe o elemento passado por par�metro.
	 *
	 * @objeto Objeto que ser� exibido.
	 */
	this.exibirObjeto = function(objeto) {
		if (this.isReferencia(objeto)) {
			var tag = objeto.tagName;
			
			if (this.isIE()) {
				objeto.style.display = "block";			
			} else {
				if (this.isStringsIguais(tag, "TR")) {
					objeto.style.display = "table-row";
				} else if (this.isStringsIguais(tag, "TBODY")) {
					objeto.style.display = "table-header-group";
				} else if (this.isStringsIguais(tag, "TFOOT")) {
					objeto.style.display = "table-footer-group";
				} else {
					objeto.style.display = "block";
				}
			}
		}
	};
	
	/**
	 * Oculta o elemento com o ID informado.
	 *
	 * @idDoObjeto ID do objeto que ser� ocultado.
	 */
	this.ocultar = function(idDoObjeto) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		this.ocultarObjeto(objeto);
	};

	/**
	 * Oculta o elemento passado por par�metro.
	 *
	 * @objeto Objeto que ser� ocultado.
	 */
	this.ocultarObjeto = function(objeto) {
		if (this.isReferencia(objeto)) {
			objeto.style.display = "none";
		}
	};
	
	/**
	 * Alterna a exibi��o do objeto com oID informado.
	 *
	 * @idDoObjeto ID do objeto que ser� exibido/ocultado.
	 */
	this.alternarExibicao = function(idDoObjeto) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		
		if (this.isReferencia(objeto)) {
			var display = objeto.style.display;
			objeto.style.display = (this.isStringsIguais(display, "none")? "block": "none");
		}
	};
	
	/**
	 * Retorna novo elemento do tipo informado.
	 * 
	 * @tag Nome da tag que ser� criada.
	 */
	this.novoElemento = function(tag) {
		var objeto = null;
		
		if (!this.isStringVazia(tag)) {
			objeto = document.createElement(tag);
		}
		return objeto;
	};

	/**
	 * Retorna novo atributo do tipo informado.
	 * 
	 * @nome Nome do atributo
	 * @valor Valor do atributo
	 */
	this.novoAtributo = function(nome, valor) {
		var objeto = null;
		
		if (!this.isStringVazia(nome)) {
			objeto = document.createAttribute(nome);
			
			if (!this.isReferencia(objeto)) {
				objeto.value = valor;
			}
		}
		return objeto;
	};
	
	/**
	 * Adiciona o conte�do no objeto indicado.
	 *
	 * @idDoObjeto ID do objeto que ter� seu conte�do alterado.
	 * @valor Valor que ser� adicionado no objeto.
	 */
	this.setConteudo = function(idDoObjeto, valor) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		if (this.isReferencia(objeto) ) {
			objeto.innerHTML = (objeto.innerHTML + valor);
		}
	};
	
	/**
	 * Remove a tag pai refer�nte � tag atual, a tag que ser� removida consiste 
	 * na tag passada por par�metro.
	 *
	 * @objeto Objeto de refer�ncia
	 * @nomeDaTag Nome da tag pai que ser� removida. 
	 */
	this.removerPai = function(objeto, nomeDaTag) {
		
		if (this.isReferencia(objeto) && !this.isStringVazia(nomeDaTag)) {
			var tag 		= null;
			nomeDaTag = this.maiuscula(nomeDaTag);
			while (this.isReferencia(objeto) && !this.isReferencia(tag)) {
				if (this.isStringsIguais(objeto.tagName, nomeDaTag)) {
					tag = objeto;
				} else {
					objeto = objeto.parentNode;
				}
			}
			this.remover(tag);
		}
	};
	
	/**
	 * Remove a tag atual.
	 *
	 * @objeto Objeto html que ser� removido.
	 */
	this.remover = function(objeto) {
		
		if (this.isReferencia(objeto)) {
			var pai = objeto.parentNode;
			pai.removeChild(objeto);
		}
	};
	
	/**
	 * Retorna todos os filhos do objeto que satisfa�am o tipo da tag informada.
	 *
	 * @objeto Objeto de refer�ncia.
	 * @nomeDaTag Nome da tag que ser� recuperada do objeto de refer�ncia.
	 */
	this.getElementosFilho = function(objeto, nomeDaTag) {
		var res = null;
		
		if (this.isReferencia(objeto) && !this.isStringVazia(nomeDaTag)) {
			res = objeto.getElementsByTagName(nomeDaTag);
		}
		return res;
	};
	
	/**
	 * Retorna a tag pai refer�nte � tag atual, a tag que ser� recuperada consiste 
	 * na tag passada por par�metro.
	 *
	 * @objeto Objeto de refer�ncia
	 * @nomeDaTag Nome da tag pai que ser� recuperada.
	 */
	this.getElementoPai = function(objeto, nomeDaTag) {
		var resultado = null;

		if (this.isReferencia(objeto) && !this.isStringVazia(nomeDaTag)) {
			nomeDaTag = this.maiuscula(nomeDaTag);
			while (this.isReferencia(objeto) && !this.isReferencia(resultado)) {
				if (this.isStringsIguais(objeto.tagName, nomeDaTag)) {
					resultado = objeto;
				} else {
					objeto = objeto.parentNode;
				}
			}
		}
		return resultado;
	};
	
	/**
	 * Remove o conte�do do objeto.
	 * 
	 * @objeto Objeto que ter� seu conte�do removido.
	 */
	this.limparConteudo = function(objeto) {
		if (this.isReferencia(objeto)) {
			objeto.innerHTML = "";
		}
	};
}