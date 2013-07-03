/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPosRelatorioDisputaDetalheDAO;
import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPosRelatorioDisputaDetalheService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccPosRelatorioDisputaDetalheServiceImpl extends AbstractService implements
				SccPosRelatorioDisputaDetalheService {

	@Autowired
	private SccPosRelatorioDisputaDetalheDAO sccPosRelatorioDisputaDetalheDAO;
	
	@Override
	public List<SccPosRelatorioDisputaDetalheView> gerarPosRelatorioDisputaDetalhe(String mesRelatorio, String anoRelatorio, String cdEotLD) throws ServiceException, DAOException {
		
		if(mesRelatorio.length() == 1){
			mesRelatorio = '0' + mesRelatorio;
		}
		String dtReferencia = anoRelatorio.concat(mesRelatorio);
		
		return sccPosRelatorioDisputaDetalheDAO.gerarPosRelatorioDisputaDetalhe(dtReferencia, cdEotLD);
	}

	
	public SccPosRelatorioDisputaDetalheDAO getSccPosRelatorioDisputaDetalheDAO() {
		return sccPosRelatorioDisputaDetalheDAO;
	}

	public void setSccPosRelatorioDisputaDetalheDAO(SccPosRelatorioDisputaDetalheDAO sccPosRelatorioDisputaDetalheDAO) {
		this.sccPosRelatorioDisputaDetalheDAO = sccPosRelatorioDisputaDetalheDAO;
	}
}
