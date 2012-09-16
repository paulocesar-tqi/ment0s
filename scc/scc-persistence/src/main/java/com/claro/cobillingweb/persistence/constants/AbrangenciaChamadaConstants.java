package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class AbrangenciaChamadaConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(1L, "Chamada Nacional");
		map.put(2L, "Chamada Internacional");
		map.put(3L, "Chamada Local");
	}
	
	public static String getLabel(Long pk) {
		return map.get(pk);
	}
	
}
