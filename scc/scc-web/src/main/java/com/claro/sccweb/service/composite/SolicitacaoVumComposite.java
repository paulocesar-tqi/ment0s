package com.claro.sccweb.service.composite;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.service.to.SolicitacaoVumTO;

public class SolicitacaoVumComposite extends SolicitacaoVumTO {

	private String nmParametro;
	private String vlParametro;
	private SccOperadora operadoraLD;
	private Date dtCriacao;
	private Date dtEvento;
	
	public String getNmParametro() {
		return nmParametro;
	}
	public void setNmParametro(String nmParametro) {
		this.nmParametro = nmParametro;
	}
	
	public String getVlParametro() {
		return vlParametro;
	}
	public void setVlParametro(String vlParametro) {
		this.vlParametro = vlParametro;
	}
	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Date getDtEvento() {
		return dtEvento;
	}
	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}
	
	
}
