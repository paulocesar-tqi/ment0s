package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class TipoStatusConstants {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("A", "Ativa");
		map.put("C", "Cancelada");
	}
	
	public static String getLabel(String pk) {
		return map.get(pk);
	}
	
}
