package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

public class CadastroPenalidadeForm extends BaseForm {

	private SccFaixaPenalidade entity;
	
	private String cdTipo;

	public SccFaixaPenalidade getEntity() {
		return entity;
	}

	public void setEntity(SccFaixaPenalidade entity) {
		this.entity = entity;
	}

	public String getCdTipo() {
		return cdTipo;
	}

	public void setCdTipo(String cdTipo) {
		this.cdTipo = cdTipo;
	}
	
	
	
}
