package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;

public interface SccBatimentoFiscalDAO extends BasicDAO<SccBatimentoFiscalView> {
	
	List<SccBatimentoFiscalView> gerarRelatorioBatimentoFiscal(Integer mesCiclo, Integer anoCiclo, Integer cdCiclo, String cdCSP) throws DAOException;

}
