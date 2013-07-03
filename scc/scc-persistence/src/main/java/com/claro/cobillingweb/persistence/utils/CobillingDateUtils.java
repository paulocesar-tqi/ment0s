/**
 * 
 */
package com.claro.cobillingweb.persistence.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author vagner.souza
 *
 */
public class CobillingDateUtils {
	
	private static Locale locale = new Locale("pt","BR");	
	
	private static final int[] daysMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 };

	private static final String[] shortMonthName = { "", "JAN", "FEV", "MAR",
			"ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" };

	private static final String[] monthName = { "", "Janeiro", "Fevereiro",
			"Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
			"Outubro", "Novembro", "Dezembro" };

	private static final String[] upperMonthName = { "", "JANEIRO",
			"FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO",
			"SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" };

	private static final String EMPTY_STRING = "";

	private static String shortDateFormatZero = "dd/MM/yyyy";

	private static String shortDateFormat = "d/M/yyyy";

	private static String sqlDateFormat = "d/M/yyyy:HH:mm:ss";

	private static String completeDateFormat = "dd/MM/yyyy hh:mm:ss a";

	private static String hourMinutesDateFormat = "dd/MM/yyyy hh:mm";

	private static String hashDateFormat = "yyyyMMddHHmmss";
	
	private static String simpleHashdateFormat = "ddMMyyyy";

	private static String dayDateFormat = "d";
	
	private static String hourDateFormat = "hh";
	
	private static String minutesDateFormat = "mm";

	private static String monthDateFormat = "M";

	private static String yearDateFormat = "yyyy";

	private static String yearMonthDateFormat = "yyyyMM";
	
	private static SimpleDateFormat hour;
	
	private static SimpleDateFormat minutes;
	
	private static SimpleDateFormat daymonthyear;

	private static SimpleDateFormat sdfzero;

	private static SimpleDateFormat sdf;

	private static SimpleDateFormat cdf;

	private static SimpleDateFormat cdfMinutes;

	private static SimpleDateFormat hdf;

	private static SimpleDateFormat sqldf;

	private static SimpleDateFormat dayFormat;

	private static SimpleDateFormat monthFormat;

	private static SimpleDateFormat yearFormat;

	private static SimpleDateFormat yearMonthFormat;

	static {
		daymonthyear = new SimpleDateFormat(simpleHashdateFormat);
		sdfzero = new SimpleDateFormat(shortDateFormatZero);
		sdf = new SimpleDateFormat(shortDateFormat);
		cdf = new SimpleDateFormat(completeDateFormat);
		cdfMinutes = new SimpleDateFormat(hourMinutesDateFormat);
		hdf = new SimpleDateFormat(hashDateFormat);
		sqldf = new SimpleDateFormat(sqlDateFormat);
		dayFormat = new SimpleDateFormat(dayDateFormat);
		monthFormat = new SimpleDateFormat(monthDateFormat);
		yearFormat = new SimpleDateFormat(yearDateFormat);
		yearMonthFormat = new SimpleDateFormat(yearMonthDateFormat);
		hour = new SimpleDateFormat(hourDateFormat);
		minutes = new SimpleDateFormat(minutesDateFormat);
	}


	/**
	 * Checks if day, month and year are valid for date conversion
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * 
	 * Updated 26/06/2006
	 */
	public static boolean checkValidateDate(String day, String month,
			String year) {

		try {
			day = CommonsUtils.getDigits(day);
			month = CommonsUtils.getDigits(month);
			year = CommonsUtils.getDigits(year);

			if (!CommonsUtils.isEmpty(day) && !CommonsUtils.isEmpty(month)
					&& !CommonsUtils.isEmpty(year)) {
				int _day, _month, _year;
				_day = Integer.parseInt(day);
				_month = Integer.parseInt(month);
				_year = Integer.parseInt(year);

				if (_month < 1 || _month > 12) {
					return false;
				}

				if (_day >= 1) {
					if (_year % 4 == 0 && _month == 2) {
						if (_day > 29) {
							return false;
						}
					} else
					if (_day > daysMonth[_month]) {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * @param month
	 *            (1-Jan / 12-Dec)
	 * @return Month name
	 */
	public static String getShortMonthName(int month) {
		if (month >= 1 && month <= 12) {
			return shortMonthName[month];
		}
		return EMPTY_STRING;
	}

	/**
	 * @param month
	 *            (1-Jan / 12-Dec)
	 * @return Month name
	 */
	public static String getMonthName(int month) {
		if (month >= 1 && month <= 12) {
			return monthName[month];
		}
		return EMPTY_STRING;
	}

	/**
	 * @param month
	 *            (1-Jan / 12-Dec)
	 * @return Month name
	 */
	public static String getUpperMonthName(int month) {
		if (month >= 1 && month <= 12) {
			return upperMonthName[month];
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada dd/MM/yyyy
	 */
	public static String convertToShortStringZero(java.util.Date date) {
		if (date != null) {
			return sdfzero.format(date);
		}
		return EMPTY_STRING;
	}
	
	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada ddMMyyyy
	 */
	public static String convertToDayMonthYear(java.util.Date date) {
		if (date != null) {
			return daymonthyear.format(date);
		}
		return EMPTY_STRING;
	}
	

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada yyyyMM
	 */
	public static String convertToYearMonth(java.util.Date date) {
		if (date != null) {
			return yearMonthFormat.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada d/M/yyyy
	 */
	public static String convertToShortString(java.util.Date date) {
		if (date != null) {
			return sdf.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada d/M/yyyy
	 */
	public static String convertToShortString(Timestamp date) {
		if (date != null) {
			return convertToShortString(new java.util.Date(date.getTime()));
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada dd/MM/yyyy h:mm:ss a
	 */
	public static String convertToLongString(java.util.Date date) {
		if (date != null) {
			return cdf.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada dd/MM/yyyy h:mm
	 */
	public static String convertToLongStringHourMinutes(java.util.Date date) {
		if (date != null) {
			return cdfMinutes.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo a data formatada
	 * 
	 * @param date
	 * @return data formatada dd/MM/yyyy:hh24:mi:ss
	 */
	public static String convertToSQLString(java.util.Date date) {
		if (date != null) {
			return sqldf.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtém uma String contendo um hash para uma dada data
	 * 
	 * @param date
	 * @return hash no formato yyyyMMddHHmmss
	 */
	public static String convertToHashString(java.util.Date date) {
		if (date != null) {
			return hdf.format(date);
		}
		return EMPTY_STRING;
	}


	/**
	 * Obtem em formato de String o dia da data passada
	 * 
	 * @param date
	 * @return String contendo o dia
	 */
	public static String getDay(java.util.Date date) {
		if (date != null) {
			return (dayFormat.format(date).length() == 1 ? "0"
					+ dayFormat.format(date) : dayFormat.format(date));
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtem em formato de String o mês da data passada
	 * 
	 * @param date
	 * @return String contendo o mês
	 */
	public static String getMonth(java.util.Date date) {
		if (date != null) {
			return (monthFormat.format(date).length() == 1 ? "0"
					+ monthFormat.format(date) : monthFormat.format(date));
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtem em formato de String a hora da data passada
	 * 
	 * @param date
	 * @return String contendo o ano
	 */
	public static String getHour(java.util.Date date) {
		if (date != null) {
			return (hour.format(date).length() == 1 ? "0"
					+ hour.format(date) : hour.format(date));
		}
		return EMPTY_STRING;
	}

	/**
	 * Obtem em formato de String o minuto da data passada
	 * 
	 * @param date
	 * @return String contendo o ano
	 */
	public static String getMinutes(java.util.Date date) {
		if (date != null) {
			return (minutes.format(date).length() == 1 ? "0"
					+ minutes.format(date) : minutes.format(date));
		}
		return EMPTY_STRING;
	}
	
	
	/**
	 * Obtem em formato de String o ano da data passada
	 * 
	 * @param date
	 * @return String contendo o ano
	 */
	public static String getYear(java.util.Date date) {
		if (date != null) {
			return yearFormat.format(date);
		}
		return EMPTY_STRING;
	}
	
	
	public static int getDayTimestamp(Timestamp ts) {
		int _day = 0;
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_day = calendar.get(Calendar.DAY_OF_MONTH);
		}
		return _day;
	}

	public static int getMonthTimestamp(Timestamp ts) {
		int _month = 0;
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_month = calendar.get(Calendar.MONTH) + 1;
		}
		return _month;
	}

	public static int getYearTimestamp(Timestamp ts) {
		int _year = 0;
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_year = calendar.get(Calendar.YEAR);
		}
		return _year;
	}
	
	public static String getDayTimestampString(Timestamp ts) {
		int _day = 0;
		String day ="";
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_day = calendar.get(Calendar.DAY_OF_MONTH);
		}
		if(_day < 10){
		   	day = "0"+_day;
		}else{
			day = ""+_day;
		}
		
		return day;
	}

	public static String getMonthTimestampString(Timestamp ts) {
		int _month = 0;
		String month = "";
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_month = calendar.get(Calendar.MONTH) + 1;
		}
		if(_month < 10){
			month = "0"+_month;
		}else{
			month = ""+_month;
		}
		return month;
	}

	public static String getYearTimestampString(Timestamp ts) {
		int _year = 0;
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			_year = calendar.get(Calendar.YEAR);
		}
		return ""+_year;
	}
	
	/**
	 * Obtain the date for Strings param, if not correct, throws Exception
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @param hour
	 * @param minutes
	 * @param seconds
	 * @return Returns date formated from parameters
	 * @throws DateConversionException
	 */
	public static java.util.Date obtainDate(String day, String month,
			String year, String hour, String minutes, String seconds) {
		Calendar cal = Calendar.getInstance(locale);
		cal.set(new Integer(year).intValue(),
        		new Integer(month).intValue(),
        		new Integer(day).intValue(),
        		new Integer(hour).intValue(),
        		new Integer(minutes).intValue(),
				new Integer(seconds).intValue());
		return cal.getTime();
	}

	/**
	 * @param d
	 * @return date as Start java.util.Date
	 */
	public static java.util.Date convertStartDate(java.util.Date d) {
		if (d != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			d = new java.util.Date(calendar.getTimeInMillis());
		}
		return d;
	}

	public static java.util.Date getPeriod(int daysFromToday) {
		java.util.Date d = null;
		if (daysFromToday >= 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.DAY_OF_MONTH, (-1) * daysFromToday);
			d = new java.util.Date(calendar.getTimeInMillis());
		}
		return d;
	}

    /**
     * @param ts
     * @return date as Start Timestamp
     */
    public static Timestamp convertStartTimestamp(Timestamp ts) {
        if (ts!=null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(ts);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            ts = new Timestamp(calendar.getTimeInMillis());
        }
        return ts;
    }
    
    /**
     * @param d
     * @return date as End java.util.Date
     */
    public static java.util.Date convertEndDate(java.util.Date d) {
        if (d!=null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);
            d = new java.util.Date(calendar.getTimeInMillis());
        }
        return d;
    }
    
    /**
     * @param ts
     * @return date as End Timestamp
     */
    public static Timestamp convertEndTimestamp(Timestamp ts) {
        if (ts!=null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(ts);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);
            ts = new Timestamp(calendar.getTimeInMillis());
        }
        return ts;
    }
    
    public static Timestamp getReferenceDateNow() 
    {
		Timestamp referenceDateNow = null;
		Calendar cal = Calendar.getInstance(locale);
		referenceDateNow = new Timestamp(cal.getTimeInMillis());
		return referenceDateNow;
	}

	public static java.sql.Date getDateNow() {
		Calendar cal = Calendar.getInstance(locale);
		java.sql.Date dateNow = new java.sql.Date(cal.getTimeInMillis());
		return dateNow;
	}

	public static java.sql.Timestamp getDateNowTimestamp() {
		Calendar cal = Calendar.getInstance(locale);
		java.sql.Timestamp dateNow = new java.sql.Timestamp(cal
				.getTimeInMillis());
		return dateNow;
	}	
    
	public static Timestamp getReferenceDateStart(Integer month, Integer year, Integer quinzena) {
		if (quinzena.intValue()==0) {
			Timestamp startReferenceDateM1 = null;
			if (month != null && year != null) {
				int _month = month.intValue();
				int _year = year.intValue();

				// Referencia M-2
				if (_month >= 1 && _month <= 12) {
					Calendar cal = Calendar.getInstance(locale);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					cal.set(Calendar.MONTH, (_month - 1));
					cal.set(Calendar.YEAR, _year);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					startReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
				}
			}
			return startReferenceDateM1;
		} else {
			if (quinzena.intValue()==1) { // 1o Repasse - de 1/mes atual/ano até 15/mes atual/ano
				Timestamp startReferenceDateM1 = null;
				if (month != null && year != null) {
					int _month = month.intValue();
					int _year = year.intValue();

					// Referencia M-2
					if (_month >= 1 && _month <= 12) {
						Calendar cal = Calendar.getInstance(locale);
						cal.set(Calendar.DAY_OF_MONTH, 1);
						cal.set(Calendar.MONTH, (_month - 1));
						cal.set(Calendar.YEAR, _year);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						startReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
					}
				}
				return startReferenceDateM1;
			} else { // 2a Repasse - de 16/mes anterior/ano até <fim mes>/mes anterior/ano
				Timestamp startReferenceDateM1 = null;
				if (month != null && year != null) {
					int _month = month.intValue();
					int _year = year.intValue();

					// Referencia M-2
					if (_month >= 1 && _month <= 12) {
						Calendar cal = Calendar.getInstance(locale);
						cal.set(Calendar.DAY_OF_MONTH, 16);
						cal.set(Calendar.MONTH, (_month - 1));
						cal.set(Calendar.YEAR, _year);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						startReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
					}
				}
				return startReferenceDateM1;
			}
		}
	}

	public static Timestamp getReferenceDateEnd(Integer month, Integer year, Integer quinzena) {
		if (quinzena.intValue()==0) {
			Timestamp endReferenceDateM1 = null;
			if (month != null && year != null) {
				int _month = month.intValue();
				int _year = year.intValue();

				int _maxDay = 0;

				// Referencia M-2
				if (_month >= 1 && _month <= 12) {
					Calendar cal = Calendar.getInstance(locale);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					cal.set(Calendar.MONTH, (_month - 1));
					cal.set(Calendar.YEAR, _year);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					_maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					cal.set(Calendar.DAY_OF_MONTH, _maxDay);
					cal.set(Calendar.HOUR_OF_DAY, 23);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.SECOND, 59);
					cal.set(Calendar.MILLISECOND, 0);
					endReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
				}
			}
			return endReferenceDateM1;
		} else {
			if (quinzena.intValue()==1) { // 1o Repasse - de 1/mes atual/ano até 15/mes atual/ano
				Timestamp endReferenceDateM1 = null;
				if (month != null && year != null) {
					int _month = month.intValue();
					int _year = year.intValue();

					// Referencia M-2
					if (_month >= 1 && _month <= 12) {
						Calendar cal = Calendar.getInstance(locale);
						cal.set(Calendar.DAY_OF_MONTH, 15);
						cal.set(Calendar.MONTH, (_month - 1));
						cal.set(Calendar.YEAR, _year);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						cal.set(Calendar.HOUR_OF_DAY, 23);
						cal.set(Calendar.MINUTE, 59);
						cal.set(Calendar.SECOND, 59);
						cal.set(Calendar.MILLISECOND, 0);
						endReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
					}
				}
				return endReferenceDateM1;
			} else { // 2a Repasse - de 16/mes anterior/ano até <fim mes>/mes anterior/ano
				Timestamp endReferenceDateM1 = null;
				if (month != null && year != null) {
					int _month = month.intValue();
					int _year = year.intValue();

					int _maxDay = 0;

					// Referencia M-2
					if (_month >= 1 && _month <= 12) {
						Calendar cal = Calendar.getInstance(locale);
						cal.set(Calendar.DAY_OF_MONTH, 1);
						cal.set(Calendar.MONTH, (_month - 1));
						cal.set(Calendar.YEAR, _year);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						_maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
						cal.set(Calendar.DAY_OF_MONTH, _maxDay);
						cal.set(Calendar.HOUR_OF_DAY, 23);
						cal.set(Calendar.MINUTE, 59);
						cal.set(Calendar.SECOND, 59);
						cal.set(Calendar.MILLISECOND, 0);
						endReferenceDateM1 = new Timestamp(cal.getTimeInMillis());
					}
				}
				return endReferenceDateM1;
			}
		}
	}

	public static Timestamp getReferenceDateStartBefore(Integer month, Integer year) {
		Timestamp startReferenceDateM2 = null;
		if (month != null && year != null) {
			int _month = month.intValue();
			int _year = year.intValue();

			// Referencia M-2
			if (_month >= 1 && _month <= 12) {
				if (_month == 1) {
					_month = 12;
					_year--;
				} else {
					_month--;
				}

				Calendar cal = Calendar.getInstance(locale);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.MONTH, (_month - 1));
				cal.set(Calendar.YEAR, _year);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				startReferenceDateM2 = new Timestamp(cal.getTimeInMillis());
			}
		}
		return startReferenceDateM2;
	}

	public static Timestamp getReferenceDateEndBefore(Integer month, Integer year) {
		Timestamp endReferenceDateM2 = null;
		if (month != null && year != null) {
			int _month = month.intValue();
			int _year = year.intValue();

			int _maxDay = 0;

			// Referencia M-2
			if (_month >= 1 && _month <= 12) {
				if (_month == 1) {
					_month = 12;
					_year--;
				} else {
					_month--;
				}

				Calendar cal = Calendar.getInstance(locale);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.MONTH, (_month - 1));
				cal.set(Calendar.YEAR, _year);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				_maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, _maxDay);
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				cal.set(Calendar.MILLISECOND, 0);
				endReferenceDateM2 = new Timestamp(cal.getTimeInMillis());
			}
		}
		return endReferenceDateM2;
	}

	public static Timestamp converteStartDate(int _day, int _month, int _year) {
		Timestamp timestamp = null;
		if (_month != 0 && _year != 0) {
			Calendar cal = Calendar.getInstance(locale);
			cal.set(Calendar.DAY_OF_MONTH, _day);
			cal.set(Calendar.MONTH, (_month - 1));
			cal.set(Calendar.YEAR, _year);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			timestamp = new Timestamp(cal.getTimeInMillis());
		}
		return timestamp;
	}

	public static Timestamp converteEndDate(int _day, int _month, int _year) {
		Timestamp timestamp = null;
		if (_month != 0 && _year != 0) {
			Calendar cal = Calendar.getInstance(locale);
			cal.set(Calendar.DAY_OF_MONTH, _day);
			cal.set(Calendar.MONTH, (_month - 1));
			cal.set(Calendar.YEAR, _year);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			timestamp = new Timestamp(cal.getTimeInMillis());
		}
		return timestamp;
	}


	/**
	 * @param ts
	 * @return date as getNextDayTimestamp Timestamp
	 */
	public static Timestamp getNextDayTimestamp(Timestamp ts) {
		if (ts != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(ts);
			int _maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (calendar.get(Calendar.DAY_OF_MONTH) < _maxDay)
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			else if (calendar.get(Calendar.MONTH) < 11) {
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.MONTH, 1);
			} else {
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.MONTH, 0);
				calendar.add(Calendar.YEAR, 1);
			}
			ts = new Timestamp(calendar.getTimeInMillis());
		}
		return ts;
	}

	/**
	 * @param dt
	 * @return date as getNextDayDate Date
	 */
	public static Date getNextDayDate(Date dt) {
		if (dt != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			dt = new Date(calendar.getTimeInMillis());
		}
		return dt;
	}

	/**
	 * @param dt
	 * @return date as getNextDayDate Date
	 */
	public static Date getNextDayDate(Date dt, int qt) {
		if (dt != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt);
			calendar.add(Calendar.DAY_OF_MONTH, qt);
			dt = new Date(calendar.getTimeInMillis());
		}
		return dt;
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public static String numDecimalString(String num){
		DecimalFormat nf = new DecimalFormat("##.00");
		return nf.format(num);
	}

	/**
	 * 
	 * @param ano
	 * @param mes
	 * @return Último dia do mês
	 */
	public static String getLastDay(int ano, int mes ){
		String a = "";
	   	Calendar c = new  GregorianCalendar(ano,mes,0);
	    return a+c.getActualMaximum(Calendar.DAY_OF_MONTH);		
	}	
     
    public static Timestamp getStartDatePagamento(int year, int month ,int day){
    	Calendar cal = Calendar.getInstance(locale);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, (month - 1 ));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
    	
    }

    public static Timestamp getEndDatePagamento(int year, int month ,int day){
    	int lastDayMonth = Integer.parseInt(getLastDay(year,month));

    	Calendar cal = Calendar.getInstance(locale);
    	if(day <= lastDayMonth ){
    		cal.set(Calendar.DAY_OF_MONTH, day);
    	}else{
    		cal.set(Calendar.DAY_OF_MONTH, lastDayMonth );
    	}
    	cal.set(Calendar.MONTH, (month - 1 ));
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MINUTE, 59);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MILLISECOND, 0);
    	return new Timestamp(cal.getTimeInMillis());

    }
    
     public static Date biggerMonth( int day, int month, int year){
    	Calendar cal = Calendar.getInstance(locale);
    	cal.set(Calendar.DAY_OF_MONTH, day);
    	cal.set(Calendar.MONTH, (month ));
		cal.set(Calendar.YEAR, year);		
		return new java.util.Date(cal.getTimeInMillis());
    	
    }
     
    public static Date dataSumDay(java.util.Date data, int dias){
         Calendar calendar = Calendar.getInstance();   
	     
         calendar.setTime(data);   
	     calendar.add(Calendar.DATE, dias);  
	     
	     return calendar.getTime();
    }
   
    public static Date dataSumMonth(java.util.Date data, int meses){
        Calendar calendar = Calendar.getInstance();   
	     
        calendar.setTime(data);   
	    calendar.add(Calendar.MONTH, meses);  
	     
	    return calendar.getTime();
    }
     /**  
      * Método para comparar as das e retornar o numero de dias de diferença entre elas  
      *  
      * Compare two date and return the difference between them in days.  
      *  
      * @param dataLow The lowest date  
      * @param dataHigh The highest date  
      *  
      * @return int  
      */   
    public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh){   
      
         GregorianCalendar startTime = new GregorianCalendar();   
         GregorianCalendar endTime = new GregorianCalendar();   
           
         GregorianCalendar curTime = new GregorianCalendar();   
         GregorianCalendar baseTime = new GregorianCalendar();   
      
         startTime.setTime(dataLow);   
         endTime.setTime(dataHigh);   
           
         int dif_multiplier = 1;   
           
         // Verifica a ordem de inicio das datas   
         if( dataLow.compareTo( dataHigh ) < 0 ){   
             baseTime.setTime(dataHigh);   
             curTime.setTime(dataLow);   
             dif_multiplier = 1;   
         }else{   
             baseTime.setTime(dataLow);   
             curTime.setTime(dataHigh);   
             dif_multiplier = -1;   
         }   
           
         int result_years = 0;   
         int result_months = 0;   
         int result_days = 0;   
      
         // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando   
         // no total de dias. Ja leva em consideracao ano bissesto   
         while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||   
                curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )   
         {   
               
             int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );   
             result_months += max_day;   
             curTime.add(GregorianCalendar.MONTH, 1);   
               
         }   
           
         // Marca que é um saldo negativo ou positivo   
         result_months = result_months*dif_multiplier;   
           
           
         // Retirna a diferenca de dias do total dos meses   
         result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));   
           
         return result_years+result_months+result_days;   
    }

    /**
     * Retorna a "Data Base" do pagamento seguindo as regras de 1° ou 2° quinzena:
     * 1° quinzena, a data deve ser dia 30 (último dia do mês ou primeiro dia útil subseqüente) do mês atual.
     * 2° quinzena, a data deve ser dia 15 (ou primeiro dia útil subseqüente) do próximo mês.
     * Se a data cair num final de semana ou feriado, ajusta para o próximo dia útil.
     * @param quinzena
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Date getPaymentDate(int quinzena, List holidaysList) {

		Calendar cal = Calendar.getInstance(locale);
		Date paymentDate = null;

		//Se for a 1° quinzena, a data deve ser dia 30 (último dia do mês ou primeiro dia útil subseqüente) do mês atual.
		if(quinzena == 1) {

			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		} else if(quinzena == 2) {
			//Se for a 2° quinzena, a data deve ser dia 15 (ou primeiro dia útil subseqüente) do próximo mês.
		   //cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, 15);
		}

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);

		paymentDate = getBusinessDay(cal, holidaysList);

		return paymentDate;
	}

    /**
     * Verifica se a data é um final de semana ou feriado e busca o próximo dia útil.
     * @param cal
     * @param holidaysList
     * @return
     */
    @SuppressWarnings("rawtypes")
	private static Date getBusinessDay(Calendar cal, List holidaysList) {

		//Se a data cair num final de semana, ajusta para segunda-feira.
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.add(Calendar.DATE, 2);
		} else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 1);
		}

		//Verifica os feriados.
		if(holidaysList != null && holidaysList.size() > 0) {

			Calendar calHoliday = Calendar.getInstance(locale);

			for(int i=0; i < holidaysList.size(); i++) {

				String[] holiday = (String[])holidaysList.get(i);

				calHoliday.set(Calendar.DAY_OF_MONTH, Integer.parseInt(holiday[0]));
				calHoliday.set(Calendar.MONTH, Integer.parseInt(holiday[1]) - 1); //-1 since the first month of the year is JANUARY which is 0.
				calHoliday.set(Calendar.HOUR_OF_DAY, 23);
				calHoliday.set(Calendar.MINUTE, 59);
				calHoliday.set(Calendar.SECOND, 59);
				calHoliday.set(Calendar.MILLISECOND, 0);

				//Se a data cair num feriado
				if(cal.getTime().equals(calHoliday.getTime())) {
					cal.add(Calendar.DATE, 1);
					getBusinessDay(cal, holidaysList);
					break;
				}
			}
		}

		return cal.getTime();
	}

    /**
     * Verifica se a data é um final de semana ou feriado e busca o próximo dia útil.
     * @param date
     * @param holidaysList
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Date getBusinessDay(Date date, List holidaysList) {
    	Calendar cal = Calendar.getInstance(locale);
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
    	return getBusinessDay(cal, holidaysList);
    }

    /**
     * Converte a Data informada para uma String formatada pelo pattern informado.
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String pattern) {
    	SimpleDateFormat sd = new SimpleDateFormat(pattern);
    	return sd.format(date);
    }

    /**
     * Converte a String informada para uma Data formatada pelo pattern informado.
     * @param date
     * @param format
     * @return
     * @throws ParseException 
     */
    public static Date stringToDate(String date, String pattern) throws ParseException {
    	SimpleDateFormat sd = new SimpleDateFormat(pattern);
    	return sd.parse(date);
    }


}
