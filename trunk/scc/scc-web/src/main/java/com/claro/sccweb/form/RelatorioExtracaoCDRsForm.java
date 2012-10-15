/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;

/**
 * @author 92031709
 *
 */
public class RelatorioExtracaoCDRsForm extends BaseForm {
	
	private SccExtracaoCDRsView entity;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	private String nuMsisdnOrigem;
	
	public SccExtracaoCDRsView getEntity() {
		return entity;
	}

	public void setEntity(SccExtracaoCDRsView entity) {
		this.entity = entity;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getNuMsisdnOrigem() {
		return nuMsisdnOrigem;
	}

	public void setNuMsisdnOrigem(String nuMsisdnOrigem) {
		this.nuMsisdnOrigem = nuMsisdnOrigem;
	}

}
