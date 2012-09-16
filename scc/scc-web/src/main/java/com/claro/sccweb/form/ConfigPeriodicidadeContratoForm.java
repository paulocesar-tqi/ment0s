package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public class ConfigPeriodicidadeContratoForm extends BaseForm {

	private SccPeriodProdContr entity;
	private SccProdutoContratadoPK produtoContratadoPK;

	public SccPeriodProdContr getEntity() {
		return entity;
	}

	public void setEntity(SccPeriodProdContr entity) {
		this.entity = entity;
	}

	public SccProdutoContratadoPK getProdutoContratadoPK() {
		return produtoContratadoPK;
	}

	public void setProdutoContratadoPK(SccProdutoContratadoPK produtoContratadoPK) {
		this.produtoContratadoPK = produtoContratadoPK;
	}
	
	
	
}
