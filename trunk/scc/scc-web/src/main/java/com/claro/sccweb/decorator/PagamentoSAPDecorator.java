package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;

public class PagamentoSAPDecorator extends BasicSccDecorator {

	private String operadoraClaro = " ";
	private String operadoraLD = " ";
	private String nuRepasse = " ";
	private String vlrRepasse = " ";
	private String sqArquivoRemessaSap = " ";
	private String sqArquivoRetornoSap = " ";
	private String dtPagamentoSap = " ";
	private String vlPagamentoSap = " ";
	
	public PagamentoSAPDecorator(SccPagamentoRepasse pagamentoRepasse,SccOperadora operadoraClaro,SccOperadora operadoraLD)
	{
		this.operadoraClaro = operadoraClaro.getDsOperadora();
		this.operadoraLD = operadoraLD.getDsOperadora();
		this.nuRepasse = String.valueOf(pagamentoRepasse.getId().getNuRepasse());
		this.vlrRepasse = decimalFormat.format(pagamentoRepasse.getVlPagamentoRepasse());
		this.sqArquivoRemessaSap = spaceIfNull(pagamentoRepasse.getSqArquivoRemessaSap());
		this.sqArquivoRetornoSap = spaceIfNull(pagamentoRepasse.getSqArquivoRetornoSap());
		if (pagamentoRepasse.getDtPagamentoSap() != null)
			{
			this.dtPagamentoSap = dateFormat.format(pagamentoRepasse.getDtPagamentoSap());	
			}
		if (pagamentoRepasse.getVlPagamentoSap() != null)
			{
			this.vlPagamentoSap = decimalFormat.format(pagamentoRepasse.getVlPagamentoRepasse());
			}
		 
	}
	
	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public String getNuRepasse() {
		return nuRepasse;
	}
	public String getVlrRepasse() {
		return vlrRepasse;
	}
	public String getSqArquivoRemessaSap() {
		return sqArquivoRemessaSap;
	}
	public String getSqArquivoRetornoSap() {
		return sqArquivoRetornoSap;
	}
	public String getDtPagamentoSap() {
		return dtPagamentoSap;
	}
	public String getVlPagamentoSap() {
		return vlPagamentoSap;
	}
	
	
	
	
}
