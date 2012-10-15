/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccContingenciaFiscalDAO;
import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccContingenciaFiscalService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccContingenciaFiscalServiceImpl extends AbstractService implements
		SccContingenciaFiscalService {

	@Autowired
	private SccContingenciaFiscalDAO sccContingenciaFiscalDAO;
	
	@Override
	public List<SccContingenciaFiscalView> gerarRelatorioContingenciaFiscal(String mesRelatorio, String anoRelatorio, String cdCSP) throws ServiceException, DAOException {
		
		if(mesRelatorio.length() == 1){
			mesRelatorio = '0' + mesRelatorio;
		}
		String dtReferencia = anoRelatorio.concat(mesRelatorio);
		
		return sccContingenciaFiscalDAO.gerarRelatorioContingenciaFiscal(dtReferencia, cdCSP);
	}

	
	public SccContingenciaFiscalDAO getSccContingenciaFiscalDAO() {
		return sccContingenciaFiscalDAO;
	}

	public void setSccContingenciaFiscalDAO(SccContingenciaFiscalDAO sccContingenciaFiscalDAO) {
		this.sccContingenciaFiscalDAO = sccContingenciaFiscalDAO;
	}
}
