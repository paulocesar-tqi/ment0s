package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;

public interface SccBatimentoWruppDAO  extends BasicDAO<BatimentoWruppPrePagoView> {
	
	List<BatimentoWruppPrePagoView> listarBatimentos(String cdEOTLD, String cdEOTClaro, int mes, int ano)  throws DAOException;

}
