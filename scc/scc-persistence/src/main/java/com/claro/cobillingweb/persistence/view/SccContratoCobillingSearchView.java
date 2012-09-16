package com.claro.cobillingweb.persistence.view;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.mapper.View;

/**
 * Pesquisa de contratos pré-pago.
 *
 */
public class SccContratoCobillingSearchView extends View {

	private String dsOperadoraLD;
	private String dsOperadoraClaro;
	private String cdEOTLD;
	private String cdEOTClaro; 
	private Date dtInicioContrato;
	private Date dtFinalContrato;
	private String cdTipoContrato;
	private String dsCriterioCusto;
	private Double valorAssociadoCriterioCusto; 
	private String dsPeriodoBase;
	private Double valorCPMF;
	private Double valorISS;
	private Long qtRepasses;
	private Double vlCriterioCustoLiquido;
	
	public String getDsOperadoraLD() {
		return dsOperadoraLD;
	}
	public void setDsOperadoraLD(String dsOperadoraLD) {
		this.dsOperadoraLD = dsOperadoraLD;
	}
	public String getDsOperadoraClaro() {
		return dsOperadoraClaro;
	}
	public void setDsOperadoraClaro(String dsOperadoraClaro) {
		this.dsOperadoraClaro = dsOperadoraClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Date getDtInicioContrato() {
		return dtInicioContrato;
	}
	public void setDtInicioContrato(Date dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}
	public Date getDtFinalContrato() {
		return dtFinalContrato;
	}
	public void setDtFinalContrato(Date dtFinalContrato) {
		this.dtFinalContrato = dtFinalContrato;
	}
	public String getCdTipoContrato() {
		return cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}
	public String getDsCriterioCusto() {
		return dsCriterioCusto;
	}
	public void setDsCriterioCusto(String dsCriterioCusto) {
		this.dsCriterioCusto = dsCriterioCusto;
	}
	public Double getValorAssociadoCriterioCusto() {
		return valorAssociadoCriterioCusto;
	}
	public void setValorAssociadoCriterioCusto(Double valorAssociadoCriterioCusto) {
		this.valorAssociadoCriterioCusto = valorAssociadoCriterioCusto;
	}
	public String getDsPeriodoBase() {
		return dsPeriodoBase;
	}
	public void setDsPeriodoBase(String dsPeriodoBase) {
		this.dsPeriodoBase = dsPeriodoBase;
	}
	public Double getValorCPMF() {
		return valorCPMF;
	}
	public void setValorCPMF(Double valorCPMF) {
		this.valorCPMF = valorCPMF;
	}
	public Double getValorISS() {
		return valorISS;
	}
	public void setValorISS(Double valorISS) {
		this.valorISS = valorISS;
	}
	public Long getQtRepasses() {
		return qtRepasses;
	}
	public void setQtRepasses(Long qtRepasses) {
		this.qtRepasses = qtRepasses;
	}
	public Double getVlCriterioCustoLiquido() {
		return vlCriterioCustoLiquido;
	}
	public void setVlCriterioCustoLiquido(Double vlCriterioCustoLiquido) {
		this.vlCriterioCustoLiquido = vlCriterioCustoLiquido;
	}
	
	
	
}
