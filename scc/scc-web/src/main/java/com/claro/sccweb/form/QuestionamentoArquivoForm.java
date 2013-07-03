/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;

/**
 * @author vagner.souza
 *
 */
public class QuestionamentoArquivoForm extends BaseForm {
	
	private SccFiltroQuestionamento filtro = new SccFiltroQuestionamento();
	
	private List<SccQuestionamentoArquivoView> listQuestionamentoArquivo;
	
	private Collection<SccCdrQuestionamento> listQuestionamentoAnalise;
	
	private String[] resultadoAnalises;
	
	private Long[] sqRemessaQuestionamentos;
	
	private Long[] sqCdrQuestionamentos;

	public SccFiltroQuestionamento getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroQuestionamento filtro) {
		this.filtro = filtro;
	}

	public List<SccQuestionamentoArquivoView> getListQuestionamentoArquivo() {
		return listQuestionamentoArquivo;
	}

	public void setListQuestionamentoArquivo(
			List<SccQuestionamentoArquivoView> listQuestionamentoArquivo) {
		this.listQuestionamentoArquivo = listQuestionamentoArquivo;
	}

	public Collection<SccCdrQuestionamento> getListQuestionamentoAnalise() {
		return listQuestionamentoAnalise;
	}

	public void setListQuestionamentoAnalise(
			Collection<SccCdrQuestionamento> listQuestionamentoAnalise) {
		this.listQuestionamentoAnalise = listQuestionamentoAnalise;
	}

	

	public Long[] getSqRemessaQuestionamentos() {
		return sqRemessaQuestionamentos;
	}

	public void setSqRemessaQuestionamentos(Long[] sqRemessaQuestionamentos) {
		this.sqRemessaQuestionamentos = sqRemessaQuestionamentos;
	}

	public Long[] getSqCdrQuestionamentos() {
		return sqCdrQuestionamentos;
	}

	public void setSqCdrQuestionamentos(Long[] sqCdrQuestionamentos) {
		this.sqCdrQuestionamentos = sqCdrQuestionamentos;
	}

	public String[] getResultadoAnalises() {
		return resultadoAnalises;
	}

	public void setResultadoAnalises(String[] resultadoAnalises) {
		this.resultadoAnalises = resultadoAnalises;
	}


}
