package com.claro.sccweb.form;

import java.util.Date;

public class PesquisaBatimentoInterfacePosForm extends BaseForm {

	 
	public String toString() {
		return "PesquisaBatimentoInterfacePosForm ["+ ", cdEOTClaro=" + cdEOTClaro + ", cdEOTLD=" + cdEOTLD
				+ ", tipoArquivo=" + tipoArquivo + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + "]";
	}

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private String tipoArquivo;
	private Date dataInicial;
	private Date dataFinal;
	
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

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
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
