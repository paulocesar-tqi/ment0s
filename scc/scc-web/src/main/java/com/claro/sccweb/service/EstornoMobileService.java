package com.claro.sccweb.service;



import java.util.Date;

import com.claro.cobillingweb.persistence.dao.DAOException;

public interface EstornoMobileService {

	public boolean verificaRequisicaoEstorno(String cdEOTLD,String cdEOTClaro,String ufClaro,Date dataInicial,Date dataFinal) throws DAOException;
	
	public void insereRequisicaoEstorno(String cdEOTLD,String cdEOTClaro,String ufClaro,Date dataInicial,Date dataFinal) throws DAOException;
	
}
