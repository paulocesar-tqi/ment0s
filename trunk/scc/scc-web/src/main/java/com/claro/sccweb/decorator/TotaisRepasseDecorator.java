package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Decorator utilizado no export para Excel do demonstrativo de repasse pós pago.
 *
 */
public class TotaisRepasseDecorator {

	private String operadoraClaro;
	private String operadoraLD;
	private Double valorRepasse;
	
	private String OperadoraClaroSemUF;
	
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    	integerFormat = NumberFormat.getInstance(locale);
    	integerFormat.setMinimumFractionDigits(0);
    	integerFormat.setMaximumFractionDigits(0);
    }

	
	public String getValorRepasseTotal(){
		String value = "";
		if(this.getValorRepasse() != null){
			value = decimalFormat.format(this.getValorRepasse());
		}
		return value;
	}
	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	
	public String getOperadoraClaroSemUF() {
		return operadoraClaro;
	}
	
	public void setOperadoraClaroSemUF(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public Double getValorRepasse() {
		return valorRepasse;
	}
	public void setValorRepasse(Double valorRepasse) {
		this.valorRepasse = valorRepasse;
	}
	
	protected String formataDouble(Double valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(trunc(valor,2));
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


	
}
