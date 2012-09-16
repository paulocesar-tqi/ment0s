package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;

public class CadastroAssinanteCriticaForm extends BaseForm {

	private String cdCritica;
	
	private SccAssinanteCritica entity;

	public String getCdCritica() {
		return cdCritica;
	}

	public void setCdCritica(String cdCritica) {
		this.cdCritica = cdCritica;
	}

	public SccAssinanteCritica getEntity() {
		return entity;
	}

	public void setEntity(SccAssinanteCritica entity) {
		this.entity = entity;
	}
	
	
	
}
