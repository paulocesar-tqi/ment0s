package com.claro.sccweb.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodicidadeRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.PeriodicidadeService;
import com.claro.sccweb.service.ServiceException;

public class PeriodicidadeServiceImpl extends AbstractService implements PeriodicidadeService {

	private SccPeriodicidadeRepasseDAO periodicidadeRepasseDAO;

	public SccPeriodicidadeRepasseDAO getPeriodicidadeRepasseDAO() {
		return periodicidadeRepasseDAO;
	}

	public void setPeriodicidadeRepasseDAO(
			SccPeriodicidadeRepasseDAO periodicidadeRepasseDAO) {
		this.periodicidadeRepasseDAO = periodicidadeRepasseDAO;
	}

	 
	public Date calculaDataInicialPeriodo(Long cdPeriodicidadeRepasse,Long mesReferencia, Long ano) throws DAOException, ServiceException {
		try {
			SccPeriodicidadeRepasse periodicidadeRepasse = getPeriodicidadeRepasseDAO().getByPk(cdPeriodicidadeRepasse, SccPeriodicidadeRepasse.class);
			if (periodicidadeRepasse == null)
				throw new ServiceException("SccPeriodicidadeRepasse com ID "+cdPeriodicidadeRepasse+" não existe na base de dados.");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, periodicidadeRepasse.getDdInicioPeriodoRepasse().intValue());
			calendar.set(Calendar.YEAR,ano.intValue());
			calendar.set(Calendar.MONTH, mesReferencia.intValue()-1);
			return calendar.getTime();
		} catch (DAOException daoException)
			{
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	 
	public Date calculaDataFinalPeriodo(Long cdPeriodicidadeRepasse,Long mesReferencia, Long ano) throws DAOException, ServiceException {	
		try {
			SccPeriodicidadeRepasse periodicidadeRepasse = getPeriodicidadeRepasseDAO().getByPk(cdPeriodicidadeRepasse, SccPeriodicidadeRepasse.class);
			if (periodicidadeRepasse == null)
				throw new ServiceException("SccPeriodicidadeRepasse com ID "+cdPeriodicidadeRepasse+" não existe na base de dados.");
			Calendar calendar = Calendar.getInstance();			
			calendar.set(Calendar.YEAR,ano.intValue());
			calendar.set(Calendar.MONTH, mesReferencia.intValue()-1);
			if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < periodicidadeRepasse.getDdFimPeriodoRepasse())
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			else
				calendar.set(Calendar.DAY_OF_MONTH, periodicidadeRepasse.getDdFimPeriodoRepasse().intValue());
			return calendar.getTime();
		} catch (DAOException daoException)
			{
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	 
	public SccPeriodicidadeRepasse getByPk(Long cdPeriodicidadeRepasse) throws DAOException {
		return getPeriodicidadeRepasseDAO().getByPk(cdPeriodicidadeRepasse, SccPeriodicidadeRepasse.class);
	}

	 
	public List<SccPeriodicidadeRepasse> getAll() throws DAOException {
		return getPeriodicidadeRepasseDAO().getAll();
	}
	
	
	
	
}
