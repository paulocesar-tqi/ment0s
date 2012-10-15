package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioChamadasRefaturamentoDAO;
import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelatorioChamadasRefaturamentoService;
import com.claro.sccweb.service.ServiceException;

	
@Service
public class SccRelatorioChamadasRefaturamentoServiceImpl extends AbstractService implements
			SccRelatorioChamadasRefaturamentoService {

			@Autowired
			private SccRelatorioChamadasRefaturamentoDAO sccRelatorioChamadasRefaturamentoDAO;
			
			@Override
			public List<SccRelatorioChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicioPeriodo, Date dtFimPeriodo, 
					String cdEOTLD, String cdEOTClaro, String cdRefaturamento) throws ServiceException, DAOException {
				
				return sccRelatorioChamadasRefaturamentoDAO.gerarRelatorioChamadasRefaturamento(dtInicioPeriodo, dtFimPeriodo, cdEOTLD, cdEOTClaro, cdRefaturamento);
			}

			
			public SccRelatorioChamadasRefaturamentoDAO getSccRelatorioChamadasRefaturamentoDAO() {
				return sccRelatorioChamadasRefaturamentoDAO;
			}

			public void setSccRelatorioChamadasRefaturamentoDAO(SccRelatorioChamadasRefaturamentoDAO sccRelatorioChamadasRefaturamentoDAO) {
				this.sccRelatorioChamadasRefaturamentoDAO = sccRelatorioChamadasRefaturamentoDAO;
			}

}
