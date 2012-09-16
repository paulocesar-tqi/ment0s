package com.claro.sccweb.service.to;

import java.util.Date;

public class DemonstrativoRepassePosPagoTO {

	private Date dtInicialPeriodo;
	
	private Date dtFinalPeriodo;
	
	
	
	/**
	 * EOT da operadora externa.
	 */
	private String cdEOT;
	
	/**
	 * EOT da Holding Claro.
	 */
	private String cdEOTHoldingClaro;
	
	/**
	 * EOT da operadora Claro selecionada para detalhes.
	 */
	private String cdEOTClaro;
	
	/**
	 * Produto de cobilling.
	 */
	private Long cdProdutoCobilling;
	
	/**
	 * Periodicidade do relatório.
	 */
	private Long cdPeriodicidadeRepasse;
	
	/**
	 * Mês do relatório.
	 */
	private Long mesRelatorio;
	
	/**
	 * Ano do relatório.
	 */
	private Long anoRelatorio;

	public String getCdEOT() {
		return cdEOT;
	}

	public void setCdEOT(String cdEOT) {
		this.cdEOT = cdEOT;
	}

	public String getCdEOTHoldingClaro() {
		return cdEOTHoldingClaro;
	}

	public void setCdEOTHoldingClaro(String cdEOTHoldingClaro) {
		this.cdEOTHoldingClaro = cdEOTHoldingClaro;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
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
	
	public Date getDtInicialPeriodo() {
		return dtInicialPeriodo;
	}

	public void setDtInicialPeriodo(Date dtInicialPeriodo) {
		this.dtInicialPeriodo = dtInicialPeriodo;
	}

	public Date getDtFinalPeriodo() {
		return dtFinalPeriodo;
	}

	public void setDtFinalPeriodo(Date dtFinalPeriodo) {
		this.dtFinalPeriodo = dtFinalPeriodo;
	}


	
	
}
