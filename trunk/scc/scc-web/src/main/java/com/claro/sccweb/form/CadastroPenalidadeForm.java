package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

public class CadastroPenalidadeForm extends BaseForm {

	private SccFaixaPenalidade entity;
	
	private String cdTipo;
	
	private List<SccFaixaPenalidade> listFaixaPenalidade;

	public SccFaixaPenalidade getEntity() {
		return entity;
	}

	public void setEntity(SccFaixaPenalidade entity) {
		this.entity = entity;
	}

	public String getCdTipo() {
		return cdTipo;
	}

	public void setCdTipo(String cdTipo) {
		this.cdTipo = cdTipo;
	}

	public List<SccFaixaPenalidade> getListFaixaPenalidade() {
		return listFaixaPenalidade;
	}

	public void setListFaixaPenalidade(List<SccFaixaPenalidade> listFaixaPenalidade) {
		this.listFaixaPenalidade = listFaixaPenalidade;
	}
	
	
	
	
}
