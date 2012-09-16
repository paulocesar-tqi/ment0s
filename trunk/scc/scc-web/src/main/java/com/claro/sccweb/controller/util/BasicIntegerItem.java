package com.claro.sccweb.controller.util;

public class BasicIntegerItem {

	private Long key;
	private String label;
	
	public BasicIntegerItem(Long key,String label){
		this.key = key;
		this.label = label;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
