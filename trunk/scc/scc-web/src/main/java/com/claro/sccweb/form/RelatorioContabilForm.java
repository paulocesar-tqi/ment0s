package com.claro.sccweb.form;

public class RelatorioContabilForm extends BaseForm {
	
	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private Long mes;
	private Long ano;
	private String cdMotivoRejeicao;
	
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
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	public Long getAno() {
		return ano;
	}
	public void setAno(Long ano) {
		this.ano = ano;
	}
	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}
	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}
	
	
	

}
