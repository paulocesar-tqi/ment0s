package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;

public interface SccChamadasRefaturamentoService {
	
	
	List<SccChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(String operadoraLD, String operadoraClaro, String tipoRefaturamento, String statusChamada, Date dtInicial, Date dtFinal) throws ServiceException, DAOException;

}
