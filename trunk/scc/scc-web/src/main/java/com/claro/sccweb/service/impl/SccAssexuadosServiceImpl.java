/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAssexuadosDAO;
import com.claro.cobillingweb.persistence.view.SccAssexuadosView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAssexuadosService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccAssexuadosServiceImpl extends AbstractService implements
		SccAssexuadosService {

	@Autowired
	private SccAssexuadosDAO sccAssexuadosDAO;
	
	@Override
	public List<SccAssexuadosView> gerarRelatorioAssexuados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException {
		
		return sccAssexuadosDAO.gerarRelatorioAssexuados(dtInicial, dtFinal, noArquivoGerado);
	}

	
	public SccAssexuadosDAO getSccAssexuadosDAO() {
		return sccAssexuadosDAO;
	}

	public void setSccAssexuadosDAO(SccAssexuadosDAO sccAssexuadosDAO) {
		this.sccAssexuadosDAO = sccAssexuadosDAO;
	}
}
