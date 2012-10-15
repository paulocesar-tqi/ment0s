package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;

public class SccRelatorioChamadasRefaturamentoForm extends BaseForm {
	
	private SccRelatorioChamadasRefaturamentoView entity;
	
	private String cdEOTLD;
	
	private String cdEOTClaro;
	
	private String cdRefaturamento;
	
	private Date dtInicioPeriodo;
	
	private Date dtFimPeriodo;

	public SccRelatorioChamadasRefaturamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccRelatorioChamadasRefaturamentoView entity) {
		this.entity = entity;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getCdRefaturamento() {
		return cdRefaturamento;
	}

	public void setCdRefaturamento(String cdRefaturamento) {
		this.cdRefaturamento = cdRefaturamento;
	}

	public Date getDtInicioPeriodo() {
		return dtInicioPeriodo;
	}

	public void setDtInicioPeriodo(Date dtInicioPeriodo) {
		this.dtInicioPeriodo = dtInicioPeriodo;
	}

	public Date getDtFimPeriodo() {
		return dtFimPeriodo;
	}

	public void setDtFimPeriodo(Date dtFimPeriodo) {
		this.dtFimPeriodo = dtFimPeriodo;
	}

	
	
}
