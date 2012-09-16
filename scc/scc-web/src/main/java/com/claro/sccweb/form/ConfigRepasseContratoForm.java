package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public class ConfigRepasseContratoForm extends BaseForm {

	private SccConfigRepasseCobranca entity;
	
	private SccProdutoContratadoPK produtoContratadoPK;	
	

	public SccConfigRepasseCobranca getEntity() {
		return entity;
	}

	public void setEntity(SccConfigRepasseCobranca entity) {
		this.entity = entity;
	}

	public SccProdutoContratadoPK getProdutoContratadoPK() {
		return produtoContratadoPK;
	}

	public void setProdutoContratadoPK(SccProdutoContratadoPK produtoContratadoPK) {
		this.produtoContratadoPK = produtoContratadoPK;
	}
	
	
	
}
