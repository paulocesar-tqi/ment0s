/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccAssexuadosView;

/**
 * @author 92031709
 *
 */
public class RelatorioAssexuadosForm extends BaseForm {
	
	private SccAssexuadosView entity;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	private String noArquivoGerado;
	
	public SccAssexuadosView getEntity() {
		return entity;
	}

	public void setEntity(SccAssexuadosView entity) {
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

}
