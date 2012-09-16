package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class TipoFranquiaConstants {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("1", "Franquia");
		map.put("2", "Excedente");
		map.put("3", "Fronteira");		
	}
	
	public static String getLabel(String pk) {
		return map.get(pk);
	}
	
}
