/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroAgingFaturas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1990092730714431019L;
	
	
	
	private SccAgingFaturasView entity;
	
	private String operadoraLd;
	
	private String operadoraClaro;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private String cdCsp;
	
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

	public String getCdCsp() {
		return cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}

	public List<SccAgingFaturasView> getListAgingFaturas() {
		return listAgingFaturas;
	}

	public void setListAgingFaturas(List<SccAgingFaturasView> listAgingFaturas) {
		this.listAgingFaturas = listAgingFaturas;
	}
	
	
	
	

}
