/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoInterfaceDAO;
import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccBatimentoInterfaceService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Service
public class SccBatimentoInterfaceServiceImpl extends AbstractService implements
		SccBatimentoInterfaceService {

	@Autowired
	private SccBatimentoInterfaceDAO sccBatimentoInterfaceDAO;
	
	@Override
	public List<SccBatimentoInterfaceView> listarBatimentoInterface(Date dtInicio, Date dtFim, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws ServiceException, DAOException {
		
		return sccBatimentoInterfaceDAO.listarBatimentoInterface(dtInicio, dtFim, cdEOTLD, cdEOTClaro, tpArquivo);
	}

	
	public SccBatimentoInterfaceDAO getSccBatimentoInterfaceDAO() {
		return sccBatimentoInterfaceDAO;
	}

	public void setSccBatimentoInterfaceDAO(SccBatimentoInterfaceDAO sccBatimentoInterfaceDAO) {
		this.sccBatimentoInterfaceDAO = sccBatimentoInterfaceDAO;
	}
	
	
	

}
