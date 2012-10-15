/**
 * 
 */
package com.claro.sccweb.vo;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class PerdaFaturamentoVO implements Cloneable {
	
	private Date dtProcExterna;
	
	private String operadoraLd;
	
	private String operadoraClaro;
	
	private Double valorLiquido = 0d ;
	
	private Double valorBruto = 0d ;
	
	private Long qtdCdr = 0L;
	
	private String status;
	
    private String fileType;
    
    private Integer cdStatusCdr;

	
	public Date getDtProcExterna() {
		return dtProcExterna;
	}

	public void setDtProcExterna(Date dtProcExterna) {
		this.dtProcExterna = dtProcExterna;
	}

	public String getOperadoraLd() {
		return operadoraLd;
	}

	public void setOperadoraLd(String operadoraLd) {
		this.operadoraLd = operadoraLd;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Long getQtdCdr() {
		return qtdCdr;
	}

	public void setQtdCdr(Long qtdCdr) {
		this.qtdCdr = qtdCdr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getCdStatusCdr() {
		return cdStatusCdr;
	}

	public void setCdStatusCdr(Integer cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
	}
	
	
	public void addValorlLiquido(Double valorLiquido){
		this.valorLiquido += valorLiquido;
	}
	
	public void addValorBruto(Double valorBruto){
		this.valorBruto += valorBruto;
	}
	
	public void addQtdCdr(Long qtdCdr){
		this.qtdCdr += qtdCdr;
	}
	
}
