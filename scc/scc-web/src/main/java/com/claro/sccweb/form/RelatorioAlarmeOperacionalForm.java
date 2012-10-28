package com.claro.sccweb.form;

import java.util.Date;

public class RelatorioAlarmeOperacionalForm extends BaseForm {

	 
	public String toString() {
		return "RelatorioAlarmeOperacionalForm ["+ ", nomeRelatorio=" + nomeRelatorio + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + "]";
	}

	private String nomeRelatorio;
	private Date dataInicial;
	private Date dataFinal;
	
	public String getNomeRelatorio() {
		return nomeRelatorio;
	}
	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
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
