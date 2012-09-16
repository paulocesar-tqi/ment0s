package com.claro.sccweb.service.data.saldos;

import java.util.ArrayList;
import java.util.List;

public class DemonstrativoSaldoEvento {

	private String cdMotivoEvento;
	private String dsMotivoEvento;
	private Long qtCdrs = 0L;
	private Double qtMinutos = 0.0;
	private Double valor = 0.0;
	private Double percentualValor;
	private Double percentualCDRs;
	private Double percentualMinutos;
	
	private List<DemonstrativoSaldoRejeicao> detalhesRejeicao = new ArrayList<DemonstrativoSaldoRejeicao>();
	
	public String getCdMotivoEvento() {
		return cdMotivoEvento;
	}
	public void setCdMotivoEvento(String cdMotivoEvento) {
		this.cdMotivoEvento = cdMotivoEvento;
	}
	public String getDsMotivoEvento() {
		return dsMotivoEvento;
	}
	public void setDsMotivoEvento(String dsMotivoEvento) {
		this.dsMotivoEvento = dsMotivoEvento;
	}
	public Long getQtCdrs() {
		return qtCdrs;
	}
	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}
	public Double getQtMinutos() {
		return qtMinutos;
	}
	public void setQtMinutos(Double qtMinutos) {
		this.qtMinutos = qtMinutos;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public List<DemonstrativoSaldoRejeicao> getDetalhesRejeicao() {
		return detalhesRejeicao;
	}
	public void setDetalhesRejeicao(
			List<DemonstrativoSaldoRejeicao> detalhesRejeicao) {
		this.detalhesRejeicao = detalhesRejeicao;
	}
	
	public void addMinutos(Double minutos)
	{
		this.qtMinutos = this.qtMinutos+minutos;
	}
	
	public void addCDRs(Long cdrs)
	{
		this.qtCdrs = this.qtCdrs+cdrs;
	}
	
	public void addValor(Double valor)
	{
		this.valor = this.valor+valor;
	}
	
	public void addItemRejeicao(DemonstrativoSaldoRejeicao item)
	{
		this.detalhesRejeicao.add(item);
	}
	public Double getPercentualValor() {
		return percentualValor;
	}
	public void setPercentualValor(Double percentualValor) {
		this.percentualValor = percentualValor;
	}
	public Double getPercentualCDRs() {
		return percentualCDRs;
	}
	public void setPercentualCDRs(Double percentualCDRs) {
		this.percentualCDRs = percentualCDRs;
	}
	public Double getPercentualMinutos() {
		return percentualMinutos;
	}
	public void setPercentualMinutos(Double percentualMinutos) {
		this.percentualMinutos = percentualMinutos;
	}
	
	
}
