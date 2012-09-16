package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccMapaStatus;

public class CadastroMapaStatusForm extends BaseForm {

	private SccMapaStatus entity;
	
	private Long filtroDe;
	
	private Long filtroPara;

	public SccMapaStatus getEntity() {
		return entity;
	}

	public void setEntity(SccMapaStatus entity) {
		this.entity = entity;
	}

	public Long getFiltroDe() {
		return filtroDe;
	}

	public void setFiltroDe(Long filtroDe) {
		this.filtroDe = filtroDe;
	}

	public Long getFiltroPara() {
		return filtroPara;
	}

	public void setFiltroPara(Long filtroPara) {
		this.filtroPara = filtroPara;
	}
	
	
	
	
}
