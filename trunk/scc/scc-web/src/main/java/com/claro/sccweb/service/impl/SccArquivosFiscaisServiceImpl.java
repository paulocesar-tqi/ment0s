/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivosFiscaisDAO;
import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccArquivosFiscaisService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccArquivosFiscaisServiceImpl extends AbstractService implements
		SccArquivosFiscaisService {

	@Autowired
	private SccArquivosFiscaisDAO sccArquivosFiscaisDAO;
	
	@Override
	public List<SccArquivosFiscaisView> gerarRelatorioArquivosFiscais(String sgUF, String cdStatusArquivo, String cdCSP, Integer cdCiclo, Integer mesCiclo, Integer anoCiclo) throws ServiceException, DAOException {
		
		return sccArquivosFiscaisDAO.gerarRelatorioArquivosFiscais(sgUF, cdStatusArquivo, cdCSP, cdCiclo, mesCiclo, anoCiclo);
	}

	
	public SccArquivosFiscaisDAO getSccArquivosFiscaisDAO() {
		return sccArquivosFiscaisDAO;
	}

	public void setSccArquivosFiscaisDAO(SccArquivosFiscaisDAO sccArquivosFiscaisDAO) {
		this.sccArquivosFiscaisDAO = sccArquivosFiscaisDAO;
	}
}
