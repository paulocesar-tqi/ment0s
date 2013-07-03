package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;

public interface SccPosRelatorioDisputaDetalheDAO extends BasicDAO<SccPosRelatorioDisputaDetalheView> {
	
	List<SccPosRelatorioDisputaDetalheView> gerarPosRelatorioDisputaDetalhe(String dtReferencia, String cdEotLD) throws DAOException;

}
