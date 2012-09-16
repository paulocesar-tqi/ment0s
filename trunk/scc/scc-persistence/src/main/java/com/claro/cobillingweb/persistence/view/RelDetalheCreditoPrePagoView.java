package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * View para o detalhe de uma sequência de crédito de pré-pago.
 *
 */
public class RelDetalheCreditoPrePagoView {

	private String nomeArquivo;
	private String numeroOrigem;
	private String numeroDestino;
	private Date dataChamada;
	private Long horaInicioChamada;
	private Long nuCDR;
	private Long seqCredito;
	private Date dataCredito;
	private Double valorCredito;
	private Double duracaoTarifada;
	private Double valorBruto;
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getNumeroOrigem() {
		return numeroOrigem;
	}
	public void setNumeroOrigem(String numeroOrigem) {
		this.numeroOrigem = numeroOrigem;
	}
	public String getNumeroDestino() {
		return numeroDestino;
	}
	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}
	public Date getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}
	public Long getHoraInicioChamada() {
		return horaInicioChamada;
	}
	public void setHoraInicioChamada(Long horaInicioChamada) {
		this.horaInicioChamada = horaInicioChamada;
	}
	public Long getNuCDR() {
		return nuCDR;
	}
	public void setNuCDR(Long nuCDR) {
		this.nuCDR = nuCDR;
	}
	public Long getSeqCredito() {
		return seqCredito;
	}
	public void setSeqCredito(Long seqCredito) {
		this.seqCredito = seqCredito;
	}
	public Date getDataCredito() {
		return dataCredito;
	}
	public void setDataCredito(Date dataCredito) {
		this.dataCredito = dataCredito;
	}
	public Double getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(Double valorCredito) {
		this.valorCredito = valorCredito;
	}
	public Double getDuracaoTarifada() {
		return duracaoTarifada;
	}
	public void setDuracaoTarifada(Double duracaoTarifada) {
		this.duracaoTarifada = duracaoTarifada;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	
	
	
}
