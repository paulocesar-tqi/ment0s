package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92038883
 *
 */

public class SccRelatorioChamadasRefaturamentoView {

	private String numeroA;
	private String numeroB;
	private Date dataHora;
	private Double valor;
	private Double minutoTarifado;
	private String codigoCriticaInicial;
	private Integer arquivoRemessa;
	private Integer arquivoRetorno;
	private Integer arquivoRemessaRefaturamento;
	
	public String getNumeroA() {
		return numeroA;
	}
	public void setNumeroA(String numeroA) {
		this.numeroA = numeroA;
	}
	public String getNumeroB() {
		return numeroB;
	}
	public void setNumeroB(String numeroB) {
		this.numeroB = numeroB;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getMinutoTarifado() {
		return minutoTarifado;
	}
	public void setMinutoTarifado(Double minutoTarifado) {
		this.minutoTarifado = minutoTarifado;
	}
	public String getCodigoCriticaInicial() {
		return codigoCriticaInicial;
	}
	public void setCodigoCriticaInicial(String codigoCriticaInicial) {
		this.codigoCriticaInicial = codigoCriticaInicial;
	}
	public Integer getArquivoRemessa() {
		return arquivoRemessa;
	}
	public void setArquivoRemessa(Integer arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}
	public Integer getArquivoRetorno() {
		return arquivoRetorno;
	}
	public void setArquivoRetorno(Integer arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}
	public Integer getArquivoRemessaRefaturamento() {
		return arquivoRemessaRefaturamento;
	}
	public void setArquivoRemessaRefaturamento(Integer arquivoRemessaRefaturamento) {
		this.arquivoRemessaRefaturamento = arquivoRemessaRefaturamento;
	}
	
	
}
