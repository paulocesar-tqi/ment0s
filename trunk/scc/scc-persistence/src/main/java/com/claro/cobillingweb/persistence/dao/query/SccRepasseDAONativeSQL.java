package com.claro.cobillingweb.persistence.dao.query;

public class SccRepasseDAONativeSQL {

	public static final String pesquisaNumRepasse = "SELECT DISTINCT NU_REPASSE , 'REPASSE' FROM SCC_REPASSE WHERE DT_INICIAL_REPASSE = :dataInicial AND DT_FIM_REPASSE = :dataFinal ";
}
