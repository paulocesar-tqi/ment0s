/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccExtracaoCDRsView {

	private String numeroDeA;
	private String numeroDeB;
	private Double duracaoTarifada;
	private Date dtChamada;
	private String statusChamada;
	private String motivoRejeicao;

	public String getNumeroDeB() {
		return numeroDeB;
	}
	public void setNumeroDeB(String numeroDeB) {
		this.numeroDeB = numeroDeB;
	}
	public String getNumeroDeA() {
		return numeroDeA;
	}
	public void setNumeroDeA(String numeroDeA) {
		this.numeroDeA = numeroDeA;
	}
	public Double getDuracaoTarifada() {
		return duracaoTarifada;
	}
	public void setDuracaoTarifada(Double duracaoTarifada) {
		this.duracaoTarifada = duracaoTarifada;
	}
	public Date getDtChamada() {
		return dtChamada;
	}
	public void setDtChamada(Date dtChamada) {
		this.dtChamada = dtChamada;
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
}
