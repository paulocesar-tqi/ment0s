package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class FormaRepasseConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(1L, "Repasse por Percentual de Valor Bruto");
		map.put(2L, "Repasse por Percentual de Valor Líquido");
		map.put(3L, "Repasse por Valor Fixo");
		map.put(4L, "Repasse por Quantidade de CDRs");
		map.put(5L, "Repasse por Minuto Tarifado");
	}
	
	public static String getFormaRepasse(Long pk) {
		return map.get(pk);
	}
	
}
