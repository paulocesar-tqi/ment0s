package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;

public interface SccPrePagoCreditosDAO extends BasicDAO<SccPrePagoCreditosView> {
	
	List<SccPrePagoCreditosView> gerarRelatorioPrePagoCreditos(Date dtInicial, Date dtFinal, String tipoCredito, String cdStatusCredito, String operadoraClaro, String operadoraLD) throws DAOException;

}
