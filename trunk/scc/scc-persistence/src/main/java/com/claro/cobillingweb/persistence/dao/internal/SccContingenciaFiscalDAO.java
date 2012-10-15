package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;

public interface SccContingenciaFiscalDAO extends BasicDAO<SccContingenciaFiscalView> {
	
	List<SccContingenciaFiscalView> gerarRelatorioContingenciaFiscal(String dtReferencia, String cdCSP) throws DAOException;

}
