/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;

/**
 * @author vagner.souza
 *
 */
public class QuestionamentoFinanceiroForm extends BaseForm {
	
	private SccQuestionamentoView entity;
	
	private SccFiltroQuestionamento filtro;
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Long sqQuestionamento;
	
	private Long mes;
	
	private Long ano;

	public SccQuestionamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccQuestionamentoView entity) {
		this.entity = entity;
	}
	
	public SccFiltroQuestionamento getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroQuestionamento filtro) {
		this.filtro = filtro;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public Long getSqQuestionamento() {
		return sqQuestionamento;
	}

	public void setSqQuestionamento(Long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}


	
}
