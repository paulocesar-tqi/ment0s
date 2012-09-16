package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccContratoCobl;

public class CadastroContratoPosForm extends BaseForm {

	private String dsOperadoraLD;
	
	private SccContratoCobl entity;

	

	public String getDsOperadoraLD() {
		return dsOperadoraLD;
	}

	public void setDsOperadoraLD(String dsOperadoraLD) {
		this.dsOperadoraLD = dsOperadoraLD;
	}

	public SccContratoCobl getEntity() {
		return entity;
	}

	public void setEntity(SccContratoCobl entity) {
		this.entity = entity;
	}
	
	
	
}
