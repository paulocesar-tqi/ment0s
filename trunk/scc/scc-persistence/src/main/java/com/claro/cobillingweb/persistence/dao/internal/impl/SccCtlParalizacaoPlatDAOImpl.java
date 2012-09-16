package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCtlParalizacaoPlatDAO;
import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;

public class SccCtlParalizacaoPlatDAOImpl extends HibernateBasicDAOImpl<SccCtlParalizacaoPlat> implements SccCtlParalizacaoPlatDAO {
	
	public List<SccCtlParalizacaoPlat> getAll() throws DAOException {
		return null;
	}
	
	public List<SccCtlParalizacaoPlat> pesquisaParalizacoes(SccCtlParalizacaoPlat filtro, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCtlParalizacaoPlat.class);
			criteria.add(Restrictions.between("dtRegistroOcorrencia", dataInicial, dataFinal));
			
			if (filtro.getCdEotLd() != null && !filtro.getCdEotLd().equals("*")) { // ok
				criteria.add(Restrictions.eq("cdEotLd", filtro.getCdEotLd()));
			}
			
			if (filtro.getNuBoletimClaro() != null && !filtro.getNuBoletimClaro().equals("")) { // ok
				criteria.add(Restrictions.eq("nuBoletimClaro", filtro.getNuBoletimClaro()));
			}
			
			if (filtro.getCdAreaPlataforma() != null && !filtro.getCdAreaPlataforma().equals("")) { // ok
				criteria.add(Restrictions.eq("cdAreaPlataforma", filtro.getCdAreaPlataforma()));
			}
			
			if ((filtro.getCdTecnologiaPlataforma() != null) && (!filtro.getCdTecnologiaPlataforma().equals(GET_ALL))) { // ok
				criteria.add(Restrictions.eq("cdTecnologiaPlataforma", filtro.getCdTecnologiaPlataforma()));
			}
			
			if ((filtro.getCdTipoFalha() != null) && (!filtro.getCdTipoFalha().equals(GET_ALL_STRING))) { // ok
				criteria.add(Restrictions.eq("cdTipoFalha", filtro.getCdTipoFalha()));
			}
			
			if ((filtro.getFgSolicDesbloqueioLd() != null) && (!filtro.getFgSolicDesbloqueioLd().equals(GET_ALL_STRING))) { // ok
				criteria.add(Restrictions.eq("fgSolicDesbloqueioLd", filtro.getFgSolicDesbloqueioLd()));
			}
			
			if (filtro.getNuBoletimLd() != null && !filtro.getNuBoletimLd().equals("")) { // ok
				criteria.add(Restrictions.eq("nuBoletimLd", filtro.getNuBoletimLd()));
			}
			
			/*
			if (filtro.getNuBoletimLd() != null && !filtro.getNuBoletimLd().equals("")) { // ok
				criteria.add(Restrictions.eq("nuBoletimLd", filtro.getNuBoletimLd()));
			}
			
			if (filtro.getNuBoletimClaro() != null && !filtro.getNuBoletimClaro().equals("")) { // ok
				criteria.add(Restrictions.eq("nuBoletimClaro", filtro.getNuBoletimClaro()));
			}
			
			if (filtro.getCdAreaPlataforma() != null && !filtro.getCdAreaPlataforma().equals("")) { // ok
				criteria.add(Restrictions.eq("cdAreaPlataforma", filtro.getCdAreaPlataforma()));
			}
			
			if ((filtro.getCdTecnologiaPlataforma() != null) && (!filtro.getCdTecnologiaPlataforma().equals(GET_ALL))) { // ok
				criteria.add(Restrictions.eq("cdTecnologiaPlataforma", filtro.getCdTecnologiaPlataforma()));
			}
			
			if ((filtro.getCdTipoFalha() != null) && (!filtro.getCdTipoFalha().equals(GET_ALL))) { // ok
				criteria.add(Restrictions.eq("cdTipoFalha", filtro.getCdTipoFalha()));
			}
			
			if ((filtro.getFgSolicDesbloqueioLd() != null) && (!filtro.getFgSolicDesbloqueioLd().equals(GET_ALL_STRING))) { // ok
				criteria.add(Restrictions.eq("fgSolicDesbloqueioLd", filtro.getFgSolicDesbloqueioLd()));
			}
			*/
			
			return criteria.list();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }		
	}
	
}
