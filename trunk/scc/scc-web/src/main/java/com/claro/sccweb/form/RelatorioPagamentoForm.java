package com.claro.sccweb.form;

import java.util.List;

import com.claro.sccweb.decorator.PagamentoSAPDecorator;

public class RelatorioPagamentoForm extends BaseForm{

	private String cdEOT;
	private String cdEOTLD;
	private String cdProdutoPrePago;
	private Long cdProdutoCobilling;
	private Long cdPeriodicidade;	
	private Long mesRepasse;	
	private Long anoRepasse;
	private String cdTipoContrato;
	
	private List<PagamentoSAPDecorator> listPagamentoSAPDecorator;
	
	private String operacao;
	private int linhaSelacionada;
	
	private Long[] nuRepasse;
	
	private Long[] lancadosSelecionados;
	

	public String getCdEOT() {
		return cdEOT;
	}
	public void setCdEOT(String cdEOT) {
		this.cdEOT = cdEOT;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	
	public String getCdProdutoPrePago() {
		return cdProdutoPrePago;
	}
	public void setCdProdutoPrePago(String cdProdutoPrePago) {
		this.cdProdutoPrePago = cdProdutoPrePago;
	}
	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
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
	public String getCdTipoContrato() {
		return cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public int getLinhaSelacionada() {
		return linhaSelacionada;
	}
	public void setLinhaSelacionada(int linhaSelacionada) {
		this.linhaSelacionada = linhaSelacionada;
	}
	public List<PagamentoSAPDecorator> getListPagamentoSAPDecorator() {
		return listPagamentoSAPDecorator;
	}
	public void setListPagamentoSAPDecorator(
			List<PagamentoSAPDecorator> listPagamentoSAPDecorator) {
		this.listPagamentoSAPDecorator = listPagamentoSAPDecorator;
	}
	public Long[] getNuRepasse() {
		return nuRepasse;
	}
	public void setNuRepasse(Long[] nuRepasse) {
		this.nuRepasse = nuRepasse;
	}
	public Long[] getLancadosSelecionados() {
		return lancadosSelecionados;
	}
	public void setLancadosSelecionados(Long[] lancadosSelecionados) {
		this.lancadosSelecionados = lancadosSelecionados;
	}
	

	
}
