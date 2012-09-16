package com.claro.sccweb.form;

import com.claro.sccweb.controller.repasse.pre.DemonstrativoRepassePrePagoController;
import com.claro.sccweb.service.to.DemonstrativoRepassePrePagoTO;

/**
 * Form object para geração do demonstrativo de repasse do pré-pago.
 * Controller associado {@link DemonstrativoRepassePrePagoController}
 *
 */
public class DemonstrativoRepassePrePagoForm extends DemonstrativoRepassePrePagoTO {

	 
	public String toString() {
		return "DemonstrativoRepassePrePagoForm [anoDemonstrativo="
				+ anoDemonstrativo + ", mesDemonstrativo=" + mesDemonstrativo
				+ ", operacao=" + operacao + ", getCdEOTClaro()="
				+ getCdEOTClaro() + ", getCdEOTLD()=" + getCdEOTLD()
				+ ", getCdProdutoPrepago()=" + getCdProdutoPrepago()
				+ ", getDtInicial()=" + getDtInicial() + ", getCdEOTHolding()="
				+ getCdEOTHolding() + ", getDtFinal()=" + getDtFinal() + "]";
	}

	private Long anoDemonstrativo;
	
	private Long mesDemonstrativo;
	
	private String operacao;

	public Long getAnoDemonstrativo() {
		return anoDemonstrativo;
	}

	public void setAnoDemonstrativo(Long anoDemonstrativo) {
		this.anoDemonstrativo = anoDemonstrativo;
	}

	public Long getMesDemonstrativo() {
		return mesDemonstrativo;
	}

	public void setMesDemonstrativo(Long mesDemonstrativo) {
		this.mesDemonstrativo = mesDemonstrativo;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	
	
	
}
