/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class RelAlarmeOperacionalView {
	private String numA;
	private Long qtdeChamadas; 
	private Long qtdeMinutosTarifados; 
	private Double valorTotalChamadas; 
	private String  nroFatura;
	private Long nroNf;
	private String nomeOperadoraLD; 
	private Date dataReferencia;
	public String getNumA() {
		return numA;
	}
	public void setNumA(String numA) {
		this.numA = numA;
	}
	public Long getQtdeChamadas() {
		return qtdeChamadas;
	}
	public void setQtdeChamadas(Long qtdeChamadas) {
		this.qtdeChamadas = qtdeChamadas;
	}
	public Long getQtdeMinutosTarifados() {
		return qtdeMinutosTarifados;
	}
	public void setQtdeMinutosTarifados(Long qtdeMinutosTarifados) {
		this.qtdeMinutosTarifados = qtdeMinutosTarifados;
	}
	public Double getValorTotalChamadas() {
		return valorTotalChamadas;
	}
	public void setValorTotalChamadas(Double valorTotalChamadas) {
		this.valorTotalChamadas = valorTotalChamadas;
	}
	public String getNroFatura() {
		return nroFatura;
	}
	public void setNroFatura(String nroFatura) {
		this.nroFatura = nroFatura;
	}
	public Long getNroNf() {
		return nroNf;
	}
	public void setNroNf(Long nroNf) {
		this.nroNf = nroNf;
	}
	public String getNomeOperadoraLD() {
		return nomeOperadoraLD;
	}
	public void setNomeOperadoraLD(String nomeOperadoraLD) {
		this.nomeOperadoraLD = nomeOperadoraLD;
	}
	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	
}
