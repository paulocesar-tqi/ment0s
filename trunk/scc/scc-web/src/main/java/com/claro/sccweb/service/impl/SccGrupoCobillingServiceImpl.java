/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccGrupoCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccGrupoCobillingService;

/**
 * @author 93046251
 *
 */
public class SccGrupoCobillingServiceImpl extends AbstractService  implements SccGrupoCobillingService {
	
	private SccGrupoCobillingDAO sccGrupoCobillingDAO;
	
	@Override
	public List<SccGrupoCobilling> findAll() throws DAOException {
		
		return getSccGrupoCobillingDAO().findAll(); 
	}

	@Override
	public SccGrupoCobilling findById(Long id) throws DAOException {
		
		return getSccGrupoCobillingDAO().findById(id);
	}

	@Transactional
	public void delete(SccGrupoCobilling entity) throws DAOException {
		getSccGrupoCobillingDAO().delete(entity);

	}

	@Transactional
	public void update(SccGrupoCobilling entity) throws DAOException {
		
		getSccGrupoCobillingDAO().update(entity);

	}

	@Transactional
	public void create(SccGrupoCobilling entity) throws DAOException {
		
		getSccGrupoCobillingDAO().create(entity);

	}

	public SccGrupoCobillingDAO getSccGrupoCobillingDAO() {
		return sccGrupoCobillingDAO;
	}

	public void setSccGrupoCobillingDAO(SccGrupoCobillingDAO sccGrupoCobillingDAO) {
		this.sccGrupoCobillingDAO = sccGrupoCobillingDAO;
	}

	
	
}
