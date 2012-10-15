package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;

public interface SccExtracaoCDRsDAO extends BasicDAO<SccExtracaoCDRsView> {
	
	List<SccExtracaoCDRsView> gerarRelatorioExtracaoCDRs(Date dtChamadaInicial, Date dtChamadaFinal, String nuMsisdnOrigem) throws DAOException;

}
