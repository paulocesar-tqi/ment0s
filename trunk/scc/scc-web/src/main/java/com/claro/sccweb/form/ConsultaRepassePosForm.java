package com.claro.sccweb.form;

import com.claro.sccweb.service.to.ConsultaRepassePosTO;

/**
 * Form object para a consulta de repasses p�s-pago.
 * Como o objetivo da tela � a pesquisa (consulta) de repasses , o form � uma heran� de {@link ConsultaRepassePosTO}
 *
 */
public class ConsultaRepassePosForm extends ConsultaRepassePosTO {

	/**
	 * Campo hidden que identifica a opera��o que o usu�rio deseja executar.
	 */
	private String operacao;
	
	/**
	 * M�s do relat�rio.
	 */
	private Long mesRelatorio;
	
	/**
	 * Ano do relat�rio.
	 */
	private Long anoRelatorio;
	


	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Long getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(Long mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public Long getAnoRelatorio() {
		return anoRelatorio;
	}

	public void setAnoRelatorio(Long anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}
	
	
	
}
