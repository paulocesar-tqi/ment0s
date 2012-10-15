/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccArquivosFiscaisView {

	
	private String cdCSP;
	private String sgUF;
	private String anoCiclo;
	private Integer mesCiclo;
	private Integer codigoCiclo;
	private String noArquivo;
	private String noDiretorioArquivo;
	private Date dtStatusArquivo;
	private String cdStatusArquivo;
	private String cdMotivoRejeicaoArquivo;
	private Date dtConnect;
	private Integer qtRegistros;
	private Double vlBrutoArquivo;
	private Double vlICMS;
	
	public String getCdCSP() {
		return cdCSP;
	}
	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}
	public String getSgUF() {
		return sgUF;
	}
	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}
	public String getAnoCiclo() {
		return anoCiclo;
	}
	public void setAnoCiclo(String anoCiclo) {
		this.anoCiclo = anoCiclo;
	}
	public Integer getMesCiclo() {
		return mesCiclo;
	}
	public void setMesCiclo(Integer mesCiclo) {
		this.mesCiclo = mesCiclo;
	}
	public Integer getCodigoCiclo() {
		return codigoCiclo;
	}
	public void setCodigoCiclo(Integer codigoCiclo) {
		this.codigoCiclo = codigoCiclo;
	}
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getNoDiretorioArquivo() {
		return noDiretorioArquivo;
	}
	public void setNoDiretorioArquivo(String noDiretorioArquivo) {
		this.noDiretorioArquivo = noDiretorioArquivo;
	}
	public Date getDtStatusArquivo() {
		return dtStatusArquivo;
	}
	public void setDtStatusArquivo(Date dtStatusArquivo) {
		this.dtStatusArquivo = dtStatusArquivo;
	}
	public String getCdStatusArquivo() {
		return cdStatusArquivo;
	}
	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}
	public String getCdMotivoRejeicaoArquivo() {
		return cdMotivoRejeicaoArquivo;
	}
	public void setCdMotivoRejeicaoArquivo(String cdMotivoRejeicaoArquivo) {
		this.cdMotivoRejeicaoArquivo = cdMotivoRejeicaoArquivo;
	}
	public Date getDtConnect() {
		return dtConnect;
	}
	public void setDtConnect(Date dtConnect) {
		this.dtConnect = dtConnect;
	}
	public Integer getQtRegistros() {
		return qtRegistros;
	}
	public void setQtRegistros(Integer qtRegistros) {
		this.qtRegistros = qtRegistros;
	}
	public Double getVlBrutoArquivo() {
		return vlBrutoArquivo;
	}
	public void setVlBrutoArquivo(Double vlBrutoArquivo) {
		this.vlBrutoArquivo = vlBrutoArquivo;
	}
	public Double getVlICMS() {
		return vlICMS;
	}
	public void setVlICMS(Double vlICMS) {
		this.vlICMS = vlICMS;
	}
		
}
