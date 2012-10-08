package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;

public class ArquivoRetornoDrillDownPosForm extends BaseForm {

	 
	public String toString() {
		return "PesquisaProcessadosPosForm [tipoOperadora=" + tipoOperadora
				+ ", cdEOTClaro=" + cdEOTClaro + ", cdEOTLD=" + cdEOTLD
				+ ", statusArquivo=" + statusArquivo + ", tipoArquivo="
				+ tipoArquivo + ", periodo=" + periodo + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + "]";
	}

	private SccArquivoCobilling arquivoSelecionado;
	
	private SccCdrCobilling filtroSelecionado;
	
	private SccCdrCobilling cdrSelecionado;
	
	private String tipoOperadora = "O";
	private String cdEOTClaro;
	private String cdEOTLD;
	private String statusArquivo;
	private String tipoArquivo;
	private String periodo;
	private Date dataInicial;
	private Date dataFinal;
	private String visaoArquivo;
	private SccPaginatedList cdrList;
	
	private String cdProdutoCobilling;
	private String cdStatusCdr;

	public String getCdStatusCdr() {
		return cdStatusCdr;
	}

	public void setCdStatusCdr(String cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
	}

	public String getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(String cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public SccArquivoCobilling getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(SccArquivoCobilling arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public String getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
	}

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

	public String getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(String statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getVisaoArquivo() {
		return visaoArquivo;
	}

	public void setVisaoArquivo(String visaoArquivo) {
		this.visaoArquivo = visaoArquivo;
	}

	public SccCdrCobilling getFiltroSelecionado() {
		return filtroSelecionado;
	}

	public void setFiltroSelecionado(SccCdrCobilling filtroSelecionado) {
		this.filtroSelecionado = filtroSelecionado;
	}

	public SccPaginatedList getCdrList() {
		return cdrList;
	}

	public void setCdrList(SccPaginatedList cdrList) {
		this.cdrList = cdrList;
	}

	public SccCdrCobilling getCdrSelecionado() {
		return cdrSelecionado;
	}

	public void setCdrSelecionado(SccCdrCobilling cdrSelecionado) {
		this.cdrSelecionado = cdrSelecionado;
	}
	
	
	
	
	
}
