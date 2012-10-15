package com.claro.sccweb.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;

public class SccPerdaFaturamentoForm extends BaseForm {
	
	private SccArquivoSumarizado entity;
	
	private String tipoOperadora = "O";
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Integer tipoEvento;
	
	private String evento; 
	
	private Date dataInicial;

	private Date dataFinal;
	
	private Boolean holding;
	
	

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
		if(StringUtils.isNotEmpty(tipoOperadora)){
			this.setHolding(tipoOperadora == "O" ? true:false);
		}
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

	public Integer getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
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

	public Boolean isHolding() {
		return holding;
	}

	public void setHolding(Boolean holding) {
		this.holding = holding;
	}
	
}
