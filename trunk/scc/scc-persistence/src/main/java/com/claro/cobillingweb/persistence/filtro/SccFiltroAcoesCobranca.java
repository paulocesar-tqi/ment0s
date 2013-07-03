/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vagner.souza
 *
 */

public class SccFiltroAcoesCobranca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243239448624857071L;
	
	private String cdCsp;
	
	private Long mes;
	
	private Long ano;
	
	private Date dataInicialPeriodo;
	
	/**
	 * Data final para a pequisa de acordo com o tipo de período.
	 */
	private Date dataFinalPeriodo;


	public String getCdCsp() {
		return cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
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

	@SuppressWarnings("unused")
	private Long pegarAnoAtual(){
		SimpleDateFormat anoAtual = new SimpleDateFormat("yyyy");
		return new Long(anoAtual.format(new Date()));
	}


}
