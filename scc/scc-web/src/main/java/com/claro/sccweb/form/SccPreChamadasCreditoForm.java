package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;

/**
 * @author 92038883
 *
 */

public class SccPreChamadasCreditoForm extends BaseForm {

	private SccPreChamadasCreditoView entity;
	
	private String cdEOTLD;
	
	private String cdEOTClaro;
	
	private String tpStatusCredito;
	
	private Date dtInicioCredito;
	
	private Date dtFimCredito;

	public SccPreChamadasCreditoView getEntity() {
		return entity;
	}

	public void setEntity(SccPreChamadasCreditoView entity) {
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

	public String getTpStatusCredito() {
		return tpStatusCredito;
	}

	public void setTpStatusCredito(String tpStatusCredito) {
		this.tpStatusCredito = tpStatusCredito;
	}

	public Date getDtInicioCredito() {
		return dtInicioCredito;
	}

	public void setDtInicioCredito(Date dtInicioCredito) {
		this.dtInicioCredito = dtInicioCredito;
	}

	public Date getDtFimCredito() {
		return dtFimCredito;
	}

	public void setDtFimCredito(Date dtFimCredito) {
		this.dtFimCredito = dtFimCredito;
	}
	
		
}
