package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;

public class CadastroConfigPenalidadeForm extends BaseForm {

	private SccPenalidadePorRejeicao entity;
	
	private SccOperadora operadoraLD;
	private SccMotivoRejeicao motivoRejeicao;

	public SccPenalidadePorRejeicao getEntity() {
		return entity;
	}

	public void setEntity(SccPenalidadePorRejeicao entity) {
		this.entity = entity;
	}
	

	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public SccMotivoRejeicao getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(SccMotivoRejeicao motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	
	
}
