package com.claro.cobillingweb.persistence.view;

import java.math.BigDecimal;

public class SccAgingIndicadorView {
	
	private String cdIndicador;
	private Long sqAgingIndicador;
	private BigDecimal vlMinimoAging;
	private BigDecimal vlMaximoAging;
	private BigDecimal apagar;
	private String tipoContrato;
	public String getCdIndicador() {
		return cdIndicador;
	}
	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}
	public Long getSqAgingIndicador() {
		return sqAgingIndicador;
	}
	public void setSqAgingIndicador(Long sqAgingIndicador) {
		this.sqAgingIndicador = sqAgingIndicador;
	}
	public BigDecimal getVlMinimoAging() {
		return vlMinimoAging;
	}
	public void setVlMinimoAging(BigDecimal vlMinimoAging) {
		this.vlMinimoAging = vlMinimoAging;
	}
	public BigDecimal getVlMaximoAging() {
		return vlMaximoAging;
	}
	public void setVlMaximoAging(BigDecimal vlMaximoAging) {
		this.vlMaximoAging = vlMaximoAging;
	}
	public BigDecimal getApagar() {
		return apagar;
	}
	public void setApagar(BigDecimal apagar) {
		this.apagar = apagar;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	
	

}
