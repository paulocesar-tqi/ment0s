package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;

public interface SccRelDemonstrativoDesempenhoPenalidadeService {
	
	
	List<SccRelDemonstrativoDesempenhoPenalidadeView> gerarRelDemonstrativoDesempenhoPenalidade(String mesRelatorio, String anoRelatorio, String cdCSP) throws ServiceException, DAOException;

}
