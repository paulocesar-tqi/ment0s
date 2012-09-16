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

public class ControlConnectFileDAOImpl extends HibernateBasicDAOImpl<ControlConnectFile> implements ControlConnectFileDAO {
	
	public List<ControlConnectFile> getAll() throws DAOException {
		throw new DAOException("getAll() não suportado pela entidade ControlConnectFile");
	}
	
	public List<ControlConnectFile> pesquisaPorFiltros(Long statusArquivo, String tipoArquivo, String nomeArquivo, Date dataInicio,Date dataFinal) throws DAOException {
		Criteria criteria = null;
		try {
			criteria = getSessionFactory().getCurrentSession().createCriteria(ControlConnectFile.class);
			if ((dataInicio != null) && (dataFinal != null)) {
				criteria.add(Restrictions.between("STOP_DATE", dataInicio,dataFinal));
			}
			if ((nomeArquivo != null) && (!nomeArquivo.trim().equals(""))) {
				criteria.add(Restrictions.like("DEST_FILE", nomeArquivo));
			}
			if (statusArquivo.longValue() != (BasicDAO.GET_ALL.longValue())) {
				if (statusArquivo == 0) {
					//criteria.add(Restrictions.eq("EXIT_CODE", 0L));
					criteria.add(Restrictions.eq("EXIT_CODE", new Integer(0))); /* CESAR 19/07/2012 */
				} else {
					//criteria.add(Restrictions.ne("EXIT_CODE", 0L));
					criteria.add(Restrictions.ne("EXIT_CODE", new Integer(0))); /* CESAR 19/07/2012 */
				}
			}
			if (!tipoArquivo.equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.like("DEST_FILE", tipoArquivo));
			}
			return criteria.list();
			
		} catch (Exception e) {
			System.out.println("==="+statusArquivo);
			throw new DAOException(e.getMessage(), "ControlConnectFileDAO.pesquisaPorFiltros");
		}
		
	}
	
}
