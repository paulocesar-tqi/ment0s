/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 93046251
 *
 */
public class RelAlarmeOperacionalView {
	
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
	
	private String numA;
	private Long qtdeChamadas; 
	private Long qtdeMinutosTarifados; 
	private Double valorTotalChamadas; 
	private String  nroFatura;
	private Long nroNf;
	private String nomeOperadoraLD; 
	private Date dataReferencia;
	public String getNumA() {
		return numA;
	}
	public void setNumA(String numA) {
		this.numA = numA;
	}
	public Long getQtdeChamadas() {
		return qtdeChamadas;
	}
	public void setQtdeChamadas(Long qtdeChamadas) {
		this.qtdeChamadas = qtdeChamadas;
	}
	public Long getQtdeMinutosTarifados() {
		return qtdeMinutosTarifados;
	}
	public void setQtdeMinutosTarifados(Long qtdeMinutosTarifados) {
		this.qtdeMinutosTarifados = qtdeMinutosTarifados;
	}
	public Double getValorTotalChamadas() {
		return valorTotalChamadas;
	}
	public void setValorTotalChamadas(Double valorTotalChamadas) {
		this.valorTotalChamadas = valorTotalChamadas;
	}
		
	public String getValorTotalChamadasStr(){
		String value = "";
		if(this.valorTotalChamadas != null){
			value = decimalFormat.format(this.getValorTotalChamadas());
		}
		return value;
	}
		
	public String getNroFatura() {
		return nroFatura;
	}
	public void setNroFatura(String nroFatura) {
		this.nroFatura = nroFatura;
	}
	public Long getNroNf() {
		return nroNf;
	}
	public void setNroNf(Long nroNf) {
		this.nroNf = nroNf;
	}
	public String getNomeOperadoraLD() {
		return nomeOperadoraLD;
	}
	public void setNomeOperadoraLD(String nomeOperadoraLD) {
		this.nomeOperadoraLD = nomeOperadoraLD;
	}
	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	
}
