package com.claro.cobillingweb.persistence.dao.external;

import java.sql.Date;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.external.EstornoMobile;

public interface EstornoMobileDAO extends BasicDAO<EstornoMobile>{
	
	public boolean verificaRequisicaoEstorno(String cdEOTLD,String cdEOTClaro,String ufClaro,Date dataInicial,Date dataFinal) throws DAOException;
	
	public void insereRequisicaoEstorno(String cdEOTLD,String cdEOTClaro,String ufClaro,Date dataInicial,Date dataFinal) throws DAOException;
	
}
