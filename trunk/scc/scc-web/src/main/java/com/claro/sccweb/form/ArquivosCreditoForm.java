package com.claro.sccweb.form;

import java.util.Date;

public class ArquivosCreditoForm extends BaseForm {

	private String cdOrigem;
	private String cdStatus;
	private Date dataInicial;
	private Date dataFinal;
	public String getCdOrigem() {
		return cdOrigem;
	}
	public void setCdOrigem(String cdOrigem) {
		this.cdOrigem = cdOrigem;
	}
	public String getCdStatus() {
		return cdStatus;
	}
	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	
}
