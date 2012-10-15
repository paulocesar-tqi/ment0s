package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;

public class SccAgingFaturasView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2278138679857830903L;

	private String operadoraLD;

	private String operadoraClaro;
	
	private Double vencer;
	
	private Double valor1a10Dias;
	
	private Double valor11a20Dias;
	
	private Double valor21a30Dias;
	
	private Double valor31a60Dias;
	
	private Double valor61a90Dias;
	
	private Double maior90Dias;

	public String getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public Double getVencer() {
		return vencer;
	}

	public void setVencer(Double vencer) {
		this.vencer = vencer;
	}

	public Double getValor1a10Dias() {
		return valor1a10Dias;
	}

	public void setValor1a10Dias(Double valor1a10Dias) {
		this.valor1a10Dias = valor1a10Dias;
	}

	public Double getValor11a20Dias() {
		return valor11a20Dias;
	}

	public void setValor11a20Dias(Double valor11a20Dias) {
		this.valor11a20Dias = valor11a20Dias;
	}

	public Double getValor21a30Dias() {
		return valor21a30Dias;
	}

	public void setValor21a30Dias(Double valor21a30Dias) {
		this.valor21a30Dias = valor21a30Dias;
	}

	public Double getValor31a60Dias() {
		return valor31a60Dias;
	}

	public void setValor31a60Dias(Double valor31a60Dias) {
		this.valor31a60Dias = valor31a60Dias;
	}

	public Double getValor61a90Dias() {
		return valor61a90Dias;
	}

	public void setValor61a90Dias(Double valor61a90Dias) {
		this.valor61a90Dias = valor61a90Dias;
	}

	public Double getMaior90Dias() {
		return maior90Dias;
	}

	public void setMaior90Dias(Double maior90Dias) {
		this.maior90Dias = maior90Dias;
	}
	
	
	


}
