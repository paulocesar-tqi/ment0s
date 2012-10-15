package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;

public class RelatorioNaoConfBatimentoEstornoDebitoForm extends BaseForm {
	
	BatimentoEstornoDebitoView entity;
	
	private Long mesReferencia;
	
	private Long anoReferencia;
	
	

	public Long getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Long mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Long getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Long anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public BatimentoEstornoDebitoView getEntity() {
		return entity;
	}

	public void setEntity(BatimentoEstornoDebitoView entity) {
		this.entity = entity;
	}
	
}
