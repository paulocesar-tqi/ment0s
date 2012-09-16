package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;

public interface SccStatusCdrDAO extends BasicDAO<SccStatusCdr> {

	public List<RelEventosArquivoView> geraRelatorioEventos(String cdEOTLD,String cdEOTClaro,Long cdCdrStatus,Date dataInicial,Date dataFinal) throws DAOException;
	
	
}
