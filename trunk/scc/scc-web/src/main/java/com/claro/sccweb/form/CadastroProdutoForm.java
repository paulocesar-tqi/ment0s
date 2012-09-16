package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;

public class CadastroProdutoForm extends BaseForm {

	private Long cdProdutoCobilling;
	private SccProdutoCobilling entity;
	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	public SccProdutoCobilling getEntity() {
		return entity;
	}
	public void setEntity(SccProdutoCobilling entity) {
		this.entity = entity;
	}
	
	
	
}
