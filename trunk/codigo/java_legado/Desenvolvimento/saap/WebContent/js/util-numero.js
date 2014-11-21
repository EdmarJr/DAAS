/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 01/05/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipula��o de n�meros.
 *******************************************************************************
 * Depend�ncias: 
 *		UtilObjeto		(util-objeto.js)
 *******************************************************************************
 * m�todos do objeto:
 *		isNumero(string)
 *		novoInteiro(string)
 *		novoFloat(string)
 *		getMaiorValor(numero0, numero1)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilNumero();
 *		alert(objeto.isNumero("35"));
 *******************************************************************************
 */
 
UtilNumero.prototype = new UtilObjeto();

function UtilNumero() {
	
	/**
	 * Retorna true se a string for um n�mero.
	 *
	 * @string
	 */
	this.isNumero = function(string) {
		var res = false;
		if (!this.isStringVazia(string)) {
			var localizar = new RegExp("[^0-9]");
			res = !localizar.test(string);
		}
		
		return res;
	}

	/**
	 * Retorna true se a string for um n�mero real.
	 *
	 * @string
	 */
	this.isReal = function(string) {
		var res = false;
		if (!this.isStringVazia(string)) {
			string = this.formatarParaRealValido(string);
			res = !isNaN(string);
		}
		
		return res;
	}
	
	/**
	 * Retorna um inteiro da string.
	 *
	 * @string
	 */
	this.novoInteiro = function(string) {
		var res = 0;
		
		if (!this.isStringVazia(string) && this.isNumero(string)) {
			res = parseInt(string);
		}
		return res;
	}
	
	/**
	 * Retorna um float da string.
	 *
	 * @string
	 */
	this.novoFloat = function(string) {
		var res = 0;
		
		if (!this.isStringVazia(string) && this.isReal(string)) {
			string = this.formatarParaRealValido(string);
			res = parseFloat(string);
		}
		return res;
	}
	
	/**
	 * Retorna o maior valor dos n�meros.
	 *
	 * @numero0 Tem que ser um n�mero v�lido.
	 * @numero1 Tem que ser um n�mero v�lido.
	 */
	this.getMaiorValor = function(numero0, numero1) {
		var res = 0;
		
		if (this.isNumero(numero0) && this.isNumero(numero1)) {
			numero0 = this.novoFloat(numero0);
			numero1 = this.novoFloat(numero1);
			res = (numero0 > numero1? numero0: numero1);
		}
		return res;
	}
	
/*******************************************************************************
 * Fun��es privadas do objeto.
 ******************************************************************************/

	/**
	 * Formata a string para um n�mero real v�lido para o javascript. Um n�mero real v�lido
	 * � composto pelo separador decimal ".".
	 * Ex:
	 *	N�mero real para a interface: 1.354,45
	 *	N�mero real v�lido para o javascript: 1354.45
	 */
	this.formatarParaRealValido = function(string) {
		if (!this.isStringVazia(string)) {
			var expressao = new RegExp("[,]", "g");
			if (expressao.test(string)) {
				expressao = new RegExp("[.]", "g");
				string = string.replace(expressao, "");
				
				expressao = new RegExp("[,]", "g");
				string = string.replace(expressao, ".");
			}
		}
		return string;
	}
}

