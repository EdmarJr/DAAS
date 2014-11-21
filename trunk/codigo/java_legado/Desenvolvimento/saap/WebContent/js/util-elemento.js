/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 05/12/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação de elementos HTML.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *******************************************************************************
 * métodos do objeto:
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
	 * @idDoObjeto ID do objeto que será exibido.
	 */
	this.exibir = function(idDoObjeto) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		this.exibirObjeto(objeto);
	};

	/**
	 * Exibe o elemento passado por parâmetro.
	 *
	 * @objeto Objeto que será exibido.
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
	 * @idDoObjeto ID do objeto que será ocultado.
	 */
	this.ocultar = function(idDoObjeto) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		this.ocultarObjeto(objeto);
	};

	/**
	 * Oculta o elemento passado por parâmetro.
	 *
	 * @objeto Objeto que será ocultado.
	 */
	this.ocultarObjeto = function(objeto) {
		if (this.isReferencia(objeto)) {
			objeto.style.display = "none";
		}
	};
	
	/**
	 * Alterna a exibição do objeto com oID informado.
	 *
	 * @idDoObjeto ID do objeto que será exibido/ocultado.
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
	 * @tag Nome da tag que será criada.
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
	 * Adiciona o conteúdo no objeto indicado.
	 *
	 * @idDoObjeto ID do objeto que terá seu conteúdo alterado.
	 * @valor Valor que será adicionado no objeto.
	 */
	this.setConteudo = function(idDoObjeto, valor) {
		var objeto = this.getObjetoPeloID(idDoObjeto);
		if (this.isReferencia(objeto) ) {
			objeto.innerHTML = (objeto.innerHTML + valor);
		}
	};
	
	/**
	 * Remove a tag pai referênte à tag atual, a tag que será removida consiste 
	 * na tag passada por parâmetro.
	 *
	 * @objeto Objeto de referência
	 * @nomeDaTag Nome da tag pai que será removida. 
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
	 * @objeto Objeto html que será removido.
	 */
	this.remover = function(objeto) {
		
		if (this.isReferencia(objeto)) {
			var pai = objeto.parentNode;
			pai.removeChild(objeto);
		}
	};
	
	/**
	 * Retorna todos os filhos do objeto que satisfaçam o tipo da tag informada.
	 *
	 * @objeto Objeto de referência.
	 * @nomeDaTag Nome da tag que será recuperada do objeto de referência.
	 */
	this.getElementosFilho = function(objeto, nomeDaTag) {
		var res = null;
		
		if (this.isReferencia(objeto) && !this.isStringVazia(nomeDaTag)) {
			res = objeto.getElementsByTagName(nomeDaTag);
		}
		return res;
	};
	
	/**
	 * Retorna a tag pai referênte à tag atual, a tag que será recuperada consiste 
	 * na tag passada por parâmetro.
	 *
	 * @objeto Objeto de referência
	 * @nomeDaTag Nome da tag pai que será recuperada.
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
	 * Remove o conteúdo do objeto.
	 * 
	 * @objeto Objeto que terá seu conteúdo removido.
	 */
	this.limparConteudo = function(objeto) {
		if (this.isReferencia(objeto)) {
			objeto.innerHTML = "";
		}
	};
}