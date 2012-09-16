package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.entity.SccPreDominioPK;

public class CadastroPreDominioForm extends BaseForm {

	private String tpDominio;
	private SccPreDominio entity = new SccPreDominio();
	
	public String getTpDominio() {
		return tpDominio;
	}
	public void setTpDominio(String tpDominio) {
		this.tpDominio = tpDominio;
	}
	public SccPreDominio getEntity() {
		if (entity == null)
			{
			entity = new SccPreDominio();
			entity.setId(new SccPreDominioPK());
			}
		return entity;
	}
	public void setEntity(SccPreDominio entity) {
		this.entity = entity;
	}
	
	
}
