package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class FormaCobrancaConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(1L, "Cobrança por Percentual de Valor Bruto");
		map.put(2L, "Cobrança por Percentual de Valor Líquido");
		map.put(3L, "Cobrança por Valor Fixo");
		map.put(4L, "Cobrança por Quantidade de CDRs");
		map.put(5L, "Cobrança por Minuto Tarifado");
	}
	
	public static String getFormaCobranca(Long pk) {
		return map.get(pk);
	}
	
}
