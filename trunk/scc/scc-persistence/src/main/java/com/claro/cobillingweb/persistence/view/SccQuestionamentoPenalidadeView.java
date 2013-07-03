package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;

public class SccQuestionamentoPenalidadeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7635776239789860196L;
	
	
    private String cdEotClaro = "";
    private String cdEotLD = "";
    
    private String cdPosicao = "";
    private String txComentarioMotivo = "";
    private String dsMotivoRejeicao = "";

    private Long   qtCdrs = 0l;
    private Double qtMinutos = 0.0;
    private Double vlLiquido = 0.0;
    private Double vlRefaturada = 0.0;
    private Double vlNaoRefaturada = 0.0;
    private Double vlPis = 0.0;
    private Double vlCofins = 0.0;
    private Double vlIcms = 0.0;
    private Double vlIss = 0.0;
    private Double vlBruto = 0.0;
    private String inResultadoAnalise = ""; 
    
    private Long   qtCdrsRefaturadas = 0l;
    private Double qtMinutosRefaturadas = 0.0;
    private Long   qtCdrsNaoRefaturadas = 0l;
    private Double qtMinutosNaoRefaturadas = 0.0;
    private Double vlPenalidade = 0.0;
    
    private Long position = 0l;

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public String getCdEotLD() {
		return cdEotLD;
	}

	public void setCdEotLD(String cdEotLD) {
		this.cdEotLD = cdEotLD;
	}

	public String getCdPosicao() {
		return cdPosicao;
	}

	public void setCdPosicao(String cdPosicao) {
		this.cdPosicao = cdPosicao;
	}

	public String getTxComentarioMotivo() {
		return txComentarioMotivo;
	}

	public void setTxComentarioMotivo(String txComentarioMotivo) {
		this.txComentarioMotivo = txComentarioMotivo;
	}

	public String getDsMotivoRejeicao() {
		return dsMotivoRejeicao;
	}

	public void setDsMotivoRejeicao(String dsMotivoRejeicao) {
		this.dsMotivoRejeicao = dsMotivoRejeicao;
	}

	public Long getQtCdrs() {
		return qtCdrs;
	}

	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}

	public Double getQtMinutos() {
		return qtMinutos;
	}

	public void setQtMinutos(Double qtMinutos) {
		this.qtMinutos = qtMinutos;
	}

	public Double getVlLiquido() {
		return vlLiquido;
	}

	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}

	public Double getVlRefaturada() {
		return vlRefaturada;
	}

	public void setVlRefaturada(Double vlRefaturada) {
		this.vlRefaturada = vlRefaturada;
	}

	public Double getVlNaoRefaturada() {
		return vlNaoRefaturada;
	}

	public void setVlNaoRefaturada(Double vlNaoRefaturada) {
		this.vlNaoRefaturada = vlNaoRefaturada;
	}

	public Double getVlPis() {
		return vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}

	public Double getVlCofins() {
		return vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}

	public Double getVlIcms() {
		return vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}

	public Double getVlIss() {
		return vlIss;
	}

	public void setVlIss(Double vlIss) {
		this.vlIss = vlIss;
	}

	public Double getVlBruto() {
		return vlBruto;
	}

	public void setVlBruto(Double vlBruto) {
		this.vlBruto = vlBruto;
	}

	public String getInResultadoAnalise() {
		return inResultadoAnalise;
	}

	public void setInResultadoAnalise(String inResultadoAnalise) {
		this.inResultadoAnalise = inResultadoAnalise;
	}

	public Long getQtCdrsRefaturadas() {
		return qtCdrsRefaturadas;
	}

	public void setQtCdrsRefaturadas(Long qtCdrsRefaturadas) {
		this.qtCdrsRefaturadas = qtCdrsRefaturadas;
	}

	public Double getQtMinutosRefaturadas() {
		return qtMinutosRefaturadas;
	}

	public void setQtMinutosRefaturadas(Double qtMinutosRefaturadas) {
		this.qtMinutosRefaturadas = qtMinutosRefaturadas;
	}

	public Long getQtCdrsNaoRefaturadas() {
		return qtCdrsNaoRefaturadas;
	}

	public void setQtCdrsNaoRefaturadas(Long qtCdrsNaoRefaturadas) {
		this.qtCdrsNaoRefaturadas = qtCdrsNaoRefaturadas;
	}

	public Double getQtMinutosNaoRefaturadas() {
		return qtMinutosNaoRefaturadas;
	}

	public void setQtMinutosNaoRefaturadas(Double qtMinutosNaoRefaturadas) {
		this.qtMinutosNaoRefaturadas = qtMinutosNaoRefaturadas;
	}

	public Double getVlPenalidade() {
		return vlPenalidade;
	}

	public void setVlPenalidade(Double vlPenalidade) {
		this.vlPenalidade = vlPenalidade;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

    
    
    

}
