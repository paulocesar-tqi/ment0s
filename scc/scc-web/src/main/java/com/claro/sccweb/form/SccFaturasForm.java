package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccFaturaView;

public class SccFaturasForm extends BaseForm{
	
	private SccFaturaView entity;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private Long tipoData;
	
	private String status;
	
	private String numeroFatura;
	
	private String relatorioSelecionado ;

	public SccFaturaView getEntity() {
		return entity;
	}

	public void setEntity(SccFaturaView entity) {
		this.entity = entity;
	}

	public Date getDataInicialPeriodo() {
		return dataInicialPeriodo;
	}

	public void setDataInicialPeriodo(Date dataInicialPeriodo) {
		this.dataInicialPeriodo = dataInicialPeriodo;
	}

	public Date getDataFinalPeriodo() {
		return dataFinalPeriodo;
	}

	public void setDataFinalPeriodo(Date dataFinalPeriodo) {
		this.dataFinalPeriodo = dataFinalPeriodo;
	}

	public Long getTipoData() {
		return tipoData;
	}

	public void setTipoData(Long tipoData) {
		this.tipoData = tipoData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(String relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}
	
	
	
	
	


}
