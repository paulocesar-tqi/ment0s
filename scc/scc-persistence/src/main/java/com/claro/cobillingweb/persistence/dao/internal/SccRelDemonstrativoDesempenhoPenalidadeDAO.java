package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;

public interface SccRelDemonstrativoDesempenhoPenalidadeDAO extends BasicDAO<SccRelDemonstrativoDesempenhoPenalidadeView> {
	
	List<SccRelDemonstrativoDesempenhoPenalidadeView> gerarRelDemonstrativoDesempenhoPenalidade(String dtReferencia, String cdCSP) throws DAOException;

}
