/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

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
	
	private List<SccAlocadasMobileSemProcessamentoView> lstSccAlocadasMobileSemProcessamento;
	
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

	public List<SccAlocadasMobileSemProcessamentoView> getLstSccAlocadasMobileSemProcessamento() {
		return lstSccAlocadasMobileSemProcessamento;
	}

	public void setLstSccAlocadasMobileSemProcessamento(
			List<SccAlocadasMobileSemProcessamentoView> lstSccAlocadasMobileSemProcessamento) {
		this.lstSccAlocadasMobileSemProcessamento = lstSccAlocadasMobileSemProcessamento;
	}
	
	

}
