package com.claro.cobillingweb.persistence.view;

import java.util.Date;

public class SccRelBatimentoEstornoDebitoView {
	
	private Long sqArquivo;
	private String operadoraLD;
	private String operadoraClaro;
	private String uf;
	private String noArquivo;
	private String dsStatus;
	private String mesAno;
	private Double valorTotalNf;
	private Double valorTotalImpugnado;
	private Date dtConnect;
	
	
	public Long getSqArquivo() {
		return sqArquivo;
	}
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
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
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getDsStatus() {
		return dsStatus;
	}
	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}
	public Double getValorTotalNf() {
		return valorTotalNf;
	}
	public void setValorTotalNf(Double valorTotalNf) {
		this.valorTotalNf = valorTotalNf;
	}
	public Double getValorTotalImpugnado() {
		return valorTotalImpugnado;
	}
	public void setValorTotalImpugnado(Double valorTotalImpugnado) {
		this.valorTotalImpugnado = valorTotalImpugnado;
	}
	public Date getDtConnect() {
		return dtConnect;
	}
	public void setDtConnect(Date dtConnect) {
		this.dtConnect = dtConnect;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	

	
	

}
