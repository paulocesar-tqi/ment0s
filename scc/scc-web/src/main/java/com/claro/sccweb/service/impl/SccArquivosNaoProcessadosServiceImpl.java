/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivosNaoProcessadosDAO;
import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccArquivosNaoProcessadosService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccArquivosNaoProcessadosServiceImpl extends AbstractService implements
		SccArquivosNaoProcessadosService {

	@Autowired
	private SccArquivosNaoProcessadosDAO sccArquivosNaoProcessadosDAO;
	
	@Override
	public List<SccArquivosNaoProcessadosView> gerarRelatorioArquivosNaoProcessados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException {
		
		return sccArquivosNaoProcessadosDAO.gerarRelatorioArquivosNaoProcessados(dtInicial, dtFinal, noArquivoGerado);
	}

	
	public SccArquivosNaoProcessadosDAO getSccArquivosNaoProcessadosDAO() {
		return sccArquivosNaoProcessadosDAO;
	}

	public void setSccArquivosNaoProcessadosDAO(SccArquivosNaoProcessadosDAO sccArquivosNaoProcessadosDAO) {
		this.sccArquivosNaoProcessadosDAO = sccArquivosNaoProcessadosDAO;
	}
}
