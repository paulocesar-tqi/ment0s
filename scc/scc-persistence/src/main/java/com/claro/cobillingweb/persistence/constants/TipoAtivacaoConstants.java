package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class TipoAtivacaoConstants {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("U", "USO");
		map.put("R", "RECORRENTE");
	}
	
	public static String getLabel(String pk) {
		return map.get(pk);
	}
	
}
