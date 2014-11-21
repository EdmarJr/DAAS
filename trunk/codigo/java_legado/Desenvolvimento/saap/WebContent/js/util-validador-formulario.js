/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 01/05/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para valida��o de formul�rio.
 *******************************************************************************
 * Depend�ncias:
 *		Nenhuma
 *******************************************************************************
 * m�todos do objeto:
 *		adicionarValidador(valor, mensagem)
 *		adicionarValidadorComAcao(valor, mensagem, acao)
 *		validar()
 *******************************************************************************
 * Exemplo:
 *		var validadores = new UtilValidadorDeFormulario();
 *
 * 		var validador = new UtilValidadorDeFormulario();
 *		validador.adicionarValidador(validadores.validarNumero("15"), "N�mero inv�lido");
 *		validador.adicionarValidadorComAcao(validadores.validarNumeroComAcao("15"), "N�mero inv�lido", "form['codigoMatricula'].focus()");
 *		alert(validador.validar());
 *******************************************************************************
 */
function UtilValidadorDeFormulario() {
	var VALOR 		= 0;
	var MENSAGEM 	= 1;
	var ACAO 		= 2;
	var array 		= new Array();
	
	/**
	 * Adiciona uma valida��o.
	 *
	 * @valor (true/false) Resultado da valida��o
	 * @mensagem Mensagem exibida caso o valor seja false.
	 */
	this.adicionarValidador = function(valor, mensagem) {
		array.push(new Array(valor, mensagem, null));
	}
	
	/**
	 * Adiciona uma valida��o com a��o.
	 *
	 * @valor (true/false) Resultado da valida��o
	 * @mensagem Mensagem exibida caso o valor seja false.
	 * @acao String da a��o que ser� executada. Ser� invocado eval da a��o definida.
	 */	
	this.adicionarValidadorComAcao = function(valor, mensagem, acao) {
		array.push(new Array(valor, mensagem, acao));
	}
	
	/**
	 * Percorre as valida��es registradas.
	 */
	this.validar = function() {
		var validacao 	= true;
		var indice 		= 0;
		
		for (indice = 0; (indice < array.length) && (validacao == true); indice++) {
			validacao = array[indice][VALOR];
		}
		indice--;
			
		if (!validacao) {
			alert(array[indice][MENSAGEM]);
			if (array[indice][ACAO] != null) {
				eval(array[indice][ACAO]);
			}
		}
		
		return validacao;
	}
}