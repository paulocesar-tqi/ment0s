/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPrePagoCreditosDAO;
import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPrePagoCreditosService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccPrePagoCreditosServiceImpl extends AbstractService implements
		SccPrePagoCreditosService {

	@Autowired
	private SccPrePagoCreditosDAO sccPrePagoCreditosDAO;
	
	@Override
	public List<SccPrePagoCreditosView> gerarRelatorioPrePagoCreditos(Date dtInicial, Date dtFinal, String tipoCredito, String cdStatusCredito, String operadoraClaro, String operadoraLD) throws ServiceException, DAOException {
		return sccPrePagoCreditosDAO.gerarRelatorioPrePagoCreditos(dtInicial, dtFinal, tipoCredito, cdStatusCredito, operadoraClaro, operadoraLD);
	}
	
	public SccPrePagoCreditosDAO getSccPrePagoCreditosDAO() {
		return sccPrePagoCreditosDAO;
	}

	public void setSccPrePagoCreditosDAO(SccPrePagoCreditosDAO sccPrePagoCreditosDAO) {
		this.sccPrePagoCreditosDAO = sccPrePagoCreditosDAO;
	}
}
