package com.claro.sccweb.excel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ExcelUtils {
	
	
	
private static NumberFormat precisionTwo = new DecimalFormat("#.##");
	
	static {
    	Locale locale = new Locale("pt","BR");

    	precisionTwo = NumberFormat.getInstance(locale);
    	precisionTwo.setMinimumFractionDigits(2);
    	precisionTwo.setMaximumFractionDigits(2);
    }
	
	public static Double getDoublePrecision2(Double value)
	{		
		if (value == null)
			return 0.00;
		else
			return Double.parseDouble(precisionTwo.format(value).replaceAll(",", "."));
	}
	
}
