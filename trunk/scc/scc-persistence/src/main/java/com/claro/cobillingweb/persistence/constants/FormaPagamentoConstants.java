package com.claro.cobillingweb.persistence.constants;

import java.util.HashMap;
import java.util.Map;

public class FormaPagamentoConstants {
	
	private static Map<Long, String> map = new HashMap<Long, String>();
	
	static {
		map.put(1L, "Dinheiro");
		map.put(2L, "Cheque");
		map.put(3L, "Boleto");
		map.put(4L, "Tranfêrencia Bancaria");
		map.put(5L, "Conciliação de Conta");
	}
	
	public static String getFormaPagamento(Long pk) {
		return map.get(pk);
	}
	
}
