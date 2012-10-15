/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoFiscalDAO;
import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccBatimentoFiscalService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccBatimentoFiscalServiceImpl extends AbstractService implements
		SccBatimentoFiscalService {

	@Autowired
	private SccBatimentoFiscalDAO sccBatimentoFiscalDAO;
	
	@Override
	public List<SccBatimentoFiscalView> gerarRelatorioBatimentoFiscal(Integer mesCiclo, Integer anoCiclo, Integer cdCiclo, String cdCSP) throws ServiceException, DAOException {
		
		return sccBatimentoFiscalDAO.gerarRelatorioBatimentoFiscal(mesCiclo, anoCiclo, cdCiclo, cdCSP);
	}
	
	public SccBatimentoFiscalDAO getSccBatimentoFiscalDAO() {
		return sccBatimentoFiscalDAO;
	}

	public void setSccBatimentoFiscalDAO(SccBatimentoFiscalDAO sccBatimentoFiscalDAO) {
		this.sccBatimentoFiscalDAO = sccBatimentoFiscalDAO;
	}
}
