package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrCobl;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;

public interface SccCdrCoblDAO extends BasicDAO<SccCdrCobl>{

	public List<RelCDRPrePagoView> pesquisaCDRsPrePago(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	public SomatorioRelCDRPrePagoView geraSomatorioCDRs(Date dataInicial,Date dataFinal,String tipoPeriodo) throws DAOException;
}
