package com.claro.sccweb.controller.util;

/**
 * Bean utilizado para popular combos genéricos não associados
 * a entidades/tabelas.
 *
 */
public class BasicStringItem {
	
	public BasicStringItem(String key,String label)
	{
		this.key = key;
		this.label = label;
	}

	private String key;
	
	private String label;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
}
