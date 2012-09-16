package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public class ProdutosContratoPosForm extends BaseForm {

	private SccProdutoContratado entity;
	private Long cdContratoCobilling;
	public SccProdutoContratado getEntity() {
		return entity;
	}
	public void setEntity(SccProdutoContratado entity) {
		this.entity = entity;
	}
	public Long getCdContratoCobilling() {
		return cdContratoCobilling;
	}
	public void setCdContratoCobilling(Long cdContratoCobilling) {
		this.cdContratoCobilling = cdContratoCobilling;
	}
	
	
	
}
