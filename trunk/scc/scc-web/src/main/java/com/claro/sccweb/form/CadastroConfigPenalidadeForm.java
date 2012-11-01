package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;

public class CadastroConfigPenalidadeForm extends BaseForm {

	private SccPenalidadePorRejeicao entity;
	
	private String cdOperadoraLD;
	private String cdMotivoRejeicao;

	public SccPenalidadePorRejeicao getEntity() {
		return entity;
	}

	public void setEntity(SccPenalidadePorRejeicao entity) {
		this.entity = entity;
	}

	public String getCdOperadoraLD() {
		return cdOperadoraLD;
	}

	public void setCdOperadoraLD(String cdOperadoraLD) {
		this.cdOperadoraLD = cdOperadoraLD;
	}

	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}

	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}
	


}
