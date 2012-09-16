package com.claro.cobillingweb.persistence.dao.external.impl;

import java.sql.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.external.EstornoMobileDAO;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.entity.external.EstornoMobile;

public class EstornoMobileDAOImpl extends HibernateBasicDAOImpl<EstornoMobile> implements EstornoMobileDAO {
	
	public List<EstornoMobile> getAll() throws DAOException {
		return null;
	}
	
	public boolean verificaRequisicaoEstorno(String cdEOTLD, String cdEOTClaro,String ufClaro, Date dataInicial, Date dataFinal) throws DAOException {
		return true;
	}
	
	public void insereRequisicaoEstorno(String cdEOTLD, String cdEOTClaro,String ufClaro, Date dataInicial, Date dataFinal) throws DAOException {
				
	}
	
}
