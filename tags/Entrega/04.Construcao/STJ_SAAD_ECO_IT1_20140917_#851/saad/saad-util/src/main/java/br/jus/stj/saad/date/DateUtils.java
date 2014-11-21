package br.jus.stj.saad.date;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

public static long calculaDiferencaHora(Date dataHoraInicio, Date dataHoraTermino) {
		
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(dataHoraInicio);

		Calendar calendarTermino = Calendar.getInstance();
		calendarTermino.setTime(dataHoraTermino);
		long diferenca = calendarInicio.getTimeInMillis()
				- calendarTermino.getTimeInMillis();
		long horas = (diferenca / (60 * 60 * 1000)* -1); // DIFERENCA EM HORAS
		return horas;
	}
	
	
	public static Time converteDateToTime(Date date) {
		
		Time tempTime;
		tempTime = new Time(date.getTime());
		return tempTime;
	}
	
	public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}	
}
