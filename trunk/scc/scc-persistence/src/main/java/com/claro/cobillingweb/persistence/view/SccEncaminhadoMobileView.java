/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccEncaminhadoMobileView {

	private Date dtRelatorio;
	private String noArquivoReferencia;
	private Integer qtChamadas;
	private Double qtMinutoTarifados;
	private Double vlLiquido;
	private Integer nuItem;
	private Integer sqRelatorioSumarizado;
	private Date dtProcClaro;
	
	public Date getDtRelatorio() {
		return dtRelatorio;
	}
	public void setDtRelatorio(Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}
	public String getNoArquivoReferencia() {
		return noArquivoReferencia;
	}
	public void setNoArquivoReferencia(String noArquivoReferencia) {
		this.noArquivoReferencia = noArquivoReferencia;
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
	public Double getVlLiquido() {
		return vlLiquido;
	}
	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}
	public Integer getNuItem() {
		return nuItem;
	}
	public void setNuItem(Integer nuItem) {
		this.nuItem = nuItem;
	}
	public Integer getSqRelatorioSumarizado() {
		return sqRelatorioSumarizado;
	}
	public void setSqRelatorioSumarizado(Integer sqRelatorioSumarizado) {
		this.sqRelatorioSumarizado = sqRelatorioSumarizado;
	}
	public Date getDtProcClaro() {
		return dtProcClaro;
	}
	public void setDtProcClaro(Date dtProcClaro) {
		this.dtProcClaro = dtProcClaro;
	}

}
