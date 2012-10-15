/**
 * 
 */
package com.claro.cobillingweb.persistence.view;


/**
 * @author 92031709
 *
 */
public class SccChamadasRefaturamentoView {

	private String mesReferencia;
	private String operadoraLD;
	private String operadoraClaro;
	private String tipoRefaturamento;
	private String statusChamada;
	private Integer quantidade;
	private Integer minutagem;
	private Double totalBruto;
	private Double totalLiquido;

	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
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
	public String getTipoRefaturamento() {
		return tipoRefaturamento;
	}
	public void setTipoRefaturamento(String tipoRefaturamento) {
		this.tipoRefaturamento = tipoRefaturamento;
	}
	public String getStatusChamada() {
		return statusChamada;
	}
	public void setStatusChamada(String statusChamada) {
		this.statusChamada = statusChamada;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getMinutagem() {
		return minutagem;
	}
	public void setMinutagem(Integer minutagem) {
		this.minutagem = minutagem;
	}
	public Double getTotalBruto() {
		return totalBruto;
	}
	public void setTotalBruto(Double totalBruto) {
		this.totalBruto = totalBruto;
	}
	public Double getTotalLiquido() {
		return totalLiquido;
	}
	public void setTotalLiquido(Double totalLiquido) {
		this.totalLiquido = totalLiquido;
	}
}
