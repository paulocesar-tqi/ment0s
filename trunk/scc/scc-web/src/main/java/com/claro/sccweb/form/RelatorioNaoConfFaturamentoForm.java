package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.FaturamentoView;

public class RelatorioNaoConfFaturamentoForm extends BaseForm {
	
	private FaturamentoView entity;
	private String cdCsp;
	private Long cdCiclo;
	private Long mmCiclo;
	private Long aaCiclo;

	public FaturamentoView getEntity() {
		return entity;
	}

	public void setEntity(FaturamentoView entity) {
		this.entity = entity;
	}

	public String getCdCsp() {
		return cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}

	public Long getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(Long cdCiclo) {
		this.cdCiclo = cdCiclo;
	}

	public Long getMmCiclo() {
		return mmCiclo;
	}

	public void setMmCiclo(Long mmCiclo) {
		this.mmCiclo = mmCiclo;
	}

	public Long getAaCiclo() {
		return aaCiclo;
	}

	public void setAaCiclo(Long aaCiclo) {
		this.aaCiclo = aaCiclo;
	}
	
	
	

}
