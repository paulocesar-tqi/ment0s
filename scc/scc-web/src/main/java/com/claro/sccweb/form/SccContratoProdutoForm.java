package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.cobillingweb.persistence.view.SccProdutoContratadoView;
import com.claro.sccweb.decorator.rownum.entity.SccContratoProduto2Decorator;

public class SccContratoProdutoForm extends BaseForm {
	
	private String cdEotLd;
	
	private String cdEotClaro;
	
	private List<SccContratoCobillingSearchView> listContratoProdutos;
	
	private SccContratoProduto2Decorator entity;

	private List<SccProdutoContratadoView> listProdutosContratados;
	
	private Long cdProdutoCobilling;
	private String inCategoriaEvento;

	
	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public List<SccContratoCobillingSearchView> getListContratoProdutos() {
		return listContratoProdutos;
	}

	public void setListContratoProdutos(
			List<SccContratoCobillingSearchView> listContratoProdutos) {
		this.listContratoProdutos = listContratoProdutos;
	}


	public SccContratoProduto2Decorator getEntity() {
		return entity;
	}

	public void setEntity(SccContratoProduto2Decorator entity) {
		this.entity = entity;
	}

	public List<SccProdutoContratadoView> getListProdutosContratados() {
		return listProdutosContratados;
	}

	public void setListProdutosContratados(
			List<SccProdutoContratadoView> listProdutosContratados) {
		this.listProdutosContratados = listProdutosContratados;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public String getInCategoriaEvento() {
		return inCategoriaEvento;
	}

	public void setInCategoriaEvento(String inCategoriaEvento) {
		this.inCategoriaEvento = inCategoriaEvento;
	}



}
