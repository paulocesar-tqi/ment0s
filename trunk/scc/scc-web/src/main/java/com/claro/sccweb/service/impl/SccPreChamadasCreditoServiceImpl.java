package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPreChamadasCreditoDAO;
import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPreChamadasCreditoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Service
public class SccPreChamadasCreditoServiceImpl extends AbstractService implements
				SccPreChamadasCreditoService {

			@Autowired
			private SccPreChamadasCreditoDAO sccPreChamadasCreditoDAO;
			
			@Override
			public List<SccPreChamadasCreditoView> gerarPreChamadasCredito(Date dtInicioCredito, Date dtFimCredito, 
					String cdEOTLD, String cdEOTClaro, String tpStatusCredito) throws ServiceException, DAOException {
				
				return sccPreChamadasCreditoDAO.gerarPreChamadasCredito(dtInicioCredito, dtFimCredito, cdEOTLD, cdEOTClaro, tpStatusCredito);
			}

			
			public SccPreChamadasCreditoDAO getSccPreChamadasCreditoDAO() {
				return sccPreChamadasCreditoDAO;
			}

			public void setSccPreChamadasCreditoDAO(	SccPreChamadasCreditoDAO sccPreChamadasCreditoDAO) {
				this.sccPreChamadasCreditoDAO = sccPreChamadasCreditoDAO;
			}
	

}
