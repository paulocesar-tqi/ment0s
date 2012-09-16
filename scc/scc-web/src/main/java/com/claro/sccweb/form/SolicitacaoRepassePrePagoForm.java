package com.claro.sccweb.form;

import com.claro.sccweb.service.to.SolicitacaoRepassePrePagoTO;

public class SolicitacaoRepassePrePagoForm extends SolicitacaoRepassePrePagoTO {

	private String operacao;
	private Integer criados;
	private String nmParam;

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Integer getCriados() {
		return criados;
	}

	public void setCriados(Integer criados) {
		this.criados = criados;
	}

	public String getNmParam() {
		return nmParam;
	}

	public void setNmParam(String nmParam) {
		this.nmParam = nmParam;
	}
	
	
	
}
