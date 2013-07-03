package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;

public class SccFiltroControleArquivo {
	
	/**
	 * Status do Arquivo.
	 */
	private Long statusArquivo;
	
	/**
	 * Tipo de Arquivo
	 */
	private String tipoArquivo;
	
	/**
	 * Nome completo ou parte do nome do arquivo.
	 */
	private String nomeArquivo;
	
	/**
	 * Início do período de pesquisa para data de término do processamento do arquivo.
	 */
	private Date dataInicio;
	
	/**
	 * Final do período de pesquisa para data de término do processamento do arquivo.
	 */
	private Date dataFinal;
	
	private List<ControlConnectFile> listControlConnectFiles;

	public Long getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(Long statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<ControlConnectFile> getListControlConnectFiles() {
		return listControlConnectFiles;
	}

	public void setListControlConnectFiles(
			List<ControlConnectFile> listControlConnectFiles) {
		this.listControlConnectFiles = listControlConnectFiles;
	}
	

	
	

}
