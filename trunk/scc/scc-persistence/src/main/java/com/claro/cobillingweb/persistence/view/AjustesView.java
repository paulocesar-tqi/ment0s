/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class AjustesView {
	
	private String csp;
	private String numeroFatura;
	private Long numeroNotaFiscal;
	private String operadoraClaro;
	private String operadoraLD;
	private String uf;
	private Date dataAjuste;
	private Double valorAjustado;
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getNumeroFatura() {
		return numeroFatura;
	}
	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Date getDataAjuste() {
		return dataAjuste;
	}
	public void setDataAjuste(Date dataAjuste) {
		this.dataAjuste = dataAjuste;
	}
	public Double getValorAjustado() {
		return valorAjustado;
	}
	public void setValorAjustado(Double valorAjustado) {
		this.valorAjustado = valorAjustado;
	}

	
	
	

}
