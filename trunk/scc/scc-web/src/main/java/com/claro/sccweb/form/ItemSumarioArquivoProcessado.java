package com.claro.sccweb.form;


/**
 * Representa uma linha de informa��o em uma tabela de sum�rio.
 *
 */
public class ItemSumarioArquivoProcessado {

	/**
	 * Representa a chave de agrupamento dos CDRs.	 
	 */
	private Object key; 
	
	/**
	 * Descri��o desse item. 
	 */
	private String descricaoItem;
	
	
	
	/**
	 * Totalizador. Representa a quantidade de CDRs no arquivo que encontra-se agrupado pela key.
	 */
	private Long total;

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
