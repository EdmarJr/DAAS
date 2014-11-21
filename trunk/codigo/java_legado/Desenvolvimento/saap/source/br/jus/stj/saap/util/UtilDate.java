/*
 * UtilData.java
 *
 * Data de criação: 29/07/2004
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import br.jus.stj.alp01.comum.data.UtilData;
import br.jus.stj.alp01.comum.string.UtilString;

/**
 * Classe que possui métodos para a formatacao em java do sistema.
 * 
 * @author Gustavo Rocha
 */
public final class UtilDate {

	private static final Locale BRASIL = new Locale("pt", "BR");

	private UtilDate() {
		//
	}


	/**
	 * Faz a conversão de java.util.Date para java.sql.Date
	 * 
	 * @param data
	 * @return java.sql.Date
	 */
	public static java.sql.Date converteDateParaSqlDate(Date data) {
		java.sql.Date dataSQL = null;
		if (data != null) {
			dataSQL = new java.sql.Date(data.getTime());
		}
		return dataSQL;
	}

	
	
	public static Date formatarDataConvite(Date dataConvite, String horaConvite){
		
		if (horaConvite!=null && !horaConvite.equals("")){
			dataConvite = UtilData.ajustarData(dataConvite, 
					new Integer(horaConvite.substring(0,2)).intValue(), 
					new Integer(horaConvite.substring(3,5)).intValue(), 
					0);
		}
		
		return dataConvite;
	}

	
	
	public static boolean isHoraValida(String hora){
		
		int horaAux = new Integer(hora.substring(0,2)).intValue(); 
		int minutoAux = new Integer(hora.substring(3,5)).intValue(); 
		
		if (horaAux <=23 && minutoAux <=59){
			return true;
		}
		
		return false;
		
	}
	
	
	
	/**
	 * Verifica se a data passada como parâmetro é anterior à data atual,
	 * comparando apenas dia, mês e ano.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isDataMenorQueDataAtual(Date data) {
		GregorianCalendar data1 = new GregorianCalendar(BRASIL);
		GregorianCalendar dataAtual = new GregorianCalendar(BRASIL);
		boolean retorno = false;

		data1.setTime(data);
		dataAtual.setTime(new Date());

		if (data1.get(Calendar.YEAR) > dataAtual.get(Calendar.YEAR)) {
			retorno = false;
		} else if (data1.get(Calendar.YEAR) < dataAtual.get(Calendar.YEAR)) {
			retorno = true;
		} else if (data1.get(Calendar.MONTH) > dataAtual.get(Calendar.MONTH)) {
			retorno = false;
		} else if (data1.get(Calendar.MONTH) < dataAtual.get(Calendar.MONTH)) {
			retorno = true;
		} else {
			retorno = data1.get(Calendar.DAY_OF_MONTH) < dataAtual
					.get(Calendar.DAY_OF_MONTH);
		}
		return retorno;
	}

	/**
	 * Verifica se a data passada como parâmetro é anterior à data atual,
	 * comparando apenas minuto, hora, dia, mês e ano.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isDataHoraMenorQueDataAtual(Date data) {
		GregorianCalendar data1 = new GregorianCalendar(BRASIL);
		GregorianCalendar dataAtual = new GregorianCalendar(BRASIL);
		boolean retorno = false;

		if (data == null) {
			return true;
		}

		data1.setTime(data);
		dataAtual.setTime(new Date());

		if (data1.get(Calendar.YEAR) > dataAtual.get(Calendar.YEAR)) {
			retorno = false;
		} else if (data1.get(Calendar.YEAR) < dataAtual.get(Calendar.YEAR)) {
			retorno = true;
		} else if (data1.get(Calendar.MONTH) > dataAtual.get(Calendar.MONTH)) {
			retorno = false;
		} else if (data1.get(Calendar.MONTH) < dataAtual.get(Calendar.MONTH)) {
			retorno = true;
		} else if (data1.get(Calendar.DAY_OF_MONTH) > dataAtual
				.get(Calendar.DAY_OF_MONTH)) {
			retorno = false;
		} else if (data1.get(Calendar.DAY_OF_MONTH) < dataAtual
				.get(Calendar.DAY_OF_MONTH)) {
			retorno = true;
		} else if (data1.get(Calendar.HOUR_OF_DAY) > dataAtual
				.get(Calendar.HOUR_OF_DAY)) {
			retorno = false;
		} else if (data1.get(Calendar.HOUR_OF_DAY) < dataAtual
				.get(Calendar.HOUR_OF_DAY)) {
			retorno = true;
		} else {
			retorno = data1.get(Calendar.MINUTE) < dataAtual
					.get(Calendar.MINUTE);
		}
		return retorno;
	}




	/**
	 * Converte a data para o tipo Timestamp
	 * 
	 * @param data
	 * @return Date
	 */
	public static Timestamp converterDateParaTimestamp(Date data) {
		if (data == null) {
			return null;
		}
		return new Timestamp(data.getTime());
	}

	/**
	 * Adiciona x dias à data informada.
	 * 
	 * @param data
	 * @param dias
	 * @return Date
	 */
	public static Date adicionaDias(Date data, int dias) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(data);
		date.  add(Calendar.DATE, dias);
		Date novaData = date.getTime();
		return novaData;
	}

	/**
	 * Transaforma a data recebida no formato dd/MM/yyyy Para o formato
	 * yyyy-MM-dd-00.00.00.000000 que é utilizado no DB2.
	 */
	public static String toTimestampFormat(String data) {
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);
		return ano + "-" + mes + "-" + dia + "-00.00.00.000000";
	}

	/**
	 * 
	 */
	public static String timestampToString(Timestamp time) {
		if (time == null){
			return "";
		}
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(time);
	}


	@SuppressWarnings("deprecation")
	public static String extraiHoraMinuto(Date data){

		int hora = data.getHours();
		int minuto = data.getMinutes();
		
		return UtilString.completarAEsquerda(hora+"","0",2) + ":" +
			UtilString.completarAEsquerda(minuto+"","0",2);
	
	}

}
