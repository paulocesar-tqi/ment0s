package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;


/**
 * Permite a pesquisa de arquivos de cobilling utilizando m�ltiplos valores (IN).
 *
 */
public class SCCArquivoCobillingFiltroMulti {

	/**
	 * Grrupo de status desejados.
	 */
	public List<String> status;
	
	/**
	 * Grupo de tipos desejados.
	 */
	public List<Long> tiposArquivo;
	
	/**
	 * Tipo de per�odo (Data de Refer�ncia ou Processamento Claro).
	 */
	private String tipoPeriodo;
	
	/**
	 * Data inicial para a pequisa de acordo com o tipo de per�odo.
	 */
	private Date dataInicialPeriodo;
	
	/**
	 * Data final para a pequisa de acordo com o tipo de per�odo.
	 */
	private Date dataFinalPeriodo;

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	

	public List<Long> getTiposArquivo() {
		return tiposArquivo;
	}

	public void setTiposArquivo(List<Long> tiposArquivo) {
		this.tiposArquivo = tiposArquivo;
	}

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
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
	
	
	
	
}
