/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.AjustesView;

/**
 * @author 93046251
 *
 */
public class RelatorioNaoConfAjustesForm extends BaseForm {
	
	
	private AjustesView entity;
	
	private String cdEOTLD;
	
	private String cdEOTClaro;

	private Date dtInicio;
	
	private Date dtFim;

	public AjustesView getEntity() {
		return entity;
	}

	public void setEntity(AjustesView entity) {
		this.entity = entity;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	
	

}
