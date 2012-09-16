package com.claro.sccweb.form;


/**
 * Form para a tela de Relatório e Ajuste de Repasse Pós Pago.
 *
 */
public class AjusteRepassePosForm {

	private String operacao;
	
	private Long cdItemRepasse;
	
	private String modificador;
	
	private Long nuRepasse;
	
	private String titulo;
	
	private String dsItemRepasse;
	
	private String observacao;
	
	private String creditoDebito;
	
	private String valor;

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Long getCdItemRepasse() {
		return cdItemRepasse;
	}

	public void setCdItemRepasse(Long cdItemRepasse) {
		this.cdItemRepasse = cdItemRepasse;
	}

	public String getModificador() {
		return modificador;
	}

	public void setModificador(String modificador) {
		this.modificador = modificador;
	}

	public Long getNuRepasse() {
		return nuRepasse;
	}

	public void setNuRepasse(Long nuRepasse) {
		this.nuRepasse = nuRepasse;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDsItemRepasse() {
		return dsItemRepasse;
	}

	public void setDsItemRepasse(String dsItemRepasse) {
		this.dsItemRepasse = dsItemRepasse;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCreditoDebito() {
		return creditoDebito;
	}

	public void setCreditoDebito(String creditoDebito) {
		this.creditoDebito = creditoDebito;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
