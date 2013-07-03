/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccDisputaDAO;
import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccDisputaService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Service
public class SccDisputaServiceImpl extends AbstractService implements SccDisputaService {
	
	@Autowired
	private SccDisputaDAO sccDisputaDAO;

	@Override
	public Collection<SccDisputa> pesquisarDisputaByFiltro(	SccFiltroDisputa filtro) throws ServiceException, DAOException {
		
		return this.sccDisputaDAO.pesquisarDisputaByFiltro(filtro);
	}
	
	@Override
	public SccDisputa pesquisarBySqDisputa(Long sqDisputa) throws ServiceException,DAOException{
		return this.sccDisputaDAO.pesquisarBySqDisputa(sqDisputa);
		
		
	}
		
	
	
	
	@Transactional
	public void saveOrUpdate(SccDisputa entity) throws ServiceException, DAOException {
		
		this.sccDisputaDAO.saveOrUpdate(entity);
		
	}


	@Transactional
	public void create(SccDisputa entity) throws ServiceException, DAOException {
		
		this.sccDisputaDAO.create(entity);
		
	}


	@Transactional
	public void delete(SccDisputa entity) throws DAOException {
		this.sccDisputaDAO.delete(entity);
	}
	

	public void setSccDisputaDAO(SccDisputaDAO sccDisputaDAO) {
		this.sccDisputaDAO = sccDisputaDAO;
	}
	
	
	

}
