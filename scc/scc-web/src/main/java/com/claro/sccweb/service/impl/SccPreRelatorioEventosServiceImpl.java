package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPreRelatorioEventosDAO;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPreRelatorioEventosService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Service
public class SccPreRelatorioEventosServiceImpl extends AbstractService implements
			SccPreRelatorioEventosService {

		@Autowired
		private SccPreRelatorioEventosDAO sccPreRelatorioEventosDAO;
		
		@Override
		public List<SccPreRelatorioEventosView> gerarPreRelatorioEventos(Date dtInicioEvento, Date dtFimEvento, 
				String cdEOTLD, String cdEOTClaro, String tpStatus) throws ServiceException, DAOException {
			
			return sccPreRelatorioEventosDAO.gerarPreRelatorioEventos(dtInicioEvento, dtFimEvento, cdEOTLD, cdEOTClaro, tpStatus);
		}

		
		public SccPreRelatorioEventosDAO getSccPreRelatorioEventosDAO() {
			return sccPreRelatorioEventosDAO;
		}

		public void setSccPreRelatorioEventosDAO(	SccPreRelatorioEventosDAO sccPreRelatorioEventosDAO) {
			this.sccPreRelatorioEventosDAO = sccPreRelatorioEventosDAO;
		}
	

}
