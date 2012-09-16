package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;

public class OperadorasContratoPosForm extends BaseForm {
	
	private Long cdContratoCobilling;
	private SccContratoAcordado entity = new SccContratoAcordado();
	
	public Long getCdContratoCobilling() {
		return cdContratoCobilling;
	}
	
	public void setCdContratoCobilling(Long cdContratoCobilling) {
		this.cdContratoCobilling = cdContratoCobilling;
	}
	
	public SccContratoAcordado getEntity() {
		return entity;
	}
	
	public void setEntity(SccContratoAcordado enity) {
		this.entity = enity;
	}
	
}
