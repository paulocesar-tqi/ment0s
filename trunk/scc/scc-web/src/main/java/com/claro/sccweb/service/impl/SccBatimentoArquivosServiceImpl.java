/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoArquivosDAO;
import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccBatimentoArquivosService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Service
public class SccBatimentoArquivosServiceImpl extends AbstractService implements
		SccBatimentoArquivosService {

	@Autowired
	private SccBatimentoArquivosDAO sccBatimentoArquivosDAO;
	
	@Override
	public List<SccBatimentoArquivosView> gerarRelatorioBatimento(Date dtInicioBatimento, Date dtFimBatimento, String cdEOTLD,
			String cdEOTClaro, String tpArquivo) throws ServiceException, DAOException {
		
		return sccBatimentoArquivosDAO.gerarRelatorioBatimento(dtInicioBatimento, dtFimBatimento, cdEOTLD, cdEOTClaro, tpArquivo);
	}

	
	public SccBatimentoArquivosDAO getSccBatimentoArquivosDAO() {
		return sccBatimentoArquivosDAO;
	}

	public void setSccBatimentoArquivosDAO(	SccBatimentoArquivosDAO sccBatimentoArquivosDAO) {
		this.sccBatimentoArquivosDAO = sccBatimentoArquivosDAO;
	}
	
	
	

}
