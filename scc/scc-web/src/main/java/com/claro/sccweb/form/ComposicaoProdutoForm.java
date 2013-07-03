package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;

public class ComposicaoProdutoForm extends BaseForm {

	private SccComposicaoProduto entity;
	
	private Long cdProdutoCobilling;
	
	private SccProdutoCobilling produtoCobilling;
	
	private List<SccComposicaoProduto> lstComposicaoProduto;

	public SccComposicaoProduto getEntity() {
		return entity;
	}

	public void setEntity(SccComposicaoProduto entity) {
		this.entity = entity;
	}

	public SccProdutoCobilling getProdutoCobilling() {
		return produtoCobilling;
	}

	public void setProdutoCobilling(SccProdutoCobilling produtoCobilling) {
		this.produtoCobilling = produtoCobilling;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public List<SccComposicaoProduto> getLstComposicaoProduto() {
		return lstComposicaoProduto;
	}

	public void setLstComposicaoProduto(
			List<SccComposicaoProduto> lstComposicaoProduto) {
		this.lstComposicaoProduto = lstComposicaoProduto;
	}
	
	
	
}
