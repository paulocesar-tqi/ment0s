/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.utils.DateUtils;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroContestacaoPrePago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1496658367616939566L;
	

	private SccContestacaoPrePago entity;
	
	private String cdEOTLD;
	
	private Integer mes;
	
	private Integer ano;
	
	private Date dataInicial;
	
	private Collection<SccContestacaoPrePago> listSccContestacaoPrePago;
	
	private Date dataFinal;	

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

	public SccContestacaoPrePago getEntity() {
		return entity;
	}

	public void setEntity(SccContestacaoPrePago entity) {
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

	public Collection<SccContestacaoPrePago> getListSccContestacaoPrePago() {
		return listSccContestacaoPrePago;
	}

	public void setListSccContestacaoPrePago(
			Collection<SccContestacaoPrePago> listSccContestacaoPrePago) {
		this.listSccContestacaoPrePago = listSccContestacaoPrePago;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
