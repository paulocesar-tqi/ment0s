package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;

public class CadastroAliquotaImpostoForm extends BaseForm {

	private String inPlataformaTarifacao;
	private String noImposto;
	private SccAliquotaImposto entity;
	
	public String getInPlataformaTarifacao() {
		return inPlataformaTarifacao;
	}
	public void setInPlataformaTarifacao(String inPlataformaTarifacao) {
		this.inPlataformaTarifacao = inPlataformaTarifacao;
	}
	public String getNoImposto() {
		return noImposto;
	}
	public void setNoImposto(String noImposto) {
		this.noImposto = noImposto;
	}
	public SccAliquotaImposto getEntity() {
		return entity;
	}
	public void setEntity(SccAliquotaImposto entity) {
		this.entity = entity;
	}
	
}
