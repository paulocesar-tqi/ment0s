package com.claro.sccweb.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioQuestionamentoResultadoDAO;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelatorioQuestionamentoResultadoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Service
public class SccRelatorioQuestionamentoResultadoServiceImpl extends AbstractService implements
				SccRelatorioQuestionamentoResultadoService {

			@Autowired
			private SccRelatorioQuestionamentoResultadoDAO sccRelatorioQuestionamentoResultadoDAO;
			
			@Override
			public List<SccRelatorioQuestionamentoResultadoView> gerarRelatorioQuestionamentoResultado( 
					String sqQuestionamento, String cdEOTLD, String tpStatus) throws ServiceException, DAOException {
				
				return sccRelatorioQuestionamentoResultadoDAO.gerarRelatorioQuestionamentoResultado(sqQuestionamento, cdEOTLD, tpStatus);
			}

			
			public SccRelatorioQuestionamentoResultadoDAO getSccRelatorioQuestionamentoResultadoDAO() {
				return sccRelatorioQuestionamentoResultadoDAO;
			}

			public void setSccRelatorioQuestionamentoResultadoDAO(	SccRelatorioQuestionamentoResultadoDAO sccRelatorioQuestionamentoResultadoDAO) {
				this.sccRelatorioQuestionamentoResultadoDAO = sccRelatorioQuestionamentoResultadoDAO;
			}
			
			@Override
			public Collection<SccQuestionamentoView> gerarCombo() throws ServiceException, DAOException {
				
				return this.sccRelatorioQuestionamentoResultadoDAO.gerarCombo();
			}
	

}
