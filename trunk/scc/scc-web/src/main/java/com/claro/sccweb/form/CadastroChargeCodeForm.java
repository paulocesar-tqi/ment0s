package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;

public class CadastroChargeCodeForm extends BaseForm {

	private SccServicoAdicional entity;
	
	private Long cdProdutoCobilling;

	public SccServicoAdicional getEntity() {
		return entity;
	}

	public void setEntity(SccServicoAdicional entity) {
		this.entity = entity;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	
	
	
}
