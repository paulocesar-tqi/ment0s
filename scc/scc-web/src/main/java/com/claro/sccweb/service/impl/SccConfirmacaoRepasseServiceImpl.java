/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccConfirmacaoRepasseDAO;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccConfirmacaoRepasseService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccConfirmacaoRepasseServiceImpl extends AbstractService implements
		SccConfirmacaoRepasseService {

	@Autowired
	private SccConfirmacaoRepasseDAO sccConfirmacaoRepasseDAO;
	
	@Override
	public List<SccConfirmacaoRepasseView> gerarRelatorioConfirmacaoRepasse(String mesRepasse, String anoRepasse, String cdEOTLD, String cdStatusRepasse) throws ServiceException, DAOException {
		
		if(mesRepasse.length() == 1){
			mesRepasse = '0' + mesRepasse;
		}
		String dtFechamento = mesRepasse.concat("/" + anoRepasse);
		
		return sccConfirmacaoRepasseDAO.gerarRelatorioConfirmacaoRepasse(dtFechamento, cdEOTLD, cdStatusRepasse);
	}

	
	public SccConfirmacaoRepasseDAO getSccConfirmacaoRepasseDAO() {
		return sccConfirmacaoRepasseDAO;
	}

	public void setSccConfirmacaoRepasseDAO(SccConfirmacaoRepasseDAO sccConfirmacaoRepasseDAO) {
		this.sccConfirmacaoRepasseDAO = sccConfirmacaoRepasseDAO;
	}
}
