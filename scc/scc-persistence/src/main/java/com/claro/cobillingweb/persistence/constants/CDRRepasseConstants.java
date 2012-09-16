package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class CDRRepasseConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(21L, "Faturado no Mobile");
		map.put(6L, "Encaminhado para Mobile");
		map.put(70L, "Chamada Arrecadada");		
	}
	
	public static String getCDRRepasse(Long pk) {
		return map.get(pk);
	}
	
}
