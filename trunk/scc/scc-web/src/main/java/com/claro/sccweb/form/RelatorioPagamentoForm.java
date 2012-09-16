package com.claro.sccweb.form;

public class RelatorioPagamentoForm {

	private String cdEOTClaro;
	private String cdEOTLD;
	private String cdProdutoPrepago;
	private Long cdProdutoCobilling;
	private Long cdPeriodicidade;	
	private Long mesRepasse;	
	private Long anoRepasse;
	private String cdTipoContrato;
	
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
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}
	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
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
	
	
	
}
