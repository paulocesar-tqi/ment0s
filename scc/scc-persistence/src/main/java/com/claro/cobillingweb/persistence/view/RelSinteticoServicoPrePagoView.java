package com.claro.cobillingweb.persistence.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Segunda parte do relatório sintético de repasse pré-pago com informações do 
 * serviço prestado (utilização da plataforma).
 *
 */
public class RelSinteticoServicoPrePagoView {

	public static NumberFormat decimalFormat = new DecimalFormat("#.##");
	
	private Long qtCDR;
	private Long qtCDROM;
	private Double valorPis;
	private Double valorCofins;
	private Double valorBruto;
	private Double valorISS;
	
	public String getChamadasString()
	{
		return (zeroIfNull(this.qtCDR)+zeroIfNull(this.qtCDROM)) + "";
	}
	
	public String getValorLiquidoString() {		
		return decimalFormat.format(zeroIfNull(getValorBruto())-zeroIfNull(getValorISS())-zeroIfNull(getValorCofins())-zeroIfNull(getValorPis()));
	}

	public String getPisCofinsString() {
		return decimalFormat.format(zeroIfNull(getValorPis())+zeroIfNull(getValorCofins()));
	}

	public String getISSString() {
		return decimalFormat.format(zeroIfNull(getValorISS()));
	}

	public String getValorBrutoString() {
		return decimalFormat.format(zeroIfNull(getValorBruto()));
	}


	
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
	
	protected Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}
	
	protected Long zeroIfNull(Long value) {
		if (value == null) {
			return 0L;
		}
		return value;
	}

	
}
