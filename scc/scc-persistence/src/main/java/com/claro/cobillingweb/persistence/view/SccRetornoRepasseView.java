/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * @author 93046251
 *
 */
public class SccRetornoRepasseView {
	
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
	
	private String csp;
	private String operadoraLD;
	private String uf;
	private String status;
	private String mes;
	private Double valor;
	private Long numRepasse;
	private String arquivo;
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getNumRepasse() {
		return numRepasse;
	}
	public void setNumRepasse(Long numRepasse) {
		this.numRepasse = numRepasse;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public String getValorStr(){
		String value = "";
		if(this.valor != null){
			value = decimalFormat.format(this.getValor());
		}
		return value;
	}

	
	
}
