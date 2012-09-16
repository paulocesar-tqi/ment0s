package com.claro.sccweb.service.composite;

import java.io.Serializable;
import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;

/**
 * Como o repasse de pós pago basea-se , no banco de dados , em uma concatenação 
 * de String , é necessário decompor essa concatenação em outros objetos.
 *
 */
public class SolicitacaoRepassePosComposite implements Serializable {

	private SccOperadora operadoraExterna;
	private SccOperadora operadoraClaro;
	private SccProdutoCobilling produtoCobilling;
	private Date periodoInicio;
	private Date periodoFinal;
	private Long mesReferencia;
	private Long anoReferencia;
	private Date dataCriacao;
	private Date dataEvento;
	private String usuario;
	private String noRequisicao;
	
	public SccOperadora getOperadoraExterna() {
		return operadoraExterna;
	}
	public void setOperadoraExterna(SccOperadora operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}
	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public SccProdutoCobilling getProdutoCobilling() {
		return produtoCobilling;
	}
	public void setProdutoCobilling(SccProdutoCobilling produtoCobilling) {
		this.produtoCobilling = produtoCobilling;
	}
	public Date getPeriodoInicio() {
		return periodoInicio;
	}
	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public Date getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public Long getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(Long mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public Long getAnoReferencia() {
		return anoReferencia;
	}
	public void setAnoReferencia(Long anoReferencia) {
		this.anoReferencia = anoReferencia;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNoRequisicao() {
		return noRequisicao;
	}
	public void setNoRequisicao(String noRequisicao) {
		this.noRequisicao = noRequisicao;
	}
	
	
	
	
}
