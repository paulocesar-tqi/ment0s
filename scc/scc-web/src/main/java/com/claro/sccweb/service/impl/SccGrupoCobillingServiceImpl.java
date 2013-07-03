/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccGrupoCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccGrupoCobillingService;

/**
 * @author 93046251
 *
 */
public class SccGrupoCobillingServiceImpl extends AbstractService  implements SccGrupoCobillingService {
	
	@Autowired
	private SccGrupoCobillingDAO sccGrupoCobillingDAO;
	
	@Override
	public List<SccGrupoCobilling> findAll() throws DAOException {
		
		return (List<SccGrupoCobilling>) getSccGrupoCobillingDAO().findAll(); 
	}

	@Override
	public SccGrupoCobilling findById(Long id) throws DAOException, ServiceException {
		
		return this.sccGrupoCobillingDAO.findById(id);
		
		
	}

	@Transactional
	public void delete(SccGrupoCobilling entity) throws DAOException, ServiceException {
		this.sccGrupoCobillingDAO.delete(entity);
		

	}

	@Transactional
	public void update(SccGrupoCobilling entity) throws DAOException, ServiceException {
		
		this.sccGrupoCobillingDAO.update(entity);

	}

	@Transactional
	public void create(SccGrupoCobilling entity) throws DAOException, ServiceException {
		
		this.sccGrupoCobillingDAO.save(entity);
		

	}

	public SccGrupoCobillingDAO getSccGrupoCobillingDAO() {
		return sccGrupoCobillingDAO;
	}

	public void setSccGrupoCobillingDAO(SccGrupoCobillingDAO sccGrupoCobillingDAO) {
		this.sccGrupoCobillingDAO = sccGrupoCobillingDAO;
	}

	@Transactional
	@Override
	public void updateGrupo(SccGrupoCobilling entity) throws DAOException {
		this.sccGrupoCobillingDAO.updateGrupo(entity);
		
	}

	@Override
	public void saveGrupo(SccGrupoCobilling entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void clearGrupo(){
		this.sccGrupoCobillingDAO.clear();
	}

	@Transactional
	@Override
	public void deleteGrupo(Long sqGrupo) throws DAOException {
		
		this.sccGrupoCobillingDAO.deleteGrupo(sqGrupo);
		
	}
	
	@Override
	public List<SccGrupoCobilling> pesquisarBySeqGrupo(Long seqGrupo) throws DAOException{
		
		return this.sccGrupoCobillingDAO.pesquisarBySeqGrupo(seqGrupo);
	}

	
}
