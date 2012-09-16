package com.claro.sccweb.form;

import java.util.Date;

public class RelatorioContabilTransicaoForm extends BaseForm {

	private Long cdTipoArquivo;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeArquivo;
	private String erro;
	
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
	
	
	
}
