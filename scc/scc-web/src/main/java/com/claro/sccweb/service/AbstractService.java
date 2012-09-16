package com.claro.sccweb.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Super classe b�sica para servi�os do SCC.
 *
 */
public abstract class AbstractService {
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Repasses de pr�-pago possuem periodicidade mensal , por isso , a data incial do repasse � sempre o in�cio do
	 * m�s e a data final , o �ltimo dia do m�s.
	 */
	protected Date calculaDataFinalPeriodoPrePago(Long mes,Long ano)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 *  Retorna um objeto Date apontando para o primeiro dia do per�odo informado.
	 */
	protected Date calculaDataInicialPeriodoPrePago(Long mes,Long ano)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	private SessionDataManager sessionDataManager;
	Logger logger = Logger.getLogger(AbstractService.class);
	
	public synchronized  String getTimeStamp()
	{
		Date now = new Date();
		return Long.toString(now.getTime());
	}
	
	/**
	 * Formato de data padr�o aceito pelo sistema.
	 */
	protected SimpleDateFormat sccDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
public  String leftZeroFill(String valor, int quantidade){
    	
    	if (valor.length() < quantidade) {  
    		StringBuffer buffer = new StringBuffer();  
    		
	    	for(int j = 0; j < (quantidade -valor.length()) ; j++){   
	    		buffer.append("0");   
	        } 
	    	
	    	buffer.append(valor);   
		      
    		return buffer.toString();  
    	}
	  return valor;   
    }

	public SessionDataManager getSessionDataManager() {
		return sessionDataManager;
	}
	
	public void setSessionDataManager(SessionDataManager sessionDataManager) {
		this.sessionDataManager = sessionDataManager;
	}
	
	protected void debug(String message)
	{
		logger.debug("["+getSessionDataManager().getUserPrincipal()+"]"+message);
	}
	
	protected void info(String message)
	{
		logger.info("["+getSessionDataManager().getUserPrincipal()+"]"+message);
	}
	
	protected void error(String message,Throwable th)
	{
		logger.error("["+getSessionDataManager().getUserPrincipal()+"]"+message,th);
	}

	/**
	 * Verifica se a data informada � um dia �til (dia de semana , n�o feriado).
	 * @param date Data a ser analisada.
	 * @return
	 */
	/*TODO Pesquisar no Mobile se a data informada n�o � feriado.*/
	protected boolean isDiaUtil(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return false;
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return false;
		return true;	
	}
	
	protected Double zeroIfNull(Double value)
	{
		if (value == null)
			return 0.00;
		return value;
	}
}
