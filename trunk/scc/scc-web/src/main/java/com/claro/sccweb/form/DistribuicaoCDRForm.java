package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.sccweb.controller.graficos.distribuicao.ItemGraficoDistribuicao;

public class DistribuicaoCDRForm extends BaseForm {

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private Date dataFinal;
	private Date dataInicial;
	private String dadosDisponiveis = "N";
	private Long produto;
	
	private List<SccArquivoSumarizado> lstSumarizado;
	private List<ItemGraficoDistribuicao> lstItemGrafico;
	private List<SccArquivoSumarizado> lstAlocados;
	private List<SccArquivoSumarizado> lstFaturados;
	
	public String getTipoOperadora() {
		return tipoOperadora;
	}
	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDadosDisponiveis() {
		return dadosDisponiveis;
	}
	public void setDadosDisponiveis(String dadosDisponiveis) {
		this.dadosDisponiveis = dadosDisponiveis;
	}
	public Long getProduto() {
		return produto;
	}
	public void setProduto(Long produto) {
		this.produto = produto;
	}
	public List<SccArquivoSumarizado> getLstSumarizado() {
		return lstSumarizado;
	}
	public void setLstSumarizado(List<SccArquivoSumarizado> lstSumarizado) {
		this.lstSumarizado = lstSumarizado;
	}
	public List<ItemGraficoDistribuicao> getLstItemGrafico() {
		return lstItemGrafico;
	}
	public void setLstItemGrafico(List<ItemGraficoDistribuicao> lstItemGrafico) {
		this.lstItemGrafico = lstItemGrafico;
	}
	public List<SccArquivoSumarizado> getLstAlocados() {
		return lstAlocados;
	}
	public void setLstAlocados(List<SccArquivoSumarizado> lstAlocados) {
		this.lstAlocados = lstAlocados;
	}
	public List<SccArquivoSumarizado> getLstFaturados() {
		return lstFaturados;
	}
	public void setLstFaturados(List<SccArquivoSumarizado> lstFaturados) {
		this.lstFaturados = lstFaturados;
	}
	
	
	
	
	
}
