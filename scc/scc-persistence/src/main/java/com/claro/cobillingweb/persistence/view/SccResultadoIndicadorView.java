package com.claro.cobillingweb.persistence.view;

import java.math.BigDecimal;
import java.util.Date;

public class SccResultadoIndicadorView {
	
	private String cdIndicador;
	private String dtReferencia;
	private String cdEotLd;
	private String cdEotClaro;
	private String cdRegional;
	private BigDecimal vlIndicador;
	private BigDecimal vlOrigemIndicador1;
	private BigDecimal vlOrigemIndicador2;
	private Date dtPeriodo;
	private Date dtCarga;
	private Long qtdAging;
	private String destFile;
	private Long exitCode;
	private Date dtAtual;
	private Date stopDate;
	private Date coblDate;
	
	private String agingDias;
	
	private Long sqAgingIndicador;
	private Long aging;
	
	private String dtIniFiltro;
	private String dtFimFiltro;
	
	private String dtReferenciaStr;
	
	public String getCdIndicador() {
		return cdIndicador;
	}
	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}
	public String getDtReferencia() {
		return dtReferencia;
	}
	public void setDtReferencia(String dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
	public String getCdEotLd() {
		return cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	
	public String getCdEotClaro() {
		return cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}
	public String getCdRegional() {
		return cdRegional;
	}
	public void setCdRegional(String cdRegional) {
		this.cdRegional = cdRegional;
	}
	public BigDecimal getVlIndicador() {
		return vlIndicador;
	}
	public void setVlIndicador(BigDecimal vlIndicador) {
		this.vlIndicador = vlIndicador;
	}
	public BigDecimal getVlOrigemIndicador1() {
		return vlOrigemIndicador1;
	}
	public void setVlOrigemIndicador1(BigDecimal vlOrigemIndicador1) {
		this.vlOrigemIndicador1 = vlOrigemIndicador1;
	}
	public BigDecimal getVlOrigemIndicador2() {
		return vlOrigemIndicador2;
	}
	public void setVlOrigemIndicador2(BigDecimal vlOrigemIndicador2) {
		this.vlOrigemIndicador2 = vlOrigemIndicador2;
	}
	public Date getDtPeriodo() {
		return dtPeriodo;
	}
	public void setDtPeriodo(Date dtPeriodo) {
		this.dtPeriodo = dtPeriodo;
	}
	public Date getDtCarga() {
		return dtCarga;
	}
	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}
	public Long getQtdAging() {
		return qtdAging;
	}
	public void setQtdAging(Long qtdAging) {
		this.qtdAging = qtdAging;
	}
	public String getDestFile() {
		return destFile;
	}
	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}
	public Long getExitCode() {
		return exitCode;
	}
	public void setExitCode(Long exitCode) {
		this.exitCode = exitCode;
	}
	public Date getDtAtual() {
		return dtAtual;
	}
	public void setDtAtual(Date dtAtual) {
		this.dtAtual = dtAtual;
	}

	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public Date getCoblDate() {
		return coblDate;
	}
	public void setCoblDate(Date coblDate) {
		this.coblDate = coblDate;
	}
	public String getAgingDias() {
		return agingDias;
	}
	public void setAgingDias(String agingDias) {
		this.agingDias = agingDias;
	}
	public Long getSqAgingIndicador() {
		return sqAgingIndicador;
	}
	public void setSqAgingIndicador(Long sqAgingIndicador) {
		this.sqAgingIndicador = sqAgingIndicador;
	}
	public Long getAging() {
		return aging;
	}
	public void setAging(Long aging) {
		this.aging = aging;
	}
	public String getDtIniFiltro() {
		return dtIniFiltro;
	}
	public void setDtIniFiltro(String dtIniFiltro) {
		this.dtIniFiltro = dtIniFiltro;
	}
	public String getDtFimFiltro() {
		return dtFimFiltro;
	}
	public void setDtFimFiltro(String dtFimFiltro) {
		this.dtFimFiltro = dtFimFiltro;
	}
	public String getDtReferenciaStr() {
		return dtReferenciaStr;
	}
	public void setDtReferenciaStr(String dtReferenciaStr) {
		this.dtReferenciaStr = dtReferenciaStr;
	}
	

}
