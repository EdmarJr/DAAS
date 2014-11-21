/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para validação de formulário.
 *******************************************************************************
 * Dependências:
 *		Nenhuma
 *******************************************************************************
 * métodos do objeto:
 *		adicionarValidador(valor, mensagem)
 *		adicionarValidadorComAcao(valor, mensagem, acao)
 *		validar()
 *******************************************************************************
 * Exemplo:
 *		var validadores = new UtilValidadorDeFormulario();
 *
 * 		var validador = new UtilValidadorDeFormulario();
 *		validador.adicionarValidador(validadores.validarNumero("15"), "Número inválido");
 *		validador.adicionarValidadorComAcao(validadores.validarNumeroComAcao("15"), "Número inválido", "form['codigoMatricula'].focus()");
 *		alert(validador.validar());
 *******************************************************************************
 */
function UtilValidadorDeFormulario() {
	var VALOR 		= 0;
	var MENSAGEM 	= 1;
	var ACAO 		= 2;
	var array 		= new Array();
	
	/**
	 * Adiciona uma validação.
	 *
	 * @valor (true/false) Resultado da validação
	 * @mensagem Mensagem exibida caso o valor seja false.
	 */
	this.adicionarValidador = function(valor, mensagem) {
		array.push(new Array(valor, mensagem, null));
	}
	
	/**
	 * Adiciona uma validação com ação.
	 *
	 * @valor (true/false) Resultado da validação
	 * @mensagem Mensagem exibida caso o valor seja false.
	 * @acao String da ação que será executada. Será invocado eval da ação definida.
	 */	
	this.adicionarValidadorComAcao = function(valor, mensagem, acao) {
		array.push(new Array(valor, mensagem, acao));
	}
	
	/**
	 * Percorre as validações registradas.
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