/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccDisputaPrePagoDAO;
import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccDisputaPrePagoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Service
public class SccDisputaPrePagoServiceImpl extends AbstractService implements SccDisputaPrePagoService {

	@Autowired
	private SccDisputaPrePagoDAO sccDisputaPrePagoDAO;

	@Override
	public Collection<SccDisputaPrePago> pesquisarDisputaPrePago(SccFiltroDisputaPrePago filtro) throws ServiceException,
			DAOException {
		
		return this.sccDisputaPrePagoDAO.pesquisarDisputaPrePago(filtro);
	}
	public void setSccDisputaPrePagoDAO(SccDisputaPrePagoDAO sccDisputaPrePagoDAO) {
		this.sccDisputaPrePagoDAO = sccDisputaPrePagoDAO;
	}
	
	@Override
	public SccDisputaPrePago pesquisarBySqDisputaPrePago(Long sqDisputaPrePago)
			throws ServiceException, DAOException {
		
		return null;
	}
	
	@Transactional
	public void saveOrUpdate(SccDisputaPrePago entity) throws ServiceException, DAOException{
		
		this.sccDisputaPrePagoDAO.saveOrUpdate(entity);
	}
	
	
	
	@Transactional
	public void create(SccDisputaPrePago entity) throws ServiceException, DAOException {
		
		this.sccDisputaPrePagoDAO.create(entity);
		
	}
	@Override
	public SccDisputaPrePago findBySqDisputaPrePago(Long sqDisputaPrePago)
			throws ServiceException, DAOException {
		
		return this.sccDisputaPrePagoDAO.findBySqDisputaPrePago(sqDisputaPrePago) ;
	}
	
	
	
	

}
