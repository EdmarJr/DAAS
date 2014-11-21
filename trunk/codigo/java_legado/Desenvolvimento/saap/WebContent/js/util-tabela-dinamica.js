/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de cria��o: 13/12/2007
 * Vers�o: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto que implementa a tabela din�mica.
 *******************************************************************************
 * Depend�ncias: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *		UtilElemento	(util-elemento.js)
 *******************************************************************************
 * m�todos do objeto:
 *		importar(objeto)
 *		definirAtributo(nome, valor)
 *		definirAtributoNaLinha(indice, nome, valor)
 *		definirAtributoNaLinha(indice, nome, valor)
 *		definirAtributoNaColuna(linha, coluna, nome, valor)
 *		criarLinha()
 *		criarLinhaComColuna(colunas)
 *		criarColuna()
 *		criarColunaNaPosicao(indice)
 *		adicionarTextoNaLinhaColuna(linha, coluna, conteudo)
 *		adicionarObjetoNaLinhaColuna(linha, coluna, objeto)
 *		getQuantidadeDeLinhas()
 *		getLinhas()
 *		getTabela()
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilTabelaDinamica();
 * 		objeto.importar(documento.getElementById("tabela"));
 * 		objeto.criarLinhaComColuna(3);
 *******************************************************************************
 */

UtilTabelaDinamica.prototype = new UtilElemento();
function UtilTabelaDinamica() {
	var tabela = null;
	
	/**
	 * Importa a tabela passada por par�metro para o objeto.
	 * 
	 * @objeto Objeto do tipo 'table'
	 */
	this.importar = function(objeto) {
		tabela = objeto;
	};

	/**
	 * Define um atributo do objeto table.
	 *
	 * @nome Nome do atributo. (ex: width)
	 * @valor Valor do atributo. (ex: 20px)
	 */
	this.definirAtributo = function(nome, valor) {
		this.getTabela().setAttribute(nome, valor);
	};
	
	/**
	 * Define um atributo na linha da tabela.
	 *
	 * @indice �ndice da linha.
	 * @nome Nome do atributo. (ex: width)
	 * @valor Valor do atributo. (ex: 20px)
	 */
	this.definirAtributoNaLinha = function(indice, nome, valor) {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
		var linha = this.getLinhas()[indice];
		if (this.isReferencia(linha)) {
			linha.setAttribute(nome, valor);
		}
	}
	
	this.definirAtributoNaColunaDaUltimaLinha = function(coluna, nome, valor) {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
		var linha = this.getLinhas()[quantidadeDeLinhas-1];
		var td = linha.cells[coluna];
		if (this.isReferencia(td)) {
			td.setAttribute(nome, valor);
		}
	}

	/**
	 * Define um atributo na �ltima linha da tabela.
	 *
	 * @nome Nome do atributo. (ex: width)
	 * @valor Valor do atributo. (ex: 20px)
	 */
	this.definirAtributoNaUltimaLinha = function(nome, valor) {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
		var linha = this.getLinhas()[quantidadeDeLinhas-1];
		if (this.isReferencia(linha)) {
			linha.setAttribute(nome, valor);
		}
	}
	
	/** 
	 * Cria uma linha na tabela.
	 * 
	 */
	this.criarLinha = function() {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
	    var linha = this.getTabela().insertRow(quantidadeDeLinhas);
	    return linha.insertCell(0);                    
	};

	/**
	 * Cria uma linha com a quantidade de linhas definidas.
	 *
	 * @colunas Quantidade de colunas.
	 */
	this.criarLinhaComColuna = function(colunas) {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
	    var linha = this.tabela.insertRow(quantidadeDeLinhas);
	    for (var quantidade = 0; quantidade < colunas; quantidade++) {
	    	linha.insertCell(0);
	    }
	    return linha;
	};
      
	/**
     * Cria uma coluna no final da �ltima linha.
     */   
	this.criarColuna = function() {
		var resultado = null;
		var linhas = this.getLinhas();
		if (linhas.length > 0) {
		 	var linha = linhas[linhas.length-1];
		 	
		 	if (this.isReferencia(linha)) {
		 		var colunas = linha.cells;
		 		resultado = linha.insertCell(colunas.length);
		 	}
		}
		return resultado;
	};

	/**
	 * Cria uma coluna na posi��o desejada da �ltima linha.
	 *
	 * @indice �ndice da posi��o da coluna.
	 */
	this.criarColunaNaPosicao = function(indice) {
		var resultado = null;
		var linhas = this.getLinhas();
		if (linhas.length > 0) {
		 	var linha = linhas[linhas.length-1];
		 	
		 	if (this.isReferencia(linha)) {
		 		resultado = linha.insertCell(indice);
		 	}
		}
		return resultado;
	};

	/** 
	 * Adiciona um texto em uma posi��o (linha, coluna) definida.
	 *
	 * @linha �ndice da Linha 
	 * @coluna �ndice da coluna.
	 * @conteudo Texto que ser� inserido na coluna.
	 */
	this.adicionarTextoNaLinhaColuna = function(linha, coluna, conteudo) {
		var linhas = this.getLinhas();
		var tr = linhas[linha];
		var td = tr.cells[coluna];
		if (this.isReferencia(td)) {
			td.innerHTML = conteudo;
		}
	};
	
	this.adicionarTextoNaColunaDaUltimaLinha = function(coluna, conteudo) {
		var quantidadeDeLinhas = this.getQuantidadeDeLinhas();
		var linha = this.getLinhas()[quantidadeDeLinhas-1];
		var td = linha.cells[coluna];
		if (this.isReferencia(td)) {
			td.innerHTML = conteudo;
		}
	} 
	
	/** 
	 * Adiciona um objeto em uma posi��o (linha, coluna) definida.
	 *
	 * @linha �ndice da Linha 
	 * @coluna �ndice da coluna.
	 * @objeto Objeto que ser� inserido na coluna.
	 */
	this.adicionarObjetoNaLinhaColuna = function(linha, coluna, objeto) {
		var linhas = this.getLinhas();
		var tr = linhas[linha];
		var td = tr.cells[coluna];
		if (this.isReferencia(td)) {
			td.insertBefore(objeto, null);
		}
	};

	/**
	 * Retorna a quantidade de linhas da tabela.
	 */
	this.getQuantidadeDeLinhas = function() {
	     return this.getLinhas().length;
	};
    
    /**
     * Retorna as linhas da tabela.
     */
	this.getLinhas = function() {
	     return this.getTabela().rows;
	};
	
	/**
	 * Retorna o objeto table.
	 */
	this.getTabela = function() {
	     return tabela;
	};
}