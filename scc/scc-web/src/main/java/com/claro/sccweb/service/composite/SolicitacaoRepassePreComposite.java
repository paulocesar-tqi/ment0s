package com.claro.sccweb.service.composite;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

public class SolicitacaoRepassePreComposite {

	private String nmParametro;
	
	private String periodo;
	
	private SccOperadora operadoraClaro;
	
	private SccOperadora operadoraLD;
	
	private String criterio;
	
	private SccProdutoPrepago produtoPrepago;
	
	private Date dtCriacao;
	
	private Date dtEvento;
	
	private String usuario;

	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}

	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getNmParametro() {
		return nmParametro;
	}

	public void setNmParametro(String nmParametro) {
		this.nmParametro = nmParametro;
	}
	
	
}
