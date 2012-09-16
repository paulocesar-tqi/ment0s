package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;

public class CadastroContratoPreForm extends BaseForm {

	private String cdEOTClaro;
	private String cdEOTLD;
	private SccContratoCobilling entity;
	
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
	public SccContratoCobilling getEntity() {
		return entity;
	}
	
	public void setEntity(SccContratoCobilling entity) {
		this.entity = entity;
	}
	
}
