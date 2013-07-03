/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;

/**
 * @author 92038883
 *
 */
public class SccRelDemonstrativoDesempenhoPenalidadeForm extends BaseForm {
	
	private SccRelDemonstrativoDesempenhoPenalidadeView entity;
	
	private String mesRelatorio;
	
	private String anoRelatorio;
	
	private String cdCSP;
	
	public SccRelDemonstrativoDesempenhoPenalidadeView getEntity() {
		return entity;
	}

	public void setEntity(SccRelDemonstrativoDesempenhoPenalidadeView entity) {
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
