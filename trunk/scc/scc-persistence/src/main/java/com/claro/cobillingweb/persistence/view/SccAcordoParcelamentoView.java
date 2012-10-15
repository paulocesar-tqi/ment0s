/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;

/**
 * @author 93046251
 *
 */
public class SccAcordoParcelamentoView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4580858850041919310L;
	

	private String operadoraLD;
	
	private String operadoraClaro;
	
	private Double codAcordo;
	
	private java.util.Date dataAcordo;
	
	private Double valorAcordado;
	
	private Long NuAcordoParcelamento;
	
	private String nuFatura;
	
	private Long numConta;
	
	private Double vlParcelaOperadora;
			
	private String status;
	
	private Double qtdParcelas;
	
	private Long nuParcela;
	
	

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

	public Double getCodAcordo() {
		return codAcordo;
	}

	public void setCodAcordo(Double codAcordo) {
		this.codAcordo = codAcordo;
	}

	public java.util.Date getDataAcordo() {
		return dataAcordo;
	}

	public void setDataAcordo(java.util.Date dataAcordo) {
		this.dataAcordo = dataAcordo;
	}

	public Double getValorAcordado() {
		return valorAcordado;
	}

	public void setValorAcordado(Double valorAcordado) {
		this.valorAcordado = valorAcordado;
	}

	public Long getNuAcordoParcelamento() {
		return NuAcordoParcelamento;
	}

	public void setNuAcordoParcelamento(Long nuAcordoParcelamento) {
		NuAcordoParcelamento = nuAcordoParcelamento;
	}

	public String getNuFatura() {
		return nuFatura;
	}

	public void setNuFatura(String nuFatura) {
		this.nuFatura = nuFatura;
	}

	public Long getNumConta() {
		return numConta;
	}

	public void setNumConta(Long numConta) {
		this.numConta = numConta;
	}

	public Double getVlParcelaOperadora() {
		return vlParcelaOperadora;
	}

	public void setVlParcelaOperadora(Double vlParcelaOperadora) {
		this.vlParcelaOperadora = vlParcelaOperadora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Double qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Long getNuParcela() {
		return nuParcela;
	}

	public void setNuParcela(Long nuParcela) {
		this.nuParcela = nuParcela;
	}
	
	
	


}
