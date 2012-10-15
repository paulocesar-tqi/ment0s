package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioEventosView {
	
	private Date dtReferencia;
	private String dsOperadoraClaro;
	private String dsOperadoraExterna;
	private String statusChamada;
	private String motivoRejeicao;
	private String dsDefeito;
	private Double quantidadeCdrs;
	private Double duracaoReal;
	private Double duracaoTarifada;
	private Double valorBruto;
	
	public Date getDtReferencia() {
		return dtReferencia;
	}
	public void setDtReferencia(Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
	public String getDsOperadoraClaro() {
		return dsOperadoraClaro;
	}
	public void setDsOperadoraClaro(String dsOperadoraClaro) {
		this.dsOperadoraClaro = dsOperadoraClaro;
	}
	public String getDsOperadoraExterna() {
		return dsOperadoraExterna;
	}
	public void setDsOperadoraExterna(String dsOperadoraExterna) {
		this.dsOperadoraExterna = dsOperadoraExterna;
	}
	public String getStatusChamada() {
		return statusChamada;
	}
	public void setStatusChamada(String statusChamada) {
		this.statusChamada = statusChamada;
	}
	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}
	public String getDsDefeito() {
		return dsDefeito;
	}
	public void setDsDefeito(String dsDefeito) {
		this.dsDefeito = dsDefeito;
	}
	public Double getQuantidadeCdrs() {
		return quantidadeCdrs;
	}
	public void setQuantidadeCdrs(Double quantidadeCdrs) {
		this.quantidadeCdrs = quantidadeCdrs;
	}
	public Double getDuracaoReal() {
		return duracaoReal;
	}
	public void setDuracaoReal(Double duracaoReal) {
		this.duracaoReal = duracaoReal;
	}
	public Double getDuracaoTarifada() {
		return duracaoTarifada;
	}
	public void setDuracaoTarifada(Double duracaoTarifada) {
		this.duracaoTarifada = duracaoTarifada;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

		
}
