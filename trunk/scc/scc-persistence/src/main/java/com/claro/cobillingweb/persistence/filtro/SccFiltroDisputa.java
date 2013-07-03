package com.claro.cobillingweb.persistence.filtro;

import java.util.Collection;
import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.utils.DateUtils;

public class SccFiltroDisputa {
	
	
	private String cdEOTLD;
	
	private Integer mes;
	
	private Integer ano;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private SccDisputa entity;
	
	private Collection<SccDisputa> listDisputa;
	
	public SccFiltroDisputa(){
		
	}
	
	public SccFiltroDisputa(Integer mes, Integer ano){
		this.mes = mes;
		this.ano = ano;
	}

	
	public SccFiltroDisputa(String cdEOTLD, Integer mes, Integer ano){
		this.cdEOTLD = cdEOTLD;
		this.mes = mes;
		this.ano = ano;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public SccDisputa getEntity() {
		return entity;
	}

	public void setEntity(SccDisputa entity) {
		this.entity = entity;
	}

	public Collection<SccDisputa> getListDisputa() {
		return listDisputa;
	}

	public void setListDisputa(Collection<SccDisputa> listDisputa) {
		this.listDisputa = listDisputa;
	}

	public Date montarDataInicial(){
		Date dataInicial = null;
		if(this.mes != null && this.ano != null){
			dataInicial =  DateUtils.calculaDataInicialPeriodo(this.mes.longValue(), this.ano.longValue());
		}
		
		return dataInicial;
		
	}
	
	public Date montarDataFinal(){
		Date dataFinal = null;
		if(this.mes != null && this.ano != null){
			dataFinal = DateUtils.calculaDataFinalPeriodo(this.mes.longValue(), this.ano.longValue());
		}
		
		return dataFinal;
	}
	
	public String montarDataDisputa(){
		
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
