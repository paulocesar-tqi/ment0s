package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.filtro.SccFiltroAgingFaturas;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;

public class SccAgingFaturasForm extends BaseForm {
	
	private SccAgingFaturasView entity;
	
	private String operadoraLd;
	
	private String operadoraClaro;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private SccFiltroAgingFaturas filtro;
	
	private List<SccAgingFaturasView> listAgingFaturas;
	

	public SccAgingFaturasView getEntity() {
		return entity;
	}

	public void setEntity(SccAgingFaturasView entity) {
		this.entity = entity;
	}

	public String getOperadoraLd() {
		return operadoraLd;
	}

	public void setOperadoraLd(String operadoraLd) {
		this.operadoraLd = operadoraLd;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
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

	public SccFiltroAgingFaturas getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroAgingFaturas filtro) {
		this.filtro = filtro;
	}

	public List<SccAgingFaturasView> getListAgingFaturas() {
		return listAgingFaturas;
	}

	public void setListAgingFaturas(List<SccAgingFaturasView> listAgingFaturas) {
		this.listAgingFaturas = listAgingFaturas;
	}
	
	
	
	
	


}
