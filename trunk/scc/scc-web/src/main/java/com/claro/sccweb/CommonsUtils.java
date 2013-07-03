/*
 * Created on 21/06/2005
 * Fabrica SW - Claro
 */
package com.claro.sccweb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.utils.CobillingDateUtils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/**
 * @author 12006126 - Leandro Yung & 13006152 Renato Caires de Oliveira
 * sds.systechnet@gmail.com
 */
public class CommonsUtils {
    // Logging support
    private static Logger logger = Logger.getLogger(CommonsUtils.class.getName());
    private static boolean logDebug = logger.isDebugEnabled();
   	
    /**
     * Checks if String contains no value inside
     * @param s
     * @return Returns true if String is empty
     */
    public static boolean isEmpty(String s) {
        if (s==null || s.equals("") || s.length()==0 || s.equals("null")) {
            return true;
        }
        return false;
    }
   	
    /**
     * Checks if Date contains no value inside
     * @param s
     * @return Returns true if Date is empty
     */
    public static boolean isEmpty(Date s) {
        if (s==null) {
            return true;
        }
        return false;
    }
   	
    /**
     * Checks if Date contains no value inside
     * @param s
     * @return Returns true if Date is empty
     */
    public static boolean isNotEmpty(Date s) {
        if (s==null) {
            return false;
        }
        return true;
    }
    
    /**
     * Removes head and tail empty characters
     * @param s
     * @return Returns String cleaned
     */
    public static String clean(String s) {
        if (s==null) {
            return "";
        }
        return s.trim();
    }
    
    /**
     * Removes head and tail empty characters
     * @param s
     * @return Returns String cleaned
     */
    public static String boolean2String(boolean s) {
        if (s) {
        	return CobillingConstants.S;
            
        }
        return CobillingConstants.N;
    }
    
    /**
     * Removes head and tail empty characters and limit characters number
     * @param s
     * @return Returns String cleaned and limited
     */
    public static String limit(String s,int limit) {
        if (s==null) {
            return "";
        }
        if (s.length()>limit) { 
        	s = s.trim().substring(0,limit-1);  
        }
        return s.trim();
    }
    
    /**
     * Remove Acents
     * @param s
     * @return String 
     */
    public static String removeAccent(String s) {
        if (s==null) { return null; }
        s = s.replaceAll("[·‡„‚‰]", "a");
        s = s.replaceAll("[ÈËÍÎ]", "e");
        s = s.replaceAll("[ÌÏÓÔ]", "i");
        s = s.replaceAll("[ÛÚıÙˆ]", "o");
        s = s.replaceAll("[˙˘˚¸]", "u");
        s = s.replaceAll("[¡¿√¬ƒ]", "A");
        s = s.replaceAll("[…» À]", "E");
        s = s.replaceAll("[ÕÃŒœ]", "I");
        s = s.replaceAll("[”“’‘÷]", "O");
        s = s.replaceAll("[⁄Ÿ€‹]", "U");
        return s;
    }
    
