package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioQuestionamentoDAO;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelatorioQuestionamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Service
public class SccRelatorioQuestionamentoServiceImpl extends AbstractService implements
				SccRelatorioQuestionamentoService {

			@Autowired
			private SccRelatorioQuestionamentoDAO sccRelatorioQuestionamentoDAO;
			
			@Override
			public List<SccRelatorioQuestionamentoView> gerarRelatorioQuestionamento( 
					String cdEOTLD, String tpStatus) throws ServiceException, DAOException {
				
				return sccRelatorioQuestionamentoDAO.gerarRelatorioQuestionamento(cdEOTLD, tpStatus);
			}

			
			public SccRelatorioQuestionamentoDAO getSccRelatorioQuestionamentoDAO() {
				return sccRelatorioQuestionamentoDAO;
			}

			public void setSccRelatorioQuestionamentoDAO(	SccRelatorioQuestionamentoDAO sccRelatorioQuestionamentoDAO) {
				this.sccRelatorioQuestionamentoDAO = sccRelatorioQuestionamentoDAO;
			}
	

}
