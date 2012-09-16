package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * View que representa o relatório de créditos de pré-pago.
 *
 */
public class RelCreditosPrePagoView {

	private String noArquivo;
	private String cdEOTLD;
	private String cdEOTClaro;
	private String tipoCredito;
	private String cdStatus;
	private String dsCredito;
	private String statusCredito;
	private String telefone;
	private Date dtCredito;
	private Double vlrCredito;	
	private Double minutosQueimados;
	private Double minutosAjustados;
	private Long sqArquivo;
	private Long sqCredito;
	private Double quantidade;
	
	private String dsStatus;
	
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getStatusCredito() {
		return statusCredito;
	}
	public void setStatusCredito(String statusCredito) {
		this.statusCredito = statusCredito;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDtCredito() {
		return dtCredito;
	}
	public void setDtCredito(Date dtCredito) {
		this.dtCredito = dtCredito;
	}
	public Double getVlrCredito() {
		return vlrCredito;
	}
	public void setVlrCredito(Double vlrCredito) {
		this.vlrCredito = vlrCredito;
	}
	
	public Double getMinutosQueimados() {
		return minutosQueimados;
	}
	public void setMinutosQueimados(Double minutosQueimados) {
		this.minutosQueimados = minutosQueimados;
	}
	public Double getMinutosAjustados() {
		return minutosAjustados;
	}
	public void setMinutosAjustados(Double minutosAjustados) {
		this.minutosAjustados = minutosAjustados;
	}
	
	
	
	public Long getSqArquivo() {
		return sqArquivo;
	}
	public void setSqArquivo(Long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	
	
	public String getCdStatus() {
		return cdStatus;
	}
	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}
	
	
	public String getDsCredito() {
		return dsCredito;
	}
	public void setDsCredito(String dsCredito) {
		this.dsCredito = dsCredito;
	}
	
	
	
	public String getDsStatus() {
		return dsStatus;
	}
	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}
	
	
	
	public Long getSqCredito() {
		return sqCredito;
	}
	public void setSqCredito(Long sqCredito) {
		this.sqCredito = sqCredito;
	}
	
	
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}
