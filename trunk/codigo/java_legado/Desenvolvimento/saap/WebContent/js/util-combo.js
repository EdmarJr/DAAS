/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação combos
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *		UtilData		(util-data.js)
 *		UtilEvento		(util-evento.js)
 *		UtilFormulario	(util-formulario.js)
 *******************************************************************************
 * métodos do objeto:
 *		adicionarNaPosicaoDoSelect(select, valor, texto, posicao)
 *		adicionarNoSelect(select, valor, texto)
 *		isExisteOptionNoSelect(select, valor)
 *		isTemOpcaoSelecionada(select)
 *		moverOptionsDosSelects(origem, destino, todos, ordenar)
 *		ordenarSelect(select)
 *		ordenarSelectDefinindoValor(select, valorDoPrimeiro, textoDoPrimeiro)
 *		removerDoSelect(select, valor)
 *		removerOptionsSelecionadosDoSelect(select)
 *		selecionarTodosDoSelectMultiplo(select)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilFormulario();
 *		alert(objeto.isSelect(document.forms[0]["selectCidade"]);
 *******************************************************************************
 */

UtilCombo.prototype = new UtilFormulario();

function UtilCombo() {
	var SEPARADOR = "§";
	
	/**
	 * Adiciona uma opção em uma posição determinada do select.
	 *
	 * @select Objeto do tipo select
	 * @valor Valor da nova opção
	 * @texto Texto da nova opção
	 * @posicao Posição do select onde a opção será inserida.
	 */
	this.adicionarNaPosicaoDoSelect = function(select, valor, texto, posicao) {
		if (this.isSelect(select) && 
			!this.isStringVazia(texto) && 
			!this.isExisteOptionNoSelect(select, valor)) {
	
			var options = select.options;
			
			if (!this.isNumero(posicao)) {
				posicao = options.length;
			}
	
			if (posicao < options.length) {
				var array = new Array();
				var indice = 0;
				var option = null;
				
				for (indice = 0; indice < options.length; indice++) {
					array[indice] = options[indice];
				}
				
				option = new Option(texto, valor);
				array.splice(posicao, 0, option);
				this.selecionarTodosDoSelectMultiplo(select);
				this.removerOptionsSelecionadosDoSelect(select);
				
				for (indice = 0; indice < array.length; indice++) {
					option = array[indice];
					this.adicionarNoSelect(select, option.value, option.text);
				}
			} else {
				option = new Option(texto, valor);
				select.options[posicao] = option;
			}
		}
	};
	
	/**
	 * Adiciona uma opção no select.
	 *
	 * @select Objeto do tipo select
	 * @valor Valor da nova opção
	 * @texto Texto da nova opção
	 */
	this.adicionarNoSelect = function(select, valor, texto) {
		this.adicionarNaPosicaoDoSelect(select, valor, texto, null);
	};

	/**
	 * Retorna true se a o valor definido existe no select.
	 *
	 * @select
	 * @valor
	 */
	this.isExisteOptionNoSelect = function(select, valor) {
		var res = false;
		
		if (this.isSelect(select)) {
			var options = select.options;
			
			for (var indice = 0; indice < options.length && res == false; indice++) {
				var option = options[indice];
				res = (option.value == valor);
			}
		}
		
		return res;
	};

	/**
	 * Retorna true se o select tiver alguma opção selecionada.
	 *
	 * @select Objeto do tipo select
	 */
	this.isTemOpcaoSelecionada = function(select) {
		var res = false;
		
		if (this.isSelect(select)) {
			var options = select.options;
			for (var indice = 0; indice < options.length && res == false; indice++) {
				res = options[indice].selected;
			}
		}
		
		return res;
	};
	
	/**
	 * Move as opções selecionadas do select de origem para o select de destino.
	 *
	 * @origem select de origem
	 * @destino select de destino
	 * @todos (true/false) se true todas as opções serão movidas.
	 * @ordenar (true/false) se true o select será ordenado.
	 */
	this.moverOptionsDosSelects = function(origem, destino, todos, ordenar) {
		if (this.isSelect(origem) && this.isSelect(destino)) {
			todos 	= eval(todos);
			ordenar	= eval(ordenar);
			
			if (todos == true) {
				this.selecionarTodosDoSelectMultiplo(origem);
			}

			var options = origem.options;
			for (var indice = 0; indice < options.length; indice++) {
				var option = options[indice];
				if (option.selected == true) {
					this.adicionarNoSelect(destino, option.value, option.text);
					origem.remove(indice);
					indice--;
				}
			}
			
			if (ordenar) {
				this.ordenarSelect(destino)
			}
		}
	};

	/**
	 * Ordena as opções do select.
	 *
	 * @select Objeto do tipo select
	 */
	this.ordenarSelect = function(select) {
		if (this.isSelect(select)) {
			var textos = new Array();
			var options = select.options;
			
			for (var indice = 0; indice < options.length; indice++) {
				var option = options[indice];
				var texto = option.text;
				var valor = option.value;
				
				textos[indice] = texto.concat(SEPARADOR).concat(valor);
			}
			textos.sort();
			this.selecionarTodosDoSelectMultiplo(select);
			this.removerOptionsSelecionadosDoSelect(select);
			
			for (var indice = 0; indice < textos.length; indice++) {
				var option = new String(textos[indice]);
				var option = option.split(SEPARADOR);
				
				var texto = option[0];
				var valor = option[1];
	
				this.adicionarNoSelect(select, valor, texto)
			}
		}
	};
	
	/**
	 * Ordena as opções do select definindo qual será a primeira opção.
	 *
	 * @select Objeto do tipo select
	 * @valorDoPrimeiro
	 * @textoDoPrimeiro
	 */
	this.ordenarSelectDefinindoValor = function(select, valorDoPrimeiro, textoDoPrimeiro) {
		this.ordenarSelect(select);
		this.removerDoSelect(select, valorDoPrimeiro);
		this.adicionarNaPosicaoDoSelect(select, valorDoPrimeiro, textoDoPrimeiro, 0);
	};

	/**
	 * Remove uma opção do select.
	 *
	 * @select Objeto do tipo select
	 * @valor Valor que será removido do select
	 */
	this.removerDoSelect = function(select, valor) {
		if (this.isSelect(select)) {
			var options = select.options;
			for (var indice = 0; indice < options.length; indice++) {
				if (options[indice].value == valor) {
					select.remove(indice);
					indice--;
				}
			}
		}
	};
		
	/**
	 * Remove todas as opções selecionadas do select.
	 *
	 * @select Objeto do tipo select 
	 */
	this.removerOptionsSelecionadosDoSelect = function(select) {
		if (this.isSelect(select)) {
			var options = select.options;
			for (var indice = 0; indice < options.length; indice++) {
				if (options[indice].selected == true) {
					select.remove(indice);
					indice--;
				}
			}
		}
	};
	
	/**
	 * Seleciona todos as opções do select multiplo.
	 *
	 * @select Objeto do tipo select-multiple
	 */
	this.selecionarTodosDoSelectMultiplo = function(select) {
		if (this.isReferencia(select) && this.isSelectMultiple(select)) {
			var options = select.options;
			for (var indice = 0; indice < options.length; indice++) {
				options[indice].selected = true;
			}
		}
	};
	
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

}