package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelBatimentoEstornoDebitoDAO;
import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelBatimentoEstornoDebitoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

	
@Service
public class SccRelBatimentoEstornoDebitoServiceImpl extends AbstractService implements
				SccRelBatimentoEstornoDebitoService {

				@Autowired
				private SccRelBatimentoEstornoDebitoDAO sccRelBatimentoEstornoDebitoDAO;
				
				@Override
				public List<SccRelBatimentoEstornoDebitoView> gerarRelBatimentoEstornoDebito(String cdEOTLD, String cdEOTClaro,
						String cdStatusArquivo, Integer mmCiclo, Integer aaCiclo) throws ServiceException, DAOException {
					
					return sccRelBatimentoEstornoDebitoDAO.gerarRelBatimentoEstornoDebito(cdEOTLD, cdEOTClaro, cdStatusArquivo, mmCiclo, aaCiclo);
				}

				
				public SccRelBatimentoEstornoDebitoDAO getSccRelBatimentoEstornoDebitoDAO() {
					return sccRelBatimentoEstornoDebitoDAO;
				}

				public void setSccRelBatimentoEstornoDebitoDAO(	SccRelBatimentoEstornoDebitoDAO sccRelBatimentoEstornoDebitoDAO) {
					this.sccRelBatimentoEstornoDebitoDAO = sccRelBatimentoEstornoDebitoDAO;
				}

}
