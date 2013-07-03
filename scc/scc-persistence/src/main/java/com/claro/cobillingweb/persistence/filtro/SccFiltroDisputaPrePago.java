/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.utils.DateUtils;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroDisputaPrePago implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7188815853871090455L;

	private SccDisputaPrePago entity;
	
	private String cdEOTLD;
	
	private Integer mes;
	
	private Integer ano;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private Collection<SccDisputaPrePago> listDisputaPrePago;
	
	
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
	
	public String montarData(){
		
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

	

	public SccDisputaPrePago getEntity() {
		return entity;
	}

	public void setEntity(SccDisputaPrePago entity) {
		this.entity = entity;
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

	public Collection<SccDisputaPrePago> getListDisputaPrePago() {
		return listDisputaPrePago;
	}

	public void setListDisputaPrePago(Collection<SccDisputaPrePago> listDisputaPrePago) {
		this.listDisputaPrePago = listDisputaPrePago;
	}
	
}
