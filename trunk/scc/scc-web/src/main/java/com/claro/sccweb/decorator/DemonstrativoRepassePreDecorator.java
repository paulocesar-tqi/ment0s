package com.claro.sccweb.decorator;

import java.util.ArrayList;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

public class DemonstrativoRepassePreDecorator {

	private boolean holding;
	
	private SccOperadora operadora;
	
	private SccOperadora operadoraLD;
	
	private SccProdutoPrepago produtoPrepago;
	
	private String periodo;
	
	/**
	 * Demonstrativos das operadoras que fazem parte da holding.	  
	 */
	private List<DemonstrativoRepassePreDecorator> demonstrativosOperadorasHolding = new ArrayList<DemonstrativoRepassePreDecorator>();
	
	private List<DemonstrativoRepassePreItemDecorator> itens = new ArrayList<DemonstrativoRepassePreItemDecorator>();

	public boolean isHolding() {
		return holding;
	}

	public void setHolding(boolean holding) {
		this.holding = holding;
	}


	public List<DemonstrativoRepassePreItemDecorator> getItens() {
		return itens;
	}

	public void setItens(List<DemonstrativoRepassePreItemDecorator> itens) {
		this.itens = itens;
	}

	public List<DemonstrativoRepassePreDecorator> getDemonstrativosOperadorasHolding() {
		return demonstrativosOperadorasHolding;
	}

	public void setDemonstrativosOperadorasHolding(
			List<DemonstrativoRepassePreDecorator> demonstrativosOperadorasHolding) {
		this.demonstrativosOperadorasHolding = demonstrativosOperadorasHolding;
	}

	public SccOperadora getOperadora() {
		return operadora;
	}

	public void setOperadora(SccOperadora operadora) {
		this.operadora = operadora;
	}

	public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}

	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
	}

	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
	
	
}
