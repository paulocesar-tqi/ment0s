package com.claro.sccweb.service.to;

import java.util.Date;

public class SolicitacaoVumTO {

	private Date dataInicioRepasse;
	private Date dataFimRepasse;
	private String cdEOTLD;
	private String plataforma;
	private String tipoArquivo;
	private String usuario;
	
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
