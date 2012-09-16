package com.claro.sccweb.form;

import java.util.Date;

public class ArquivoRetornoForm extends BaseForm {

	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private Long cdTipoArquivo;
	private String statusArquivo;
	private String sistemaDestino;
	private String tipoPeriodo;
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
	public Long getCdTipoArquivo() {
		return cdTipoArquivo;
	}
	public void setCdTipoArquivo(Long cdTipoArquivo) {
		this.cdTipoArquivo = cdTipoArquivo;
	}
	public String getStatusArquivo() {
		return statusArquivo;
	}
	public void setStatusArquivo(String statusArquivo) {
		this.statusArquivo = statusArquivo;
	}
	public String getSistemaDestino() {
		return sistemaDestino;
	}
	public void setSistemaDestino(String sistemaDestino) {
		this.sistemaDestino = sistemaDestino;
	}
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
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
