/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;

/**
 * @author 92038883
 *
 */
public class SccPosRelatorioDisputaDetalheForm extends BaseForm {
	
	private SccPosRelatorioDisputaDetalheView entity;
	
	private String mesRelatorio;
	
	private String anoRelatorio;
	
	private String cdEotLD;
	
	public SccPosRelatorioDisputaDetalheView getEntity() {
		return entity;
	}

	public void setEntity(SccPosRelatorioDisputaDetalheView entity) {
		this.entity = entity;
	}

	public String getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(String mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public String getAnoRelatorio() {
		return anoRelatorio;
	}

	public void setAnoRelatorio(String anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}

	public String getCdEotLD() {
		return cdEotLD;
	}

	public void setCdEotLD(String cdEotLD) {
		this.cdEotLD = cdEotLD;
	}


	
}
