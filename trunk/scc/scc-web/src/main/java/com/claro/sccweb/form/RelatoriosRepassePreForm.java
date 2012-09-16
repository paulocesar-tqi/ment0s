package com.claro.sccweb.form;

import com.claro.sccweb.controller.repasse.pre.RelatoriosRepassePreController;

public class RelatoriosRepassePreForm extends BaseForm {

	private String cdEOTClaro;
	private String cdEOTLD;
	private String cdStatusFechamento;
	private String cdTipoRelatorio;
	private Long anoRelatorio;
	private Long mesRelatorio;
	private String cdProdutoPrepago;
	private String operacao = RelatoriosRepassePreController.OPERACAO_PESQUISAR;
	
	
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdStatusFechamento() {
		return cdStatusFechamento;
	}
	public void setCdStatusFechamento(String cdStatusFechamento) {
		this.cdStatusFechamento = cdStatusFechamento;
	}
	public String getCdTipoRelatorio() {
		return cdTipoRelatorio;
	}
	public void setCdTipoRelatorio(String cdTipoRelatorio) {
		this.cdTipoRelatorio = cdTipoRelatorio;
	}
	public Long getAnoRelatorio() {
		return anoRelatorio;
	}
	public void setAnoRelatorio(Long anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}
	public Long getMesRelatorio() {
		return mesRelatorio;
	}
	public void setMesRelatorio(Long mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}
	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	 
	public String toString() {
		return "RelatoriosRepassePreForm [cdEOTClaro=" + cdEOTClaro
				+ ", cdEOTLD=" + cdEOTLD + ", cdStatusFechamento="
				+ cdStatusFechamento + ", cdTipoRelatorio=" + cdTipoRelatorio
				+ ", anoRelatorio=" + anoRelatorio + ", mesRelatorio="
				+ mesRelatorio + ", cdProdutoPrepago=" + cdProdutoPrepago
				+ ", operacao=" + operacao + "]";
	}
	
	
	
}
