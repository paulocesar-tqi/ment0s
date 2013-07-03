/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileView {
	
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
	
	private String noArquivoReferencia;
	private Date dtRelatorio;
	private Integer cdCiclo;
	private Integer qtChamadas;
	private Double qtMinutoTarifados;
	private Double vlLiquido;
	private Date dtChamada;

	public String getNoArquivoReferencia() {
		return noArquivoReferencia;
	}
	public void setNoArquivoReferencia(String noArquivoReferencia) {
		this.noArquivoReferencia = noArquivoReferencia;
	}
	public Date getDtRelatorio() {
		return dtRelatorio;
	}
	public void setDtRelatorio(Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}
	public Integer getCdCiclo() {
		return cdCiclo;
	}
	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	public Integer getQtChamadas() {
		return qtChamadas;
	}
	public void setQtChamadas(Integer qtChamadas) {
		this.qtChamadas = qtChamadas;
	}
	public Double getQtMinutoTarifados() {
		return qtMinutoTarifados;
	}
	public void setQtMinutoTarifados(Double qtMinutoTarifados) {
		this.qtMinutoTarifados = qtMinutoTarifados;
	}
	
	public String getQtMinutoTarifadosStr(){
		String value = "";
		if(this.qtMinutoTarifados != null){
			value = decimalFormat.format(this.getQtMinutoTarifados());
		}
		return value;
	}
	
	public Double getVlLiquido() {
		return vlLiquido;
	}
	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}
	
	public String getVlLiquidoStr(){
		String value = "";
		if(this.vlLiquido != null){
			value = decimalFormat.format(this.getVlLiquido());
		}
		return value;
	}
	
	public Date getDtChamada() {
		return dtChamada;
	}
	public void setDtChamada(Date dtChamada) {
		this.dtChamada = dtChamada;
	}
}
