package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;

/**
 * @author vagner.souza
 *
 */

public class SccDisputaPrePagoForm extends BaseForm {
	
	private SccFiltroDisputaPrePago filtro = new SccFiltroDisputaPrePago();
	
	private SccDisputaPrePago entity;
	
	private List<SccDisputaPrePago> listDisputaPrePago;

	
	
	public SccFiltroDisputaPrePago getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroDisputaPrePago filtro) {
		this.filtro = filtro;
	}

	public SccDisputaPrePago getEntity() {
		return entity;
	}

	public void setEntity(SccDisputaPrePago entity) {
		this.entity = entity;
	}

	public List<SccDisputaPrePago> getListDisputaPrePago() {
		return listDisputaPrePago;
	}

	public void setListDisputaPrePago(List<SccDisputaPrePago> listDisputaPrePago) {
		this.listDisputaPrePago = listDisputaPrePago;
	}
	
	
	

}
