package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.sccweb.service.data.PeriodoCDR;

public interface EvolucaoCDRService {

	public List<PeriodoCDR> geraEvolucaoCDRs(String grpCdr, String cdEOTClaro,String cdEOTLD,Long produto,Date dataInicial,Date dataFinal,boolean holding)
	throws DAOException,ServiceException;
	 
	
}
