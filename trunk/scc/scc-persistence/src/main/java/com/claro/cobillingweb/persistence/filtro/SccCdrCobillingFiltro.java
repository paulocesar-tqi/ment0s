package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;
import java.util.List;


/**
 * Filtro para anteder as pesquisas mais genéricas de CDRs.
 *
 */
public class SccCdrCobillingFiltro implements Filtro {

	private Long seqArquivo;
	
	private List<Long> statusCDR;
	
	private Date dataChamada;

	public Long getSeqArquivo() {
		return seqArquivo;
	}

	public void setSeqArquivo(Long seqArquivo) {
		this.seqArquivo = seqArquivo;
	}

	public List<Long> getStatusCDR() {
		return statusCDR;
	}

	public void setStatusCDR(List<Long> statusCDR) {
		this.statusCDR = statusCDR;
	}

	public Date getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}
	
	
	
	
	
}
