package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;

public class ControleRemessaEventoForm extends BaseForm {
	
	private SccArquivoSumarizado entity;
	
	private String tipoOperadora = "O";
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Long cdProdutoCobilling;
	
	private Long[] cdStatusCdr;
	
	private String tipoPeriodo;

	private Date dataInicial;

	private Date dataFinal;
	
	/*    Getters and Setters         */
	
	public SccArquivoSumarizado getEntity() {
		return entity;
	}

	public void setEntity(SccArquivoSumarizado entity) {
		this.entity = entity;
	}
	

	public String getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
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

	public Long[] getCdStatusCdr() {
		return cdStatusCdr;
	}

	public void setCdStatusCdr(Long[] cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
