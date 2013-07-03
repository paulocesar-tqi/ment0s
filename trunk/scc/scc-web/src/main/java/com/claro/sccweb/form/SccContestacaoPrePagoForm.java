/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Collection;

import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;

/**
 * @author vagner.souza
 *
 */
public class SccContestacaoPrePagoForm extends BaseForm {
	
	private SccFiltroContestacaoPrePago filtro = new SccFiltroContestacaoPrePago();
	
	private SccContestacaoPrePago entity;
	
	private Collection<SccContestacaoPrePago> listSccContestacaoPrePago;
	
	private String cdEOTLD;
	
	private Integer mes;
	
	private Integer ano;

	
	

	public SccFiltroContestacaoPrePago getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroContestacaoPrePago filtro) {
		this.filtro = filtro;
	}

	public SccContestacaoPrePago getEntity() {
		return entity;
	}

	public void setEntity(SccContestacaoPrePago entity) {
		this.entity = entity;
	}

	public Collection<SccContestacaoPrePago> getListSccContestacaoPrePago() {
		return listSccContestacaoPrePago;
	}

	public void setListSccContestacaoPrePago(Collection<SccContestacaoPrePago> listSccContestacaoPrePago) {
		this.listSccContestacaoPrePago = listSccContestacaoPrePago;
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
	
	

}
