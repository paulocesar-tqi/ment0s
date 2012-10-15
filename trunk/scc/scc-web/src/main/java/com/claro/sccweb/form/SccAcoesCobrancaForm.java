/**
 * 
 */
package com.claro.sccweb.form;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private Long pegarAnoAtual(){
		SimpleDateFormat anoAtual = new SimpleDateFormat("yyyy");
		return new Long(anoAtual.format(new Date()));
	}


}
