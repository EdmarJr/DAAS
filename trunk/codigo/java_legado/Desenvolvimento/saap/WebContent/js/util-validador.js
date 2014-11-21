/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para validação de dados.
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
 *		validarNumero(valor)
 *		validarCNPJ(cnpj)
 *		validarCPF(cpf)
 *		validarCartao(cartao)
 *		validarCPFouCNPJ(cpf)
 *		validarEMail(email)
 *		validarData(data)
 *		validarData_ddMM(data)
 *		validarData_MMyyyy(data)
 *		validarHora(hora)
 *		validarHora_HHmmss(hora)
 *		validarDataInicialIgualAFinal(dataInicial, dataFinal)
 *		validarDataInicialMaiorQueFinal(dataInicial, dataFinal)
 *		validarDataInicialMaiorOuIgualQueFinal(dataInicial, dataFinal)
 *		validarDataInicialMaiorQueFinal_ddMMyyyyHHmm(dataInicial, dataFinal)
 *		validarNumeroRealInicialMaiorQueFinal(realInicial, realFinal)
 *		validarNumeroInteiroInicialMaiorQueFinal(inteiroInicial, inteiroFinal)
 *		validarStringPreenchida(string)
 *		validarStringsIguais(string0, string1)
 *		validarCheckboxMarcado(checkboxes)
 *		validarRadioMarcado(radios)
 *		validarExistenciaDeCampo(campos)
 *		validarSelectMultiploMarcado(select)
 *		validarFormObrigatorio(form, mensagem)
 *		validarTamanhoStringInicialMenorOuIgualQueStringFinal(stringInicial, stringFinal)
 *		validarTamanhoStringInicialMenorQueStringFinal(stringInicial, stringFinal)
 *		validarCEP(cep)
 *		validarTelefone(cep)
 *		validarDataNoPeriodoDeAno(data, anoInicial, anoFinal)
 *		validarData_MMyyyyNoPeriodoDeAno(data, anoInicial, anoFinal)
 *		validarTamanhoDeStringMaiorOuIgual(string, tamanho)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilValidador();
 *		alert(objeto.validarNumero("15"));
 *******************************************************************************
 */
 
UtilValidador.prototype = new UtilFormulario();

