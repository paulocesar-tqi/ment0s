package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.util.Date;

public class DisponibilizacaoPacotePrePagoView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataReferencia;
	private String operadoraClaro;
	private String operadoraLD;
	private String terminal;
	private String status;
	private String descricaoPacote;
	private String cdProdutoPrepago;
	private Integer qtdChamadas;
	private Integer qtdPacotes;
	private Integer duracaoReal;
	private Double qtdConsumida;
	private Double valorBruto;
	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
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
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescricaoPacote() {
		return descricaoPacote;
	}
	public void setDescricaoPacote(String descricaoPacote) {
		this.descricaoPacote = descricaoPacote;
	}
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}
	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	public Integer getQtdChamadas() {
		return qtdChamadas;
	}
	public void setQtdChamadas(Integer qtdChamadas) {
		this.qtdChamadas = qtdChamadas;
	}
	public Integer getQtdPacotes() {
		return qtdPacotes;
	}
	public void setQtdPacotes(Integer qtdPacotes) {
		this.qtdPacotes = qtdPacotes;
	}
	public Integer getDuracaoReal() {
		return duracaoReal;
	}
	public void setDuracaoReal(Integer duracaoReal) {
		this.duracaoReal = duracaoReal;
	}
	public Double getQtdConsumida() {
		return qtdConsumida;
	}
	public void setQtdConsumida(Double qtdConsumida) {
		this.qtdConsumida = qtdConsumida;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	
	
	
}