    /**
     * Obtain the digits from a String
     * @param s
     * @return Returns the digits char
     */
    public static String getDigits(String s) {
        if (s!=null) {
            boolean negative = false;
            char [] cData = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int cont=0; cont<cData.length; cont++) {
                if (cData[cont]>='0' && cData[cont]<='9') {
                    sb.append(cData[cont]);
                } else if (cData[cont]=='-') {
                    negative = true;
                }
            }
            return (sb.toString()==null ? "" : negative? "-" + sb.toString() : sb.toString());
        }
        return "";
    }
    
    /**
     * Obtain the digits from a String
     * @param s
     * @return Returns the digits char
     */
    public static String getDigitsHour(String s) {
		String digits = getDigits(s);
		Integer hour = new Integer(CommonsUtils.isEmpty(digits)?0:
									(Integer.parseInt(digits)>23?0:
										(Integer.parseInt(digits)<0?0:Integer.parseInt(digits))
									));
        return hour.toString();
    }
    
    /**
     * Obtain the digits from a String
     * @param s
     * @return Returns the digits char
     */
    public static String getDigitsMinutes(String s) {
		String digits = getDigits(s);
		Integer minutes = new Integer(CommonsUtils.isEmpty(digits)?0:
									(Integer.parseInt(digits)>59?0:
										(Integer.parseInt(digits)<0?0:Integer.parseInt(digits))
									));
        return minutes.toString();
    }
    
    /**
     * Obtain the digits from a String
     * @param s
     * @return Returns the digits char
     */
    public static String getFractionDigits(String s) {
        if (s!=null) {
            boolean negative = false;
            boolean fraction = false;
            char [] cData = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int cont=0; cont<cData.length; cont++) {
                if (cData[cont]>='0' && cData[cont]<='9') {
                    sb.append(cData[cont]);
                } else if (cData[cont]=='-') {
                    negative = true;
                } else if (cData[cont]==',' || cData[cont]=='.') {
                    if (!fraction) {
                        sb.append(".");
                        fraction = true;
                    }
                }
            }
            return (sb.toString()==null ? "" : negative? "-" + sb.toString() : sb.toString());
        }
        return "";
    }
    
    /**
     * Obtain the digits from a String
     * @param s
     * @return Returns the digits char
     */
    public static String getPositiveFractionDigits(String s) {
        if (s!=null) {
            boolean fraction = false;
            char [] cData = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int cont=0; cont<cData.length; cont++) {
                if (cData[cont]>='0' && cData[cont]<='9') {
                    sb.append(cData[cont]);
                } else if (cData[cont]==',' || cData[cont]=='.') {
                    if (!fraction) {
                        sb.append(".");
                        fraction = true;
                    }
                }
            }
            return (sb.toString()==null ? "" : sb.toString());
        }
        return "";
    }
    
    /**
     * Compare two Dates and return true if dates are equals
     * @param date1
     * @param date2
     * @return true if Strings are equals
     */
    public static boolean isEquals(Date date1, Date date2) {
        if (date1==date2) { return true; }
        if (date1==null) {
            return true;
        } else if (date2==null) {
            return false;
        } else {
            return date1.equals(date2);
        }
    }
    
    /**
     * Compare two String and return true if objects are equals
     * @param obj1
     * @param obj2
     * @return true if Strings are equals
     */
    public static boolean isEquals(String obj1, String obj2) {
    	if (obj1==obj2) { return true; }
    	if (obj1!=null && obj2!=null) {
    		return obj1.equals(obj2);
    	}
    	return false;
    }
    
    /**
     * Compare two objects and return true if objects are equals
     * @param obj1
     * @param obj2
     * @return true if objects are equals
     */
    public static boolean isEquals(Object obj1, Object obj2) {
    	if (obj1==obj2) { return true; }
    	if (obj1!=null && obj2!=null && obj1.getClass()==obj2.getClass()) {
    		return obj1.equals(obj2);
    	}
    	return false;
    }
    
    /**
     * Compare two Strings
     * @param str1
     * @param str2
     * @return int
     */
    public static int compare(String str1, String str2) {
    	if (str1==str2) { return 0; }
    	if (str1==null) {
    		return 1;
    	} else if (str2==null) {
    		return -1;
    	} else {
    		return str1.compareTo(str2);
    	}
    }
    
    /**
     * Compare two Dates
     * @param date1
     * @param date2
     * @return int
     */
    public static int compare(Date date1, Date date2) {
        if (date1==date2) { return 0; }
        if (date1==null) {
            return 1;
        } else if (date2==null) {
            return -1;
        } else {
            return date1.compareTo(date2);
        }
    }
    
    /**
     * Compare two Comparable
     * @param obj1
     * @param obj2
     * @return int
     */
    public static int compare(Comparable obj1, Comparable obj2) {
        if (obj1==obj2) { return 0; }
        if (obj1==null) {
            return 1;
        } else if (obj2==null) {
            return -1;
        } else {
            return obj1.compareTo(obj2);
        }
    }
    
    /**
     * @param date
     * @return Obtain hashcode for Object
     */
    public static int getHash(Object obj) {
    	return obj==null ? 0 : obj.hashCode();
    }

    /**
     * @param String[]
     * @return Obtain string with ";" separator
     */
    public static String concatenaListaComPontoVirgula(String[] list) {
    	return concatenaListaComSeparador(list, ';');
    }
    
    /**
     * @param list
     * @return Obtain String with "," separator from a list
     */
    public static String concatenaListaComVirgula(List list) {
    	return concatenaListaComSeparador(list.toArray(), ',');
    }
    
    /**
     * @param list
     * @param separador
     * @return
     */
    public static String concatenaListaComSeparador(Object[] list, char separador) {
    	StringBuffer temp = new StringBuffer();    	
    	if (list!=null) {
	    	for (int i = 0; i < list.length; i++) {
				Object obj = list[i];
				if (logDebug) {
					logger.debug("concatenaListaComSeparador (elemento) = " + list[i]);
				}
				if(temp.length()>0) {
					temp.append(separador);
				}
				temp.append(obj);
			}
    	}
    	return temp.toString();
    }
    
    /**
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String removerEspacoString(String str) throws Exception{
		String resultado = "";
		StringTokenizer strTokenizer = new StringTokenizer(str);
		
		while(strTokenizer.hasMoreTokens()){
			resultado += strTokenizer.nextElement();
		}
		return resultado;
	}
    
    /**
     * @param double
     * @return String
     */
    public static String getIntegerFormat(long numero){
    	Locale locale = new Locale("pt","BR");
		NumberFormat nf = NumberFormat.getIntegerInstance(locale);
		return nf.format(numero);    	
    }
    
    /**
     * @param double
     * @return String
     */
    public static String getNumberFormat(double numero, int casas){
    	Locale locale = new Locale("pt","BR");
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);
		return nf.format(numero);    	
    }
    
    /**
     * @param double
     * @return String
     */
    public static String getNumberFormat(double numero){
    	int casas = 2;
    	Locale locale = new Locale("pt","BR");
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);
		return nf.format(numero);    	
    }
    
    /**
     * @param double
     * @return String
     */
    public static String getCurrencyFormat(double numero){
    	int casas = 2;
    	Locale locale = new Locale("pt","BR");
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);
		return nf.format(numero);    	
    }
    
    /**
     * @param double
     * @return String
     */
    public static String getPercentFormat(double numero){
    	int casas = 2;
    	Locale locale = new Locale("pt","BR");
		NumberFormat nf = NumberFormat.getPercentInstance(locale);
		nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);
		return nf.format(numero);    	
    }
    
    /**
     * Compare two Strings
     * @param str1
     * @param str2
     * @return int
     */
    public static double trunc(double db) {
    	int casas = 2;
    	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	return Math.round(db*fator)/fator;
    }
    
    /**
     * Compare two Strings
     * @param str1
     * @param str2
     * @return int
     */
    public static double trunc(double db, int casas) {
    	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	return Math.round(db*fator)/fator;
    }
    
    /**
     * Compare two Strings
     * @param str1
     * @param str2
     * @return int
     */
    public static double truncMinutes(double db) {
    	double fator = 10.0;
    	return (new Double(Math.round(db*fator)/fator)).doubleValue();
    }

    public static String truncDouble2StringVirgula(double db) 
    {
    	int casas = 2;
   		Locale locale = new Locale("pt","BR");    	
    	NumberFormat dNf = NumberFormat.getNumberInstance(locale);
		dNf.setMinimumFractionDigits(casas);
		dNf.setMaximumFractionDigits(casas);

       	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	double result = Math.round(db*fator)/fator;
		String vlr = new String();
		try {
			vlr = CommonsUtils.removerEspacoString(dNf.format(result).replace('.', ' '));
			vlr = vlr.replace('.',',');		
			vlr = vlr.substring(vlr.length()-2,vlr.length()-1).equalsIgnoreCase(",")?vlr+"0":vlr;
		} catch (Exception e) {
			vlr = "0,00";
			logger.debug(e);
		}
		return vlr;
    }

    public static double truncStringVirgula2Double(String valor) 
    {
    	int casas = 2;
   		Locale locale = new Locale("pt","BR");    	
    	NumberFormat dNf = NumberFormat.getNumberInstance(locale);
		dNf.setMinimumFractionDigits(casas);
		dNf.setMaximumFractionDigits(casas);

       	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	double result = 0.0;
		try {
			String vlr = valor.replace(',','.');
			result = Math.round(Double.parseDouble(vlr)*fator)/fator;
		} catch (Exception e) {
			result = 0.0;
			logger.debug(e);
		}
		return result;
    }

    public static String truncDoubleStringNotFormated(double db, int casas) 
    {
   		Locale locale = new Locale("pt","BR");    	
    	NumberFormat dNf = NumberFormat.getNumberInstance(locale);
		dNf.setMinimumFractionDigits(casas);
		dNf.setMaximumFractionDigits(casas);

       	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	double result = Math.round(db*fator)/fator;
		String vlr = new String("0.00");
		try {
			vlr = CommonsUtils.removerEspacoString(dNf.format(result).replace('.', ' '));
			vlr = vlr.replace(',','.');		
		} catch (Exception e) {
			vlr = "0.00";
			logger.debug(e);
		}
		return vlr;
    }
	
    public static String truncDoubleStringNotFormated(double db) 
    {
    	int casas = 2;
   		Locale locale = new Locale("pt","BR");    	
    	NumberFormat dNf = NumberFormat.getNumberInstance(locale);
		dNf.setMinimumFractionDigits(casas);
		dNf.setMaximumFractionDigits(casas);

       	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	double result = Math.round(db*fator)/fator;
		String vlr = new String("0.00");
		try {
			vlr = CommonsUtils.removerEspacoString(dNf.format(result).replace('.', ' '));
			vlr = vlr.replace(',','.');		
		} catch (Exception e) {
			vlr = "0.00";
			logger.debug(e);
		}
		return vlr;
    }
    
    
    public static boolean isEmptyOrNull(String pString){
    	return (pString == null  || pString.trim().equals("")); 
    }
    
    
	/**
     * Retorna um array de String contendo os meses da tela de Relatorio de Financeiro
     * @param int month, int year
     * @return String[]
     */
    public static String[] getMesesRetroativosRelFinanceiro(int month, int year) {
    	List list = new ArrayList();

    	// ObservaÁ„o M
    	int month_M = month;
    	int year_M = year;    	
        String szCompetenciaM = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_1 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_2 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_3 = year_M + (month_M>9?""+month_M:"0"+month_M);       
        
        // retornar 12 meses
        String szCompetenciaLimite = year + (month>9?""+month:"0"+month);
        year--;
    	String szCompetencia = year + (month>9?""+month:"0"+month); 
        do {
        	// Monta Mensagem
        	String szMesAnoCompetencia = CobillingDateUtils.getShortMonthName(month) + "/" + year;
        	szCompetencia = year + (month>9?""+month:"0"+month);
        	
        	// ObservaÁ„o M
        	if (szCompetenciaM.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M"+""+"</br>"+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_1.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-1"+""+"</br>"+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_2.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-2"+""+"</br>"+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_3.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-3"+""+"</br>"+""+szMesAnoCompetencia+"";
        	} else {
    			szMesAnoCompetencia = ""+"†"+""+"</br>"+""+szMesAnoCompetencia+"";
        	}

        	// AvanÁar Mes
        	month++;
        	if (month>12) {
        		month = 1;
        		year++;
        	}
        	list.add(szMesAnoCompetencia);
        } while(!szCompetencia.equalsIgnoreCase(szCompetenciaLimite));
        
        String[] vetorREF = new String[14];
        int index = 0;
        vetorREF[index] = "††††††††††††††††††††††††††††††††††††††††";
        index++;
    	for (java.util.Iterator iter1 = list.iterator(); iter1.hasNext();) {
    		vetorREF[index] = (String) iter1.next();
    		index++;
		}
        return vetorREF;
    }
    
    /**
     * Retorna um array de String contendo os meses da tela de Relatorio de Financeiro
     * @param int month, int year
     * @return String[]
     */
    public static String[] getMesesRetroativosRelFinanceiroTotal(int month, int year, String pBreakLine) {
    	List list = new ArrayList();

    	// ObservaÁ„o M
    	int month_M = month;
    	int year_M = year;    	
        String szCompetenciaM = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_1 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_2 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_3 = year_M + (month_M>9?""+month_M:"0"+month_M);       
        
        // retornar 12 meses
        String szCompetenciaLimite = year + (month>9?""+month:"0"+month);
        year--;
    	String szCompetencia = year + (month>9?""+month:"0"+month); 
        do {
        	// Monta Mensagem
        	String szMesAnoCompetencia = CobillingDateUtils.getShortMonthName(month) + "/" + year;
        	szCompetencia = year + (month>9?""+month:"0"+month);
        	
        	// ObservaÁ„o M
        	if (szCompetenciaM.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_1.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-1"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_2.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-2"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_3.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-3"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else {
    			szMesAnoCompetencia = ""+"†"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	}

        	// AvanÁar Mes
        	month++;
        	if (month>12) {
        		month = 1;
        		year++;
        	}
        	list.add(szMesAnoCompetencia);
        } while(!szCompetencia.equalsIgnoreCase(szCompetenciaLimite));
        
        String[] vetorREF = new String[15];
        int index = 0;
        vetorREF[index] = "††††††††††††††††††††††††††††††††††††††††";
        index++;
    	for (java.util.Iterator iter1 = list.iterator(); iter1.hasNext();) {
    		vetorREF[index] = (String) iter1.next();
    		index++;
		}
    	vetorREF[14] = "YTD";
        return vetorREF;
    }
    
    /**
     * Retorna um array de String contendo os meses da tela de Relatorio de Financeiro
     * @param int month, int year
     * @return String[]
     */
    public static String[] getMesesRetroativosRelFinanceiro(int month, int year, String pBreakLine) {
    	List list = new ArrayList();

    	// ObservaÁ„o M
    	int month_M = month;
    	int year_M = year;    	
        String szCompetenciaM = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_1 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_2 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_3 = year_M + (month_M>9?""+month_M:"0"+month_M);       
        
        // retornar 12 meses
        String szCompetenciaLimite = year + (month>9?""+month:"0"+month);
        year--;
    	String szCompetencia = year + (month>9?""+month:"0"+month); 
        do {
        	// Monta Mensagem
        	String szMesAnoCompetencia = CobillingDateUtils.getShortMonthName(month) + "/" + year;
        	szCompetencia = year + (month>9?""+month:"0"+month);
        	
        	// ObservaÁ„o M
        	if (szCompetenciaM.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_1.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-1"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_2.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-2"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else
        	if (szCompetenciaM_3.equals(szCompetencia)) {
        		szMesAnoCompetencia = ""+"M-3"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	} else {
    			szMesAnoCompetencia = ""+"†"+""+pBreakLine+""+szMesAnoCompetencia+"";
        	}

        	// AvanÁar Mes
        	month++;
        	if (month>12) {
        		month = 1;
        		year++;
        	}
        	list.add(szMesAnoCompetencia);
        } while(!szCompetencia.equalsIgnoreCase(szCompetenciaLimite));
        
        String[] vetorREF = new String[14];
        int index = 0;
        vetorREF[index] = "††††††††††††††††††††††††††††††††††††††††";
        index++;
    	for (java.util.Iterator iter1 = list.iterator(); iter1.hasNext();) {
    		vetorREF[index] = (String) iter1.next();
    		index++;
		}
        return vetorREF;
    }

    /**
     * Retorna um array de String contendo os meses da tela de Relatorio de Financeiro
     * @param int month, int year
     * @return String[]
     */
    public static String[] getMesesRetroativosRelRecebiveis(int month, int year, String pBreakLine) {
    	List list = new ArrayList();

    	// ObservaÁ„o M
    	int month_M = month;
    	int year_M = year;    	
        String szCompetenciaM = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_1 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_2 = year_M + (month_M>9?""+month_M:"0"+month_M);
    	month_M--;
    	if (month_M<1) {
    		month_M = 12;
    		year_M--;
    	}
        String szCompetenciaM_3 = year_M + (month_M>9?""+month_M:"0"+month_M);       
        
        // retornar 12 meses
        String szCompetenciaLimite = year + (month>9?""+month:"0"+month);
        year--;
    	String szCompetencia = year + (month>9?""+month:"0"+month); 
        do {
        	// Monta Mensagem
        	String szMesAnoCompetencia = CobillingDateUtils.getShortMonthName(month) + "/" + year;
        	szCompetencia = year + (month>9?""+month:"0"+month);
        	


        	// AvanÁar Mes
        	month++;
        	if (month>12) {
        		month = 1;
        		year++;
        	}
        	list.add(szMesAnoCompetencia);
        } while(!szCompetencia.equalsIgnoreCase(szCompetenciaLimite));
        
        String[] vetorREF = new String[14];
        int index = 0;
        vetorREF[index] = "††††††††††††††††††††††††††††††††††††††††";
        index++;
    	for (java.util.Iterator iter1 = list.iterator(); iter1.hasNext();) {
    		vetorREF[index] = (String) iter1.next();
    		index++;
		}
        return vetorREF;
    }
    
    
    /**
     * Serializa um objecto usando o BASE64Encoder da sun
     * @param pObject
     * @return
     * @throws Exception
     */
    public static String encode(Serializable pObject) throws Exception{
    	String lRetorno = null;
    	
    	ByteArrayOutputStream lBaos = new ByteArrayOutputStream();
    	ObjectOutput lObjOut = new ObjectOutputStream(lBaos);
    	
    	lObjOut.writeObject(pObject);
    	
    	BASE64Encoder lEncoder = new BASE64Encoder();
    	lRetorno = lEncoder.encode(lBaos.toByteArray());
    	
    	return lRetorno;
    }

    /**
     * Desserializa um objeto usando o BASE64Decoder da sun 
     * @param pObject
     * @return
     * @throws Exception
     */
    public static Object decode(String pObject) throws Exception{
    	Object lRetorno = null;
    	
    	BASE64Decoder lDecode = new BASE64Decoder();
    	
    	byte[] lObject = lDecode.decodeBuffer(pObject);
    	
    	ByteArrayInputStream lIn = new ByteArrayInputStream(lObject);
    	ObjectInputStream lObjectInput = new ObjectInputStream(lIn);
    	
    	lRetorno = lObjectInput.readObject();;
    	
    	return lRetorno;
    }

    /**
     * MÈtodo que trunca o numero passado por parametro na quantidade de 
     * casas decimais tambem passadas por parametro
     * @param pNumero
     * @param pCasasDecimais
     * @return
     */
    public static double truncate(double pNumero, int pCasasDecimais) {
    	int fator = 10 ^ pCasasDecimais;    	
		if (pNumero > 0) {
			return Math.floor(pNumero * fator) / fator;			
		}
		else {
			return Math.ceil(pNumero * fator) / fator;
		}
	}
    
    /**
     * Completa a string str com o caracter fill ‡ esquerda atÈ completar a quantidade 
     * qtdChars de caractes. 
     * @param str String original
     * @param qtdChars Quantidade m·xima de caracteres final para a string
     * @param fill Caracter de preenchimento
     * @return
     */
    public static String lpad(String str, int qtdChars, char fill) {
    	return lpad(str, qtdChars, fill, false);
    }

    /**
     * Completa a string str com o caracter fill ‡ esquerda atÈ completar a quantidade 
     * qtdChars de caractes. 
     * @param str String original
     * @param qtdChars Quantidade m·xima de caracteres final para a string
     * @param fill Caracter de preenchimento
     * @param truncate Indica se a string original tiver mais que qtdChars ser· utilizado o
     * 	substring 0 qtdChars-1
     * @return
     */
    public static String lpad(String str, int qtdChars, char fill, boolean truncate) {
    	if (str == null) str = "";
    	
    	while(str.length() < qtdChars) {
    		str = fill + str;
    	}
    	if (truncate && str.length() > qtdChars) {
    		str = str.substring(0, qtdChars);
    	}
    	return str;
    }
}
