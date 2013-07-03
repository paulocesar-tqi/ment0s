/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccChamadasRefaturamentoDAO;
import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccChamadasRefaturamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccChamadasRefaturamentoServiceImpl extends AbstractService implements
		SccChamadasRefaturamentoService {

	@Autowired
	private SccChamadasRefaturamentoDAO sccChamadasRefaturamentoDAO;
	
	@Override
	public List<SccChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(String operadoraLD, String operadoraClaro, String tipoRefaturamento, String statusChamada, Date dtInicial, Date dtFinal) throws ServiceException, DAOException {
		return sccChamadasRefaturamentoDAO.gerarRelatorioChamadasRefaturamento(dtInicial, dtFinal, statusChamada, operadoraLD, operadoraClaro, tipoRefaturamento);
	}
	
	public SccChamadasRefaturamentoDAO getSccChamadasRefaturamentoDAO() {
		return sccChamadasRefaturamentoDAO;
	}

	public void setSccChamadasRefaturamentoDAO(SccChamadasRefaturamentoDAO sccChamadasRefaturamentoDAO) {
		this.sccChamadasRefaturamentoDAO = sccChamadasRefaturamentoDAO;
	}
}
