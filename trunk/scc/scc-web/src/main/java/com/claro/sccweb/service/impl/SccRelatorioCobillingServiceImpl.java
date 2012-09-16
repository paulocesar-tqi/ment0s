/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccRelatorioCobillingService;

/**
 * @author 93046251
 *
 */
public class SccRelatorioCobillingServiceImpl extends AbstractService implements
		SccRelatorioCobillingService {
	
	@Autowired
	private SccRelatorioCobillingDAO sccRelatorioCobillingDAO;

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccRelatorioCobillingService#findAll()
	 */
	@Override
	public List<SccRelatorioCobilling> findAll() throws DAOException {
		
		return sccRelatorioCobillingDAO.findAll();
	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccRelatorioCobillingService#findById(java.lang.Long)
	 */
	@Override
	public SccRelatorioCobilling findById(Long id) throws DAOException {
		
		return sccRelatorioCobillingDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccRelatorioCobillingService#delete(com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling)
	 */
	@Transactional
	public void delete(SccRelatorioCobilling entity) throws DAOException {
		
		sccRelatorioCobillingDAO.delete(entity);

	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccRelatorioCobillingService#update(com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling)
	 */
	@Transactional
	public void update(SccRelatorioCobilling entity) throws DAOException {
		
		sccRelatorioCobillingDAO.update(entity);

	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccRelatorioCobillingService#create(com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling)
	 */
	@Transactional
	public void create(SccRelatorioCobilling entity) throws DAOException {
		
		sccRelatorioCobillingDAO.create(entity);

	}

	public SccRelatorioCobillingDAO getSccRelatorioCobillingDAO() {
		return sccRelatorioCobillingDAO;
	}

	public void setSccRelatorioCobillingDAO(
			SccRelatorioCobillingDAO sccRelatorioCobillingDAO) {
		this.sccRelatorioCobillingDAO = sccRelatorioCobillingDAO;
	}
	
	

}
