package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class TipoCentroConstants {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("NE", "Nenhum");
		map.put("CC", "Centro de Custo");
		map.put("CL", "Centro de Lucro");
	}
	
	public static String getLabel(String pk) {
		return map.get(pk);
	}
	
}
