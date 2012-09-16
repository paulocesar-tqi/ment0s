package com.claro.sccweb.form;

import java.util.Date;

public class SolicitacaoVolumeSuspeitoForm extends BaseForm {

	private Long minutos;
	private Double valorBruto;
	private Date dataInicial;
	private Date dataFinal;
	private String resultado;
	
	public Long getMinutos() {
		return minutos;
	}
	public void setMinutos(Long minutos) {
		this.minutos = minutos;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
	
}
