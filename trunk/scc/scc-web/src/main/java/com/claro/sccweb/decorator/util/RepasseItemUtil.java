package com.claro.sccweb.decorator.util;

import com.claro.cobillingweb.persistence.entity.SccItemRepasse;


/**
 * Traduz os items de repasse em termos de neg�cio.
 *
 */
public class RepasseItemUtil {

	public String getDescricaoItem(SccItemRepasse itemRepasse)
	{		
		switch (itemRepasse.getCdItemRepasse().intValue()) {
		case 20:return "1 - TOTAL DO VALOR ARRECADADO";			
		case 30:return "1.1 VALOR ARRECADADO CORRENTE";
		case 31:return "1.2 JUROS+MULTAS CORRENTE";
		case 32:return "1.3 VALOR ARRECADADO AP�S EXPURGO";
		case 33:return "1.4 JUROS+MULTAS AP�S 90 DIAS";
		case 34:return "1.5 VALOR ARRECADADO DE PARCELAMENTOS";
		case 28:return "2 - TOTAL DAS DEDU��ES";
		case 35:return "     2.1 CONTESTA��ES DE CLIENTES AP�S REPASSE";
		case 36:return "     2.2 REVERS�ES DE PAGAMENTO AP�S REPASSE";
		case 22:return "     2.3 REMUNERA��O DO SERVI�O OBJETO DO CONTRATO";
		case 37:return "          2.3.1 DETALHAMENTO DAS CHAMADAS FATURADAS";
		case 38:return "     2.4 REMUNERA��O DO SERVI�O EXTRA DE ADM. DE CART�ES (OPCIONAL)";
		case 39:return "     2.5 REMUNERA��O DO SERVI�O EXTRA DE COBRAN�A ESPECIAL (OPCIONAL)";
		
		default:return "### Item Desconhecido ###";			
		}		
	}
}
