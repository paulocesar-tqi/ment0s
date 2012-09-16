package com.claro.sccweb.form;

import java.util.Date;

public class EventosArquivoRetornoForm extends BaseForm {

	private String cdEOTLD;
	private String cdEOTClaro;
	private Long cdStatusCdr;
	private Date dataInicial;
	private Date dataFinal;
	
	
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Long getCdStatusCdr() {
		return cdStatusCdr;
	}
	public void setCdStatusCdr(Long cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
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
