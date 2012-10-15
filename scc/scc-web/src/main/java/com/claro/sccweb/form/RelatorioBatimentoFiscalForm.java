/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;

/**
 * @author 92031709
 *
 */
public class RelatorioBatimentoFiscalForm extends BaseForm {
	
	private SccBatimentoFiscalView entity;
	
	private Integer mesCiclo;
	
	private Integer anoCiclo;
	
	private Integer cdCiclo;
	
	private String cdCSP;
	
	public SccBatimentoFiscalView getEntity() {
		return entity;
	}

	public void setEntity(SccBatimentoFiscalView entity) {
		this.entity = entity;
	}

	public Integer getMesCiclo() {
		return mesCiclo;
	}

	public void setMesCiclo(Integer mesCiclo) {
		this.mesCiclo = mesCiclo;
	}

	public Integer getAnoCiclo() {
		return anoCiclo;
	}

	public void setAnoCiclo(Integer anoCiclo) {
		this.anoCiclo = anoCiclo;
	}

	public Integer getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}

}
