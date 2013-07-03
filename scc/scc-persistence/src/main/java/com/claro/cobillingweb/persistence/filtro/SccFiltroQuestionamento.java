/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroQuestionamento {
	
	private SccQuestionamentoView entity;
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Long sqQuestionamento;
	
	private Long mes;
	
	private Long ano;
	
	private String inResultadoAnalise;
	
	private Long sqRemessaQuestionamento;
	
	private String statusQuestionamentoArquivo;
	
	private String status;
	
	private Long sqCdrQuestionamento;
	

	public SccQuestionamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccQuestionamentoView entity) {
		this.entity = entity;
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
	
	public String getInResultadoAnalise() {
		return inResultadoAnalise;
	}

	public void setInResultadoAnalise(String inResultadoAnalise) {
		this.inResultadoAnalise = inResultadoAnalise;
	}
	
	

	public Long getSqRemessaQuestionamento() {
		return sqRemessaQuestionamento;
	}

	public void setSqRemessaQuestionamento(Long sqRemessaQuestionamento) {
		this.sqRemessaQuestionamento = sqRemessaQuestionamento;
	}

	public String getStatusQuestionamentoArquivo() {
		return statusQuestionamentoArquivo;
	}

	public void setStatusQuestionamentoArquivo(String statusQuestionamentoArquivo) {
		this.statusQuestionamentoArquivo = statusQuestionamentoArquivo;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getSqCdrQuestionamento() {
		return sqCdrQuestionamento;
	}

	public void setSqCdrQuestionamento(Long sqCdrQuestionamento) {
		this.sqCdrQuestionamento = sqCdrQuestionamento;
	}

	public String montarDataRepasse(){
		
		String value = "";
		if(this.mes != null && this.mes.toString().length() > 0){
			if(this.mes.toString().length() == 1){
				value = "0" + this.mes.toString() + this.ano.toString();
			}else{
				value = this.mes.toString() + this.ano.toString();
			}
		}
		return value;
	}
	
	


}
