/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação de data.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *******************************************************************************
 * métodos do objeto:
 * 		adicionarAnosNaData(data, quantidade)
 * 		adicionarDiasNaData(data, quantidade)
 * 		adicionarHorasNaData(data, quantidade)
 * 		adicionarMesesNaData(data, quantidade)
 * 		adicionarMinutosNaData(data, quantidade)
 * 		getAnosEntreDatas(dataInicial, dataFinal)
 * 		getDiasEntreDatas(dataInicial, dataFinal)
 * 		getHorasEntreDatas(dataInicial, dataFinal)
 * 		getMesesEntreDatas(dataInicial, dataFinal)
 * 		getMinutosEntreDatas(dataInicial, dataFinal)
 * 		getSemanasEntreDatas(dataInicial, dataFinal)
 * 		isDataValida(data)
 * 		novoDate()
 * 		novoDateDeDiaMesAno(dia, mes, ano)
 * 		novoDateDeDiaMesAnoHoraMinuto(dia, mes, ano, hora, minuto)
 * 		novoDate_ddMMyyyy(data)
 * 		novoDate_ddMMyyyyEspacohhMM(data)
 *		novaDataAtual();
 *		getDia(data)
 *		getMes(data)
 *		getAno(data)
 *		getUltimoDiaDoMes(data)
 *		getUltimoDiaDoMesAno(mes, ano)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilData();
 *		alert(objeto.isDataValida("15/05/2007"));
 *******************************************************************************
 */

UtilData.prototype = new UtilString();

