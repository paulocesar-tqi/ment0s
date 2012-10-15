/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileView {
	
	private String noArquivoReferencia;
	private Date dtRelatorio;
	private Integer cdCiclo;
	private Integer qtChamadas;
	private Integer qtMinutoTarifados;
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
	public Integer getQtMinutoTarifados() {
		return qtMinutoTarifados;
	}
	public void setQtMinutoTarifados(Integer qtMinutoTarifados) {
		this.qtMinutoTarifados = qtMinutoTarifados;
	}
	public Double getVlLiquido() {
		return vlLiquido;
	}
	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}
	public Date getDtChamada() {
		return dtChamada;
	}
	public void setDtChamada(Date dtChamada) {
		this.dtChamada = dtChamada;
	}
}
