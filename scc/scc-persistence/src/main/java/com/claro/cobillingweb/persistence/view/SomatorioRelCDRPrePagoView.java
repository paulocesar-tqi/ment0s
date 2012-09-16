package com.claro.cobillingweb.persistence.view;

public class SomatorioRelCDRPrePagoView {

	private Long quantidadeCDRs;
	private Double minutosReais;
	private Double minutosTarifados;
	private Double valorBruto;
	public Long getQuantidadeCDRs() {
		return quantidadeCDRs;
	}
	public void setQuantidadeCDRs(Long quantidadeCDRs) {
		this.quantidadeCDRs = quantidadeCDRs;
	}
	public Double getMinutosReais() {
		return minutosReais;
	}
	public void setMinutosReais(Double minutosReais) {
		this.minutosReais = minutosReais;
	}
	public Double getMinutosTarifados() {
		return minutosTarifados;
	}
	public void setMinutosTarifados(Double minutosTarifados) {
		this.minutosTarifados = minutosTarifados;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	
	
	
}
