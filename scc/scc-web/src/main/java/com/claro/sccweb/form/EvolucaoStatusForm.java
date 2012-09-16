package com.claro.sccweb.form;

public class EvolucaoStatusForm extends BaseForm {

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private Long cdProdutoCobilling;
	private Long mesInicial;
	private Long mesFinal;
	private Long anoInicial;
	private Long anoFinal;
	
	
	
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
	
	
	
	
	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	public Long getMesInicial() {
		return mesInicial;
	}
	public void setMesInicial(Long mesInicial) {
		this.mesInicial = mesInicial;
	}
	public Long getMesFinal() {
		return mesFinal;
	}
	public void setMesFinal(Long mesFinal) {
		this.mesFinal = mesFinal;
	}
	public Long getAnoInicial() {
		return anoInicial;
	}
	public void setAnoInicial(Long anoInicial) {
		this.anoInicial = anoInicial;
	}
	public Long getAnoFinal() {
		return anoFinal;
	}
	public void setAnoFinal(Long anoFinal) {
		this.anoFinal = anoFinal;
	}
	
}
