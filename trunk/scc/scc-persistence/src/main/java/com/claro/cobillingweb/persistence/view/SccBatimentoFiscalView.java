/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;


/**
 * @author 92031709
 *
 */
public class SccBatimentoFiscalView {

	private String cdCSP;
	private String nomeOperadora;
	private String nomeEmpresa;
	private String cdEOTClaro;
	private String sgUF;
	private String noArquivo;
	private String cdStatusArquivo;
	private Integer cdCiclo;
	private Double vlTotalNF;
	private Date dtConnect;
	
	public String getCdCSP() {
		return cdCSP;
	}
	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}
	public String getNomeOperadora() {
		return nomeOperadora;
	}
	public void setNomeOperadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getSgUF() {
		return sgUF;
	}
	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getCdStatusArquivo() {
		return cdStatusArquivo;
	}
	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}
	public Integer getCdCiclo() {
		return cdCiclo;
	}
	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}
	public Double getVlTotalNF() {
		return vlTotalNF;
	}
	public void setVlTotalNF(Double vlTotalNF) {
		this.vlTotalNF = vlTotalNF;
	}
	public Date getDtConnect() {
		return dtConnect;
	}
	public void setDtConnect(Date dtConnect) {
		this.dtConnect = dtConnect;
	}
}
