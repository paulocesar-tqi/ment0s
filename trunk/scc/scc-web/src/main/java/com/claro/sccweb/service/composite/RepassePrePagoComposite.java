package com.claro.sccweb.service.composite;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.decorator.BasicSccDecorator;

/**
 * Composite para {@link SccPreFechamento}.
 *
 */
public class RepassePrePagoComposite extends BasicSccDecorator {

	private SccOperadora operadoraLD;
	
	private SccOperadora operadoraClaro;
	
	private SccProdutoPrepago produtoPrepago;
	
	private Double valorRepasse;
	
	private String status;
	
	private String periodo;
	
	private Long sqPreFechamento;
	
	private SccPreFechamento preFechamento;

	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public SccOperadora getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public Double getValorRepasse() {
		return valorRepasse;
	}

	public void setValorRepasse(Double valorRepasse) {
		this.valorRepasse = valorRepasse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSqPreFechamento() {
		return sqPreFechamento;
	}

	public void setSqPreFechamento(Long sqPreFechamento) {
		this.sqPreFechamento = sqPreFechamento;
	}

	public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}

	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public SccPreFechamento getPreFechamento() {
		return preFechamento;
	}

	public void setPreFechamento(SccPreFechamento preFechamento) {
		this.preFechamento = preFechamento;
	}
	
	public String getValorRepasseString()
	{
		return formataDouble(valorRepasse);
	}
	
}
