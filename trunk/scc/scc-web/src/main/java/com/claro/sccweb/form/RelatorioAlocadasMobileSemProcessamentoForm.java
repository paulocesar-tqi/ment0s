/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;

/**
 * @author 92031709
 *
 */
public class RelatorioAlocadasMobileSemProcessamentoForm extends BaseForm {
	
	private SccAlocadasMobileSemProcessamentoView entity;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	private String noArquivoGerado;
	
	public SccAlocadasMobileSemProcessamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccAlocadasMobileSemProcessamentoView entity) {
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
