package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.sql.Timestamp;

public class SccAcoesCobrancaView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 443634856294087691L;
	
	
	
	private Long ban;
	
	private String terminal;
	
	private String faturaLD;
	
	private Double valorFatura;
	
	private Timestamp dataEmissao;
	
	private Timestamp dataVencimento;
	
	private Timestamp dataCarta;
	
	private String arquivoCarta;
	
	private Timestamp dataConnectCarta;
	
	private Timestamp dataBloqueio;
	
	private String arquivoBloqueio;
	
	private Timestamp dataConnectBloqueio;
	
	private Timestamp dataPagamento;
	
	private String arquivoPagamento;
	
	private Timestamp dataConnectPagamento;
	

	public Long getBan() {
		return ban;
	}

	public void setBan(Long ban) {
		this.ban = ban;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getFaturaLD() {
		return faturaLD;
	}

	public void setFaturaLD(String faturaLD) {
		this.faturaLD = faturaLD;
	}

	public Double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(Double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Timestamp getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Timestamp getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Timestamp dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Timestamp getDataCarta() {
		return dataCarta;
	}

	public void setDataCarta(Timestamp dataCarta) {
		this.dataCarta = dataCarta;
	}

	public String getArquivoCarta() {
		return arquivoCarta;
	}

	public void setArquivoCarta(String arquivoCarta) {
		this.arquivoCarta = arquivoCarta;
	}

	public Timestamp getDataConnectCarta() {
		return dataConnectCarta;
	}

	public void setDataConnectCarta(Timestamp dataConnectCarta) {
		this.dataConnectCarta = dataConnectCarta;
	}

	public Timestamp getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Timestamp dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public String getArquivoBloqueio() {
		return arquivoBloqueio;
	}

	public void setArquivoBloqueio(String arquivoBloqueio) {
		this.arquivoBloqueio = arquivoBloqueio;
	}

	public Timestamp getDataConnectBloqueio() {
		return dataConnectBloqueio;
	}

	public void setDataConnectBloqueio(Timestamp dataConnectBloqueio) {
		this.dataConnectBloqueio = dataConnectBloqueio;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getArquivoPagamento() {
		return arquivoPagamento;
	}

	public void setArquivoPagamento(String arquivoPagamento) {
		this.arquivoPagamento = arquivoPagamento;
	}

	public Timestamp getDataConnectPagamento() {
		return dataConnectPagamento;
	}

	public void setDataConnectPagamento(Timestamp dataConnectPagamento) {
		this.dataConnectPagamento = dataConnectPagamento;
	}		
	
	
	


}
