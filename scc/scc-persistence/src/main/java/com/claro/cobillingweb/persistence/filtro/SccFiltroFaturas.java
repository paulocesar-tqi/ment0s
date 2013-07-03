/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;
import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccFaturaView;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroFaturas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998903007295178047L;
	
	
	private SccFaturaView entity;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private Long tipoData;
	
	private String status;
	
	private String numeroFatura;
	
	private String relatorioSelecionado ;
	
	private String operadoraClaro;
	
	private String cdCsp;
	
	private String statusFatura;
	
	private String cdEOTClaro;

	public SccFaturaView getEntity() {
		return entity;
	}

	public void setEntity(SccFaturaView entity) {
		this.entity = entity;
	}

	public Date getDataInicialPeriodo() {
		return dataInicialPeriodo;
	}

	public void setDataInicialPeriodo(Date dataInicialPeriodo) {
		this.dataInicialPeriodo = dataInicialPeriodo;
	}

	public Date getDataFinalPeriodo() {
		return dataFinalPeriodo;
	}

	public void setDataFinalPeriodo(Date dataFinalPeriodo) {
		this.dataFinalPeriodo = dataFinalPeriodo;
	}

	public Long getTipoData() {
		return tipoData;
	}

	public void setTipoData(Long tipoData) {
		this.tipoData = tipoData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(String relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getCdCsp() {
		return cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}

	public String getStatusFatura() {
		return statusFatura;
	}

	public void setStatusFatura(String statusFatura) {
		this.statusFatura = statusFatura;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	
	

	

}
