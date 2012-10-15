/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAlocadasMobileSemProcessamentoDAO;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAlocadasMobileSemProcessamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccAlocadasMobileSemProcessamentoServiceImpl extends AbstractService implements
		SccAlocadasMobileSemProcessamentoService {

	@Autowired
	private SccAlocadasMobileSemProcessamentoDAO sccAlocadasMobileSemProcessamentoDAO;
	
	@Override
	public List<SccAlocadasMobileSemProcessamentoView> gerarRelatorioAlocadasMobileSemProcessamento(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException {
		
		return sccAlocadasMobileSemProcessamentoDAO.gerarRelatorioAlocadasMobileSemProcessamento(dtInicial, dtFinal, noArquivoGerado);
	}

	
	public SccAlocadasMobileSemProcessamentoDAO getSccAlocadasMobileSemProcessamentoDAO() {
		return sccAlocadasMobileSemProcessamentoDAO;
	}

	public void setSccAlocadasMobileSemProcessamentoDAO(SccAlocadasMobileSemProcessamentoDAO sccAlocadasMobileSemProcessamentoDAO) {
		this.sccAlocadasMobileSemProcessamentoDAO = sccAlocadasMobileSemProcessamentoDAO;
	}
}
