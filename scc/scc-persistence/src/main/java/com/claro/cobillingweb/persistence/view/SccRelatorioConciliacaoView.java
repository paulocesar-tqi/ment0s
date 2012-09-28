/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 93046251
 *
 */
public class SccRelatorioConciliacaoView {
	
	private Date dataLancamento;
	private String descricao;
	private Long contaCredito;
	private Long contaDebito;
	private String codCsp;
	private String localNegocio;
	private String empresaContabil;
	private Double valorBruto;
	private Date dataProcessamento;
	private String codCentro;
	
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(Long contaCredito) {
		this.contaCredito = contaCredito;
	}
	public Long getContaDebito() {
		return contaDebito;
	}
	public void setContaDebito(Long contaDebito) {
		this.contaDebito = contaDebito;
	}
	public String getLocalNegocio() {
		return localNegocio;
	}
	public void setLocalNegocio(String localNegocio) {
		this.localNegocio = localNegocio;
	}
	public String getEmpresaContabil() {
		return empresaContabil;
	}
	public void setEmpresaContabil(String empresaContabil) {
		this.empresaContabil = empresaContabil;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public String getCodCsp() {
		return codCsp;
	}
	public void setCodCsp(String codCsp) {
		this.codCsp = codCsp;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public String getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
	}

	
}
