package com.claro.sccweb.service.impl;



import java.util.Date;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.external.EstornoMobileDAO;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.EstornoMobileService;

public class EstornoMobileServiceImpl extends AbstractService implements EstornoMobileService {

	private EstornoMobileDAO estornoMobileDAO;
	
	 
	public boolean verificaRequisicaoEstorno(String cdEOTLD, String cdEOTClaro,String ufClaro, Date dataInicial, Date dataFinal) throws DAOException {
		return false;
	}

	 
	public void insereRequisicaoEstorno(String cdEOTLD, String cdEOTClaro,String ufClaro, Date dataInicial, Date dataFinal) throws DAOException {

		
	}

	public EstornoMobileDAO getEstornoMobileDAO() {
		return estornoMobileDAO;
	}

	public void setEstornoMobileDAO(EstornoMobileDAO estornoMobileDAO) {
		this.estornoMobileDAO = estornoMobileDAO;
	}

	
	
}
