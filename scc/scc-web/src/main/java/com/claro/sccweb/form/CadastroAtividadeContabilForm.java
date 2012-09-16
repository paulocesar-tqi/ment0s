package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;

public class CadastroAtividadeContabilForm extends BaseForm {
	
	private SccAtividadeContabil entity;
	
	public SccAtividadeContabil getEntity() {
		return entity;
	}
	
	public void setEntity(SccAtividadeContabil entity) {
		this.entity = entity;
	}
	
}
