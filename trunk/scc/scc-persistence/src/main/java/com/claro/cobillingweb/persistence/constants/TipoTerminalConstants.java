package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class TipoTerminalConstants {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	static{
		map.put("M", "MÓVEL");
		map.put("F", "FIXO");
		
	}
	
	public static String getLabel(String pk) {
		return map.get(pk);
	}
	
}
