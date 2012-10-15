/**
 * 
 */
package com.claro.cobillingweb.persistence.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 93046251
 *
 */
public class DateUtils {
	
	
	private DateUtils(){
		
	}   
	
    /**  
     * Retorna o valor do hor�rio minimo para a data de referencia passada.  
     * <BR>  
     * <BR> Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data  
     * retornada por este metodo ser� "30/01/2009 as 00h:00m:00s e 000ms".  
     * @param date de referencia.  
     * @return {@link Date} que representa o hor�rio minimo para dia informado.  
     */   
    public static Date lowDateTime(Date date) {   
        Calendar aux = Calendar.getInstance();   
        aux.setTime(date);   
        toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec   
        return aux.getTime();   
    }   

    /**  
     * Retorna o valor do hor�rio maximo para a data de referencia passada.  
     * <BR>  
     * <BR> Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data  
     * retornada por este metodo ser� "30/01/2009 as 23h:59m:59s e 999ms".  
     * @param date de referencia.  
     * @return {@link Date} que representa o hor�rio maximo para dia informado.  
     */   
    public static Date highDateTime(Date date) {   
        Calendar aux = Calendar.getInstance();   
        aux.setTime(date);   
        toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec   
        aux.roll(Calendar.DATE, true); //vai para o dia seguinte   
        aux.roll(Calendar.MILLISECOND, false); //reduz 1 milisegundo   
        return aux.getTime();   
    }   
  
    /**  
     * Zera todas as referencias de hora, minuto, segundo e milesegundo do  
     * {@link Calendar}.  
     * @param date a ser modificado.  
     */   
    public static void toOnlyDate(Calendar date) {   
        date.set(Calendar.HOUR_OF_DAY, 0);   
        date.set(Calendar.MINUTE, 0);   
        date.set(Calendar.SECOND, 0);   
        date.set(Calendar.MILLISECOND, 0);   
    }  

	public Date calculaDataFinalPeriodo(Long mes,Long ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 *  Retorna um objeto Date apontando para o primeiro dia do per�odo informado.
	 */
	public Date calculaDataInicialPeriodo(Long mes,Long ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}



}
