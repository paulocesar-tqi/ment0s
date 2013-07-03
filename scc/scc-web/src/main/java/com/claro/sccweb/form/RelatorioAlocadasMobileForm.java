/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;

/**
 * @author 92031709
 *
 */
public class RelatorioAlocadasMobileForm extends BaseForm {
	
	private SccAlocadasMobileView entity;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	private String noArquivoGerado;
	
	private List<SccAlocadasMobileView> lstAlocadasMobile;
	
	public SccAlocadasMobileView getEntity() {
		return entity;
	}

	public void setEntity(SccAlocadasMobileView entity) {
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

	public String getNoArquivoGerado() {
		return noArquivoGerado;
	}

	public void setNoArquivoGerado(String noArquivoGerado) {
		this.noArquivoGerado = noArquivoGerado;
	}

	public List<SccAlocadasMobileView> getLstAlocadasMobile() {
		return lstAlocadasMobile;
	}

	public void setLstAlocadasMobile(List<SccAlocadasMobileView> lstAlocadasMobile) {
		this.lstAlocadasMobile = lstAlocadasMobile;
	}
	
	

}
