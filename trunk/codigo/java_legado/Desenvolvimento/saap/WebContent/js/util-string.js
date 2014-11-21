
/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 01/05/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipula��o de string's.
 *******************************************************************************
 * Depend�ncias: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *******************************************************************************
 * m�todos do objeto:
 *		isStringsIguais(string0, string1)
 *		isStringContidaEm(string, localizar)
 *		isLetraDefinidaContidaEm(string, localizar)
 *		isLetraContidaEm(string)
 *		substituirString(string, localizar, novaString)
 *		substituirStringNoIndice(string, indice, novaString)
 *		inserirStringNaEsquerda(string, novaString, quantidade, total)
 *		inserirStringNaDireita(string, novaString, quantidade, total)
 *		removerAcentuacao(string)
 *		removerCaracteresEspeciais(string)
 *		removerString(string, localizar)
 *		removerStringsDaEsquerda(string, localizar)
 *		removerStringsDaDireita(string, localizar)
 *		removerLetras(string)
 *		removerLetrasComExcecao(string, excecao)
 *		getTamanho(string)
 *		separarStringPor(string, separador)
 *		trim(string)
 *		maiuscula(string)
 *		min�scula(string)
 *		converterCodigoHTMLparaASCI(string)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilString();
 *		alert(objeto.isStringsIguais("x", "x"));
 *******************************************************************************
 */
 
UtilString.prototype = new UtilNumero();

