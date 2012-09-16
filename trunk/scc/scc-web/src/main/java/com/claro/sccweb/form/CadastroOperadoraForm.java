package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccOperadora;

public class CadastroOperadoraForm extends BaseForm {

	private SccOperadora entity;
	
	private String holding = "N";

	public SccOperadora getEntity() {
		return entity;
	}

	public void setEntity(SccOperadora entity) {
		this.entity = entity;
	}

	public String getHolding() {
		return holding;
	}

	public void setHolding(String holding) {
		this.holding = holding;
	}
	
	
	
}
