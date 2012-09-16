package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;

public class CadastroPagamentoForm  {	
	
	private SccPagamentoRepasse entity;
	
	private String operadoraClaro;
	
	private String operadoraLD;
	
	private String cdEOTLD;
	
	private String cdEOT;
	
	private String periodo;
	
	private Long cdProdutoCobilling;
	
	private String cdTipoContrato;
	
	private Long cdPeriodicidade;
	
	private Long mesRepasse;
	
	private Long anoRepasse;
	
	private String operacao;
	
	private int linhaSelacionada;

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public SccPagamentoRepasse getEntity() {
		return entity;
	}

	public void setEntity(SccPagamentoRepasse entity) {
		this.entity = entity;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEOT() {
		return cdEOT;
	}

	public void setCdEOT(String cdEOT) {
		this.cdEOT = cdEOT;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public String getCdTipoContrato() {
		return cdTipoContrato;
	}

	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	public Long getCdPeriodicidade() {
		return cdPeriodicidade;
	}

	public void setCdPeriodicidade(Long cdPeriodicidade) {
		this.cdPeriodicidade = cdPeriodicidade;
	}

	public Long getMesRepasse() {
		return mesRepasse;
	}

	public void setMesRepasse(Long mesRepasse) {
		this.mesRepasse = mesRepasse;
	}

	public Long getAnoRepasse() {
		return anoRepasse;
	}

	public void setAnoRepasse(Long anoRepasse) {
		this.anoRepasse = anoRepasse;
	}

	public int getLinhaSelacionada() {
		return linhaSelacionada;
	}

	public void setLinhaSelacionada(int linhaSelacionada) {
		this.linhaSelacionada = linhaSelacionada;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	
}