function UtilString() {
	var CARACTERES_ESPECIAIS = "<>!@#$%�&�()*'\"\\\\/";
	
	/**
	 * Retorna true se as strings forem iguais.
	 *
	 * @string0
	 * @string1 
	 */
	this.isStringsIguais = function(string0, string1) {
		return (string0 == string1);
	}
	
	/**
	 * Retorna true se a string localizada exitir na string definida.
	 *
	 * @string String que sofrer� a localiza��o.
	 * @localizar String que ser� localizada.
	 */
	this.isStringContidaEm = function(string, localizar) {
		var res = false;

		if (!this.isStringVazia(string) && !this.isStringVazia(localizar)) {
			string = new String(string);
			localizar = new RegExp(localizar, "g");
			res = string.search(localizar) != -1
		}
		
		return res;
	}
	
	/**
	 * Retorna true se a string possuir alguma letra definida no par�metro localizar.
	 *
	 * @string String que ser� verificada
	 * @localizar Lista de strings
	 */
	this.isLetraDefinidaContidaEm = function(string, localizar) {
		var res = false;

		if (!this.isStringVazia(string) && !this.isStringVazia(localizar)) {
			string = new String(string);
			localizar = this.separarStringPor(localizar, "|");
			localizar = "["+ localizar +"]";
			localizar = new RegExp(localizar, "g");
			res = string.search(localizar) != -1
		}
		
		return res;
	}
	
	/**
	 * Retorna true se existir alguma letra na string passada por par�metro.
	 *
	 * string String validada.
	 */
	this.isLetraContidaEm = function(string) {
		var res = false;
		
		if (!this.isStringVazia(string)) {
			res = this.isStringContidaEm(string, "[^0-9]");
		}
	
		return res;
	}
	
	/**
	 * Substitui a string localizada caso ela exista.
	 *
	 * @string String que ser� alterada. 
	 * @locali(zar String que ser� localizada.
	 * @novaString Nova string.
	 */
	this.substituirString = function(string, localizar, novaString) {
		if (!this.isStringVazia(string) && !this.isStringVazia(localizar)) {
			string = new String(string);
			localizar = new RegExp(localizar, "g");
			string = string.replace(localizar, novaString);	
		}
		
		return string;
	}
	
	/**
	 * Substitui a string do �ndice determinado por uma nova String.
	 *
	 * @string String que ser� alterada.  
	 * @indice �ndice da String
	 * @novaString  Nova string.
	 */
	this.substituirStringNoIndice = function(string, indice, novaString) {
		if (!this.isStringVazia(string) && !this.isStringVazia(indice)) {
			if (this.isStringVazia(novaString)) {
				novaString = "";
			}
			indice =  this.novoInteiro(indice);
			var prefixo = string.substring(0, indice);
			var sufixo = string.substring(indice+1, string.length);

			string = prefixo + novaString + sufixo;
		}
		
		return string;
	}
	
	/**
	 * Insere uma cadeia de strings � esquerda.
	 *
	 * @string String que ser� usada para inserir os novos valores.
	 * @novaString String que ser� adicionada.
	 * @quantidade Quantidade de ocorr�ncias.
	 * @total Total de ocorr�ncias permitida.
	 */
	this.inserirStringNaEsquerda = function(string, novaString, quantidade, total) {
		if (!this.isStringVazia(novaString)) {
			if (!this.isReferencia(string)) {
				string = "";
			}
			var inicio = string.length;
			var fim = inicio + this.novoInteiro(quantidade);
			if (!this.isStringVazia(total)) {
				fim = this.novoInteiro(total);
			}
			
			for (var tamanho = inicio; tamanho < fim; tamanho = string.length) {
				string = novaString + string;
			}
		}
		return string;
	}
	
	/**
	 * Insere uma cadeia de strings � direita.
	 *
	 * @string String que ser� usada para inserir os novos valores.
	 * @novaString String que ser� adicionada.
	 * @quantidade Quantidade de ocorr�ncias.
	 * @total Total de ocorr�ncias permitida.
	 */
	this.inserirStringNaDireita = function(string, novaString, quantidade, total) {
		if (!this.isStringVazia(novaString)) {
			var inicio = string.length;
			var fim = inicio + this.novoInteiro(quantidade);
			if (!isStringVazia(total)) {
				fim = this.novoInteiro(total);
			}
			
			for (var tamanho = inicio; tamanho < fim; tamanho = string.length) {
				string += novaString;
			}
		}
		return string;
	}
	
	/**
	 * Remove os caracteres especiais de uma string.
	 *
	 * @string String que ser� usada para remover os caractes acentuados.
	 */
	this.removerCaracteresEspeciais = function(string) {
		if (!this.isStringVazia(string)) {
			string = this.substituirString(string, "["+ CARACTERES_ESPECIAIS +"]", "");
		}
		
		return string;
	}
	
	/**
	 * Remove uma string.
	 *
	 * @string String que sofrer� a remo��o dos caracteres.
	 * @localizar String que ser� removida.
	 */
	this.removerString = function(string, localizar) {
		
		if (!this.isStringVazia(string) && !this.isStringVazia(localizar)) {
			string = this.substituirString(string, localizar, "");
		}
		return string;
	}
	
	/**
	 * Remove as string's � esquerda.
	 *
	 * @string String que sofrer� a remo��o dos caracteres.
	 * @localizar String que ser� removida.
	 */
	this.removerStringsDaEsquerda = function(string, localizar) {
		if (!this.isStringVazia(string)) {
			while (string.charAt(0) == localizar) {
				string = string.substring(1);
			}
		}
		return string;
	}

	/**
	 * Remove as string's � direita.
	 *
	 * @string String que sofrer� a remo��o dos caracteres.
	 * @localizar String que ser� removida.
	 */
	this.removerStringsDaDireita = function(string, localizar) {
		if (!this.isStringVazia(string)) {
			while (string.charAt(string.length-1) == localizar) {
				string = string.substring(0, string.length-1);
			}
		}
		return string;
	}
	
	/**
	 * Remove as letras da string, restando somente os n�meros.
	 * 
	 * @string String que sofrer� a remo��o. 
	 */
	this.removerLetras = function(string) {
		var res = null;
		
		if (!this.isStringVazia(string)) {
			res = string.replace(/[^0-9]/g, "");
		}
		return res;
	}
	
	/**
	 * Remove todos os caracteres da string, exceto os determinados por par�metro.
	 *
	 * @string String que ser� alterada
	 * @excecao Strings que n�o ser�o removidas.
	 */
	this.removerLetrasComExcecao = function(string, excecao) {
		
		if (!this.isStringVazia(string) && !this.isStringVazia(excecao)) {
			string = this.substituirString(string, "[^"+ excecao +"]", "");
		}
		return string;
	}

	/**
	 * Retorna o tamanho da string.
	 * 
	 * @string String
	 */
	this.getTamanho = function(string) {
		return (this.isStringVazia(string) ? 0: string.length);
	}
	
	/**
	 * Adiciona um separador na string.
	 *
	 * @string String que ser� alterada.
	 * @separador Separador que ser� usado.
	 */
	this.separarStringPor = function(string, separador) {
		
		if (!this.isStringVazia(string) && !this.isStringVazia(separador)) {
			var nova = "";
			for (var indice = 0; indice < this.getTamanho(string); indice++) {
				nova += string.charAt(indice);
				if (indice < (this.getTamanho(string) - 1)) {
					nova += separador;
				}
			}
			string = nova;
		}
		return string;
	}
	
	/**
	 * Fun��o respons�vel em remover os espa�os iniciais e finais de uma string.
	 *
	 * @string String que ser� alterada.
	 */
	this.trim = function(string) {

		if (!this.isStringVazia(string)) {
			string = string.replace(/^\s+|\s+$/g, "")
		}
		
		return string;
	}

	/**
	 * Converte todas as letras da string em mai�scula.
	 *
	 * @string String que ser� alterada.
	 */
	this.maiuscula = function(string) {

		if (!this.isStringVazia(string)) {
			string = string.toLocaleUpperCase();
		}
		
		return string;
	}

	/**
	 * Converte todas as letras da string em min�scula.
	 *
	 * @string String que ser� alterada.
	 */
	this.minuscula = function(string) {

		if (!this.isStringVazia(string)) {
			string = string.toLocaleLowerCase();
		}
		
		return string;
	}
	
	/**
	 * Converte os c�digos HTML de uma string em ASCI.
	 * Ex: &aacute; para "�"
	 * 
	 * @string String que ser� convertida.
	 */
	this.converterCodigoHTMLparaASCI = function(string) {
		var htmlCode = new Array('&aacute;', '&acirc;', '&acirc;', '&acute;', '&aelig;', '&agrave;', '&amp;', '&aring;', '&atilde;', '&auml;', '&brvbar;', '&ccedil;', '&cedil;', '&cent;', '&copy;', '&curren;', '&deg;', '&divide;', '&eacute;', '&ecirc;', '&egrave;', '&eth;', '&euml;', '&frac12;', '&frac14;', '&frac34;', '&iacute;', '&icirc;', '&iexcl;', '&igrave;', '&iquest;', '&iuml;', '&laquo;', '&ldquo;', '&lsquo;', '&macr;', '&mdash;', '&micro;', '&middot;', '&nbsp;', '&ndash;', '&not;', '&ntilde;', '&oacute;', '&ocirc;', '&ograve;', '&ordf;', '&ordm;', '&oslash;', '&otilde;', '&ouml;', '&para;', '&plusmn;', '&pound;', '&quot;', '&raquo;', '&rdquo;', '&reg;', '&rsquo;', '&sect;', '&shy;', '&sup1;', '&sup2;', '&sup3;', '&szlig;', '&thorn;', '&times;', '&uacute;', '&ucirc;', '&ugrave;', '&uml;', '&uuml;', '&yacute;', '&yen;');
		var charCode = new Array(225, 226, 226, 180, 230, 224, 38, 229, 227, 228, 124, 231, 184, 162, 169, 63, 176, 247, 233, 234, 232, 60, 235, 49, 49, 51, 237, 238, 161, 236, 191, 239, 171, 32, 32, 175, 32, 181, 183, 160, 32, 172, 241, 243, 244, 242, 170, 186, 248, 245, 246, 182, 177, 163, 34, 187, 32, 174, 32, 167, 45, 49, 50, 51, 223, 60, 42, 250, 251, 249, 168, 252, 121, 165);
		var stripped = string.replace(/(<([^>]+)>)/ig,""); 
		stripped = stripped.replace(/\t/g," "); 
		stripped = stripped.replace(/(  )*/g,"");
		for(x=0;x<htmlCode.length;x++) {
			var reg = new RegExp(htmlCode[x], "ig");
			stripped = stripped.replace(reg,String.fromCharCode(charCode[x]));
		}
		for(x=32;x<=127;x++) {
			var reg = new RegExp('&#'+x+';', "ig"); 
			stripped = stripped.replace(reg,String.fromCharCode(x));
		}
		
		return stripped;
	}
	
/*******************************************************************************
 * Fun��es privadas do objeto.
 ******************************************************************************/

}
