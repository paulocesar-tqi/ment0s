package com.claro.sccweb.form;

import java.util.Date;

public class CadastroSolicitacaoVUMForm extends BaseForm {

	private String nmParam;
	private Date dataInicioRepasse;
	private Date dataFimRepasse;
	private String cdEOTLD;
	private String plataforma;
	private String tipoArquivo;
	private String nomeDiretorio;
	private String nomeArquivo;
	private String erro;
	
	public String getNmParam() {
		return nmParam;
	}
	public void setNmParam(String nmParam) {
		this.nmParam = nmParam;
	}
	public Date getDataInicioRepasse() {
		return dataInicioRepasse;
	}
	public void setDataInicioRepasse(Date dataInicioRepasse) {
		this.dataInicioRepasse = dataInicioRepasse;
	}
	public Date getDataFimRepasse() {
		return dataFimRepasse;
	}
	public void setDataFimRepasse(Date dataFimRepasse) {
		this.dataFimRepasse = dataFimRepasse;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public String getNomeDiretorio() {
		return nomeDiretorio;
	}
	public void setNomeDiretorio(String nomeDiretorio) {
		this.nomeDiretorio = nomeDiretorio;
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
