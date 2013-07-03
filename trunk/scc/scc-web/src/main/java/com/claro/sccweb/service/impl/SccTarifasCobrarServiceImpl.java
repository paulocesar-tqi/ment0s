package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccTarifasCobrarDAO;
import com.claro.cobillingweb.persistence.entity.SccPreItemTarifaAcb;
import com.claro.cobillingweb.persistence.entity.SccPreTarifaAcb;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccTarifasCobrarService;

@Service
public class SccTarifasCobrarServiceImpl extends AbstractService implements SccTarifasCobrarService {
	
	@Autowired
	private SccTarifasCobrarDAO sccTarifasCobrarDAO;
	
	@Override
	public List<SccPreTarifaAcb> findByOperadora(String cdEotLd) throws DAOException{
		return this.sccTarifasCobrarDAO.findByOperadora(cdEotLd);
		
	}

	@Override
	public List<SccPreItemTarifaAcb> findByIdTarifa(Long idTarifa)throws DAOException{
		return this.sccTarifasCobrarDAO.findByIdTarifa(idTarifa);
		
	}
	
	@Override
	public SccPreTarifaAcb findById(Long idTarifa) throws DAOException{
		
		return this.sccTarifasCobrarDAO.findById(idTarifa);
		
	}

	public void setSccTarifasCobrarDAO(SccTarifasCobrarDAO sccTarifasCobrarDAO) {
		this.sccTarifasCobrarDAO = sccTarifasCobrarDAO;
	}
	
}
