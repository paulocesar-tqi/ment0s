package com.claro.sccweb.form;

import java.io.Serializable;

public class BaseForm {

	/**
	 * Operação que o usuário deseja executar.
	 */
	private String operacao;
	
	/**
	 * Em caso de telas com tabelas esse campo informará qual foi a linha selecionada.
	 */
	private Integer itemSelecionado;
	private Object entidadeSelecionada;
	private Serializable pkEntidadeSelecionada;
	private String mensagem;
	private String errosValidacao = "N";
	private String activeTab = "0";

	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public Integer getItemSelecionado() {
		return itemSelecionado;
	}
	public void setItemSelecionado(Integer itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}
	
	public Object getEntidadeSelecionada() {
		return entidadeSelecionada;
	}
	public void setEntidadeSelecionada(Object entidadeSelecionada) {
		this.entidadeSelecionada = entidadeSelecionada;
	}
	
	public Serializable getPkEntidadeSelecionada() {
		return pkEntidadeSelecionada;
	}
	public void setPkEntidadeSelecionada(Serializable pkEntidadeSelecionada) {
		this.pkEntidadeSelecionada = pkEntidadeSelecionada;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getErrosValidacao() {
		return errosValidacao;
	}
	public void setErrosValidacao(String errosValidacao) {
		this.errosValidacao = errosValidacao;
	}
	
	public String getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
}
