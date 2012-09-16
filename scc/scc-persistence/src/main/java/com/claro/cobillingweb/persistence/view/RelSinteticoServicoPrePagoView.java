package com.claro.cobillingweb.persistence.view;

/**
 * Segunda parte do relatório sintético de repasse pré-pago com informações do 
 * serviço prestado (utilização da plataforma).
 *
 */
public class RelSinteticoServicoPrePagoView {

	private Long qtCDR;
	private Long qtCDROM;
	private Double valorPis;
	private Double valorCofins;
	private Double valorBruto;
	private Double valorISS;
	
	public Long getQtCDR() {
		return qtCDR;
	}
	public void setQtCDR(Long qtCDR) {
		this.qtCDR = qtCDR;
	}
	public Long getQtCDROM() {
		return qtCDROM;
	}
	public void setQtCDROM(Long qtCDROM) {
		this.qtCDROM = qtCDROM;
	}
	public Double getValorPis() {
		return valorPis;
	}
	public void setValorPis(Double valorPis) {
		this.valorPis = valorPis;
	}
	public Double getValorCofins() {
		return valorCofins;
	}
	public void setValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public Double getValorISS() {
		return valorISS;
	}
	public void setValorISS(Double valorISS) {
		this.valorISS = valorISS;
	}
	
	
	
}