function UtilValidador() {
	/**
	 * Retorna true se o valor for um número válido.
	 *
	 * @valor
	 */
	this.validarNumero = function(valor) {
		return (!this.isStringVazia(valor) && this.isNumero(valor));
	};
	
	/**
	 * Retorna true se o cnpj for válido.
	 *
	 * @cnpj
	 */
	this.validarCNPJ = function(cnpj) {
		var result = false;
		
		if (cnpj != "" && (parseFloat(cnpj) > 0)) {
			x = cnpj;
			var temp = "";
			for(i = 0; i < 19; i++){
				if(x.substr(i,1) != "." && x.substr(i,1) != "/" && x.substr(i,1) != "-"){
					temp = temp + x.substr(i,1);
				}
			}
			cnpj = temp;
		
			var
				strDV = cnpj.substr(12, 2),
				intSoma,
				intDigito = 0,
				strControle = "",
				strMultiplicador = "543298765432";
			cnpj = cnpj.substr(0, 12);
			for(var j = 1; j <= 2; j++){
				intSoma = 0;
				for(var i = 0; i <= 11; i++){
					intSoma += (parseInt(cnpj.substr(i, 1), 10) * parseInt(strMultiplicador.substr(i, 1), 10))
				}
				if(j == 2){intSoma += (2 * intDigito)}
				intDigito = (intSoma * 10) % 11;
				if(intDigito == 10){intDigito = 0}
				strControle += intDigito.toString();
				strMultiplicador = "654329876543";
			}
			result = (strControle == strDV);
		}
		
		return result;
	};
	
	/**
	 * Retorna true se o cpf for válido.
	 *
	 * @cpf
	 */
	this.validarCPF = function(cpf) {
		cpf = this.substituirString(cpf, "[.-]", "")
		if (this.isNumeroDeCPFNullOuPadrao(cpf) || this.getTamanho(cpf) != 11) {
			return false;
		}
		cpf1 = cpf.substr(0,9) //rcpf1
		cpf2 = cpf.substr(9,2) //rcpf2
	
	    d1 = 0;
	    for (i=0;i<9;i++)
	    d1 += cpf1.charAt(i)*(10-i);
	    d1 = 11 - (d1 % 11);
	    if (d1>9) d1 = 0;
	
	    if (cpf2.charAt(0) != d1){
			return false;
	    }
	
	    d1 *= 2;
	    for (i=0;i<9;i++){
	        d1 += cpf1.charAt(i)*(11-i);
		}
	    d1 = 11 - (d1 % 11);
	    if (d1>9) d1 = 0;
	    if (cpf2.charAt(1) != d1){
			return false;
	    }
	   
	    return true;
	};
	
	/**
	 * Retorna true se o valor passado por parâmetro for um cartão válido.
	 *
	 * @cartao Cartão.
	 */
	this.validarCartao = function(cartao) {
		var resultado = false;
		cartao = this.substituirString(cartao, "[.]", "");
		cartao = this.trim(cartao);
		return (!this.isStringVazia(cartao) && this.getTamanho(cartao) == 16);		
	}

	/**
	 * Retorna true se o valor for um CPF ou CNPJ.
	 * 
	 * @string CPF ou CNPJ.
	 */
	this.validarCPFouCNPJ = function(string) {
		return this.validarCPF(string) || this.validarCNPJ(string);
	};
	
	/**
	 * Retorna true se o email for válido.
	 *
	 * @email
	 */
	this.validarEMail = function(email) {
		var res = false;
		
		if (!this.isStringVazia(email)) {
			var reg = /^[\w!#$%&'*+\/=?^`{|}~-]+(\.[\w!#$%&'*+\/=?^`{|}~-]+)*@(([\w-]+\.)+[A-Za-z]{2,6}|\[\d{1,3}(\.\d{1,3}){3}\])$/;
			res = reg.test(email);
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a data for válida.
	 *
	 * @data Objeto date ou uma data no formato dd/MM/yyyy
	 */
	this.validarData = function(data) {
		return this.isDataValida(data);
	};
	
	/**
	 * Retorna true se a data for válida.
	 *
	 * @data Data no formato dd/MM
	 */
	this.validarData_ddMM = function(data) {
		var res = false;
		
		if (!this.isStringVazia(data)) {
			var ano = new Date().getFullYear();
			data += "/"+ ano;
			res = this.validarData(data);
		}
		
		return res;
	};

	/**
	 * Retorna true se a data for válida.
	 *
	 * @data Data no formato MM/yyyy
	 */
	this.validarData_MMyyyy = function(data) {
		var res = false;
		
		if (!this.isStringVazia(data)) {
			data = "01/"+ data;
			res = this.validarData(data);
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a hora for válida.
	 *
	 * @hora Hora no formato hh:MM
	 */
	this.validarHora = function(hora) {
		var res = false;
		
		if (!this.isStringVazia(hora)) {
			var reg =/^([0-1]\d|2[0-3]):[0-5]\d$/;
			res = reg.test(hora);
		}
		
		return res;
	};

	/**
	 * Retorna true se a hora for válida.
	 *
	 * @hora Hora no formato HH:mm:ss
	 */
	this.validarHora_HHmmss = function(hora) {
		var res = false;
		
		if (!this.isStringVazia(hora)) {
			var reg =/^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/;
			res = reg.test(hora);
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a data inicial for igual a data final.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.validarDataInicialIgualAFinal = function(dataInicial, dataFinal) {
		var res = false;
		
		if (!this.isStringVazia(dataInicial) && !this.isStringVazia(dataFinal)) {
			dataInicial 	= this.novoDate_ddMMyyyy(dataInicial);
			dataFinal 		= this.novoDate_ddMMyyyy(dataFinal);
			res = dataInicial.valueOf() == dataFinal.valueOf();
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a data inicial for maior que a data final.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.validarDataInicialMaiorQueFinal = function(dataInicial, dataFinal) {
		var res = false;
		
		if (!this.isStringVazia(dataInicial) && !this.isStringVazia(dataFinal)) {
			dataInicial 	= this.novoDate_ddMMyyyy(dataInicial);
			dataFinal 		= this.novoDate_ddMMyyyy(dataFinal);
			res = 	this.isReferencia(dataInicial) && 
				  	this.isReferencia(dataFinal) && 
					dataInicial.valueOf() > dataFinal.valueOf();
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a data inicial for maior ou igual que a data final.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.validarDataInicialMaiorOuIgualQueFinal = function(dataInicial, dataFinal) {
		var res = false;
		
		if (!this.isStringVazia(dataInicial) && !this.isStringVazia(dataFinal)) {
			dataInicial 	= this.novoDate_ddMMyyyy(dataInicial);
			dataFinal 		= this.novoDate_ddMMyyyy(dataFinal);
			res = 	this.isReferencia(dataInicial) && 
				  	this.isReferencia(dataFinal) && 
					dataInicial.valueOf() >= dataFinal.valueOf();
		}
		
		return res;
	};

	/**
	 * Retorna true se a data inicial for maior que a data final.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy HH:mm
	 * @dataFinal Data no formato dd/MM/yyyy HH:mm
	 */
	this.validarDataInicialMaiorQueFinal_ddMMyyyyHHmm = function(dataInicial, dataFinal) {
		var res = false;
		
		if (!this.isStringVazia(dataInicial) && !this.isStringVazia(dataFinal)) {
			dataInicial 	= this.novoDate_ddMMyyyyEspacohhMM(dataInicial);
			dataFinal 		= this.novoDate_ddMMyyyyEspacohhMM(dataFinal);
			res = 	this.isReferencia(dataInicial) && 
					this.isReferencia(dataFinal) && 
					(dataInicial.valueOf() > dataFinal.valueOf());
		}
		
		return res;
	};

	/**
	 * Retorna true se o número real inicial for maior que o número real final.
	 *
	 * @realInicial número real válido. (Ex: 1.359,44)
	 * @realFinal número real válido. (Ex: 1.359,44)
	 */
	this.validarNumeroRealInicialMaiorQueFinal = function(realInicial, realFinal) {
		var res = false;
		
		if (this.isReal(realInicial) && this.isReal(realFinal)) {
			realInicial 	= this.novoFloat(realInicial);
			realFinal 		= this.novoFloat(realFinal);
			res = realInicial > realFinal;
		}
		
		return res;
	};

	/**
	 * Retorna true se o número inteiro inicial for maior que o número inteiro final.
	 *
	 * @inteiroInicial número inteiro válido. (Ex: 1.359)
	 * @inteiroFinal número inteiro válido. (Ex: 1.359)
	 */
	this.validarNumeroInteiroInicialMaiorQueFinal = function(inteiroInicial, inteiroFinal) {
		var res = false;
		
		if (this.isNumero(inteiroInicial) && this.isNumero(inteiroFinal)) {
			inteiroInicial 	= this.novoInteiro(inteiroInicial);
			inteiroFinal 	= this.novoInteiro(inteiroFinal);
			res = inteiroInicial > inteiroFinal;
		}
		
		return res;
	};
	
	/**
	 * Retorna true se a string não estiver vazia.
	 *
	 * @string
	 */
	this.validarStringPreenchida = function(string) {
		return !this.isStringVazia(string);
	};
	
	/**
	 * Retorna true se as strings forem iguais.
	 *
	 * @string0
	 * @string1
	 */
	this.validarStringsIguais = function(string0, string1) {
		return (string0 == string1);
	};
	
	/**
	 * Retorna true se houver um checkbox marcado.
	 *
	 * @checkboxes Checkbox ou array de checkboxex.
	 */
	this.validarCheckboxMarcado = function(checkboxes) {
		var res = false;
		
		if (this.isArray(checkboxes)) {
			for (var indice = 0; indice < checkboxes.length && res == false; indice++) {
				res = checkboxes[indice].checked;
			}
		} else {
			res = (checkboxes != null && checkboxes.checked);
		}
	
		return res;
	};
	
	/**
	 * Retorna true se houver um radio marcado.
	 *
	 * @radios Radio ou array de radios.
	 */
	this.validarRadioMarcado = function(radios) {
		var res = false;
		
		if (this.isArray(radios)) {
			for (var indice = 0; indice < radios.length && res == false; indice++) {
				res = radios[indice].checked;
			}
		} else {
			res = (radios != null && radios.checked);
		}
		
		return res;
	};

	/**
	 * Retorna true se houver pelo menos um campo na lista passada por parâmetro.
	 *
	 * @campos Campo ou array de campos.
	 */
	this.validarExistenciaDeCampo = function(campos) {
		var res = false;
		
		if (this.isReferencia(campos)) {
			if (this.isArray(campos)) {
				res = (campos.length > 0);
			} else {
				res = true;
			}
		}
		return res;
	};
	
	/**
	 * Retorna true se houver uma opção do select marcado.
	 *
	 * @select
	 */
	this.validarSelectMultiploMarcado = function(select) {
		var res = false;
		
		if (this.isSelectMultiple(select)) {
			var options = select.options;
			
			for (var indice = 0; indice < options.length && res == false; indice++) {
				res = options[indice].selected;
			}
		}
		
		return res;
	};
	
	/**
	 * Valida os campos do formulário, todos os campos do formulário são obrigatórios por default.
	 * O campo do formulário pode conter o atributo "label-mensagem", se o campo estiver esse 
	 * atributo a mensagem poderá conter o indicador {campo}, que será substituído pelo atributo
	 * "label-mensagem".
	 * Exemplo:
	 *		validarFormObrigatorio(form, "O campo {campo} é de preenchimento obrigatório", "nascimento");
	 *		Se o campo tiver o atributo "label-mensagem" definido como "Nome" será exibida a mensagem 
	 *		"O campo Nome é de preenchimento obrigatório.
	 *		O campo de nome 'nascimento' não será obrigatório.
	 *
	 * @form Objeto do formulário
	 * @mensagem Mensagem exibida caso o campo esteja vazio. 
	 * @outros parametros: consiste no nome dos campos que seráo ignorados.
	 */
	this.validarFormObrigatorio = function(form, mensagem) {
		var res 			= true;
		var camposIgnorados	= "";
		
		//Criando lista dos campos que seráo ignorados.
		for (var indice = 2; indice < this.validarFormObrigatorio.arguments.length; indice++) {
			var campoIgnorado = this.validarFormObrigatorio.arguments[indice];
			camposIgnorados += ","+ campoIgnorado +","
		}

		if (form != null && form.tagName == "FORM") {
			for (var indice = 0; indice < form.length && res == true && form[indice].style.display != "none"; indice++) {
				var campo = form[indice];
				
				if (!this.isStringContidaEm(camposIgnorados, ","+ campo.name +",")) {
					switch (campo.type) {
						case "text":
						case "textarea":
						case "password":
						case "select-one":
						case "file":
							res = this.validarStringPreenchida(campo.value);
							break;
						case "select-multiple":
							res = this.validarSelectMultiploMarcado(campo);
							break;
						case "radio":
							res = this.validarRadioMarcado(form[campo.name]);
							break;
						case "checkbox":
							res = this.validarCheckboxMarcado(form[campo.name]);
							break;
					}
				}
			}
			
			//defindo a mensagem que será exibida.
			if (!res && !this.isStringVazia(mensagem)) {
				if (this.isStringContidaEm(mensagem, "{campo}")) {
					var labelMensagem = campo.getAttribute("label-mensagem");
					if (this.isStringVazia(labelMensagem)) {
						labelMensagem = "label-mensagem não definido";
					}
					mensagem = this.substituirString(mensagem, "{campo}", labelMensagem);
				}
				
				alert(mensagem);
				campo.focus();
			}
		}
		
		return res;
	};
	
	/**
	 * Retorna true se o tamanho da stringInicial for menor ou igual que a stringFinal
	 *
	 * @stringInicial String inicial
	 * @stringFinal String final
	 */
	this.validarTamanhoStringInicialMenorOuIgualQueStringFinal = function(stringInicial, stringFinal) {
		return this.getTamanho(stringInicial) <= this.getTamanho(stringFinal);
	};

	/**
	 * Retorna true se o tamanho da stringInicial for menor que a stringFinal
	 *
	 * @stringInicial String inicial
	 * @stringFinal String final
	 */
	this.validarTamanhoStringInicialMenorQueStringFinal = function(stringInicial, stringFinal) {
		return this.getTamanho(stringInicial) < this.getTamanho(stringFinal);
	};

	/**
	 * Retorna true se a string for um CEP válido.
	 *
	 * @string CEP que será validado 
	 */
	this.validarCEP = function(string) {
		string = this.removerLetras(string);
		return this.isNumero(string) && (this.getTamanho(string) == 8)
	};
	
	/**
	 * Retorna true se a string for um telefone de 8 dígitos.
	 *
	 * @string telefone.
	 */
	this.validarTelefone = function(string) {
		string = this.removerLetras(string);
		return this.isNumero(string) && (this.getTamanho(string) == 8)
	};
	
	/**
	 * Retorna true se a data estiver no período determinado.
	 * 
	 * @data Data no formato dd/MM/yyyy.
	 * @anoInicial Ano inicial
	 * @anoFinal Ano final 
	 */
	this.validarDataNoPeriodoDeAno = function(data, anoInicial, anoFinal) {
		var resultado = false;
		
		if (!this.isStringVazia(data) && !this.isStringVazia(anoInicial) && !this.isStringVazia(anoFinal)) {
			var dataAtual 	= this.novoDate_ddMMyyyy(data)
			var dataInicial = this.novoDateDeDiaMesAno(1, 1, anoInicial);
			var dataFinal	= this.novoDateDeDiaMesAno(31, 12, anoFinal);
			
			if (this.isDataValida(dataAtual) && this.isDataValida(dataInicial) && this.isDataValida(dataFinal)) {
				resultado = (dataAtual.valueOf() >= dataInicial.valueOf() &&
							dataAtual.valueOf() <= dataFinal.valueOf());
			}
		}
		return resultado;
	};

	/**
	 * Retorna true se a data estiver no período determinado.
	 * 
	 * @data Data no formato MM/yyyy.
	 * @anoInicial Ano inicial
	 * @anoFinal Ano final 
	 */
	this.validarData_MMyyyyNoPeriodoDeAno = function(data, anoInicial, anoFinal) {
		var resultado = false;
		
		if (!this.isStringVazia(data)) {
			data = "01/"+ data;
			resultado = this.validarDataNoPeriodoDeAno(data, anoInicial, anoFinal);
		}
		return resultado;
	};
	
	/**
	 * Retorna true se a string tiver no tamanho maior ou iguao ao solicitado.
	 *
	 * @string String validada
	 * @tamanho Tamanho solicitado
	 */
	this.validarTamanhoDeStringMaiorOuIgual = function(string, tamanho) {
		var resultado = false;
		
		if (!this.isStringVazia(tamanho)) {
			resultado = this.getTamanho(string) >= tamanho;
		}
		return resultado;
	};
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

	/**
	 * Retorna true se o número passado por parâmetro for um CPF nulo ou padrão.
	 */
	this.isNumeroDeCPFNullOuPadrao = function(numero) {
		return 	this.isStringVazia(numero) || 
				numero == "00000000000" || 
				numero == "11111111111" ||
				numero == "22222222222" ||
				numero == "33333333333" ||
				numero == "44444444444" ||
				numero == "55555555555" ||
				numero == "66666666666" ||
				numero == "77777777777" ||
				numero == "88888888888" ||
				numero == "99999999999";
	};
}