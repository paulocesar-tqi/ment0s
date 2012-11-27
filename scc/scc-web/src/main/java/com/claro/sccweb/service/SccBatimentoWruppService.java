package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;

public interface SccBatimentoWruppService {

	List<BatimentoWruppPrePagoView> listarBatimentos(String cdEOTLD,
			String cdEOTClaro, Date dataInicio, Date dataFim) throws ServiceException, DAOException;
}
