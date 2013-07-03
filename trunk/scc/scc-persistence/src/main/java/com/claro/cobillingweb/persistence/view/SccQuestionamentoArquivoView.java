package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;


public class SccQuestionamentoArquivoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627322187959430123L;
	
	private Long sqQuestionamento;
	
	private Long sqRemessaQuestionamento;
	
	private String cdEotLd;
	
	private String cdEotClaro;
	
	private Long qtdAssinante;
	
	private Long qtdRegra;
	
	private Long qtdParametro;
	
	private Long qtdLogica;
	
	private Long qtdCdr;
	
	private Double minutosTarifados;
	
	private Double vlrLiquido;

	private String noArquivo;
	
	
	public Long getSqQuestionamento() {
		return sqQuestionamento;
	}

	public void setSqQuestionamento(Long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}

	public Long getSqRemessaQuestionamento() {
		return sqRemessaQuestionamento;
	}

	public void setSqRemessaQuestionamento(Long sqRemessaQuestionamento) {
		this.sqRemessaQuestionamento = sqRemessaQuestionamento;
	}

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public Long getQtdAssinante() {
		return qtdAssinante;
	}

	public void setQtdAssinante(Long qtdAssinante) {
		this.qtdAssinante = qtdAssinante;
	}

	public Long getQtdRegra() {
		return qtdRegra;
	}

	public void setQtdRegra(Long qtdRegra) {
		this.qtdRegra = qtdRegra;
	}

	public Long getQtdParametro() {
		return qtdParametro;
	}

	public void setQtdParametro(Long qtdParametro) {
		this.qtdParametro = qtdParametro;
	}

	public Long getQtdLogica() {
		return qtdLogica;
	}

	public void setQtdLogica(Long qtdLogica) {
		this.qtdLogica = qtdLogica;
	}

	public Long getQtdCdr() {
		return qtdCdr;
	}

	public void setQtdCdr(Long qtdCdr) {
		this.qtdCdr = qtdCdr;
	}

	public Double getMinutosTarifados() {
		return minutosTarifados;
	}

	public void setMinutosTarifados(Double minutosTarifados) {
		this.minutosTarifados = minutosTarifados;
	}

	public Double getVlrLiquido() {
		return vlrLiquido;
	}

	public void setVlrLiquido(Double vlrLiquido) {
		this.vlrLiquido = vlrLiquido;
	}

	public String getNoArquivo() {
		return noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}
	
}
