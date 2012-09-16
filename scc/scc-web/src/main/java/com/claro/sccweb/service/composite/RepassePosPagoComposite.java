package com.claro.sccweb.service.composite;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRepasse;

public class RepassePosPagoComposite {

	private String tipoContrato;
	
	private Long nuRepasse;
	
	/**
	 * Status do repasse.
	 */
	private String statusRepasse;
	
	/**
	 * Como no demonstrativo : 5 - BASE DE CÁLCULO DO REPASSE (1 - 2 + 3 + 4 )
	 */
	private Double valorBrutoRepasse;
	
	/**
	 * Operadora Claro.
	 */
	private SccOperadora operadoraClaro;
	
	/**
	 * Operadora Longa Distância.
	 */
	private SccOperadora operadoraLD;
	
	/**
	 * Produto de cobilling associada ao repasse.
	 */
	private SccProdutoCobilling produtoCobilling;
	
	/**
	 * Items de repasse indexados por tipo de item.
	 */
	private Map<SccItemRepasse, SccRepasse> items = new HashMap<SccItemRepasse, SccRepasse>();

	/**
	 * Data inicial do repasse.
	 */
	private Date dtInicialRepasse;
	
	/**
	 * Periodicidade do repasse.
	 */
	private SccPeriodicidadeRepasse periodicidadeRepasse;
		

	public Double getValorBrutoRepasse() {
		return valorBrutoRepasse;
	}

	public void setValorBrutoRepasse(Double valorBrutoRepasse) {
		this.valorBrutoRepasse = valorBrutoRepasse;
	}

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

	public Map<SccItemRepasse, SccRepasse> getItems() {
		return items;
	}

	public void setItems(Map<SccItemRepasse, SccRepasse> items) {
		this.items = items;
	}

	public SccProdutoCobilling getProdutoCobilling() {
		return produtoCobilling;
	}

	public void setProdutoCobilling(SccProdutoCobilling produtoCobilling) {
		this.produtoCobilling = produtoCobilling;
	}

	public Date getDtInicialRepasse() {
		return dtInicialRepasse;
	}

	public void setDtInicialRepasse(Date dtInicialRepasse) {
		this.dtInicialRepasse = dtInicialRepasse;
	}

	public SccPeriodicidadeRepasse getPeriodicidadeRepasse() {
		return periodicidadeRepasse;
	}

	public void setPeriodicidadeRepasse(SccPeriodicidadeRepasse periodicidadeRepasse) {
		this.periodicidadeRepasse = periodicidadeRepasse;
	}

	public String getStatusRepasse() {
		return statusRepasse;
	}

	public void setStatusRepasse(String statusRepasse) {
		this.statusRepasse = statusRepasse;
	}

	public Long getNuRepasse() {
		return nuRepasse;
	}

	public void setNuRepasse(Long nuRepasse) {
		this.nuRepasse = nuRepasse;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	
	
}
