package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;

public class CadastroContratoPreForm extends BaseForm {

	private String cdEOTClaro;
	private String cdEOTLD;
	private String cdTipo;
	
	private SccContratoCobilling entity;
	
	private List<SccContratoCobilling> listContrato;
	
	private List<SccContratoCobillingSearchView> listContratoSearchView;

	public String getCdTipo() {
		return cdTipo;
	}
	public void setCdTipo(String cdTipo) {
		this.cdTipo = cdTipo;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public SccContratoCobilling getEntity() {
		return entity;
	}
	
	public void setEntity(SccContratoCobilling entity) {
		this.entity = entity;
	}
	public List<SccContratoCobilling> getListContrato() {
		return listContrato;
	}
	public void setListContrato(List<SccContratoCobilling> listContrato) {
		this.listContrato = listContrato;
	}
	public List<SccContratoCobillingSearchView> getListContratoSearchView() {
		return listContratoSearchView;
	}
	public void setListContratoSearchView(
			List<SccContratoCobillingSearchView> listContratoSearchView) {
		this.listContratoSearchView = listContratoSearchView;
	}
	
	
	
}
