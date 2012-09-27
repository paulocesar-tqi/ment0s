/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioConciliacaoDAO;
import com.claro.cobillingweb.persistence.view.SccRelatorioConciliacaoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelatorioConciliacaoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Service
public class SccRelatorioConciliacaoServiceImpl extends AbstractService implements
	SccRelatorioConciliacaoService {

	@Autowired
	private SccRelatorioConciliacaoDAO sccRelatorioConciliacao;
	
	@Override
	public List<SccRelatorioConciliacaoView> search(String operadoraClaro, String operadoraExterna, Date dataInicio, Date dataFim) throws ServiceException, DAOException {
		
		return sccRelatorioConciliacao.search(operadoraClaro, operadoraExterna, dataInicio, dataFim);
	}

	
	public SccRelatorioConciliacaoDAO getSccRelatorioConciliacaoDAO() {
		return sccRelatorioConciliacao;
	}

	public void setSccRelatorioConciliacaoDAO(SccRelatorioConciliacaoDAO sccRelatorioConciliacao) {
		this.sccRelatorioConciliacao = sccRelatorioConciliacao;
	}
	
	
	

}
