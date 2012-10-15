/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccAssexuadosView {

	private Date dtRelatorio;
	private String numeroDeA;
	private Integer qtChamadas;
	private Integer qtMinutoTarifados;
	private Double vlLiquido;
	private String cdErroReciclagem;
	private Integer nuItem;
	private Integer sqRelatorioSumarizado;
	
	public Date getDtRelatorio() {
		return dtRelatorio;
	}
	public void setDtRelatorio(Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}
	public String getNumeroDeA() {
		return numeroDeA;
	}
	public void setNumeroDeA(String numeroDeA) {
		this.numeroDeA = numeroDeA;
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
	public String getCdErroReciclagem() {
		return cdErroReciclagem;
	}
	public void setCdErroReciclagem(String cdErroReciclagem) {
		this.cdErroReciclagem = cdErroReciclagem;
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

}
