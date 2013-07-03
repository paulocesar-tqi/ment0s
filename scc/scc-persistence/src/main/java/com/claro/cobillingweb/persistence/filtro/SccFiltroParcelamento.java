package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;

public class SccFiltroParcelamento {
	
	private String tipoRelatorio;
	
	private String cdEOTLD;
	
	private String operadoraClaro;
	
	private String status;
	
	private Long numeroConta;
	
	private Long numeroAcordo;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private String cdCsp;

	
	private Boolean isSintetico;
	private Boolean isAnalitico;
	private Boolean isFatura;
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Long getNumeroAcordo() {
		return numeroAcordo;
	}
	public void setNumeroAcordo(Long numeroAcordo) {
		this.numeroAcordo = numeroAcordo;
	}
	public Date getDataInicialPeriodo() {
		return dataInicialPeriodo;
	}
	public void setDataInicialPeriodo(Date dataInicialPeriodo) {
		this.dataInicialPeriodo = dataInicialPeriodo;
	}
	public Date getDataFinalPeriodo() {
		return dataFinalPeriodo;
	}
	public void setDataFinalPeriodo(Date dataFinalPeriodo) {
		this.dataFinalPeriodo = dataFinalPeriodo;
	}
	public Boolean getIsSintetico() {
		return isSintetico;
	}
	public void setIsSintetico(Boolean isSintetico) {
		this.isSintetico = isSintetico;
	}
	public Boolean getIsAnalitico() {
		return isAnalitico;
	}
	public void setIsAnalitico(Boolean isAnalitico) {
		this.isAnalitico = isAnalitico;
	}
	public Boolean getIsFatura() {
		return isFatura;
	}
	public void setIsFatura(Boolean isFatura) {
		this.isFatura = isFatura;
	}
	public String getCdCsp() {
		return cdCsp;
	}
	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}
	
	
	
	
	

}
