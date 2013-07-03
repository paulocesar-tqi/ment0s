/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelDemonstrativoDesempenhoPenalidadeDAO;
import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelDemonstrativoDesempenhoPenalidadeService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccRelDemonstrativoDesempenhoPenalidadeServiceImpl extends AbstractService implements
					SccRelDemonstrativoDesempenhoPenalidadeService {

	@Autowired
	private SccRelDemonstrativoDesempenhoPenalidadeDAO sccRelDemonstrativoDesempenhoPenalidadeDAO;
	
	@Override
	public List<SccRelDemonstrativoDesempenhoPenalidadeView> gerarRelDemonstrativoDesempenhoPenalidade(String mesRelatorio, String anoRelatorio, String cdCSP) throws ServiceException, DAOException {
		
		if(mesRelatorio.length() == 1){
			mesRelatorio = '0' + mesRelatorio;
		}
		String dtReferencia = anoRelatorio.concat(mesRelatorio);
		
		return sccRelDemonstrativoDesempenhoPenalidadeDAO.gerarRelDemonstrativoDesempenhoPenalidade(dtReferencia, cdCSP);
	}

	
	public SccRelDemonstrativoDesempenhoPenalidadeDAO getSccRelDemonstrativoDesempenhoPenalidadeDAO() {
		return sccRelDemonstrativoDesempenhoPenalidadeDAO;
	}

	public void setSccRelDemonstrativoDesempenhoPenalidadeDAO(SccRelDemonstrativoDesempenhoPenalidadeDAO sccRelDemonstrativoDesempenhoPenalidadeDAO) {
		this.sccRelDemonstrativoDesempenhoPenalidadeDAO = sccRelDemonstrativoDesempenhoPenalidadeDAO;
	}
}
