package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;

public interface SccConfirmacaoRepasseDAO extends BasicDAO<SccConfirmacaoRepasseView> {
	
	List<SccConfirmacaoRepasseView> gerarRelatorioConfirmacaoRepasse(String dtFechamento, String cdEOTLD, String cdStatusRepasse) throws DAOException;

}
