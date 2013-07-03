/**
 * 
 */
package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;

/**
 * @author vagner.souza
 *
 */
public class SccFiltroContabilTransicao {
	
	private Long cdTipoArquivo;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeArquivo;
	private String erro;

	private SccArquivoCobilling entity;

	public Long getCdTipoArquivo() {
		return cdTipoArquivo;
	}

	public void setCdTipoArquivo(Long cdTipoArquivo) {
		this.cdTipoArquivo = cdTipoArquivo;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public SccArquivoCobilling getEntity() {
		return entity;
	}

	public void setEntity(SccArquivoCobilling entity) {
		this.entity = entity;
	}
	
	
	

}
