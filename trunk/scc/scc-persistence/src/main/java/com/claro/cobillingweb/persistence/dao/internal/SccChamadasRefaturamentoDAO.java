package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;

public interface SccChamadasRefaturamentoDAO extends BasicDAO<SccChamadasRefaturamentoView> {
	
	List<SccChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicial, Date dtFinal, String statusChamada, String operadoraLD, String operadoraClaro, String cdRefaturamento) throws DAOException;

}
