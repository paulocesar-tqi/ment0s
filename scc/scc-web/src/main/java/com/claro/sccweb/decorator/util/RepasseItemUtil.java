package com.claro.sccweb.decorator.util;

import com.claro.cobillingweb.persistence.entity.SccItemRepasse;


/**
 * Traduz os items de repasse em termos de negócio.
 *
 */
public class RepasseItemUtil {

	public String getDescricaoItem(SccItemRepasse itemRepasse)
	{		
		switch (itemRepasse.getCdItemRepasse().intValue()) {
		case 20:return "1 - TOTAL DO VALOR ARRECADADO";			
		case 30:return "1.1 VALOR ARRECADADO CORRENTE";
		case 31:return "1.2 JUROS+MULTAS CORRENTE";
		case 32:return "1.3 VALOR ARRECADADO APÓS EXPURGO";
		case 33:return "1.4 JUROS+MULTAS APÓS 90 DIAS";
		case 34:return "1.5 VALOR ARRECADADO DE PARCELAMENTOS";
		case 28:return "2 - TOTAL DAS DEDUÇÕES";
		case 35:return "     2.1 CONTESTAÇÕES DE CLIENTES APÓS REPASSE";
		case 36:return "     2.2 REVERSÕES DE PAGAMENTO APÓS REPASSE";
		case 22:return "     2.3 REMUNERAÇÃO DO SERVIÇO OBJETO DO CONTRATO";
		case 37:return "          2.3.1 DETALHAMENTO DAS CHAMADAS FATURADAS";
		case 38:return "     2.4 REMUNERAÇÃO DO SERVIÇO EXTRA DE ADM. DE CARTÕES (OPCIONAL)";
		case 39:return "     2.5 REMUNERAÇÃO DO SERVIÇO EXTRA DE COBRANÇA ESPECIAL (OPCIONAL)";
		
		default:return "### Item Desconhecido ###";			
		}		
	}
}
