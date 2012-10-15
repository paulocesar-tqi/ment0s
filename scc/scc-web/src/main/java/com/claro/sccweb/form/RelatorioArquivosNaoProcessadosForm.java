/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;

/**
 * @author 92031709
 *
 */
public class RelatorioArquivosNaoProcessadosForm extends BaseForm {
	
	private SccArquivosNaoProcessadosView entity;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	private String noArquivoGerado;
	
	public SccArquivosNaoProcessadosView getEntity() {
		return entity;
	}

	public void setEntity(SccArquivosNaoProcessadosView entity) {
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
