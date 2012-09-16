package com.claro.cobillingweb.persistence.view;

/**
 * Value Object com sumário de CDRs.
 * Como o resultado das pesquisas de CDRs não é usado para edição , o sistema irá retornar classes POJO e 
 * não entidades Hibernate para deixar o retorno mais leve.
 *
 */
public class SumarioCDRView {

	private String motivoRejeicao;
	private String status;
	private String dataStatus;
	private String CSP;
	private String numeroA;
	private String numeroB;
	private String cdEOTOrigem;
	private String cdEOTExterna;
	private String dataChamada;
	private String horaChamada;
	private Double duracao;
	private Double valorLiquido;
	private String arquivoRetorno;
	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getCSP() {
		return CSP;
	}
	public void setCSP(String cSP) {
		CSP = cSP;
	}
	public String getNumeroA() {
		return numeroA;
	}
	public void setNumeroA(String numeroA) {
		this.numeroA = numeroA;
	}
	public String getNumeroB() {
		return numeroB;
	}
	public void setNumeroB(String numeroB) {
		this.numeroB = numeroB;
	}
	public String getCdEOTOrigem() {
		return cdEOTOrigem;
	}
	public void setCdEOTOrigem(String cdEOTOrigem) {
		this.cdEOTOrigem = cdEOTOrigem;
	}
	public String getCdEOTExterna() {
		return cdEOTExterna;
	}
	public void setCdEOTExterna(String cdEOTExterna) {
		this.cdEOTExterna = cdEOTExterna;
	}
	public String getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(String dataChamada) {
		this.dataChamada = dataChamada;
	}
	public String getHoraChamada() {
		return horaChamada;
	}
	public void setHoraChamada(String horaChamada) {
		this.horaChamada = horaChamada;
	}
	public Double getDuracao() {
		return duracao;
	}
	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}
	public Double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public String getArquivoRetorno() {
		return arquivoRetorno;
	}
	public void setArquivoRetorno(String arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}
	
	
	
	
}
