package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.displaytag.decorator.TableDecorator;

public class BasicSccDecorator extends TableDecorator {
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat datePeriodoFormat = new SimpleDateFormat("MM/yyyy");
	protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat decimalFormat34 = new DecimalFormat("###.####");
	
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    }
	
	protected static NumberFormat sccCurrencyFormat = new DecimalFormat("#.##");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");
	static {
    	Locale locale = new Locale("pt","BR");
    	sccCurrencyFormat = NumberFormat.getInstance(locale);
    	sccCurrencyFormat.setMinimumFractionDigits(2);
    	sccCurrencyFormat.setMaximumFractionDigits(2);
    	integerFormat = NumberFormat.getInstance(locale);
    	integerFormat.setMinimumFractionDigits(0);
    	integerFormat.setMaximumFractionDigits(0);
    }
	
	protected Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}
	
	protected String formataDouble(Double valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(trunc(valor,2));
	}
	
	protected String formataDouble34(Double valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat34.format(trunc(valor, 4));
	}
	
	protected String formataDouble(Double valor, int prec) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(trunc(valor,prec));
	}
	
	protected String formataLong(Long valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(valor);
	}
	
	protected String formataInteger(Integer valor) {
		if (valor == null) {
			return " ";
		}
		return integerFormat.format(valor);
	}
	
	protected String exibeInteger(Integer valor) {
		if (valor == null) {
			return "";
		} else {
			return valor.toString();
		}
	}
	
	protected String exibeLong(Long valor) {
		if (valor == null) {
			return "";
		} else {
			return valor.toString();
		}
	}
	
	protected String formataDate(Date valor) {
		if (valor == null) {
			return " ";
		}
		return dateFormat.format(valor);
	}
	
	protected String formataDateTime(Date valor) {
		if (valor == null) {
			return " ";
		}
		return dateTimeFormat.format(valor);
	}
	
	protected String formataString(String value) {
		if (value == null) {
			return " ";
		}
		return value;
	}
	
	protected double trunc(Double db, int casas) {
		if (db == null) {
			return trunc(0.0000,2);
		}
    	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	return Math.round(db*fator)/fator;
    }
	
	protected boolean isFlagTrue(String value) {
		return ((value != null) && (value.equalsIgnoreCase("S")));
	}
	
	protected String spaceIfNull(Object value) {
		if (value == null) {
			return " ";
		} else {
			return value.toString();
		}
	}
	
}
