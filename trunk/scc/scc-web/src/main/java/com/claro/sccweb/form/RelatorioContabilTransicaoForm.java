package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContabilTransicao;

public class RelatorioContabilTransicaoForm extends BaseForm {

	private Long cdTipoArquivo;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeArquivo;
	private String erro;
	
	private String diretorio;
	
	private SccArquivoCobilling entity;
	
	private SccFiltroContabilTransicao filtro = new SccFiltroContabilTransicao();
	
	private List<SccArquivoCobilling> listArquivoCobilling;
	
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
	public SccFiltroContabilTransicao getFiltro() {
		return filtro;
	}
	public void setFiltro(SccFiltroContabilTransicao filtro) {
		this.filtro = filtro;
	}
	
	public SccArquivoCobilling getEntity() {
		return entity;
	}
	public void setEntity(SccArquivoCobilling entity) {
		this.entity = entity;
	}
	public List<SccArquivoCobilling> getListArquivoCobilling() {
		return listArquivoCobilling;
	}
	public void setListArquivoCobilling(List<SccArquivoCobilling> listArquivoCobilling) {
		this.listArquivoCobilling = listArquivoCobilling;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	
	
	
	
}
