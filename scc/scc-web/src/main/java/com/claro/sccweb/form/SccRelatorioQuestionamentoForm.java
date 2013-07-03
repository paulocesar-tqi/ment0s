package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoForm extends BaseForm {

	private SccRelatorioQuestionamentoView entity;
	
	private String cdEOTLD;
		
	private String tpStatus;
	

	public SccRelatorioQuestionamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccRelatorioQuestionamentoView entity) {
		this.entity = entity;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getTpStatus() {
		return tpStatus;
	}

	public void setTpStatus(String tpStatus) {
		this.tpStatus = tpStatus;
	}


		
}
