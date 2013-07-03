package com.claro.cobillingweb.persistence.dao.external.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.external.ControlConnectFileDAO;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.cobillingweb.persistence.filtro.SccFiltroControleArquivo;

public class ControlConnectFileDAOImpl extends HibernateBasicDAOImpl<ControlConnectFile> implements ControlConnectFileDAO {
	
	public List<ControlConnectFile> getAll() throws DAOException {
		throw new DAOException("getAll() não suportado pela entidade ControlConnectFile");
	}
	
	@SuppressWarnings("unchecked")
	public List<ControlConnectFile> pesquisaPorFiltros(SccFiltroControleArquivo filtro) throws DAOException {
		Criteria criteria = null;
		try {
			criteria = getSessionFactory().getCurrentSession().createCriteria(ControlConnectFile.class);
			if ((filtro.getDataInicio() != null) && (filtro.getDataFinal() != null)) {
				criteria.add(Restrictions.between("STOP_DATE", filtro.getDataInicio(), filtro.getDataFinal()));
			}
			if ((filtro.getNomeArquivo() != null) && (! filtro.getNomeArquivo().trim().equals(""))) {
				criteria.add(Restrictions.like("DEST_FILE", filtro.getNomeArquivo()));
			}
			if (filtro.getStatusArquivo().longValue() != (BasicDAO.GET_ALL.longValue())) {
				if (filtro.getStatusArquivo() == 0) {
					//criteria.add(Restrictions.eq("EXIT_CODE", 0L));
					criteria.add(Restrictions.eq("EXIT_CODE", new Integer(0))); /* CESAR 19/07/2012 */
				} else {
					//criteria.add(Restrictions.ne("EXIT_CODE", 0L));
					criteria.add(Restrictions.ne("EXIT_CODE", new Integer(0))); /* CESAR 19/07/2012 */
				}
			}
			if (!filtro.getTipoArquivo().equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.like("DEST_FILE", filtro.getTipoArquivo()));
			}
			return criteria.list();
			
		} catch (Exception e) {
			System.out.println("==="+filtro.getStatusArquivo());
			throw new DAOException(e.getMessage(), "ControlConnectFileDAO.pesquisaPorFiltros");
		}
		
	}
	
}
