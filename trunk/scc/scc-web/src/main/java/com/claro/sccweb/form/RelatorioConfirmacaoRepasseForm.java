/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;

/**
 * @author 92031709
 *
 */
public class RelatorioConfirmacaoRepasseForm extends BaseForm {
	
	private SccConfirmacaoRepasseView entity;
	
	private String mesRepasse;
	
	private String anoRepasse;
	
	private String cdEOTLD;
	
	private String cdStatusRepasse;
	
	public SccConfirmacaoRepasseView getEntity() {
		return entity;
	}

	public void setEntity(SccConfirmacaoRepasseView entity) {
		this.entity = entity;
	}

	public String getMesRepasse() {
		return mesRepasse;
	}

	public void setMesRepasse(String mesRepasse) {
		this.mesRepasse = mesRepasse;
	}

	public String getAnoRepasse() {
		return anoRepasse;
	}

	public void setAnoRepasse(String anoRepasse) {
		this.anoRepasse = anoRepasse;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdStatusRepasse() {
		return cdStatusRepasse;
	}

	public void setCdStatusRepasse(String cdStatusRepasse) {
		this.cdStatusRepasse = cdStatusRepasse;
	}

}
