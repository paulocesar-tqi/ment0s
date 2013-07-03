package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoResultadoForm extends BaseForm {

	private SccRelatorioQuestionamentoResultadoView entity;
	
	private String sqQuestionamento;
	
	private String cdEOTLD;
		
	private String tpStatus;
	

	public SccRelatorioQuestionamentoResultadoView getEntity() {
		return entity;
	}

	public void setEntity(SccRelatorioQuestionamentoResultadoView entity) {
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

	public String getSqQuestionamento() {
		return sqQuestionamento;
	}

	public void setSqQuestionamento(String sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}


		
}