function UtilData() {
	var PATTERNS = "ymdwhn";
	
	/**
	 * Incrementa a quantidade de anos de uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade
	 */
	this.adicionarAnosNaData = function(data, quantidade) {
		data = this.novoDate_ddMMyyyy(data);
		return this.incremetarData(data, quantidade, "y");
	};
	
	/**
	 * Incrementa a quantidade de dias de uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade
	 */
	this.adicionarDiasNaData = function(data, quantidade) {
		data = this.novoDate_ddMMyyyy(data);
		return this.incremetarData(data, quantidade, "d");
	};
	
	/**
	 * Incrementa a quantidade de horas de uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade
	 */
	this.adicionarHorasNaData = function(data, quantidade) {
		data = this.novoDate_ddMMyyyy(data);
		return this.incremetarData(data, quantidade, "h");
	};

	/**
	 * Incrementa a quantidade de meses de uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade
	 */
	this.adicionarMesesNaData = function(data, quantidade) {
		data = this.novoDate_ddMMyyyy(data);
		return this.incremetarData(data, quantidade, "m");
	};
			
	/**
	 * Incrementa a quantidade de minutos de uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade 
	 */
	this.adicionarMinutosNaData = function(data, quantidade) {
		data = this.novoDate_ddMMyyyy(data);
		return this.incremetarData(data, quantidade, "n");
	};
	
	/**
	 * Retorna a quantidade de anos entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.getAnosEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyy(dataInicial);
		dataFinal = this.novoDate_ddMMyyyy(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "y");
	};
		
	/**
	 * Retorna a quantidade de dias entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.getDiasEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyy(dataInicial);
		dataFinal = this.novoDate_ddMMyyyy(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "d");
	};
	
	/**
	 * Retorna a quantidade de horas entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy hh:mm
	 * @dataFinal Data no formato dd/MM/yyyy hh:mm
	 */
	this.getHorasEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyyEspacohhMM(dataInicial);
		dataFinal = this.novoDate_ddMMyyyyEspacohhMM(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "h");
	};
	
	/**
	 * Retorna string no formato dd/MM/yyyy da data atual.
	 */
	this.novaDataAtual = function() {
		var data = this.novoDate();
		
		var dia = this.inserirStringNaEsquerda(this.getDia(data), "0", null, 2);
		var mes = this.inserirStringNaEsquerda(this.getMes(data), "0", null, 2);
		var ano = this.getAno(data);
		
		
		return (dia +"/"+ mes +"/"+ ano);
	};
		
	/**
	 * Retorna a quantidade de meses entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.getMesesEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyy(dataInicial);
		dataFinal = this.novoDate_ddMMyyyy(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "m");
	};
	
	/**
	 * Retorna a quantidade de minutos entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy hh:mm
	 * @dataFinal Data no formato dd/MM/yyyy hh:mm
	 */
	this.getMinutosEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyyEspacohhMM(dataInicial);
		dataFinal = this.novoDate_ddMMyyyyEspacohhMM(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "n");
	};

	/**
	 * Retorna a quantidade de semanas entre duas datas.
	 *
	 * @dataInicial Data no formato dd/MM/yyyy
	 * @dataFinal Data no formato dd/MM/yyyy
	 */
	this.getSemanasEntreDatas = function(dataInicial, dataFinal) {
		dataInicial = this.novoDate_ddMMyyyy(dataInicial);
		dataFinal = this.novoDate_ddMMyyyy(dataFinal);
		return this.getDiferencaEntreDatas(dataInicial, dataFinal, "w");
	};

	/**
	 * Retorna true se a data for válida. Uma data válida deve estar no formato
	 * dd/MM/yyyy.
	 *
	 * @data Data no formato dd/MM/yyyy.
	 */
	this.isDataValida = function(data) {
		var res = false;
		
		if (data instanceof Date) {
			res = true;
		} else {
			var reg = new RegExp("(0[1-9]|[1-9]|[12][0-9]|3[01])/(0[1-9]|[1-9]|1[012])/[0-9]{4}");
			if (reg.test(data)) {
				data = data.split("/");
				var dia = parseInt(data[0], 10);
				var mes = parseInt(data[1], 10);
				var ano = parseInt(data[2], 10);
				
				var dataNova = this.novoDateDeDiaMesAno(dia, mes, ano);
				res = (	dia == dataNova.getDate() && 
						mes == (dataNova.getMonth()+1) && 
						ano == dataNova.getFullYear() &&
						ano != 0);
			}
		}
		return res;
	};

	/**
	 * Retorna um novo objeto do tipo Date.
	 */
	this.novoDate = function() {
		return new Date();
	};
	
	/**
	 * Retorna um novo objeto do tipo Date.
	 *
	 * @dia
	 * @mes
	 * @ano
	 */
	this.novoDateDeDiaMesAno = function(dia, mes, ano) {
		var res = null;
		
		if (!this.isStringVazia(dia) && !this.isStringVazia(mes) && !this.isStringVazia(ano)) {
			res = this.novoDateDeDiaMesAnoHoraMinuto(dia, mes, ano, 0, 0)
		}
		return res;
	};
	
	/**
	 * Retorna um novo objeto do tipo Date.
	 *
	 * @dia
	 * @mes
	 * @ano
	 * @hora
	 * @minuto
	 */
	this.novoDateDeDiaMesAnoHoraMinuto = function(dia, mes, ano, hora, minuto) {
		var res = null;
		
		if (!this.isStringVazia(dia) && !this.isStringVazia(mes) && !this.isStringVazia(ano)) {
			res = new Date();
			res.setSeconds(0);
			res.setMinutes(minuto);
			res.setHours(hora);
			res.setDate(dia)
			res.setMonth(mes-1);
			res.setFullYear(ano);
			if (res.getDate() != dia) {
				res.setDate(dia)
				res.setHours(hora + 1);
			}
			
		}
		return res;
	};

	/**
	 * Retorna um novo objeto do tipo Date.
	 *
	 * @data Data no formato dd/MM/yyyy
	 */
	this.novoDate_ddMMyyyy = function(data) {
		var res = null;
		if (data instanceof Date) {
			res = data;
		} else if (!this.isStringVazia(data) && this.isDataValida(data)) {
			var dia = parseInt(data.split("/")[0], 10);
			var mes = parseInt(data.split("/")[1], 10);
			var ano = parseInt(data.split("/")[2], 10);
			
			res = this.novoDateDeDiaMesAno(dia, mes, ano);
		}
		return res;
	};
	
	/**
	 *Retorna um novo objeto do tipo Date.
	 *
	 * @data Data no formato dd/MM/yyyy hh:MM
	 */
	this.novoDate_ddMMyyyyEspacohhMM = function(data) {
		var res = null;
		
		if (data instanceof Date) {
			res = data;
		} else if (!this.isStringVazia(data) && this.isDataValida(data)) {
			var strData = data.split(" ");
			if (!this.isReferencia(strData[1])) {
				strData[1] = "00:01";
			}
			var strHora = strData[1].split(":");
			strData = strData[0].split("/");
	
			var dia 	= parseInt(strData[0], 10);
			var mes 	= parseInt(strData[1], 10);
			var ano 	= parseInt(strData[2], 10);
			var hora 	= parseInt(strHora[0], 10);
			var minuto 	= parseInt(strHora[1], 10);
			res = this.novoDateDeDiaMesAnoHoraMinuto(dia, mes, ano, hora, minuto);
		}
		
		return res;
	};

	/**
	 * Retorna o dia da data passada por parâmetro.
	 *
	 * @data Objeto do tipo Date
	 */
	this.getDia = function(data) {
		var res = null;
		
		if (this.isDataValida(data)) {
			res = data.getDate();
		}
		return res;
	};

	/**
	 * Retorna o mês da data passada por parâmetro.
	 *
	 * @data Objeto do tipo Date
	 */
	this.getMes = function(data) {
		var res = null;
		
		if (this.isDataValida(data)) {
			res = (data.getMonth() + 1);
		}
		return res;
	};

	/**
	 * Retorna o ano da data passada por parâmetro.
	 *
	 * @data Objeto do tipo Date
	 */
	this.getAno = function(data) {
		var res = null;
		
		if (this.isDataValida(data)) {
			res = data.getFullYear();
		}
		return res;
	};
	
	/**
	 * Retorna o último dia do mês da data informada.
	 *
	 * @data Data no formato dd/MM/yyyy
	 */
	this.getUltimoDiaDoMes = function(data) {
		var res = 0;
		
		if (!this.isStringVazia(data)) {
			data = this.novoDate_ddMMyyyy(data);
			var mes = this.getMes(data);
			var ano = this.getAno(data);
			var bissexto = ((((ano%4)==0 && (ano%100)!=0) || (ano%400)==0)? 29: 28);
			var meses = new Array(31, bissexto, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
			
			res = meses[(mes-1)];
		}
		return res;
	};

	/**
	 * Retorna o último dia do mês/ano da data informada.
	 *
	 * @mes Mês
	 * @ano Ano
	 */
	this.getUltimoDiaDoMesAno = function(mes, ano) {
		var res = 0;
		
		if (!this.isStringVazia(mes) && !this.isStringVazia(ano)) {
			var data = data = "01/"+ mes +"/"+ ano;
			res = this.getUltimoDiaDoMes(data);
		}
		return res;
	};
	
	
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

	/**
	 * Incrementa uma data.
	 *
	 * @data Data no formato dd/MM/yyyy
	 * @quantidade Quantidade do incremento.
	 * @pattern Parte da data que será incrementada. Valores válidos:
	 *		y: ano
	 *		m: mês 
	 *		d: dia
	 *		h: hora
	 *		n: minuto
	 *		s: segundo
	 */
	this.incremetarData = function(data, quantidade, pattern) {
		quantidade = new Number(quantidade);
		
		if (this.isDataValida(data) && 
			this.isNumero(quantidade) && 
			this.isStringContidaEm(PATTERNS, pattern)) {
			
			switch(pattern.toLowerCase()) {
				case "y":// ano
					data.setFullYear(data.getFullYear() + quantidade);
					break;
				case "m":// mês
					data.setMonth(data.getMonth() + quantidade);
					break;
				case "d":// dia
					data.setDate(data.getDate() + quantidade);
					break;
				case "h":// hora
					data.setHours(data.getHours() + quantidade);
					break;
				case "n":// minuto
					data.setMinutes(data.getMinutes() + quantidade);
					break;
				case "s":// segundo
					data.setSeconds(data.getSeconds() + quantidade);
					break;
			}
		}
		
		return data;
	};

	/**
	 * Retorna a diferença entre duas datas.
	 *
	 * @dataInicial Objeto Date do javascript.
	 * @dataFinal Objeto Date do javascript.
	 * @pattern Parte da data que será usada para retornar a diferença. Valores válidos:
	 *		y: ano
	 *		m: mês
	 *		d: dia
	 *		w: semana
	 *		h: hora
	 *		n: minuto
	 */
	this.getDiferencaEntreDatas = function(dataInicial, dataFinal, pattern) {
		var res = 0;
	
		if (this.isDataValida(dataInicial) && 
			this.isDataValida(dataFinal) && 
			this.isStringContidaEm(PATTERNS, pattern)) {
			
			var milisegundos = dataFinal.valueOf() - dataInicial.valueOf();
			var dataDaDiferenca = new Date(milisegundos);
			
			var anos  		= dataFinal.getUTCFullYear() - dataInicial.getUTCFullYear();
			var meses 		= dataFinal.getUTCMonth() - dataInicial.getUTCMonth() + (anos!=0 ? anos*12 : 0);
			var segundos 	= parseInt(milisegundos/1000, 10);
			var minutos 	= parseInt(segundos/60, 10);
			var horas 		= parseInt(minutos/60, 10);
			var dias 		= parseInt(horas/24, 10);
			var semanas 	= parseInt(dias/7, 10);
			
			switch (pattern.toLowerCase()) {
				case "y":// anos
					res = anos;
					break;
				case "m":// meses
					res = meses;
					break;
				case "d":// dias
					res = dias;
					break;
				case "w":// semanas
					res = semanas;
					break;
				case "h":// horas
					res = horas;
					break;
				case "n":// minutos
					res = minutos;
					break;
			}
		}
			
		return res;
	};
}