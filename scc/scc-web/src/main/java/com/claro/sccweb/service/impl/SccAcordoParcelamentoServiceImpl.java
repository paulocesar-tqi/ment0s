package com.claro.sccweb.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAcordoParcelamentoDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.filtro.SccFiltroParcelamento;
import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAcordoParcelamentoService;
import com.claro.sccweb.service.ServiceException;

@Service
public class SccAcordoParcelamentoServiceImpl extends AbstractService implements
		SccAcordoParcelamentoService {
	
	@Autowired
	private SccAcordoParcelamentoDAO sccAcordoParcelamentoDAO;

	@Override
	public Collection<SccAcordoParcelamentoView> findByFilter(SccFiltroParcelamento filtro) throws ServiceException, DAOException {
		
		Collection<SccAcordoParcelamentoView> collection = null;
		
		if(filtro.getIsSintetico()){
			
			collection = this.sccAcordoParcelamentoDAO.findByFilterSintetico(filtro);
		}else{
			
			collection = this.sccAcordoParcelamentoDAO.findByFilterAnalitico(filtro);
		}
		
		return collection;
	}
	
	@Override
	public Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamento(
			SccFiltro filtro) throws ServiceException, DAOException {
		
		Collection<SccAcordoParcelamentoView> collection = null;
		
		if(filtro.getIsSintetico()){
			
			collection = this.sccAcordoParcelamentoDAO.findByFilterRelAcompanhamentoSintetico(filtro);
		}else{
			
			collection = this.sccAcordoParcelamentoDAO.findByFilterRelAcompanhamentoAnalitico(filtro);
		}
		
		return collection;

	}




	public void setSccAcordoParcelamentoDAO(SccAcordoParcelamentoDAO sccAcordoParcelamentoDAO) {
		this.sccAcordoParcelamentoDAO = sccAcordoParcelamentoDAO;
	}
	
	
	

}
