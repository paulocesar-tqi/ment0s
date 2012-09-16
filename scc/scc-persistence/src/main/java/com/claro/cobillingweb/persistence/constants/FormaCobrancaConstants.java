package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class FormaCobrancaConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(1L, "Cobran�a por Percentual de Valor Bruto");
		map.put(2L, "Cobran�a por Percentual de Valor L�quido");
		map.put(3L, "Cobran�a por Valor Fixo");
		map.put(4L, "Cobran�a por Quantidade de CDRs");
		map.put(5L, "Cobran�a por Minuto Tarifado");
	}
	
	public static String getFormaCobranca(Long pk) {
		return map.get(pk);
	}
	
}
