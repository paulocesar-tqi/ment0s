/**
 * 
 */
package com.claro.sccweb.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.filtro.SccFiltroAcoesCobranca;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;

/**
 * @author 93046251
 *
 */
public class SccAcoesCobrancaForm extends BaseForm {
	
	private SccAcoesCobrancaView entity;
	
	private String cdCsp;
	
	private Long mes;
	
	private Long ano;
	
	private Date dataInicialPeriodo;
	private Date dataFinalPeriodo;
	
	private List<SccAcoesCobrancaView> listAcoesCobranca;
	
	private SccFiltroAcoesCobranca filtro;
	
	

	public SccAcoesCobrancaView getEntity() {
		return entity;
	}

	public void setEntity(SccAcoesCobrancaView entity) {
		this.entity = entity;
	}
	
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
	
	
	public List<SccAcoesCobrancaView> getListAcoesCobranca() {
		return listAcoesCobranca;
	}

	public void setListAcoesCobranca(List<SccAcoesCobrancaView> listAcoesCobranca) {
		this.listAcoesCobranca = listAcoesCobranca;
	}
	
	public SccFiltroAcoesCobranca getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroAcoesCobranca filtro) {
		this.filtro = filtro;
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
