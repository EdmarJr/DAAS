/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação de objetos de formulário.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *		UtilData		(util-data.js)
 *		UtilEvento		(util-evento.js)
 *******************************************************************************
 * métodos do objeto:
 *		limparFormulario(form)
 *		desmarcarRadio(radios)
 *		desmarcarCheckbox(checkboxes)
 *		isSelect(objeto)
 *		isSelectOne(objeto)
 *		isSelectMultiple(objeto)
 *		isText(objeto)
 *		isHidden(objeto)
 *		isTextArea(objeto)
 *		isPassword(objeto)
 *		isFile(objeto)
 *		isRadio(objeto)
 *		isCheckbox(objeto)
 *		focoNoPrimeiroObjeto(form)
 *		getValorDoSelect(select)
 *		getTextoDoSelect(select)
 *		getValorDoRadio(form, nome)
 *		getValor(form, nome)
 *		setValor(form, nome, valor)
 *		foco(form, nome)
 *		moverFocoParaProximoCampo(objeto)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilFormulario();
 *		alert(objeto.isSelect(document.forms[0]["selectCidade"]);
 *******************************************************************************
 */

UtilFormulario.prototype = new UtilEvento();

function UtilFormulario() {
	
	/**
	 * Limpa todos os campos do formulário.
	 *
	 * @form Objeto do tipo form.
	 */
	this.limparFormulario = function(form) {
		if (this.isReferencia(form)) {
			for (var indice = 0; indice < form.length; indice++) {
				var campo = form[indice];
				if (campo.style.display != "none" && campo.style.visibility != "hidden") {
					if (this.isText(campo) || 
						this.isTextArea(campo) ||
						this.isPassword(campo) ||
						this.isFile(campo)) {
						campo.value = "";	
					}
					
					if (this.isSelectOne(campo)) {
						campo.selectedIndex = 0;
					}
					
					if (this.isSelectMultiple(campo)) {
						campo.selectedIndex = -1;
					}
					
					if (this.isRadio(campo) || this.isCheckbox(campo)) {
						this.desmarcarRadio(campo);
					}
				}
			}
		}
	}
	
	/**
	 * Desmarca o rádio passado por parâmetro.
	 *
	 * @radios radio button ou array de radio button
	 */
	this.desmarcarRadio = function(radios) {
		if (this.isArray(radios)) {
			for (var indice = 0; indice < radios.length; indice++) {
				var radio = radios[indice];
				radio.checked = false;
			}
		} else {
			radios.checked = false;
		}
	}
	
	/**
	 * Desmarca o checkbox passado por parâmetro.
	 *
	 * @radios checkbox ou array de checkboxex
	 */
	this.desmarcarCheckbox = function(checkboxes) {
		if (this.isArray(checkboxes)) {
			for (var indice = 0; indice < checkboxes.length; indice++) {
				var checkbox = checkboxes[indice];
				checkbox.checked = false;
			}
		} else {
			checkboxes.checked = false;
		}
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo select.
	 *
	 * @objeto 
	 */
	this.isSelect = function(objeto) {
		return (this.isSelectOne(objeto) || this.isSelectMultiple(objeto));
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo select-one.
	 *
	 * @objeto
	 */
	this.isSelectOne = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "select-one");
	}
	
	/**
	 *	Retorna true se o objeto for um objeto do tipo select-multiple.
	 *
	 * @objeto
	 */
	this.isSelectMultiple = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "select-multiple");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo text.
	 *
	 * @objeto
	 */
	this.isText = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "text");
	}

	/**
	 * Retorna true se o objeto for um objeto do tipo hidden.
	 *
	 * @objeto
	 */
	this.isHidden = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "hidden");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo textarea.
	 *
	 * @objeto
	 */
	this.isTextArea = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "textarea");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo password.
	 *
	 * @objeto
	 */
	this.isPassword = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "password");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo file.
	 *
	 * @objeto
	 */
	this.isFile = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "file");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo radio.
	 *
	 * @objeto
	 */
	this.isRadio = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "radio");
	}
	
	/**
	 * Retorna true se o objeto for um objeto do tipo checkbox.
	 *
	 * @objeto
	 */
	this.isCheckbox = function(objeto) {
		return (this.isReferencia(objeto) && 
				this.isReferencia(objeto.type) && 
				objeto.type == "checkbox");
	}
	
	/**
	 * Seta o foco no primeiro objeto do formulário.
	 *
	 * @form Objeto de formulário.
	 */
	this.focoNoPrimeiroObjeto = function(form) {
		if (this.isReferencia(form)) {
			var focoSetado = false;
			for (var indice = 0; indice < form.length && focoSetado == false; indice++) {
				var campo = form[indice];

				if (!this.isHidden(campo) && 
					campo.disabled != true &&
					campo.style.display != "none" && 
					campo.style.visibility != "hidden") {

					campo.focus();
					focoSetado = true;
				}
			}
		}
	}
	
	/**
	 * Retorna o valor de um select.
	 *
	 * @select Select
	 */
	this.getValorDoSelect = function(objeto) {
		var res = null;
		
		if (this.isSelect(objeto)) {
			res = objeto[objeto.selectedIndex].value;
		}
		return res;
	}
	
	/**
	 * Retorna o valor do label de um select.
	 *
	 * @select Select
	 */
	this.getTextoDoSelect = function(objeto) {
		var res = null;
		
		if (this.isSelect(objeto)) {
			res = objeto[objeto.selectedIndex].text;
		}
		return res;
	}
	
	/**
	 * Retorna o valor do radio marcado.
	 *
	 * @form Objeto de formulário.
	 * @nome Nome do campo de formulário que representa o radio.
	 */
	this.getValorDoRadio = function(form, nome) {
		var res = null;
		if (this.isReferencia(form) && !this.isStringVazia(nome)) {
			var radios = form[nome];
			if (radios.length == undefined) {			
				res = (radios.checked == true? radios.value: null);
			}else {
				for (var indice = 0; indice < radios.length  && !this.isReferencia(res); indice++) {
					var radio = radios[indice];
					res = (radio.checked == true? radio.value: null);
				}
			}
		}
		return res;
	}
	
	/**
	 * Retorna o valor do campo de formulário.
	 *
	 * @form Objeto form
	 * @nome Nome do campo de formulário.
	 */
	this.getValor = function(form, nome) {
		var res = null;
		
		if (this.isReferencia(form) && !this.isStringVazia(nome)) {
			var objeto = form[nome];
			if (this.isReferencia(objeto)) {
				if (this.isSelect(objeto)) {
					res = this.getValorDoSelect(objeto);
				} else {
					res = objeto.value;
				}
			}
		}
		return res;
	}

	/**
	 * Retorna o valor do campo de formulário.
	 *
	 * @form Objeto form
	 * @nome Nome do campo de formulário.
	 * @valor Valor que será atribuído.
	 */
	this.setValor = function(form, nome, valor) {
		var res = null;
		
		if (this.isReferencia(form) && !this.isStringVazia(nome)) {
			var objeto = form[nome];
			if (this.isReferencia(objeto)) {
				objeto.value = valor;
			}
		}
		return res;
	}
	
	/**
	 * Seta o foco no campo informado.
	 *
	 * @form Objeto form
	 * @nome Nome do campo de formulário.
	 */
	this.foco = function(form, nome) {
		
		if (this.isReferencia(form) && !this.isStringVazia(nome)) {
			var objeto = form[nome];
			if (this.isReferencia(objeto)) {
				objeto.focus();
			}
		}
	}
	
	/**
	 * Seta o foco no próximo campo.
	 *
	 * @objeto Objeto de formulário.
	 */
	this.moverFocoParaProximoCampo = function(objeto) {
		if (this.isReferencia(objeto)) {
			var formulario 		= objeto.form;
			var foco 			= false;
			var iniciarBusca 	= false;
			for (var indice = 0; indice < formulario.length && !foco; indice++) {
				var campo = formulario[indice];
				if (objeto == campo) {
					iniciarBusca = true;
				}
				
				if (iniciarBusca) {
					if (objeto != campo && !this.isHidden(campo) && campo.readOnly == false) {
						try {
							campo.focus();
							foco = true;
						} catch (erro) {}
					}
				}
			}
		}
	}
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

}