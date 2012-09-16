package com.claro.sccweb.dto;

import java.util.Date;

public class SccParamProcessoDto {
	
	
	private String cdProcesso;
	private String nmParametro;
	private String cdTipoParametro;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String txValorParametro;
	
	public String getCdProcesso() {
		return cdProcesso;
	}
	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
	}
	public String getNmParametro() {
		return nmParametro;
	}
	public void setNmParametro(String nmParametro) {
		this.nmParametro = nmParametro;
	}
	public String getCdTipoParametro() {
		return cdTipoParametro;
	}
	public void setCdTipoParametro(String cdTipoParametro) {
		this.cdTipoParametro = cdTipoParametro;
	}
	public String getCdUsuarioManut() {
		return cdUsuarioManut;
	}
	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getTxValorParametro() {
		return txValorParametro;
	}
	public void setTxValorParametro(String txValorParametro) {
		this.txValorParametro = txValorParametro;
	}

	
	

}
