/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;

import com.claro.cobillingweb.persistence.dao.BasicDAO;

/**
 * @author 93046251
 *
 */
public class SccFiltro {
	
	/**
	 * Código da operadora CLARO destinatária do arquivo.
	 */
	private String operadoraClaro;
	
	/**
	 * Tipo de operadora (Holding ou Operadora).
	 */
	private String tipoOperadora;

	/**
	 * Código da operadora LD que gerou o arquivo.
	 */
	private String operadoraExterna;
	
	/**
	 * Grupo de status do arquivo.
	 */
	private Long cdGrupoStatusArquivo;
	
	/**
	 * Status do arquivo.
	 */
	private String statusArquivo = BasicDAO.GET_ALL_STRING;
	
	/**
	 * Tipo de arquivo.
	 */
	private Long tipoArquivo = BasicDAO.GET_ALL;
	
	/**
	 * Tipo de período (Data de Referência ou Processamento Claro).
	 */
	private String tipoPeriodo;
	
	/**
	 * Data inicial para a pequisa de acordo com o tipo de período.
	 */
	private Date dataInicialPeriodo;
	
	/**
	 * Data final para a pequisa de acordo com o tipo de período.
	 */
	private Date dataFinalPeriodo;
	
	/**
	 * Campo CD_ERRO_PROTOCOLO. Essa pesquisa pode usar {@link BasicDAO} GET_ALL_STRING para indicar todos as situações,
	 * OK para indicar 'OK'/NULO e NOK para indicar codigos <> 'OK'.
	 *
	 */
	private String cdErroProtocolo;
	
	/**
	 * Tipo de batimento do arquivo.
	 */
	private String cdTipoBatimento = BasicDAO.GET_ALL_STRING;

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getTipoOperadora() {
		return tipoOperadora;
	}

	public void setTipoOperadora(String tipoOperadora) {
		this.tipoOperadora = tipoOperadora;
	}

	public String getOperadoraExterna() {
		return operadoraExterna;
	}

	public void setOperadoraExterna(String operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}

	public Long getCdGrupoStatusArquivo() {
		return cdGrupoStatusArquivo;
	}

	public void setCdGrupoStatusArquivo(Long cdGrupoStatusArquivo) {
		this.cdGrupoStatusArquivo = cdGrupoStatusArquivo;
	}

	public String getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(String statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public Long getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(Long tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
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

	public String getCdErroProtocolo() {
		return cdErroProtocolo;
	}

	public void setCdErroProtocolo(String cdErroProtocolo) {
		this.cdErroProtocolo = cdErroProtocolo;
	}

	public String getCdTipoBatimento() {
		return cdTipoBatimento;
	}

	public void setCdTipoBatimento(String cdTipoBatimento) {
		this.cdTipoBatimento = cdTipoBatimento;
	}
	

	
	

}
