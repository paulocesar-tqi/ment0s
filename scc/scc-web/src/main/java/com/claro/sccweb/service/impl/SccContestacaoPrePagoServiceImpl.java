/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccContestacaoPrePagoDAO;
import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccContestacaoPrePagoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Service
public class SccContestacaoPrePagoServiceImpl extends AbstractService implements
		SccContestacaoPrePagoService {
	
	@Autowired
	private SccContestacaoPrePagoDAO sccContestacaoPrePagoDAO;

	@Override
	public Collection<SccContestacaoPrePago> pesquisarByFiltro(SccFiltroContestacaoPrePago filtro) throws ServiceException, DAOException {
		
		return this.sccContestacaoPrePagoDAO.pesquisarByFiltro(filtro);
	}

	@Override
	public SccContestacaoPrePago findBySqContestacaoPrePago(
			Long sqContestacaoPrePago) throws ServiceException, DAOException {
		
		return this.sccContestacaoPrePagoDAO.findBySqContestacaoPrePago(sqContestacaoPrePago);
	}
	
	@Transactional
	public void update(SccContestacaoPrePago entity)throws DAOException{
		this.sccContestacaoPrePagoDAO.update(entity);
	}

	@Override
	@Transactional
	public SccContestacaoPrePago salvarEntity(SccContestacaoPrePago entity)
			throws ServiceException, DAOException {
		
		SccContestacaoPrePago retorno = null;
		Long sqContestacaoPrePago = entity.getSqContestacaoPrePago();
		try {
			if(entity != null){
				saveOrUpdate(entity);
				this.sccContestacaoPrePagoDAO.update(entity);
				retorno = (SccContestacaoPrePago) this.sccContestacaoPrePagoDAO.findBySqContestacaoPrePago(sqContestacaoPrePago);
				
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		return retorno;
	}

	
	@Transactional
	public void delete(SccContestacaoPrePago entity) throws DAOException {
		
		this.sccContestacaoPrePagoDAO.delete(entity);
		
	}
	
	
	@Transactional
	public void saveOrUpdate(SccContestacaoPrePago entity) throws DAOException {
		
		if(entity.getSqContestacaoPrePago() != null){
			this.sccContestacaoPrePagoDAO.merge(entity);
		}else{
			this.sccContestacaoPrePagoDAO.create(entity);
		}
		
		
	}

	public void setSccContestacaoPrePagoDAO(SccContestacaoPrePagoDAO sccContestacaoPrePagoDAO) {
		this.sccContestacaoPrePagoDAO = sccContestacaoPrePagoDAO;
	}
	
	

}
