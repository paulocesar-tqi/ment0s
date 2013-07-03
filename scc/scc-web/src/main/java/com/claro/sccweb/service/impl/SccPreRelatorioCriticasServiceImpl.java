package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPreRelatorioCriticasDAO;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPreRelatorioCriticasService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Service
public class SccPreRelatorioCriticasServiceImpl extends AbstractService implements
SccPreRelatorioCriticasService {

		@Autowired
		private SccPreRelatorioCriticasDAO sccPreRelatorioCriticasDAO;
		
		@Override
		public List<SccPreRelatorioCriticasView> gerarPreRelatorioCriticas(Date dtInicioEvento, Date dtFimEvento, 
				String cdEOTLD, String cdEOTClaro, String cdStatus, String cdMotivoRejeicao, String cdDefeito) throws ServiceException, DAOException {
			
			return sccPreRelatorioCriticasDAO.gerarPreRelatorioCriticas(dtInicioEvento, dtFimEvento, cdEOTLD, cdEOTClaro, cdStatus, cdMotivoRejeicao, cdDefeito);
		}

		
		public SccPreRelatorioCriticasDAO getSccPreRelatorioCriticasDAO() {
			return sccPreRelatorioCriticasDAO;
		}

		public void setSccPreRelatorioCriticasDAO(	SccPreRelatorioCriticasDAO sccPreRelatorioCriticasDAO) {
			this.sccPreRelatorioCriticasDAO = sccPreRelatorioCriticasDAO;
		}
	

}
