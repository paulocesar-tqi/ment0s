/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;

/**
 * @author 92031709
 *
 */
public class RelatorioContingenciaFiscalForm extends BaseForm {
	
	private SccContingenciaFiscalView entity;
	
	private String mesRelatorio;
	
	private String anoRelatorio;
	
	private String cdCSP;
	
	public SccContingenciaFiscalView getEntity() {
		return entity;
	}

	public void setEntity(SccContingenciaFiscalView entity) {
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

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}
	
}
