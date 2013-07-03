/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;

/**
 * @author vagner.souza
 *
 */
public class SccQuestionamentoFinanceiroExcelView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1977371106549843994L;
	
	private String descricaoEotLD;
	private String cdEotClaro;
	private String sgUf;
	private String sqQuestionamento;
	private String noArquivo;
	private String totais;
	private String cdrQuestionados;
	private String cdrConfirmadosSemAnalise;
	private String cdrConfirmadosComAnalise;
	private String cdrConfirmadosComAnaliseSemProcedencia;
	private String cdrPenalidadeClaro;
	private String cdrPenalidadeLd;
	public String getDescricaoEotLD() {
		return descricaoEotLD;
	}
	public void setDescricaoEotLD(String descricaoEotLD) {
		this.descricaoEotLD = descricaoEotLD;
	}
	public String getCdEotClaro() {
		return cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}
	public String getSgUf() {
		return sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}
	public String getSqQuestionamento() {
		return sqQuestionamento;
	}
	public void setSqQuestionamento(String sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}
	public String getNoArquivo() {
		return noArquivo;
	}
	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	public String getTotais() {
		return totais;
	}
	public void setTotais(String totais) {
		this.totais = totais;
	}
	public String getCdrQuestionados() {
		return cdrQuestionados;
	}
	public void setCdrQuestionados(String cdrQuestionados) {
		this.cdrQuestionados = cdrQuestionados;
	}
	public String getCdrConfirmadosSemAnalise() {
		return cdrConfirmadosSemAnalise;
	}
	public void setCdrConfirmadosSemAnalise(String cdrConfirmadosSemAnalise) {
		this.cdrConfirmadosSemAnalise = cdrConfirmadosSemAnalise;
	}
	public String getCdrConfirmadosComAnalise() {
		return cdrConfirmadosComAnalise;
	}
	public void setCdrConfirmadosComAnalise(String cdrConfirmadosComAnalise) {
		this.cdrConfirmadosComAnalise = cdrConfirmadosComAnalise;
	}
	public String getCdrConfirmadosComAnaliseSemProcedencia() {
		return cdrConfirmadosComAnaliseSemProcedencia;
	}
	public void setCdrConfirmadosComAnaliseSemProcedencia(
			String cdrConfirmadosComAnaliseSemProcedencia) {
		this.cdrConfirmadosComAnaliseSemProcedencia = cdrConfirmadosComAnaliseSemProcedencia;
	}
	public String getCdrPenalidadeClaro() {
		return cdrPenalidadeClaro;
	}
	public void setCdrPenalidadeClaro(String cdrPenalidadeClaro) {
		this.cdrPenalidadeClaro = cdrPenalidadeClaro;
	}
	public String getCdrPenalidadeLd() {
		return cdrPenalidadeLd;
	}
	public void setCdrPenalidadeLd(String cdrPenalidadeLd) {
		this.cdrPenalidadeLd = cdrPenalidadeLd;
	}
	
	
	

}
