package com.claro.sccweb.form;

import com.claro.sccweb.service.to.DemonstrativoRepassePosPagoTO;

public class DemonstrativoRepassePosPagoForm extends DemonstrativoRepassePosPagoTO {

	
	private String operacao;

	private String operadoraClaro;
	
	private String operadoraExterna;
	
	private Long produtoCobilling;
	
	private Long periodo;
	
	

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getOperadoraExterna() {
		return operadoraExterna;
	}

	public void setOperadoraExterna(String operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}

	public Long getProdutoCobilling() {
		return produtoCobilling;
	}

	public void setProdutoCobilling(Long produtoCobilling) {
		this.produtoCobilling = produtoCobilling;
	}

	public Long getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Long periodo) {
		this.periodo = periodo;
	}

	public String getOperacao() {
		return operacao;
	}

	
	public void populaTO()
	{		
		
	}
	
	
}
