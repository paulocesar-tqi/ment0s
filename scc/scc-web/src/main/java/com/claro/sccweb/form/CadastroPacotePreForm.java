package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;

public class CadastroPacotePreForm extends BaseForm {

	private SccPacotePrepago entity;
	
	private Long cdPacotePrepago;
	private String noPacotePrepago;
	private String cdProdutoPrepago;

	public SccPacotePrepago getEntity() {
		return entity;
	}

	public void setEntity(SccPacotePrepago entity) {
		this.entity = entity;
	}

	public Long getCdPacotePrepago() {
		return cdPacotePrepago;
	}

	public void setCdPacotePrepago(Long cdPacotePrepago) {
		this.cdPacotePrepago = cdPacotePrepago;
	}

	public String getNoPacotePrepago() {
		return noPacotePrepago;
	}

	public void setNoPacotePrepago(String noPacotePrepago) {
		this.noPacotePrepago = noPacotePrepago;
	}

	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}

	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	
	
	
	
}
