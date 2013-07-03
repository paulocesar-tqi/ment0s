package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreTarifaAcb;

public class SccTarifaForm extends BaseForm {
	
	private String cdEotLd;
	
	private List<SccOperadora> listOperadora;
	
	private List<SccPreTarifaAcb> listPreTarifaAcb;
	
	private SccPreTarifaAcb entity;

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public List<SccOperadora> getListOperadora() {
		return listOperadora;
	}

	public void setListOperadora(List<SccOperadora> listOperadora) {
		this.listOperadora = listOperadora;
	}

	public List<SccPreTarifaAcb> getListPreTarifaAcb() {
		return listPreTarifaAcb;
	}

	public void setListPreTarifaAcb(List<SccPreTarifaAcb> listPreTarifaAcb) {
		this.listPreTarifaAcb = listPreTarifaAcb;
	}

	public SccPreTarifaAcb getEntity() {
		return entity;
	}

	public void setEntity(SccPreTarifaAcb entity) {
		this.entity = entity;
	}
	
	
	
	

}
