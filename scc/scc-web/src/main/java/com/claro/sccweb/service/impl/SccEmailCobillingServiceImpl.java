/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccEmailCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccEmailCobillingService;

/**
 * @author 93046251
 *
 */
@Service
public class SccEmailCobillingServiceImpl extends AbstractService implements
		SccEmailCobillingService {
	
	@Autowired
	private SccEmailCobillingDAO sccEmailCobillingDAO;

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccEmailCobillingService#findAll()
	 */
	@Override
	public List<SccEmailCobilling> findAll() throws DAOException {
		
		return sccEmailCobillingDAO.findAll();
	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccEmailCobillingService#findById(java.lang.Long)
	 */
	@Override
	public SccEmailCobilling findById(Long id) throws DAOException {
		
		return sccEmailCobillingDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccEmailCobillingService#delete(com.claro.cobillingweb.persistence.entity.SccEmailCobilling)
	 */
	@Transactional
	public void delete(SccEmailCobilling entity) throws DAOException {
		
		sccEmailCobillingDAO.delete(entity);

	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccEmailCobillingService#update(com.claro.cobillingweb.persistence.entity.SccEmailCobilling)
	 */
	@Transactional
	public void update(SccEmailCobilling entity) throws DAOException {
		
		sccEmailCobillingDAO.update(entity);

	}

	/* (non-Javadoc)
	 * @see com.claro.sccweb.service.SccEmailCobillingService#create(com.claro.cobillingweb.persistence.entity.SccEmailCobilling)
	 */
	@Transactional
	public void create(SccEmailCobilling entity) throws DAOException {
		
		sccEmailCobillingDAO.create(entity);

	}

	public SccEmailCobillingDAO getSccEmailCobillingDAO() {
		return sccEmailCobillingDAO;
	}

	public void setSccEmailCobillingDAO(SccEmailCobillingDAO sccEmailCobillingDAO) {
		this.sccEmailCobillingDAO = sccEmailCobillingDAO;
	}
	
	

}
