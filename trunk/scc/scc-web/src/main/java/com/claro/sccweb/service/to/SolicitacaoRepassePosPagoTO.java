package com.claro.sccweb.service.to;


/**
 * Argumento de entrada para servi�o de solicita��o de relat�rio de
 * repasse para p�s-pago.
 *
 */
public class SolicitacaoRepassePosPagoTO {

	/**
	 * EOT da operadora externa.
	 */
	private String cdEOT;
	
	/**
	 * Produto de cobilling.
	 */
	private Long cdProdutoCobilling;
	
	/**
	 * Periodicidade do relat�rio.
	 */
	private Long cdPeriodicidadeRepasse;
	
	/**
	 * M�s do relat�rio.
	 */
	private Long mesRelatorio;
	
	/**
	 * Ano do relat�rio.
	 */
	private Long anoRelatorio;
	
	/**
	 * Usuario solicitou a inclusao da solicita��o.
	 */
	private String usuarioManutencao;

	public String getCdEOT() {
		return cdEOT;
	}

	public void setCdEOT(String cdEOT) {
		this.cdEOT = cdEOT;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public Long getCdPeriodicidadeRepasse() {
		return cdPeriodicidadeRepasse;
	}

	public void setCdPeriodicidadeRepasse(Long cdPeriodicidadeRepasse) {
		this.cdPeriodicidadeRepasse = cdPeriodicidadeRepasse;
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

	public String getUsuarioManutencao() {
		return usuarioManutencao;
	}

	public void setUsuarioManutencao(String usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
	}
	
	
}
