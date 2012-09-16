package com.claro.sccweb.form;

import java.util.Date;

public class ControleRemessaCDRPreForm extends BaseForm {

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private String tipoPeriodo = "C";
	private Date dataInicial;
	private Date dataFinal;
	
	private String quantidadeCDRs;
	private String minutosReais;
	private String minutosTarifados;
	private String valorBruto;
	private boolean mostrarSomatorio = false;
	
	public String getTipoOperadora() {
		return tipoOperadora;
	}
	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getQuantidadeCDRs() {
		return quantidadeCDRs;
	}
	public void setQuantidadeCDRs(String quantidadeCDRs) {
		this.quantidadeCDRs = quantidadeCDRs;
	}
	public String getMinutosReais() {
		return minutosReais;
	}
	public void setMinutosReais(String minutosReais) {
		this.minutosReais = minutosReais;
	}
	public String getMinutosTarifados() {
		return minutosTarifados;
	}
	public void setMinutosTarifados(String minutosTarifados) {
		this.minutosTarifados = minutosTarifados;
	}
	public String getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(String valorBruto) {
		this.valorBruto = valorBruto;
	}
	public boolean isMostrarSomatorio() {
		return mostrarSomatorio;
	}
	public void setMostrarSomatorio(boolean mostrarSomatorio) {
		this.mostrarSomatorio = mostrarSomatorio;
	}
	
	
	
	
}
