/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 13/12/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto que implementa um mapa de objetos, o mapa é composto por chave e 
 *		valor. Os objetos recuperados do mapa são objetos do tipo ObjetoMapa.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *******************************************************************************
 * métodos do objeto:
 *		getUltimoDiaDoMesAno(mes, ano)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilMapa();
 *		objeto.adicionar(1, "nome");
 *		alert(objeto.recuperar(1));
 *******************************************************************************
 */

UtilMapa.prototype = new UtilString();
function UtilMapa() {
	var array = new Array();
	
	/**
	 * Adiciona um objeto no mapa.
	 *
	 * @chave Chave do objeto
	 * @valor Objeto que será inserido.
	 */
	this.adicionar = function(chave, valor) {
		var indice = this.getIndice(chave);
		if (indice > -1) {
			this.remover(chave);
			this.adicionarNoIndice(indice, chave, valor);
		} else {
			var objetoMapa = this.novoObjetoMapa(chave, valor);
			this.getArray().push(objetoMapa);	
		}
	};
	
	/** 
	 * Adiciona um objeto no índice do mapa.
	 *
	 * @indice Índice do objeto no mapa.
	 * @chave Chave do objeto
	 * @valor Objeto que será inserido.
	 */
	this.adicionarNoIndice = function(indice, chave, valor) {
		if (!this.isExiste(chave)) {
			if (indice <= this.getTamanho()) {
				var objetoMapa = this.novoObjetoMapa(chave, valor);
				
				var arrayAntes = this.getArray().splice(0, indice);
				arrayAntes.push(objetoMapa);
				arrayAntes = arrayAntes.concat(this.getArray());
				
				this.setArray(arrayAntes);
			}
		}
	};
	
	/**
	 * Retorna true se a chave solicitada existe no mapa.
	 *
	 * @chave Chave do objeto
	 */
	this.isExiste = function(chave) {
		return (this.getIndice() > -1);
	};
	
	/**
	 * Retorna o índice da chave solicitada.
	 */
	this.getIndice = function(chave) {
		var resultado = -1;
		for (var indice = 0; indice < this.getTamanho() && resultado == -1; indice++) {
			var objetoMapa = this.getArray()[indice];
			if (objetoMapa.getChave() == chave) {
				resultado = indice;
			}
		}
		return resultado;
	};
	
	/** 
	 * Retorna o objeto da chave solicitada.
	 *
	 * @chave Chave do objeto
	 */
	this.recuperar = function(chave) {
		var resultado = null;
		
		var indice = this.getIndice(chave);
		if (indice > -1) {
			resultado = this.getArray()[indice];
		}
		
		return resultado;
	};
	
	/** 
	 * Remove o objeto da chave solicitada.
	 *
	 * @chave Chave do objeto
	 */
	this.remover = function(chave) {
		var resultado = null;
		
		var indice = this.getIndice(chave);
		if (indice != -1) {
			resultado = this.getArray().splice(indice, 1);
		}
		return resultado;
	};
	
	/**
	 * Retorna o tamanho do mapa.
	 */
	this.getTamanho = function() {
		return this.getArray().length;
	};
	
	/**
	 * Retorna o array mantido pelo mapa.
	 */
	this.getArray = function() {
		return array;
	};
	
	/**
	 * Atribui um array ao mapa.
	 *
	 * @novoArray Array de objetos. 
	 */
	this.setArray = function(novoArray) {
		array = novoArray;
	};
	
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/
	
	/**
	 * Cria um novo objeto do tipo ObjetoMapa.
	 *
	 * @chave Chave do objeto
	 * @valor Objeto que será inserido.
	 */
	this.novoObjetoMapa = function(chave, valor) {
		var resultado = new ObjetoMapa();
		resultado.setChave(chave);
		resultado.setValor(valor);
		return resultado;
	};
}
	

/**
 * Objeto que compôe os objetos do mapa.
 */
function ObjetoMapa() {
	var chave = null;
	var valor = null;
	
	this.getChave = function() {
		return chave;
	};
	
	this.setChave = function(c) {
		chave = c;
	};
	
	this.getValor = function() {
		return valor;
	};
	
	this.setValor = function(v) {
		valor = v;
	};
}