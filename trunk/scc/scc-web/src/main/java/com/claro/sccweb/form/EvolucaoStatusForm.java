package com.claro.sccweb.form;

import java.util.List;

import com.claro.sccweb.controller.util.BasicIntegerItem;

public class EvolucaoStatusForm extends BaseForm {

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private Long cdProdutoCobilling;
	private Long mesInicial;
	private Long mesFinal;
	private Long anoInicial;
	private Long anoFinal;
	private List<BasicIntegerItem> listTipoArquivo;
	private Long grupo;
	private List<String> lstCdr ;
	
	
	
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
	public List<BasicIntegerItem> getListTipoArquivo() {
		return listTipoArquivo;
	}
	public void setListTipoArquivo(List<BasicIntegerItem> listTipoArquivo) {
		this.listTipoArquivo = listTipoArquivo;
	}
	public Long getGrupo() {
		return grupo;
	}
	public void setGrupo(Long grupo) {
		this.grupo = grupo;
	}
	public List<String> getLstCdr() {
		return lstCdr;
	}
	public void setLstCdr(List<String> lstCdr) {
		this.lstCdr = lstCdr;
	}

	
	
	
	
	
}
